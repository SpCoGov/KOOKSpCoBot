/*
 * Copyright 2023 SpCo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package top.spco.commands;

import snw.jkook.message.component.card.Theme;
import top.spco.utils.CardUtil;

import java.util.*;

/**
 * <p>
 * Created on 2023/7/18 0018 20:40
 * <p>
 *
 * @author SpCo
 * @version 1.0
 * @since 1.0
 */
public class HelpCommand extends SpCoCommand {
    private static final Map<String, String> helpList = new HashMap<>();

    static {
        helpList.put("help", "获取命令帮助列表");
        helpList.put("help <命令>", "获取命令对应的帮助列表");
        helpList.put("?", " -> /help");
    }

    public HelpCommand() {
        super("help", (sender, arguments, message) -> {
            if (arguments.length == 0) {
                List<String> sections = new ArrayList<>();
                for (Map.Entry<String, SpCoCommand> entry : Commands.getCommandMap().entrySet()) {
                    for (Map.Entry<String, String> entry1 : entry.getValue().getHelpList().entrySet()) {
                        sections.add(String.format("`%s`", entry1.getKey()) + " " + entry1.getValue());
                    }
                }
                String[] array = new String[sections.size()];
                array = sections.toArray(array);
                message.reply(CardUtil.headerAndSections("SpCoBot 命令帮助", array));
            } else if (arguments.length == 1) {
                String command = arguments[0].toString().toLowerCase(Locale.ENGLISH);
                if (!Commands.getCommandMap().containsKey(command)) {
                    message.reply(CardUtil.headerAndSection(Theme.WARNING, command + " 的命令帮助", String.format("没有找到`%s`命令", command)));
                    return;
                }
                SpCoCommand s = Commands.getCommandMap().get(command);
                List<String> sections = new ArrayList<>();
                for (Map.Entry<String, String> entry : s.getHelpList().entrySet()) {
                    sections.add(String.format("`%s`", entry.getKey()) + " " + entry.getValue());
                }
                String[] array = new String[sections.size()];
                array = sections.toArray(array);
                message.reply(CardUtil.headerAndSections(command + " 的命令帮助", array));
            } else {
                message.reply(CardUtil.headerAndSection(Theme.WARNING, "告知: 提交参数错误", "正确格式: `/help <命令>`"));
            }
        }, helpList, CommandPermission.NORMAL_USER, "?");
    }
}