package dev.josemc.pixucord.files;

import dev.josemc.pixucord.PixuCord;
import dev.josemc.pixucord.data.PlayerData;

import java.util.UUID;

public class PlayerFile extends Json {

    private PlayerData data;
    private UUID uuid;

    public PlayerFile(UUID uuid) {
        super(PixuCord.getBasePath().resolve(uuid.toString() + ".json").toString(), false, PlayerData.class);
        data = (PlayerData) getJson();
        this.uuid = uuid;
    }

    @Override
    protected String getName() {
        return uuid.toString();
    }

    public PlayerData getData() {
        return data;
    }

    public void save(Object data) {
        super.save(data, PixuCord.getBasePath().resolve(uuid.toString() + ".json").toString());
    }
}
