package dev.josemc.pixucord.cache;

import dev.josemc.pixucord.data.PlayerData;
import dev.josemc.pixucord.files.PlayerFile;

import java.util.HashMap;
import java.util.UUID;

public class PlayerCache {
    private static HashMap<UUID, PlayerData> playerCache = new HashMap<>();

    public static HashMap<UUID, PlayerData> get() {
        return playerCache;
    }

    public static PlayerData getPlayer(UUID uuid) {
        return playerCache.get(uuid);
    }

    public static void add(UUID uuid, PlayerData data) {
        playerCache.put(uuid, data);
    }

    public static void add(UUID uuid) {
        PlayerData data = new PlayerFile(uuid).getData();
        PlayerCache.add(uuid, data);
    }
}
