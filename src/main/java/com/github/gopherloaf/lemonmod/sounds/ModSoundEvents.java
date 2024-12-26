package com.github.gopherloaf.lemonmod.sounds;

import com.github.gopherloaf.lemonmod.LemonMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, LemonMod.MODID);

    public static final RegistryObject<SoundEvent> COMBUSTIBLE_LEMON_THROW = SOUND_EVENTS.register("combustible_lemon_throw", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(LemonMod.MODID, "combustible_lemon_throw")));
    public static final RegistryObject<SoundEvent> BIG_LEMON_HOLLOW = SOUND_EVENTS.register("big_lemon_hollow", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(LemonMod.MODID, "big_lemon_hollow")));
    public static final RegistryObject<SoundEvent> PINEAPPLE_BUSH_SHEAR = SOUND_EVENTS.register("pineapple_bush_shear", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(LemonMod.MODID, "pineapple_bush_shear")));

    public static void register(){
        // Register the Deferred Register to the mod event bus so item get registered
        SOUND_EVENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
