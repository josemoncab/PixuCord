package dev.josemc.pixucord.configuration;

import org.spongepowered.configurate.CommentedConfigurationNode;

import java.util.HashMap;
import java.util.Map;

public class FileManager {

    // Static files that always load (Lang file is loaded after this)
    private Map<String, String> files = Map.of(
            "server-properties.conf", "/configurations/server-properties.conf"
            );

    private final Map<String, CFile> configs = new HashMap<>();
    public FileManager() {
        for (Map.Entry<String, String> f: files.entrySet()) {
            configs.put(f.getKey().split("\\.")[0], new CFile(f.getKey(), f.getValue()));
        }

    }

    public CommentedConfigurationNode getConfig() {
        return configs.get("server-properties").get();
    }

    public CommentedConfigurationNode getLang() {
        return configs.get("lang").get();
    }

    public void loadLang() {
        configs.put("lang",  new CFile("lang/" + ServerConfig.LANG.get() + ".conf", "/configurations/lang/" + ServerConfig.LANG.get() + ".conf"));
    }
}
