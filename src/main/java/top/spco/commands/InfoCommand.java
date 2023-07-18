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

import snw.jkook.command.JKookCommand;
import top.spco.utils.CardUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Created on 2023/7/18 0018 17:19
 * <p>
 *
 * @author SpCo
 * @version 1.0
 * @since 1.0
 */
public class InfoCommand extends SpCoCommand {
    private static final Map<String, String> helpList = new HashMap<>();
    static {
        helpList.put("info", "获取机器人运行信息");
        helpList.put("i", " -> /info");
    }
    public InfoCommand() {
        super(new JKookCommand("info", '/').executesUser(
                (sender, arguments, message) -> {
                    if (message == null) return;
                    message.reply(CardUtil.header("告知: 机器人正常运行中"));
                }
        ).addAlias("i") ,helpList);
    }
}

