/*
 * Copyright 2023 SpCo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package top.spco.utils;

import cn.hutool.core.lang.Pair;
import snw.jkook.entity.Guild;
import snw.jkook.entity.User;
import snw.jkook.entity.channel.VoiceChannel;
import snw.jkook.message.Message;
import snw.jkook.message.PrivateMessage;
import snw.jkook.message.TextChannelMessage;
import top.spco.SpCoBot;
import top.spco.commands.CommandSource;

import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Created on 2023/7/19 0019 4:19
 * <p>
 *
 * @author SpCo
 * @version 1.0
 * @since 1.0
 */
public class Util {
    public static int parseInt(String str, Consumer<Exception> errorHandler) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            errorHandler.accept(e);
            throw new RuntimeException("Invalid integer: " + str);
        }
    }

    public static void isNewUser(User user, boolean register) {
        if (SpCoBot.getDatabase().selectInt("user", "smf_coin", "id", user.getId()) == null) {
            if (register)
                SpCoBot.getDatabase().insertData("insert into user(id,smf_coin) values (?,?)", user.getId(), 0);
        }
    }

    public static void isNewGuild(Guild guild, boolean register) {
        if (SpCoBot.getDatabase().selectInt("guild", "warn", "id", guild.getId()) == null) {
            if (register)
                SpCoBot.getDatabase().insertData("insert into guild(id,warn) values (?,?)", guild.getId(), 3);
        }
    }

    public static CommandSource getMessageSource(Message message) {
        if (message instanceof TextChannelMessage) {
            return CommandSource.TEXT_CHANNEL;
        } else if (message instanceof PrivateMessage) {
            return CommandSource.PERSONAL_MESSAGE;
        }
        return null;
    }

    /**
     * 获取用户发送的命令执行的目标用户。<p>
     * <u>注意：本方法通过判断传入参数的数量来判断命令格式，并不判断传入的参数是否合法。</u><p><p>
     * 用户有三种方法可以指定命令执行的对象：
     * <ol>
     *      <li>在命令帮助所定义的参数位置提交用户ID：/set user 12345678 海绵山币 100。</li>
     *      <li>在命令帮助所定义的参数位置@目标用户（不能@全体成员、@在线成员、@某角色）：/set user @某人 海绵山币 100。</li>
     *      <li>发送命令时<u>回复</u>命令执行对象的消息，此时不需要在命令中传入命令执行对象：（回复某人）/set user 海绵山币 100。</li>
     * </ol>
     *
     * @param targetIndex  命令参数中应该提交目标用户的索引。
     *                     <p>                             如set命令： set user [目标] <海绵山币|权限> <值>
     *                     <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     *                     ↑ 应该在此处（第二个参数）输入目标，则此参数应该传入'1'。<p>
     * @param targetLength 命令参数中应提交的参数数量。
     *                     <p>                              在上个例子中，set应该传入4个参数，则此参数应该传入'4'。<p>
     * @param args         命令的实际参数数组。<p>
     * @param message      用户发送命令的消息对象。<p>
     *                     <p>
     * @return 返回一个Pair对象：<p>   键（Boolean）为获取User的状态，成功为true，失败为false。
     * <p>                          值（Object）当键为true时，返回User对象。否则返回null或者对应失败的原因，如提供的ID不正确。
     */
    public static Pair<Boolean, Object> getUserByCommand(int targetIndex, int targetLength, Object[] args, Message message) {
        // 假设某命令要求格式：/command [用户] <键> <值>
        // 判断命令消息是否回复了一条消息
        if (message.getQuote() == null) {
            /*
             * 以下代码用于判断命令格式是否属于以下任意一种形式
             *      /command (met)有效用户ID(met) <键> <值>   -> A
             *      /command 有效用户ID <键> <值>             -> B
             *
             * 并获取命令提供的ID，然后尝试通过此ID来获取User对象
             */
            if (args.length != targetLength) {
                return new Pair<>(false, null);
            }
            String userId;
            // 通过正则表达式在应该传入的参数中寻找(met)xxx(met)
            Pattern pattern = Pattern.compile("\\(met\\)(.*?)\\(met\\)");
            Matcher matcher = pattern.matcher(args[targetIndex].toString());
            if (matcher.find()) {
                // 找到了(met)xxx(met)
                if (!(args[targetIndex].toString().startsWith("(met)") && args[targetIndex].toString().endsWith("(met)"))) {
                    return new Pair<>(false, String.format("请检查您第%d个参数前后是否有空格", targetIndex + 1));
                }
                // 该参数有且仅有(met)xxx(met)
                String id = matcher.group(1);
                // 检测获取的ID是否为纯数字
                if (id.matches("\\d+")) {
                    userId = id;
                } else {
                    return new Pair<>(false, "您不能@多位用户作为目标");
                }
            } else {
                // 未找到(met)xxx(met)，开始判断该参数是否为纯数字
                String id = args[targetIndex].toString();
                if (id.matches("\\d+")) {
                    userId = id;
                } else {
                    return new Pair<>(false, "您提交的目标ID有误");
                }
            }
            try {
                return new Pair<>(true, SpCoBot.getInstance().getCore().getHttpAPI().getUser(userId));
            } catch (Exception e) {
                return new Pair<>(false, String.format("您提供的用户ID **%s** 无效", args[targetIndex]));
            }
        } else {
            /*
             * 以下代码用于判断命令格式是否属于以下形式
             *      /command <键> <值>
             *
             * 然后直接返回回复消息的发送者对象
             */
            if (args.length != targetLength - 1) {
                return new Pair<>(false, null);
            }
            // 有回复的目标，且参数数量等于形式参数数量减一 直接返回回复的消息的发送者
            return new Pair<>(true, message.getQuote().getSender());
        }
    }
}