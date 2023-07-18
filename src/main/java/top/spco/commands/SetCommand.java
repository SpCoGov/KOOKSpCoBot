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
package top.spco.commands;

import org.apache.logging.log4j.Logger;
import snw.jkook.entity.User;
import snw.jkook.message.component.card.Theme;
import top.spco.SpCoBot;
import top.spco.domain.BotUser;
import top.spco.service.UserService;
import top.spco.service.impl.UserServiceImpl;
import top.spco.utils.CardUtil;
import top.spco.utils.LogUtil;
import top.spco.utils.Util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Created on 2023/7/19 0019 3:01
 * <p>
 *
 * @author SpCo
 * @version 1.0
 * @since 1.0
 */
public class SetCommand extends SpCoCommand {
    private static final Logger LOGGER = LogUtil.getLogger();
    private static final UserService userService = new UserServiceImpl();
    private static final Map<String, String> helpList = new HashMap<>();

    static {
        helpList.put("set user [目标] <海绵山币|权限> <值>", "修改用户信息");
    }

    public SetCommand() {
        super("set", (sender, arguments, message) -> {
            Util.isNewUser(sender, true);
            if (arguments.length < 1) {
                message.reply(CardUtil.headerAndSections(Theme.WARNING, "告知: 提交参数错误", message.getComponent() + "<--[HERE]"));
                return;
            }
            switch (arguments[0].toString()) {
                case "user" -> {
                    if (arguments.length != 3 && arguments.length != 4) {
                        message.reply(CardUtil.headerAndSections(Theme.WARNING, "告知: 提交参数错误", message.getComponent() + "<--[HERE]"));
                        return;
                    }
                    BotUser target;
                    int value;
                    if (arguments.length == 3) {
                        if (message.getQuote() == null) {
                            message.reply(CardUtil.headerAndSections(Theme.WARNING, "告知: 提交参数错误", message.getComponent() + "<--[HERE]"));
                            return;
                        }
                        value = Util.parseInt(arguments[2].toString(), e -> message.reply(CardUtil.headerAndSection(Theme.WARNING, "告知: 错误发生", "详细信息: " + e.getMessage().replace("For input string", "错误的参数"))));
                        target = userService.getBotUser(message.getQuote().getSender());
                    } else {
                        Pattern pattern = Pattern.compile("\\(met\\)(.*?)\\(met\\)");
                        Matcher matcher = pattern.matcher(arguments[1].toString());
                        User user;
                        if (matcher.find()) {
                            String id = matcher.group(1);
                            if (id.matches("\\d+")) {
                                user = SpCoBot.getInstance().getCore().getHttpAPI().getUser(id);
                            } else {
                                message.reply(CardUtil.headerAndSections(Theme.WARNING, "告知: 提交参数错误", "您不能@多位用户作为目标"));
                                return;
                            }
                        } else {
                            String id = arguments[1].toString();
                            if (id.matches("\\d+")) {
                                try {
                                    user = SpCoBot.getInstance().getCore().getHttpAPI().getUser(id);
                                } catch (Exception e) {
                                    message.reply(CardUtil.headerAndSections(Theme.WARNING, "告知: 提交参数错误", String.format("您提供的用户ID **%s** 无效", arguments[1])));
                                    return;
                                }
                            } else {
                                message.reply(CardUtil.headerAndSections(Theme.WARNING, "告知: 提交参数错误", "您提交的目标ID有误"));
                                return;
                            }
                        }
                        value = Util.parseInt(arguments[3].toString(), e -> message.reply(CardUtil.headerAndSection(Theme.WARNING, "告知: 错误发生", "详细信息: " + e.getMessage().replace("For input string", "错误的参数"))));
                        target = userService.getBotUser(user);
                    }
                    switch (arguments[1].toString()) {
                        case "海绵山币" -> {
                            target.setSmfCoin(value);
                            message.reply(CardUtil.headerAndSections("告知: 设置成功", String.format("已将**%s**的海绵山币设置为%d", target.getUser().getName(), value)));
                        }
                        case "权限" -> {
                            target.setPermission(value);
                            message.reply(CardUtil.headerAndSections("告知: 设置成功", String.format("已将**%s**的权限设置为%d", target.getUser().getName(), value)));
                        }
                        default -> {
                            if (arguments.length == 3) {
                                message.reply(CardUtil.headerAndSections(Theme.WARNING, "告知: 提交参数错误",
                                        String.format("/set %s %s<--[HERE] %s", arguments[0], arguments[1], arguments[2])));
                                return;
                            }
                            switch (arguments[2].toString()) {
                                case "海绵山币" -> {
                                    target.setSmfCoin(value);
                                    message.reply(CardUtil.headerAndSections("告知: 设置成功", String.format("已将**%s**的海绵山币设置为%d", target.getUser().getName(), value)));
                                }
                                case "权限" -> {
                                    target.setPermission(value);
                                    message.reply(CardUtil.headerAndSections("告知: 设置成功", String.format("已将**%s**的权限设置为%d", target.getUser().getName(), value)));
                                }
                                default -> message.reply(CardUtil.headerAndSections(Theme.WARNING, "告知: 提交参数错误",
                                        String.format("/set %s %s %s<--[HERE] %s", arguments[0], arguments[1], arguments[2], arguments[3])));
                            }
                        }
                    }
                }
                default ->
                        message.reply(CardUtil.headerAndSections(Theme.WARNING, "告知: 提交参数错误", message.getComponent() + " <--[HERE]"));
            }
        }, helpList, CommandPermission.DEVELOPER);
    }
}