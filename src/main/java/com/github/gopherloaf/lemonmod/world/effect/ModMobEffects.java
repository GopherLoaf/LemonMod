package com.github.gopherloaf.lemonmod.world.effect;

import com.github.gopherloaf.lemonmod.LemonMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, LemonMod.MODID);

    public static final RegistryObject<MobEffect> SOUR = MOB_EFFECTS.register("sour", () -> new ModMobEffect(MobEffectCategory.HARMFUL, 8322841));

    public static void register(){
        // Register the Deferred Register to the mod event bus so item get registered
        MOB_EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
