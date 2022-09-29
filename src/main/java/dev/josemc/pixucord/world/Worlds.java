package dev.josemc.pixucord.world;

import dev.josemc.pixucord.world.generator.*;
import net.minestom.server.MinecraftServer;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;

import java.util.List;

public class Worlds {

    private static final InstanceManager instanceManager = MinecraftServer.getInstanceManager();
    public static InstanceContainer overworldContainer, netherContainer, endContainer, aetherContainer, deepdarkContainer, lobbyContainer;
    public static void setupWorlds() {
        DimensionTypes.registerAll(MinecraftServer.getDimensionTypeManager());
        overworldContainer = instanceManager.createInstanceContainer(DimensionTypes.OVERWORLD);
        netherContainer = instanceManager.createInstanceContainer(DimensionTypes.NETHER);
        endContainer = instanceManager.createInstanceContainer(DimensionTypes.END);
        aetherContainer = instanceManager.createInstanceContainer(DimensionTypes.AETHER);
        deepdarkContainer = instanceManager.createInstanceContainer(DimensionTypes.DEEPDARK);
        lobbyContainer = instanceManager.createInstanceContainer(DimensionTypes.LOBBY);

        overworldContainer.setGenerator(new OverworldGenerator());
        netherContainer.setGenerator(new NetherGenerator());
        endContainer.setGenerator(new EndGenerator());
        aetherContainer.setGenerator(new AetherGenerator());
        deepdarkContainer.setGenerator(new DeepdarkGenerator());
        lobbyContainer.setGenerator(new LobbyGenerator());

    }
}
