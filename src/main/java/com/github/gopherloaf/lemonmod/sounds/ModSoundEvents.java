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
    public static final RegistryObject<SoundEvent> LEMON_LAUNCHER_SHOOT = SOUND_EVENTS.register("lemon_launcher_shoot", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(LemonMod.MODID, "lemon_launcher_shoot")));
    public static final RegistryObject<SoundEvent> LEMON_LAUNCHER_LOADING_START = SOUND_EVENTS.register("lemon_launcher_loading_start", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(LemonMod.MODID, "lemon_launcher_loading_start")));
    public static final RegistryObject<SoundEvent> LEMON_LAUNCHER_BAZOOKA = SOUND_EVENTS.register("lemon_launcher_bazooka", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(LemonMod.MODID, "lemon_launcher_bazooka")));
    public static final RegistryObject<SoundEvent> LEMON_LAUNCHER_MULTI = SOUND_EVENTS.register("lemon_launcher_multi", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(LemonMod.MODID, "lemon_launcher_multi")));
    public static final RegistryObject<SoundEvent> LEMON_LAUNCHER_LOADING_MIDDLE = SOUND_EVENTS.register("lemon_launcher_loading_middle", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(LemonMod.MODID, "lemon_launcher_loading_middle")));
    public static final RegistryObject<SoundEvent> LEMON_LAUNCHER_LOADING_END = SOUND_EVENTS.register("lemon_launcher_loading_end", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(LemonMod.MODID, "lemon_launcher_loading_end")));

    public static void register(){
        // Register the Deferred Register to the mod event bus so item get registered
        SOUND_EVENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
