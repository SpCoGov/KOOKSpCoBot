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

import snw.jkook.entity.Guild;
import top.spco.SpCoBot;
import top.spco.domain.BotGuild;
import top.spco.service.GuildService;
import top.spco.utils.Util;

/**
 * <p>
 * Created on 2023/7/19 0019 18:46
 * <p>
 *
 * @author SpCo
 * @version 1.0
 * @since 1.0
 */
public class GuildServiceImpl implements GuildService {
    @Override
    public BotGuild getBotGuild(Guild guild) {
        Util.isNewGuild(guild, true);
        String id = guild.getId();
        BotGuild botGuild = new BotGuild(id,
                SpCoBot.getDatabase().selectInt("guild", "warn", "id", id));
        botGuild.setGuild(guild);
        return botGuild;
    }
}