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
package top.spco.service.impl;

import snw.jkook.entity.User;
import top.spco.SpCoBot;
import top.spco.domain.BotUser;
import top.spco.service.UserService;
import top.spco.utils.Util;

/**
 * <p>
 * Created on 2023/7/19 0019 3:12
 * <p>
 *
 * @author SpCo
 * @version 1.0
 * @since 1.0
 */
public class UserServiceImpl implements UserService {
    @Override
    public BotUser getBotUser(User user) {
        Util.isNewUser(user, true);
        String id = user.getId();
        BotUser botUser = new BotUser(id,
                        SpCoBot.getDatabase().selectInt("user", "permission", "id", id),
                        SpCoBot.getDatabase().selectInt("user", "smf_coin", "id", id));
        botUser.setUser(user);
        return botUser;
    }
}