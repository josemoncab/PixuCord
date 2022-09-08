package dev.josemc.pixucord.files;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class LangConfig extends Json {

    public LangConfig() {
        super("lang.json", true);
    }

    public Component get(String path) {
        return MiniMessage.miniMessage().deserialize((String) getJson().get(path));
    }

    @Override
    protected String getName() {
        return "lang";
    }
}
