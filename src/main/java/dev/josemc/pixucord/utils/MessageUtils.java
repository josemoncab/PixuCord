package dev.josemc.pixucord.utils;

import dev.josemc.pixucord.PixuCord;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.minestom.server.adventure.audience.Audiences;
import net.minestom.server.command.CommandSender;

public class MessageUtils {

    private static final MiniMessage miniMessage = MiniMessage.miniMessage();
    public static void sendAll(String msg) {
        Audiences.all().sendMessage(parse(msg));
    }
    public static void send(CommandSender sender, String msg) {
        sender.sendMessage(parse(msg));
    }
    public static Component parse(String str) {
        return miniMessage.deserialize(str,
                Placeholder.parsed("prefix", PixuCord.getLang().get("prefix"))
        );
    }
}
