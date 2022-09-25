package dev.josemc.pixucord.listeners;

import dev.josemc.pixucord.PixuCord;
import dev.josemc.pixucord.cache.PlayerCache;
import dev.josemc.pixucord.utils.MessageUtils;
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
            MessageUtils.send(player, "PlayerData test:" + PlayerCache.getPlayer(player.getUuid()).getTest());
            PlayerCache.getPlayer(player.getUuid()).setTest("nuevo!!");

            MessageUtils.sendAll(PixuCord.getLang().get("player-join"));
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
