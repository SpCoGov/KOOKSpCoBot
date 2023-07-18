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
import snw.jkook.entity.User;
import snw.jkook.entity.channel.Channel;
import snw.jkook.event.EventHandler;
import snw.jkook.event.Listener;
import snw.jkook.event.channel.*;
import snw.jkook.message.Message;
import top.spco.SpCoBot;
import top.spco.utils.LogUtil;
import top.spco.utils.Util;

/**
 * <p>
 * Created on 2023/7/18 0018 17:04
 * <p>
 *
 * @author SpCo
 * @version 1.0
 * @since 1.0
 */
public class ChannelEvents implements Listener {
    private static final Logger LOGGER = LogUtil.getLogger();
    @EventHandler
    public void onCrate(ChannelCreateEvent event) {
        Channel channel = event.getChannel();
        Guild guild = channel.getGuild();
        LOGGER.info("{}({})中被创建了新频道{}({})", guild.getName(), guild.getId(), channel.getName(), channel.getId());
    }

    @EventHandler
    public void onDelete(ChannelDeleteEvent event) {
        Channel channel = SpCoBot.getInstance().getCore().getHttpAPI().getChannel(event.getChannelId());
        Guild guild = channel.getGuild();
        LOGGER.info("{}({})的{}({})被删除", guild.getName(), guild.getId(), channel.getName(), channel.getId());
    }

    @EventHandler
    public void onInfoUpdate(ChannelDeleteEvent event) {
        Channel channel = SpCoBot.getInstance().getCore().getHttpAPI().getChannel(event.getChannelId());
        Guild guild = channel.getGuild();
        LOGGER.info("{}({})的{}({})的频道消息被更新", guild.getName(), guild.getId(), channel.getName(), channel.getId());
    }

    @EventHandler
    public void onMessageDelete(ChannelMessageDeleteEvent event) {
        Channel channel = event.getChannel();
        Guild guild = channel.getGuild();
        LOGGER.info("{}({})的{}({})中的消息({})被删除", guild.getName(), guild.getId(), channel.getName(), channel.getId(), event.getMessageId());
    }

    @EventHandler
    public void onMessageUnpin(ChannelMessageUnpinEvent event) {
        Channel channel = event.getChannel();
        Guild guild = channel.getGuild();
        LOGGER.info("{}({})的{}({})中的消息({})被{}取消置顶", guild.getName(), guild.getId(), channel.getName(), channel.getId(), event.getMessageId(), event.getOperator().getName());
    }

    @EventHandler
    public void onMessage(ChannelMessageEvent event) {
        if (SpCoBot.getInstance().getCore().getUser() == event.getMessage().getSender()) return;
        Channel channel = event.getChannel();
        Guild guild = channel.getGuild();
        Message message = event.getMessage();
        String content = message.getComponent().clone().toString();
        User sender = message.getSender();
        LOGGER.info("从{}({})的{}({}), 收到{}({})发送的消息: {}", guild.getName(), guild.getId(), channel.getName(), channel.getId(), sender.getName(), sender.getId(), content);
        Util.isNewUser(sender, true);
    }

    @EventHandler
    public void onMessagePin(ChannelMessagePinEvent event) {
        Channel channel = event.getChannel();
        Guild guild = channel.getGuild();
        LOGGER.info("{}({})的{}({})中的消息({})被{}置顶", guild.getName(), guild.getId(), channel.getName(), channel.getId(), event.getMessageId(), event.getOperator().getName());

    }

    @EventHandler
    public void onMessageUpdate(ChannelMessageUpdateEvent event) {
        Channel channel = event.getChannel();
        Guild guild = channel.getGuild();
        String message = event.getContent();
        String messageId = event.getMessageId();
        LOGGER.info("用户将消息({})修改为: {}", messageId, message);
    }
}