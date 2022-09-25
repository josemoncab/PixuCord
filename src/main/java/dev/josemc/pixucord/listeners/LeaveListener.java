package dev.josemc.pixucord.listeners;

import dev.josemc.pixucord.configuration.Message;
import dev.josemc.pixucord.utils.MessageUtils;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerDisconnectEvent;

public class LeaveListener extends EventListener {
    @Override
    public void load() {
        eventHandler.addListener(PlayerDisconnectEvent.class, event -> {
            Player player = event.getPlayer();

            //PlayerCache.saveAndRemove(player.getUuid());
            Message.PLAYER_LEAVE.send(player, MessageUtils.tagResolver("player", player.getUsername()));
        });
    }
}
