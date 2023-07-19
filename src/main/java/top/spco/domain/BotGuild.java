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

import snw.jkook.entity.Guild;
import top.spco.SpCoBot;

/**
 * <p>
 * Created on 2023/7/19 0019 18:11
 * <p>
 *
 * @author SpCo
 * @version 1.0
 * @since 1.0
 */
public class BotGuild {
    private final String id;
    private int warn;
    private Guild guild;

    public BotGuild(String id, int warn) {
        this.id = id;
        this.warn = warn;
    }

    public int getWarn() {
        this.warn = SpCoBot.getDatabase().selectInt("guild", "warn", "id", id);
        return warn;
    }

    public void setWarn(int warn) {
        this.warn = warn;
        SpCoBot.getDatabase().update("update guild set warn=? where id=?", this.warn, id);
    }

    public void setGuild(Guild guild) {
        this.guild = guild;
    }

    public Guild getGuild() {
        return guild;
    }
}