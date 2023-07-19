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

import top.spco.domain.BotUser;
import top.spco.service.UserService;
import top.spco.service.impl.UserServiceImpl;
import top.spco.utils.CardUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Created on 2023/7/19 0019 16:40
 * <p>
 *
 * @author SpCo
 * @version 1.0
 * @since 1.0
 */
public class SignCommand extends SpCoCommand {
    private static final Map<String, String> helpList = new HashMap<>();
    private static final UserService userService = new UserServiceImpl();

    static {
        helpList.put("sign", "签到");
    }

    public SignCommand() {
        super("sign", (sender, arguments, message) -> {
            BotUser botUser = userService.getBotUser(sender);
            int i = botUser.sign();
            if (i == -1) {
                message.reply(CardUtil.headerAndSection("签到失败", "您今天已经签到过了"));
                return;
            }
            message.reply(CardUtil.headerAndSection("签到成功", String.format("您今天签到获得了%d海绵山币, 您现在拥有%d海绵山币", i, botUser.getSmfCoin())));
        }, helpList);
    }
}