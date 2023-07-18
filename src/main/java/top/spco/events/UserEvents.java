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
package top.spco.events;

import org.apache.logging.log4j.Logger;
import snw.jkook.entity.Guild;
import snw.jkook.entity.Reaction;
import snw.jkook.entity.User;
import snw.jkook.entity.channel.Channel;
import snw.jkook.entity.channel.VoiceChannel;
import snw.jkook.event.EventHandler;
import snw.jkook.event.Listener;
import snw.jkook.event.user.*;
import top.spco.utils.LogUtil;

/**
 * <p>
 * Created on 2023/7/18 0018 17:42
 * <p>
 *
 * @author SpCo
 * @version 1.0
 * @since 1.0
 */
public class UserEvents implements Listener {
    private static final Logger LOGGER = LogUtil.getLogger();

    @EventHandler
    public void onJoinGuild(UserJoinGuildEvent event) {
        User user = event.getUser();
        Guild guild = event.getGuild();
        LOGGER.info("{}({})加入了服务器{}({})", user.getName(), user.getId(), guild.getName(), guild.getId());
    }

    @EventHandler
    public void onLeaveGuild(UserLeaveGuildEvent event) {
        User user = event.getUser();
        Guild guild = event.getGuild();
        LOGGER.info("{}({})离开了服务器{}({})", user.getName(), user.getId(), guild.getName(), guild.getId());
    }

    @EventHandler
    public void onJoinVoiceChannel(UserJoinVoiceChannelEvent event) {
        User user = event.getUser();
        VoiceChannel channel = event.getChannel();
        Guild guild = event.getChannel().getGuild();
        LOGGER.info("{}({})进入了{}({})的语音频道{}({})", user.getName(), user.getId(), guild.getName(), guild.getId(), channel.getName(), channel.getId());
    }

    @EventHandler
    public void onLeaveVoiceChannel(UserLeaveVoiceChannelEvent event) {
        User user = event.getUser();
        VoiceChannel channel = event.getChannel();
        Guild guild = event.getChannel().getGuild();
        LOGGER.info("{}({})离开了{}({})的语音频道{}({})", user.getName(), user.getId(), guild.getName(), guild.getId(), channel.getName(), channel.getId());
    }

    @EventHandler
    public void onAddReaction(UserAddReactionEvent event) {
        User user = event.getUser();
        Reaction reaction = event.getReaction();
        LOGGER.info("{}({})回应[{}({})]了消息{}", user.getName(), user.getId(), reaction.getEmoji().getName(), reaction.getEmoji().getId(), reaction.getMessageId());
    }

    @EventHandler
    public void onRemoveReaction(UserRemoveReactionEvent event) {
        User user = event.getUser();
        Reaction reaction = event.getReaction();
        LOGGER.info("{}({})在消息{}上移除了回应[{}({})]", user.getName(), user.getId(), reaction.getMessageId(), reaction.getEmoji().getName(), reaction.getEmoji().getId());
    }

    @EventHandler
    public void onClickButton(UserClickButtonEvent event) {
        String value = event.getValue();
        User user = event.getUser();
        Channel channel = event.getChannel();
        LOGGER.info("{}({})点击了{}({})中消息{}的值为{}的按钮", user.getName(), user.getId(), channel.getName(), channel.getId(), event.getMessageId(), value);
    }

    @EventHandler
    public void onInfoUpdate(UserInfoUpdateEvent event) {

    }

    @EventHandler
    public void onOnline(UserOnlineEvent event) {

    }

    @EventHandler
    public void onOffline(UserOfflineEvent event) {

    }


}