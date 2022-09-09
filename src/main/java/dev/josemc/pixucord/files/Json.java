package dev.josemc.pixucord.files;

import com.google.gson.Gson;
import dev.josemc.pixucord.PixuCord;
import dev.josemc.pixucord.data.PlayerData;
import org.apache.logging.log4j.core.util.FileUtils;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/*
* Extraer Json del Jar (lang.json)
* Crear archivo Json vacio
* Crear Json con el contenido de una plantilla (playerdata.json)
*
* */

public abstract class Json {
    private Gson gson;
    private Reader reader;
    // Path del archivo a escribir (fuera del jar)
    private Path path;

    /**
     *
     * @param file Nombre del archivo a crear
     * @param internal Nombre del archivo dentro del Jar
     */
    public Json(String file, String internal) {
        gson = new Gson();
        path = PixuCord.getBasePath().resolve(file);
        try {
            if (!Files.exists(path)) {
                if (!Files.exists(path.getParent())) Files.createDirectories(path.getParent());
                if (internal == null) {
                    Files.createFile(path);
                } else {
                    Files.write(path, FileUtils.class.getClassLoader().getResourceAsStream(internal).readAllBytes(), StandardOpenOption.CREATE);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Json(String file) {
        this(file, file);
    }

    public Object read(Class<?> clazz) {
        try {
            reader = Files.newBufferedReader(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return gson.fromJson(reader, clazz);
    }

    public void save(PlayerData data) {
        try {
            Writer writer = Files.newBufferedWriter(path);
            gson.toJson(data, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
