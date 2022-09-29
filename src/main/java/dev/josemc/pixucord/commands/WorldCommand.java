package dev.josemc.pixucord.commands;

import dev.josemc.pixucord.world.Worlds;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentString;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.command.builder.suggestion.SuggestionEntry;
import net.minestom.server.entity.Player;

import java.util.List;

public class WorldCommand extends Command {
    public WorldCommand() {
        super("wm");

        setCondition((sender, commandString) ->
                sender instanceof Player p
        );

        List<String> valid = List.of("overworld", "nether", "end", "aether", "deepdark", "lobby");

        ArgumentString world = ArgumentType.String("world");
        world.setSuggestionCallback(((sender, context, suggestion) -> {
            valid.forEach(v -> suggestion.addEntry(new SuggestionEntry(v)));
        }));

        addSyntax((sender, context) -> {
            Player p = (Player) sender;

            switch (context.get(world)) {
                case "overworld" -> p.setInstance(Worlds.overworldContainer);
                case "nether" -> p.setInstance(Worlds.netherContainer);
                case "end" -> p.setInstance(Worlds.endContainer);
                case "aether" -> p.setInstance(Worlds.aetherContainer);
                case "deepdark" -> p.setInstance(Worlds.deepdarkContainer);
                case "lobby" -> p.setInstance(Worlds.lobbyContainer);
                default -> p.sendMessage("No existe!");
            }
        }, world);
    }
}
