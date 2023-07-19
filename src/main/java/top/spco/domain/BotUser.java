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
package top.spco.domain;


import snw.jkook.entity.User;
import top.spco.SpCoBot;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <p>
 * Created on 2023/7/19 0019 2:20
 * <p>
 *
 * @author SpCo
 * @version 1.0
 * @since 1.0
 */

public class BotUser {
    private final String id;
    private int permission;
    private int smfCoin;
    private User user;

    public BotUser(String id, int permission, int smfCoin) {
        this.id = id;
        this.permission = permission;
        this.smfCoin = smfCoin;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public int getPermission() {
        smfCoin = SpCoBot.getDatabase().selectInt("user", "permission", "id", id);
        return permission;
    }

    public int getSmfCoin() {
        smfCoin = SpCoBot.getDatabase().selectInt("user", "smf_coin", "id", id);
        return smfCoin;
    }

    public String getId() {
        return id;
    }

    public void setSmfCoin(int smfCoin) {
        this.smfCoin = smfCoin;
        SpCoBot.getDatabase().update("update user set smf_coin=? where id=?", this.smfCoin, id);
    }

    public void setPermission(int permission) {
        this.permission = permission;
        SpCoBot.getDatabase().update("update user set permission=? where id=?", this.permission, id);
    }

    public int sign() {
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Shanghai"));
        String signDate = SpCoBot.getDatabase().select("user", "sign", "id", id);
        if (Objects.equals(signDate, today.toString())) {
            return -1;
        }
        SpCoBot.getDatabase().update("update user set sign=? where id=?", today, id);
        int randomNumber = ThreadLocalRandom.current().nextInt(10, 101);
        setSmfCoin(getSmfCoin() + randomNumber);
        return randomNumber;
    }
}