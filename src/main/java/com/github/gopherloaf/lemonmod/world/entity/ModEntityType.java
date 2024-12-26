package com.github.gopherloaf.lemonmod.world.entity;

import com.github.gopherloaf.lemonmod.LemonMod;
import com.github.gopherloaf.lemonmod.world.entity.projectile.ThrownCombustibleLemon;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.github.gopherloaf.lemonmod.world.entity.vehicle.ModBoat;
import com.github.gopherloaf.lemonmod.world.entity.vehicle.ModChestBoat;

public class ModEntityType {
    // Create a Deferred Register to hold EntityTypes which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, LemonMod.MODID);

    public static final RegistryObject<EntityType<ModBoat>> MOD_BOAT = ENTITY_TYPES.register("boat", () -> EntityType.Builder.<ModBoat>of(ModBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build(new ResourceLocation(LemonMod.MODID, "boat").toString()));
    public static final RegistryObject<EntityType<ModChestBoat>> MOD_CHEST_BOAT = ENTITY_TYPES.register("chest_boat", () -> EntityType.Builder.<ModChestBoat>of(ModChestBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build(new ResourceLocation(LemonMod.MODID, "chest_boat").toString()));
    public static final RegistryObject<EntityType<ThrownCombustibleLemon>> COMBUSTIBLE_LEMON = ENTITY_TYPES.register("combustible_lemon", () -> EntityType.Builder.<ThrownCombustibleLemon>of(ThrownCombustibleLemon::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build(new ResourceLocation(LemonMod.MODID, "combustible_lemon").toString()));

    public static void register(){
        // Register the Deferred Register to the mod event bus so item get registered
        ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
