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

import top.spco.utils.CardUtil;
import top.spco.utils.Util;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Created on 2023/7/19 0019 4:55
 * <p>
 *
 * @author SpCo
 * @version 1.0
 * @since 1.0
 */
public class GetMeCommand extends SpCoCommand {
    private static final Map<String, String> helpList = new HashMap<>();

    static {
        helpList.put("getme", "获取个人信息");
    }

    public GetMeCommand() {
        super("getme", (sender, arguments, message) -> {
            Util.isNewUser(sender, true);
            message.reply(CardUtil.userInfo(sender));
        }, helpList);
    }
}