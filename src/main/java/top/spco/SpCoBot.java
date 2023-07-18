package top.spco;

import org.apache.logging.log4j.Logger;
import snw.jkook.HttpAPI;
import snw.jkook.entity.channel.TextChannel;
import snw.jkook.plugin.BasePlugin;
import top.spco.utils.CardUtil;
import top.spco.utils.LogUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * Created on 2023/7/12 0012 14:54
 * <p>
 *
 * @author SpCo
 * @version 1.0
 * @since 1.0
 */

public class SpCoBot extends BasePlugin {
    private static SpCoBot instance;
    private static Database database;
    private TextChannel noticeChannel;
    private HttpAPI httpAPI;
    private static final Logger LOGGER = LogUtil.getLogger();

    @Override
    public void onLoad() {
        instance = this;
        httpAPI = getCore().getHttpAPI();
    }

    public static SpCoBot getInstance() {
        return instance;
    }

    public static Database getDatabase() {
        return database;
    }

    @Override
    public void onEnable() {
        Register.eventRegister();
        Register.commandRegister();
        database = new Database();
        noticeChannel = ((TextChannel) httpAPI.getChannel("9544841603814956"));
        noticeChannel.sendComponent(CardUtil.headerAndSection("告知: 机器人已上线", "机器人正常运行中 " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))));
        LOGGER.info("SpCoBot已上线");
    }

    @Override
    public void onDisable() {
        noticeChannel.sendComponent(CardUtil.headerAndSection("告知: 机器人已下线", "机器人已关闭 " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))));
        database.close();
        LOGGER.info("SpCoBot已离线");
    }
}