package dev.josemc.pixucord.world;

import net.minestom.server.utils.NamespaceID;
import net.minestom.server.world.DimensionType;
import net.minestom.server.world.DimensionTypeManager;

import java.util.List;

public class DimensionTypes {

    public static final DimensionType OVERWORLD = DimensionType.builder(NamespaceID.from("pixucord:overworld"))
            .ultrawarm(false)
            .natural(true)
            .piglinSafe(false)
            .respawnAnchorSafe(false)
            .bedSafe(true)
            .raidCapable(true)
            .skylightEnabled(true)
            .ceilingEnabled(false)
            .fixedTime(null)
            .ambientLight(2.0f)
            .logicalHeight(256)
            .infiniburn(NamespaceID.from("minecraft:infiniburn_overworld"))
            .build();

    public static final DimensionType NETHER = DimensionType.builder(NamespaceID.from("pixucord:nether"))
            .ultrawarm(true)
            .natural(true)
            .piglinSafe(true)
            .respawnAnchorSafe(true)
            .bedSafe(false)
            .raidCapable(false)
            .skylightEnabled(true)
            .ceilingEnabled(false)
            .fixedTime(null)
            .ambientLight(2.0f)
            .logicalHeight(256)
            .effects("the_nether")
            .infiniburn(NamespaceID.from("minecraft:infiniburn_nether"))
            .build();

    public static final DimensionType END = DimensionType.builder(NamespaceID.from("pixucord:end"))
            .ultrawarm(false)
            .respawnAnchorSafe(false)
            .piglinSafe(false)
            .natural(false)
            .logicalHeight(256)
            .raidCapable(true)
            .skylightEnabled(false)
            .ceilingEnabled(false)
            .fixedTime(6000L)
            .ambientLight(2.0f)
            .logicalHeight(256)
            .coordinateScale(1)
            .effects("the_end")
            .infiniburn(NamespaceID.from("minecraft:infiniburn_end"))
            .build();

    public static final DimensionType AETHER = DimensionType.builder(NamespaceID.from("pixucord:aether"))
            .ultrawarm(false)
            .natural(true)
            .piglinSafe(false)
            .respawnAnchorSafe(false)
            .bedSafe(true)
            .raidCapable(true)
            .skylightEnabled(true)
            .ceilingEnabled(false)
            .fixedTime(null)
            .ambientLight(2.0f)
            .logicalHeight(256)
            .infiniburn(NamespaceID.from("minecraft:infiniburn_overworld"))
            .build();

    public static final DimensionType DEEPDARK = DimensionType.builder(NamespaceID.from("pixucord:deepdark"))
            .ultrawarm(false)
            .natural(true)
            .piglinSafe(false)
            .respawnAnchorSafe(false)
            .bedSafe(true)
            .raidCapable(true)
            .skylightEnabled(true)
            .ceilingEnabled(false)
            .fixedTime(null)
            .ambientLight(2.0f)
            .logicalHeight(256)
            .infiniburn(NamespaceID.from("minecraft:infiniburn_overworld"))
            .build();

    public static final DimensionType LOBBY = DimensionType.builder(NamespaceID.from("pixucord:lobby"))
            .ultrawarm(false)
            .natural(true)
            .piglinSafe(false)
            .respawnAnchorSafe(false)
            .bedSafe(true)
            .raidCapable(true)
            .skylightEnabled(true)
            .ceilingEnabled(false)
            .fixedTime(null)
            .ambientLight(2.0f)
            .logicalHeight(256)
            .infiniburn(NamespaceID.from("minecraft:infiniburn_overworld"))
            .build();

    public static void registerAll(DimensionTypeManager dimensionTypeManager) {
        for (DimensionType dimension : List.of(OVERWORLD, NETHER, END, AETHER, DEEPDARK, LOBBY)) {
            dimensionTypeManager.addDimension(dimension);
        }
    }
}

