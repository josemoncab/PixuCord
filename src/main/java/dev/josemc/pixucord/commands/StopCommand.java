package dev.josemc.pixucord.commands;

import dev.josemc.pixucord.PixuCord;
import net.minestom.server.command.ConsoleSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.entity.Player;

public class StopCommand extends Command {
    public StopCommand() {
        super("stop");

        setCondition((sender, commandString) ->
                sender instanceof ConsoleSender ||
                        (sender instanceof Player p && p.hasPermission("pixucord.commands.stop"))
        );

        setDefaultExecutor((sender, context) -> PixuCord.onStop());
    }
}
