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
package top.spco.utils;

import snw.jkook.entity.User;
import snw.jkook.message.component.card.CardBuilder;
import snw.jkook.message.component.card.MultipleCardComponent;
import snw.jkook.message.component.card.Size;
import snw.jkook.message.component.card.Theme;
import snw.jkook.message.component.card.element.BaseElement;
import snw.jkook.message.component.card.element.MarkdownElement;
import snw.jkook.message.component.card.element.PlainTextElement;
import snw.jkook.message.component.card.module.DividerModule;
import snw.jkook.message.component.card.module.HeaderModule;
import snw.jkook.message.component.card.module.SectionModule;
import snw.jkook.message.component.card.structure.Paragraph;
import top.spco.commands.CommandPermission;
import top.spco.commands.CommandSource;
import top.spco.domain.BotUser;
import top.spco.service.UserService;
import top.spco.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

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
    private static final UserService userService = new UserServiceImpl();

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

    public static MultipleCardComponent headerAndSections(Theme theme, String header, String... sections) {
        CardBuilder cardBuilder = new CardBuilder()
                .setSize(Size.LG)
                .setColor("#4bc1ff")
                .setTheme(theme)
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

    public static MultipleCardComponent insufficientPermission(String rootName, CommandPermission commandPermission, BotUser sender) {
        return new CardBuilder()
                .setSize(Size.LG)
                .setTheme(Theme.DANGER)
                .addModule(new HeaderModule(new PlainTextElement("权限不足", false)))
                .addModule(DividerModule.INSTANCE)
                .addModule(new SectionModule("抱歉, 您没有足够的权限来执行此命令", true))
                .addModule(new SectionModule(String.format("`%s`命令至少需要权限等级**%d**, 而您的权限等级为**%d**", rootName, commandPermission.getPermission(), sender.getPermission())))
                .build();
    }

    public static MultipleCardComponent userInfo(User user) {
        List<BaseElement> collection = new ArrayList<>();
        BotUser botUser = userService.getBotUser(user);
        collection.add(new MarkdownElement("**ID**\n" + botUser.getId()));
        collection.add(new MarkdownElement("**海绵山币**\n" + botUser.getSmfCoin()));
        collection.add(new MarkdownElement("**权限**\n" + botUser.getPermission()));
        return new CardBuilder()
                .setSize(Size.LG)
                .setColor("#4bc1ff")
                .setTheme(Theme.PRIMARY)
                .addModule(new HeaderModule(new PlainTextElement(String.format("%s的用户信息", user.getName()), false)))
                .addModule(DividerModule.INSTANCE)
                .addModule(new SectionModule(new Paragraph(3, collection)))
                .build();
    }

    public static MultipleCardComponent wrongCommandSource(CommandSource source) {
        return CardUtil.headerAndSections(Theme.WARNING, "告知: 命令来源错误", String.format("该命令只能在%s中使用", source.getName()));
    }
}