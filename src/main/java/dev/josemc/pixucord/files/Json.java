package dev.josemc.pixucord.files;

import com.google.gson.Gson;
import dev.josemc.pixucord.PixuCord;
import org.apache.logging.log4j.core.util.FileUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;

public abstract class Json {
    private Gson gson;

    private Object json;
    private Reader reader;

    public Json(String file, boolean fromJar) {
        this(file, fromJar, HashMap.class);
    }
    public Json(String file, boolean fromJar, Class<?> clazz) {
        gson = new Gson();
        Path path = PixuCord.getBasePath().resolve(file);
        try {
            if (!Files.exists(path)) {
                if (!fromJar) {
                    Files.createFile(path);
                } else {
                    Files.write(path, FileUtils.class.getClassLoader().getResourceAsStream(file).readAllBytes(), StandardOpenOption.CREATE);
                }
            }
            reader = Files.newBufferedReader(PixuCord.getBasePath().resolve(file));
            json = gson.fromJson(reader, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected Reader getReader() {
        return reader;
    }
    protected Object getJson() {
        return json;
    }

    protected void save(Object obj, String path) {
        try {
            gson.toJson(obj, new FileWriter(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract String getName();
}
