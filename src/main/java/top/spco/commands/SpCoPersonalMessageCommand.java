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

import snw.jkook.message.PrivateMessage;
import top.spco.utils.CardUtil;

import java.util.Map;

/**
 * <p>
 * Created on 2023/7/23 0023 20:23
 * <p>
 *
 * @author SpCo
 * @version 1.0
 * @since 1.0
 */
public abstract class SpCoPersonalMessageCommand extends SpCoCommand {
    public SpCoPersonalMessageCommand(String rootName, SpCoPersonalMessageCommandExecuter executer, Map<String, String> helpList) {
        super(rootName, (sender, arguments, message) -> executer.onCommand(sender, arguments, (PrivateMessage) message), helpList, CommandSource.PERSONAL_MESSAGE);

    }

    public SpCoPersonalMessageCommand(String rootName, SpCoPersonalMessageCommandExecuter executer, Map<String, String> helpList, CommandPermission needPermission, String... alias) {
        super(rootName, (sender, arguments, message) -> executer.onCommand(sender, arguments, (PrivateMessage) message), helpList, CommandSource.PERSONAL_MESSAGE, needPermission, alias);
    }

    public SpCoPersonalMessageCommand(String rootName, SpCoPersonalMessageCommandExecuter executer, Map<String, String> helpList, CommandPermission needPermission) {
        super(rootName, (sender, arguments, message) -> executer.onCommand(sender, arguments, (PrivateMessage) message), helpList, CommandSource.PERSONAL_MESSAGE, needPermission);
    }
}