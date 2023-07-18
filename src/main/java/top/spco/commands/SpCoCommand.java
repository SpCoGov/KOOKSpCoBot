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
    private final JKookCommand jKookCommand;
    private final Map<String, String> helpList;
    private final CommandPermission needPermission;

    public SpCoCommand(JKookCommand jKookCommand, Map<String, String> helpList, CommandPermission needPermission) {
        this.jKookCommand = jKookCommand;
        this.helpList = helpList;
        this.needPermission = needPermission;
    }

    public SpCoCommand(JKookCommand jKookCommand, Map<String, String> helpList) {
        this.jKookCommand = jKookCommand;
        this.helpList = helpList;
        this.needPermission = CommandPermission.NORMAL_USER;
    }

    public CommandPermission getNeedPermission() {
        return needPermission;
    }

    public Map<String, String> getHelpList() {
        return helpList;
    }

    public JKookCommand getjKookCommand() {
        return jKookCommand;
    }

}