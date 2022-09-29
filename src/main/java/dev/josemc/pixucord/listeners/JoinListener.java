package dev.josemc.pixucord.listeners;

import dev.josemc.pixucord.configuration.Message;
import dev.josemc.pixucord.utils.MessageUtils;
import dev.josemc.pixucord.world.Worlds;
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
            event.setSpawningInstance(Worlds.lobbyContainer);
            player.setRespawnPoint(new Pos(0,5,0));


           Message.PLAYER_JOIN.send(player, MessageUtils.tagResolver("player", player.getUsername()));
        });
    }
}
