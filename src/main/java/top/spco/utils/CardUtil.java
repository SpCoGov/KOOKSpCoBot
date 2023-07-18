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
package top.spco.utils;

import snw.jkook.message.component.card.CardBuilder;
import snw.jkook.message.component.card.MultipleCardComponent;
import snw.jkook.message.component.card.Size;
import snw.jkook.message.component.card.Theme;
import snw.jkook.message.component.card.element.PlainTextElement;
import snw.jkook.message.component.card.module.DividerModule;
import snw.jkook.message.component.card.module.HeaderModule;
import snw.jkook.message.component.card.module.SectionModule;

/**
 * <p>
 * Created on 2023/7/18 0018 19:36
 * <p>
 *
 * @author SpCo
 * @version 1.0
 * @since 1.0
 */
public class CardUtil {
    public static MultipleCardComponent headerAndSection(String header, String section) {
        return new CardBuilder()
                .setSize(Size.LG)
                .setColor("#4bc1ff")
                .setTheme(Theme.PRIMARY)
                .addModule(new HeaderModule(new PlainTextElement(header, false)))
                .addModule(DividerModule.INSTANCE)
                .addModule(new SectionModule(section, true))
                .build();
    }

    public static MultipleCardComponent headerAndSection(Theme theme, String header, String section) {
        return new CardBuilder()
                .setSize(Size.LG)
                .setTheme(theme)
                .addModule(new HeaderModule(new PlainTextElement(header, false)))
                .addModule(DividerModule.INSTANCE)
                .addModule(new SectionModule(section, true))
                .build();
    }

    public static MultipleCardComponent headerAndSections(String header, String... sections) {
        CardBuilder cardBuilder = new CardBuilder()
                .setSize(Size.LG)
                .setColor("#4bc1ff")
                .setTheme(Theme.PRIMARY)
                .addModule(new HeaderModule(new PlainTextElement(header, false)))
                .addModule(DividerModule.INSTANCE);
        for (String section : sections) {
            cardBuilder.addModule(new SectionModule(section, true));
        }
        return cardBuilder.build();
    }

    public static MultipleCardComponent header(String header) {
        return new CardBuilder()
                .setColor("#4bc1ff")
                .setSize(Size.LG)
                .setTheme(Theme.PRIMARY)
                .addModule(new HeaderModule(new PlainTextElement(header, false)))
                .build();
    }
}