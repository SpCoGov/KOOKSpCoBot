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
package top.spco;

import top.spco.commands.*;
import top.spco.events.ChannelEvents;
import top.spco.events.PrivateMessageEvents;
import top.spco.events.UserEvents;

/**
 * <p>
 * Created on 2023/7/18 0018 17:27
 * <p>
 *
 * @author SpCo
 * @version 1.0
 * @since 1.0
 */
public class Register {
    private static final SpCoBot spcobot = SpCoBot.getInstance();
    public static void eventRegister() {
        spcobot.getCore().getEventManager().registerHandlers(spcobot, new ChannelEvents());
        spcobot.getCore().getEventManager().registerHandlers(spcobot, new UserEvents());
        spcobot.getCore().getEventManager().registerHandlers(spcobot, new PrivateMessageEvents());

    }

    public static void commandRegister() {
        Commands.register(new GetMeCommand());
        Commands.register(new HelpCommand());
        Commands.register(new InfoCommand());
        Commands.register(new RollCommand());
        Commands.register(new SetCommand());
        Commands.register(new SignCommand());

    }
}