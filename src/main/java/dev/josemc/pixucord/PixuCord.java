package dev.josemc.pixucord;

import java.nio.file.Path;

public class PixuCord {

    private static final Path BASE_PATH = Path.of("");
    public static void main(String[] args) {
        new ServerBootstrap();
    }

    public static Path getBasePath() {
        return BASE_PATH;
    }
}
