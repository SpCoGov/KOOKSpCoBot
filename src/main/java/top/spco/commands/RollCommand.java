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

import snw.jkook.message.component.card.Theme;
import top.spco.utils.CardUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * <p>
 * Created on 2023/7/18 0018 18:18
 * <p>
 *
 * @author SpCo
 * @version 1.0
 * @since 1.0
 */
public class RollCommand extends SpCoCommand {
    private static final Map<String, String> helpList = new HashMap<>();

    static {
        helpList.put("roll <最小值> <最大值>", "于范围中取随机数");
    }

    public RollCommand() {
        super("roll", (sender, arguments, message) -> {
            if (arguments.length != 2) {
                message.reply(CardUtil.headerAndSection(Theme.WARNING, "告知: 提交参数错误", "正确格式: `/roll <最小值> <最大值>`"));
                return;
            }
            try {
                int min = Integer.parseInt((String) arguments[0]);
                int max = Integer.parseInt((String) arguments[1]);
                if (min >= max) {
                    message.reply(CardUtil.headerAndSection(Theme.WARNING, "告知: 错误发生", "详细信息: 范围的最小值必须小于最大值"));
                    return;
                }
                message.reply(CardUtil.headerAndSection(String.format("告知：于%d-%d中取随机数", min, max), "返回: " + (new Random().nextInt((max - min) + 1) + min)));
            } catch (Exception e) {
                message.reply(CardUtil.headerAndSection(Theme.WARNING, "告知: 错误发生", "详细信息: " + e.getMessage().replace("For input string", "错误的参数")));
            }
        }, helpList);
    }
}