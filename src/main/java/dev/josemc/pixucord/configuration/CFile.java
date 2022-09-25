package dev.josemc.pixucord.configuration;

import dev.josemc.pixucord.PixuCord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

public class CFile {

    private final Logger LOGGER = LoggerFactory.getLogger(CFile.class);
    private Path path;
    private CommentedConfigurationNode root;
    private HoconConfigurationLoader loader;

    /**
     * Create a new HOCON file based in internal resource
     * @param path External path to the file
     * @param internal Internal path to the file
     * */
    public CFile(String path, String internal) {
        this.path = PixuCord.getBasePath().resolve("config/" + path);

        try {
            if (!Files.exists(this.path) || !Files.exists(this.path.getParent())) {
                Files.createDirectories(this.path.getParent());
                if (internal == null) {
                    Files.createFile(this.path);
                } else {
                    Files.write(this.path, CFile.class.getResourceAsStream(internal).readAllBytes(), StandardOpenOption.CREATE);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            load();
            validate(internal);

        }
    }

    /**
     * Create a new HOCON empty file
     * @param path The path of the file
     * */
    public CFile(String path) {
        new CFile(path, null);
    }

    /**
     * Save the new data to the file
     * */
    public void save() {
        try {
            this.loader.save(this.root);
        } catch (ConfigurateException e) {
            throw new RuntimeException(e);
        }
    }

    private void load() {
        this.loader = HoconConfigurationLoader.builder()
                .path(this.path)
                .build();
        try {
            this.root = this.loader.load();
        } catch (ConfigurateException e) {
            throw new RuntimeException(e);
        }


    }
    private void validate(String internal) {
        boolean error = false;
        try {
            Files.write(Path.of(this.path.toString() + ".bk"), CFile.class.getResourceAsStream(internal).readAllBytes(), StandardOpenOption.CREATE);
            CommentedConfigurationNode tmpLoader = null;
            tmpLoader = HoconConfigurationLoader.builder().path(Path.of(this.path.toString() + ".bk")).build().load();
            for (Map.Entry<Object, CommentedConfigurationNode> entry : tmpLoader.childrenMap().entrySet()) {
                if (!this.root.hasChild(entry.getKey())) {
                    this.root.node(entry.getValue().path()).comment(tmpLoader.node(entry.getValue().path()).comment());
                    this.root.node(entry.getValue().path()).set(tmpLoader.node(entry.getValue().path()).get(Object.class));
                    error = true;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (error) {
            LOGGER.info("Found missing keys in config files! Fixing...");
            save();
        }
        new File(this.path.toString() + ".bk").delete();
    }

    public CommentedConfigurationNode get() {
        return root;
    }
}
