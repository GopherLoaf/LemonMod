package com.github.gopherloaf.lemonmod;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = LemonMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.BooleanValue FISH_TO_PLAYER = BUILDER
            .comment("If this is true, fish will teleport to the player when fed a lemon.")
            .define("fishToPlayer", false);

    private static final ForgeConfigSpec.BooleanValue FISH_TO_HELL = BUILDER
            .comment("If this is true, fish will teleport to the nether, instead of dying, when fed a lemon.")
            .define("fishToHell", false);

    private static final ForgeConfigSpec.BooleanValue EXPLOSIVE_LEMONS = BUILDER
            .comment("If this is false, regular lemons will no longer explode.")
            .define("explosiveLemons", true);

    private static final ForgeConfigSpec.IntValue FISH_EXPLOSION_SIZE = BUILDER
            .comment("Defines the size of the explosion that occurs when a fish is fed a lemon. Keep in mind that Indirectly Combustible Lemon explosion size is this value * (amount of tnt + 1).")
            .defineInRange("fishExplosionSize", 1, 0, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.BooleanValue FIREY_LEMONS = BUILDER
            .comment("If this is false, lemon explosions will no longer create fire.")
            .define("fireyLemons", true);

    private static final ForgeConfigSpec.DoubleValue COMBUSTIBLE_EXPLOSION_INCREMENT = BUILDER
            .comment("Defines how much Indirectly Combustible Lemon explosion size increases per tnt used/explosion level. At 1 tnt, Indirectly Combustible Lemon explosion size is 1 increment greater than a normal lemon.")
            .defineInRange("combustibleExplosionIncrement", 0.5F, 0, Float.MAX_VALUE);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean fishToPlayer;
    public static boolean fishToHell;
    public static boolean explosiveLemons;
    public static int fishExplosionSize;
    public static boolean fireyLemons;
    public static double combustibleExplosionIncrement;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        fishToPlayer = FISH_TO_PLAYER.get();
        fishToHell = FISH_TO_HELL.get();
        explosiveLemons = EXPLOSIVE_LEMONS.get();
        fishExplosionSize = FISH_EXPLOSION_SIZE.get();
        fireyLemons = FIREY_LEMONS.get();
        combustibleExplosionIncrement = COMBUSTIBLE_EXPLOSION_INCREMENT.get();
    }
}
