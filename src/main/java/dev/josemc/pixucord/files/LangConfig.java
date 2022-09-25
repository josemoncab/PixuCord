package dev.josemc.pixucord.files;

import java.util.Map;

public class LangConfig extends Json {

    private Map<?,?> json;

    public LangConfig() {
        super("./config/lang.json", "config/lang.json");
        json = (Map<?, ?>) this.read(Map.class);
    }

    public String get(String path) {
        return (String) json.get(path);
    }

}
