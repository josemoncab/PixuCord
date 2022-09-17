package dev.josemc.pixucord.cache;

import dev.josemc.pixucord.data.PlayerData;
import dev.josemc.pixucord.files.PlayerFile;

import java.util.HashMap;
import java.util.UUID;

public class PlayerCache {
    private static HashMap<UUID, PlayerFile> playerCache = new HashMap<>();
    public static HashMap<UUID, PlayerFile> get() {
        return playerCache;
    }

    public static PlayerData getPlayer(UUID uuid) {
        return playerCache.get(uuid).getData();
    }
    public static PlayerFile getPlayerFile(UUID uuid) {
        return playerCache.get(uuid);
    }

    public static void add(UUID uuid, PlayerFile data) {
        playerCache.put(uuid, data);
    }

    public static void add(UUID uuid) {
        PlayerCache.add(uuid, new PlayerFile(uuid));
    }
    public static void update(UUID uuid, PlayerData data) {
        playerCache.put(uuid, getPlayerFile(uuid).setData(data).save());
    }

    public static void saveAll() {
        playerCache.forEach((playerUuid, playerFile) -> playerFile.save());

        playerCache.clear();
    }
}
