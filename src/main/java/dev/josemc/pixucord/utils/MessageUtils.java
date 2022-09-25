package dev.josemc.pixucord.utils;

import dev.josemc.pixucord.configuration.Message;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

import java.util.ArrayList;
import java.util.List;

public class MessageUtils {

    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    public static Component parse(String str, TagResolver... placeholders) {
        ArrayList<TagResolver> tagResolvers = new ArrayList<>(List.of(placeholders));
        tagResolvers.add(tagResolver("prefix", Message.PREFIX.get()));
        return miniMessage.deserialize(str, TagResolver.resolver(tagResolvers));
    }

    public static TagResolver tagResolver(String tag, String value) {
        return TagResolver.resolver(tag, Tag.inserting(miniMessage.deserialize(value)));
    }
}
