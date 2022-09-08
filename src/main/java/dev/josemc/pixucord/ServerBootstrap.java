package dev.josemc.pixucord;

import dev.josemc.pixucord.terminal.TerminalConsole;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerCommandEvent;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.extras.optifine.OptifineSupport;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.block.Block;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class ServerBootstrap {

    private static final Logger LOGGER = LoggerFactory.getLogger("Pixucord");
    private final String[] args;
    public ServerBootstrap(String[] args) {
        this.args = args;
        init();
    }

    public void init() {
        long initTime = System.nanoTime();
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

        // TODO: Server config file
        // set server port
        int port = 25565;

        // disable terminal
        System.setProperty("minestom.terminal.disabled", "false");

        // Initialization
        MinecraftServer minecraftServer = MinecraftServer.init();

        // Test instance
        InstanceContainer instance = MinecraftServer.getInstanceManager().createInstanceContainer();
        instance.setGenerator(unit -> {
            unit.modifier().fillHeight(0, 5, Block.GRASS_BLOCK);
        });

        MinecraftServer.getGlobalEventHandler().addListener(PlayerLoginEvent.class, event -> {
            Player player = event.getPlayer();
            event.setSpawningInstance(instance);
            player.setRespawnPoint(new Pos(0,7,0));
        });

        // Set band name
        MinecraftServer.setBrandName("PixuCord");

        // offline-mode
        MojangAuth.init();

        // proxy protection
        /*
        if (cmd.hasOption(proxySecretOption)) {
            VelocityProxy.enable(cmd.getOptionValue(proxySecretOption));
        }*/

        // optifine enhancement
        OptifineSupport.enable();


        // log players executing commands
        MinecraftServer.getGlobalEventHandler().addListener(PlayerCommandEvent.class, e -> {
            if (e.isCancelled()) return;
            LOGGER.info("{} issued command: {}", e.getPlayer().getUsername(), e.getCommand());
        }).setPriority(Integer.MAX_VALUE);


        // Start the server on port 25565
        minecraftServer.start("0.0.0.0", port);

        long diff = System.nanoTime() - initTime;
        LOGGER.info(String.format("\u00A7bServer started in %.2fs.", diff / 1000000000f));

        // enable better terminal
        new TerminalConsole().start();
    }

}
