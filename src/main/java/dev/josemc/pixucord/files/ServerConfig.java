package dev.josemc.pixucord.files;

import java.util.Map;

public class ServerConfig extends Json {
    private final Map<?,?> json;
    public ServerConfig() {
        super("./config/server-properties.json", "config/server-properties.json");
        json = (Map<?, ?>) this.read(Map.class);
    }

    public int port() {
        return ((Number) json.get("server-port")).intValue();
    }

    public String brand() {
        return (String) json.get("server-brand");
    }

    public boolean onlineMode() {
        return (boolean) json.get("online-mode");
    }

}
