package dev.josemc.pixucord.listeners;

import dev.josemc.pixucord.cache.PlayerCache;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerDisconnectEvent;

public class LeaveListener extends EventListener {
    @Override
    public void load() {
        eventHandler.addListener(PlayerDisconnectEvent.class, event -> {
            Player player = event.getPlayer();

            PlayerCache.get().remove(player.getUuid());
        });
    }
}
