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

import org.jetbrains.annotations.NotNull;
import snw.jkook.entity.User;
import snw.jkook.message.PrivateMessage;

/**
 * <p>
 * Created on 2023/7/23 0023 20:21
 * <p>
 *
 * @author SpCo
 * @version 1.0
 * @since 1.0
 */
public interface SpCoPersonalMessageCommandExecuter {
    void onCommand(User sender, Object[] arguments, @NotNull PrivateMessage message);
}