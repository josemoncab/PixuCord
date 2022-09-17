package dev.josemc.pixucord.files;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

import java.util.Map;

public class LangConfig extends Json {

    private Map<?,?> json;

    public LangConfig() {
        super("./config/lang.json", "config/lang.json");
        json = (Map<?, ?>) this.read(Map.class);
    }

    public Component get(String path) {
        return MiniMessage.miniMessage().deserialize((String) json.get(path));
    }

}
