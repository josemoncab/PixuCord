package dev.josemc.pixucord.listeners;

import net.minestom.server.MinecraftServer;
import net.minestom.server.event.GlobalEventHandler;

public abstract class EventListener {

    protected GlobalEventHandler eventHandler = MinecraftServer.getGlobalEventHandler();

    public abstract void load();
}
