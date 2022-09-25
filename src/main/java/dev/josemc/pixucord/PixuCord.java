package dev.josemc.pixucord;

import dev.josemc.pixucord.cache.PlayerCache;
import dev.josemc.pixucord.files.LangConfig;
import dev.josemc.pixucord.files.ServerConfig;
import dev.josemc.pixucord.managers.CommandLoader;
import dev.josemc.pixucord.managers.EventLoader;
import dev.josemc.pixucord.terminal.TerminalConsole;
import net.minestom.server.MinecraftServer;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.extras.optifine.OptifineSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.nio.file.Path;

public class PixuCord {
    private static final Logger LOGGER = LoggerFactory.getLogger("PixuCord");
    private static final Path BASE_PATH = Path.of("");
    private static LangConfig lang;
    public static void main(String[] args) {
        init();
    }
    private static void init() {
        long initTime = System.nanoTime();
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

        ServerConfig serverConfig = new ServerConfig();
        lang = new LangConfig();

        // disable terminal
        System.setProperty("minestom.terminal.disabled", "false");

        // Initialization
        MinecraftServer minecraftServer = MinecraftServer.init();

        // Set band name
        MinecraftServer.setBrandName(serverConfig.brand());

        // offline-mode
        if (serverConfig.onlineMode())
            MojangAuth.init();

        // optifine enhancement
        OptifineSupport.enable();

        // command loader
        new CommandLoader();
        new EventLoader();

        // Start the server on port 25565
        minecraftServer.start("0.0.0.0", serverConfig.port());

        // Stop server
        MinecraftServer.getSchedulerManager().buildShutdownTask(PixuCord::onStop);

        long diff = System.nanoTime() - initTime;
        LOGGER.info(String.format("\u00A7bServer started in %.2fs.", diff / 1000000000f));

        // enable better terminal
        new TerminalConsole().start();
    }
    public static void onStop() {
        PlayerCache.saveAll();
        MinecraftServer.stopCleanly();
    }
    public static Path getBasePath() {
        return BASE_PATH;
    }
    public static LangConfig getLang() {
        return lang;
    }
}
