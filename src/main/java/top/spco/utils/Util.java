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

import snw.jkook.entity.User;
import top.spco.SpCoBot;

import java.util.function.Consumer;

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
}