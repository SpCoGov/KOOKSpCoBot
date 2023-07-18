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
package top.spco.events;

import org.apache.logging.log4j.Logger;
import snw.jkook.entity.User;
import snw.jkook.event.EventHandler;
import snw.jkook.event.Listener;
import snw.jkook.event.pm.PrivateMessageDeleteEvent;
import snw.jkook.event.pm.PrivateMessageReceivedEvent;
import snw.jkook.event.pm.PrivateMessageUpdateEvent;
import top.spco.utils.LogUtil;

/**
 * <p>
 * Created on 2023/7/18 0018 17:49
 * <p>
 *
 * @author SpCo
 * @version 1.0
 * @since 1.0
 */
public class PrivateMessageEvents implements Listener {
    private static final Logger LOGGER = LogUtil.getLogger();
    @EventHandler
    public void messageReceive(PrivateMessageReceivedEvent event) {
        User user = event.getUser();
        LOGGER.info("收到了{}({})发来的私聊消息: {}", user.getName(), user.getId(), event.getMessage().getComponent());
    }

    @EventHandler
    public void messageDelete(PrivateMessageDeleteEvent event) {
        LOGGER.info("私聊消息{}被删除", event.getMessageId());
    }

    @EventHandler
    public void messageUpdate(PrivateMessageUpdateEvent event) {
        String content = event.getContent();
        LOGGER.info("私聊消息{}被修改为: " + content, event.getMessageId());
    }


}