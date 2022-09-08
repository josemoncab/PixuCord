package dev.josemc.pixucord.file;

public class ServerConfig extends Json {
    public ServerConfig() {
        super("server-properties.json", true);
    }

    public int port() {
        return ((Number) getJson().get("server-port")).intValue();
    }

    public String brand() {
        return (String) getJson().get("server-brand");
    }

    public boolean onlineMode() {
        return (boolean) getJson().get("online-mode");
    }

    @Override
    protected String getName() {
        return "server-properties";
    }
}
