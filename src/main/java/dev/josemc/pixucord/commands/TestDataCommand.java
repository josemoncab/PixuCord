package dev.josemc.pixucord.commands;

import dev.josemc.pixucord.cache.PlayerCache;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentString;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;

public class TestDataCommand extends Command {
    public TestDataCommand() {
        super("testData");

        setCondition((sender, commandString) ->
                        sender instanceof Player
        );

        ArgumentString newData = ArgumentType.String("new-data");

        addSyntax((sender, context) -> {
            PlayerCache.getPlayer(((Player) sender).getUuid()).setTest(context.get(newData));
        }, newData);
    }
}
