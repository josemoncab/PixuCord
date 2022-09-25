package dev.josemc.pixucord.configuration;

import dev.josemc.pixucord.PixuCord;
import dev.josemc.pixucord.utils.MessageUtils;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.minestom.server.command.CommandSender;

import java.util.List;

public enum Message {
    PREFIX("general.prefix"),
    UNKNOWN_COMMAND("general.unknown-command"),
    PLAYER_JOIN("general.player-join"),
    PLAYER_LEAVE("general.player-leave"),
    ;
    private final String path;

    Message(final String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
    public String get() {
        List<String> s = List.of(path.split("\\."));
        return PixuCord.getFileManager().getLang().node(s).getString();
    }

    public void send(CommandSender sender, TagResolver... placeholders) {
        sender.sendMessage(MessageUtils.parse(get(), placeholders));
    }
}
