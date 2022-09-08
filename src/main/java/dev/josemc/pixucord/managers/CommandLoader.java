package dev.josemc.pixucord.managers;

import dev.josemc.pixucord.PixuCord;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandManager;
import net.minestom.server.command.builder.Command;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CommandLoader {
    private static final Map<String, Command> AVALIBLE_COMMANDS = new HashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandLoader.class);

    static {
        Reflections reflections = new Reflections("dev.josemc.pixucord.commands");
        Set<Class<? extends Command>> commands = reflections.getSubTypesOf(Command.class);
        for (Class<? extends Command> cmd : commands) {
            try {
                Command command = cmd.getDeclaredConstructor().newInstance();
                AVALIBLE_COMMANDS.put(command.getName(), command);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public CommandLoader() {
        LOGGER.info("Iniciando el registro de comandos...");
        CommandManager commandManager = MinecraftServer.getCommandManager();

        // Default unknown command handler
        commandManager.setUnknownCommandCallback((sender, command) -> {
            sender.sendMessage(PixuCord.getLang().get("unknown-command"));
        });

        // Registro de comandos dinamicos
        for (Map.Entry<String, Command> entry : AVALIBLE_COMMANDS.entrySet()) {
            commandManager.register(entry.getValue());
        }

        LOGGER.info("{} coamndos registrados!", AVALIBLE_COMMANDS.size());
    }
}
