package dev.josemc.pixucord.files;

import com.google.gson.Gson;
import dev.josemc.pixucord.PixuCord;
import org.apache.logging.log4j.core.util.FileUtils;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

public abstract class Json {
    private final Map<?, ?> json;
    public Json(String file, boolean fromJar) {
        Path path = PixuCord.getBasePath().resolve(file);
        try {
            if (!Files.exists(path)) {
                if (!fromJar) {
                    Files.createFile(path);
                } else {
                    Files.write(path, FileUtils.class.getClassLoader().getResourceAsStream(file).readAllBytes(), StandardOpenOption.CREATE);
                }
            }
            Reader reader = Files.newBufferedReader(PixuCord.getBasePath().resolve(file));
            Gson gson = new Gson();
            json = gson.fromJson(reader, HashMap.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected Map<?, ?> getJson() {
        return json;
    }

    protected abstract String getName();
}
