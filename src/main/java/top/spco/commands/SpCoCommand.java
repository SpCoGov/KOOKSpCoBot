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

import snw.jkook.command.JKookCommand;
import top.spco.service.UserService;
import top.spco.service.impl.UserServiceImpl;
import top.spco.utils.CardUtil;
import top.spco.utils.Util;

import java.util.Map;

/**
 * <p>
 * Created on 2023/7/18 0018 20:46
 * <p>
 *
 * @author SpCo
 * @version 1.0
 * @since 1.0
 */
public abstract class SpCoCommand {
    private final CommandSource needSource;
    private final String rootName;
    private final JKookCommand jKookCommand;
    private final Map<String, String> helpList;
    private final CommandPermission needPermission;
    private static final UserService userService = new UserServiceImpl();

    public SpCoCommand(String rootName, SpCoCommandExecuter executer, Map<String, String> helpList, CommandSource needSource) {
        this.rootName = rootName;
        this.needSource = needSource;
        this.needPermission = CommandPermission.NORMAL_USER;
        this.helpList = helpList;
        this.jKookCommand = new JKookCommand(this.rootName, '/').executesUser((sender, arguments, message) -> {
            if (message == null) return;
            if (Util.getMessageSource(message) != this.needSource && this.needSource != CommandSource.BOTH) {
                message.reply(CardUtil.wrongCommandSource(this.needSource));
                return;
            }
            if (userService.getBotUser(sender).getPermission() < needPermission.getPermission()) {
                message.reply(CardUtil.insufficientPermission(rootName, needPermission, userService.getBotUser(sender)));
                return;
            }
            executer.onCommand(sender, arguments, message);
        });
    }

    public SpCoCommand(String rootName, SpCoCommandExecuter executer, Map<String, String> helpList, CommandSource needSource, CommandPermission needPermission, String... alias) {
        this.rootName = rootName;
        this.needSource = needSource;
        this.needPermission = needPermission;
        this.helpList = helpList;
        this.jKookCommand = new JKookCommand(this.rootName, '/').executesUser((sender, arguments, message) -> {
            if (message == null) return;
            if (Util.getMessageSource(message) != this.needSource && this.needSource != CommandSource.BOTH) {
                message.reply(CardUtil.wrongCommandSource(this.needSource));
                return;
            }
            if (userService.getBotUser(sender).getPermission() < needPermission.getPermission()) {
                message.reply(CardUtil.insufficientPermission(rootName, needPermission, userService.getBotUser(sender)));
                return;
            }
            executer.onCommand(sender, arguments, message);
        });
        for (String alia : alias) {
            this.jKookCommand.addAlias(alia);
        }
    }

    public SpCoCommand(String rootName, SpCoCommandExecuter executer, Map<String, String> helpList, CommandSource needSource, CommandPermission needPermission) {
        this.rootName = rootName;
        this.needSource = needSource;
        this.needPermission = needPermission;
        this.helpList = helpList;
        this.jKookCommand = new JKookCommand(this.rootName, '/').executesUser((sender, arguments, message) -> {
            if (message == null) return;
            if (Util.getMessageSource(message) != this.needSource && this.needSource != CommandSource.BOTH) {
                message.reply(CardUtil.wrongCommandSource(this.needSource));
                return;
            }
            if (userService.getBotUser(sender).getPermission() < needPermission.getPermission()) {
                message.reply(CardUtil.insufficientPermission(rootName, needPermission, userService.getBotUser(sender)));
                return;
            }
            executer.onCommand(sender, arguments, message);
        });
    }

    public SpCoCommand(String rootName, SpCoCommandExecuter executer, Map<String, String> helpList, CommandPermission needPermission) {
        this(rootName, executer, helpList, CommandSource.BOTH, needPermission);
    }

    public SpCoCommand(String rootName, SpCoCommandExecuter executer, Map<String, String> helpList, CommandPermission needPermission, String... alias) {
        this(rootName, executer, helpList, CommandSource.BOTH, needPermission, alias);
    }

    public SpCoCommand(String rootName, SpCoCommandExecuter executer, Map<String, String> helpList) {
        this(rootName, executer, helpList, CommandSource.BOTH);
    }

    public Map<String, String> getHelpList() {
        return helpList;
    }

    public JKookCommand getjKookCommand() {
        return jKookCommand;
    }
}