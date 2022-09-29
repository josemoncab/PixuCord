package dev.josemc.pixucord.listeners;


import net.minestom.server.event.player.PlayerChatEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChatListener extends EventListener{

    private final Logger LOGGER = LoggerFactory.getLogger(ChatListener.class);
    @Override
    public void load() {
        eventHandler.addListener(PlayerChatEvent.class, event -> {
            LOGGER.info("[{}] {}",event.getPlayer().getUsername(), event.getMessage());
        });
    }
}
