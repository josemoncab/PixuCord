package dev.josemc.pixucord;

import dev.josemc.pixucord.configuration.FileManager;
import dev.josemc.pixucord.configuration.ServerConfig;
import dev.josemc.pixucord.managers.CommandLoader;
import dev.josemc.pixucord.managers.EventLoader;
import dev.josemc.pixucord.terminal.TerminalConsole;
import dev.josemc.pixucord.world.Worlds;
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
    private static FileManager fileManager;
    public static void main(String[] args) {
        init();
    }
    private static void init() {
        long initTime = System.nanoTime();
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

        fileManager = new FileManager();
        fileManager.loadLang();

        // disable terminal
        System.setProperty("minestom.terminal.disabled", "false");

        // Initialization
        MinecraftServer minecraftServer = MinecraftServer.init();

        // Set band name
        MinecraftServer.setBrandName((String) ServerConfig.BRAND.get());

        // Load dimmensions
        Worlds.setupWorlds();

        // offline-mode
        if ((boolean) ServerConfig.ONLINE.get())
            MojangAuth.init();

        // optifine enhancement
        OptifineSupport.enable();

        // command loader
        new CommandLoader();
        new EventLoader();

        // Start the server on port 25565
        minecraftServer.start("0.0.0.0", (Integer) ServerConfig.PORT.get());

        // Stop server
        MinecraftServer.getSchedulerManager().buildShutdownTask(PixuCord::onStop);

        long diff = System.nanoTime() - initTime;
        LOGGER.info(String.format("\u00A7bServer started in %.2fs.", diff / 1000000000f));

        // enable better terminal
        new TerminalConsole().start();
    }
    public static void onStop() {
       // PlayerCache.saveAll();
        MinecraftServer.stopCleanly();
    }
    public static Path getBasePath() {
        return BASE_PATH;
    }
    public static FileManager getFileManager() {
        return fileManager;
    }
}
