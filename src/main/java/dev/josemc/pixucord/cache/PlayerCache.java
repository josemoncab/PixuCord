package dev.josemc.pixucord.cache;

// TODO: Improve player cache system

public class PlayerCache {/*
    private static ConcurrentHashMap<UUID, PlayerFile> playerCache = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<UUID, PlayerFile> get() {
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
    public static void saveAndRemove(UUID uuid) {
        getPlayerFile(uuid).save();
        get().remove(uuid);
    }*/
}
