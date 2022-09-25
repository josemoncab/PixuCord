package dev.josemc.pixucord.configuration;

import dev.josemc.pixucord.PixuCord;
import org.spongepowered.configurate.serialize.SerializationException;

import java.util.List;

public enum ServerConfig {
    PORT("server-port"),
    ONLINE("online-mode"),
    BRAND("server-brand"),
    LANG("lang")
    ;

    private final String path;

    ServerConfig(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public Object get() {
        try {
            List<String> s = List.of(path.split("\\."));
            return PixuCord.getFileManager().getConfig().node(s).get(Object.class);
        } catch (SerializationException e) {
            throw new RuntimeException(e);
        }
    }
}
