package dev.josemc.pixucord.files;

import dev.josemc.pixucord.data.PlayerData;

import java.util.UUID;

public class PlayerFile extends Json {
    private PlayerData data;

    public PlayerFile(UUID uuid) {
        super("./playerdata/" + uuid.toString() + ".json", "config/templates/playerdata.json");

        data = (PlayerData) this.read(PlayerData.class);
    }

    public PlayerData getData() {
        return data;
    }

    public PlayerFile setData(PlayerData data) {
        this.data = data;
        return this;
    }

    public PlayerFile save() {
       super.save(data);
       return this;
    }

}
