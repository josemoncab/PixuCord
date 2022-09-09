package dev.josemc.pixucord.files;

import dev.josemc.pixucord.data.PlayerData;

import java.util.UUID;

// TODO: Mejorar el codigo
public class PlayerFile extends Json {
    private UUID uuid;
    private PlayerData data;

    public PlayerFile(UUID uuid) {
        super("./playerdata/" + uuid.toString() + ".json", "playerdata.json");

        this.uuid = uuid;
        data = (PlayerData) this.read(PlayerData.class);
    }

    /*public PlayerFile(UUID uuid) {
        if (!PixuCord.getPlayerDataPath().toFile().exists()) PixuCord.getPlayerDataPath().toFile().mkdirs();

        gson = new Gson();
        Path path = PixuCord.getPlayerDataPath().resolve(uuid.toString() + ".json");
        try {
            if (!Files.exists(path)) {
                Files.write(path, FileUtils.class.getClassLoader().getResourceAsStream("playerdata.json").readAllBytes(), StandardOpenOption.CREATE);
            }
            reader = Files.newBufferedReader(path);
            data = gson.fromJson(reader, PlayerData.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        PlayerFile.uuid = uuid;
    }*/

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
