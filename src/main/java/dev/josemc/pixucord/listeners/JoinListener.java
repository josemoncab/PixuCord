package dev.josemc.pixucord.listeners;

import dev.josemc.pixucord.cache.PlayerCache;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.block.Block;

public class JoinListener extends EventListener {
    @Override
    public void load() {
       eventHandler.addListener(PlayerLoginEvent.class, event -> {
            Player player = event.getPlayer();
            event.setSpawningInstance(instance());
            player.setRespawnPoint(new Pos(0,5,0));

            PlayerCache.add(player.getUuid());
        });
    }
    // TODO: Test Instance
    private InstanceContainer instance() {
        InstanceContainer instance = MinecraftServer.getInstanceManager().createInstanceContainer();
        instance.setGenerator(unit -> {
            unit.modifier().fillHeight(0, 5, Block.GRASS_BLOCK);
        });
        return instance;
    }
}
