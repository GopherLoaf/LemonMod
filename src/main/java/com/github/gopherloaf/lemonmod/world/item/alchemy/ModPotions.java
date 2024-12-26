package com.github.gopherloaf.lemonmod.world.item.alchemy;

import com.github.gopherloaf.lemonmod.LemonMod;
import com.github.gopherloaf.lemonmod.world.effect.ModMobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPotions {
    // Create a Deferred Register to hold Potions which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, LemonMod.MODID);

    public static final RegistryObject<Potion> SOUR = POTIONS.register("sour", () -> new Potion("sour", new MobEffectInstance[]{new MobEffectInstance(ModMobEffects.SOUR.get(), 1800)}));
    public static final RegistryObject<Potion> LONG_SOUR = POTIONS.register("long_sour", () -> new Potion("sour", new MobEffectInstance[]{new MobEffectInstance(ModMobEffects.SOUR.get(), 4800)}));
    public static final RegistryObject<Potion> STRONG_SOUR = POTIONS.register("strong_sour", () -> new Potion("sour", new MobEffectInstance[]{new MobEffectInstance(ModMobEffects.SOUR.get(), 400, 3)}));
    public static final RegistryObject<Potion> WITHER = POTIONS.register("wither", () -> new Potion("wither", new MobEffectInstance[]{new MobEffectInstance(MobEffects.WITHER, 900)}));
    public static final RegistryObject<Potion> LONG_WITHER = POTIONS.register("long_wither", () -> new Potion("wither", new MobEffectInstance[]{new MobEffectInstance(MobEffects.WITHER, 1800)}));
    public static final RegistryObject<Potion> STRONG_WITHER = POTIONS.register("strong_wither", () -> new Potion("wither", new MobEffectInstance[]{new MobEffectInstance(MobEffects.WITHER, 432, 1)}));
    public static final RegistryObject<Potion> SATURATION = POTIONS.register("saturation", () -> new Potion("saturation", new MobEffectInstance[]{new MobEffectInstance(MobEffects.SATURATION, 1, 0)}));
    public static final RegistryObject<Potion> STRONG_SATURATION = POTIONS.register("strong_saturation", () -> new Potion("saturation", new MobEffectInstance[]{new MobEffectInstance(MobEffects.SATURATION, 1, 1)}));
    public static final RegistryObject<Potion> HUNGER = POTIONS.register("hunger", () -> new Potion("hunger", new MobEffectInstance[]{new MobEffectInstance(MobEffects.HUNGER, 1800)}));
    public static final RegistryObject<Potion> LONG_HUNGER = POTIONS.register("long_hunger", () -> new Potion("hunger", new MobEffectInstance[]{new MobEffectInstance(MobEffects.HUNGER, 4800)}));
    public static final RegistryObject<Potion> STRONG_HUNGER = POTIONS.register("strong_hunger", () -> new Potion("hunger", new MobEffectInstance[]{new MobEffectInstance(MobEffects.HUNGER, 400, 3)}));
    public static final RegistryObject<Potion> BLINDNESS = POTIONS.register("blindness", () -> new Potion("blindness", new MobEffectInstance[]{new MobEffectInstance(MobEffects.BLINDNESS, 100)}));
    public static final RegistryObject<Potion> LONG_BLINDNESS = POTIONS.register("long_blindness", () -> new Potion("blindness", new MobEffectInstance[]{new MobEffectInstance(MobEffects.BLINDNESS, 200)}));
    public static final RegistryObject<Potion> LEMON_LEGEND = POTIONS.register("lemon_legend", () -> new Potion("lemon_legend", new MobEffectInstance[]{new MobEffectInstance(ModMobEffects.SOUR.get(), 400, 3), new MobEffectInstance(ModMobEffects.SATURATION.get(), 400, 2)}));
    public static final RegistryObject<Potion> LONG_LEMON_LEGEND = POTIONS.register("long_lemon_legend", () -> new Potion("lemon_legend", new MobEffectInstance[]{new MobEffectInstance(ModMobEffects.SOUR.get(), 800, 3), new MobEffectInstance(ModMobEffects.SATURATION.get(), 800, 2)}));
    public static final RegistryObject<Potion> STRONG_LEMON_LEGEND = POTIONS.register("strong_lemon_legend", () -> new Potion("lemon_legend", new MobEffectInstance[]{new MobEffectInstance(ModMobEffects.SOUR.get(), 400, 5), new MobEffectInstance(ModMobEffects.SATURATION.get(), 400, 3)}));

    public static void register(){
        // Register the Deferred Register to the mod event bus so item get registered
        POTIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
