package com.github.gopherloaf.lemonmod.data.worldgen.features;

import com.github.gopherloaf.lemonmod.LemonMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class ModTreeFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> LEMON = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(LemonMod.MODID, "lemon"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_LEMON = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(LemonMod.MODID, "fancy_lemon"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> LEMON_BEES_005 = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(LemonMod.MODID, "lemon_bees_005"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_LEMON_BEES_005 = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(LemonMod.MODID, "fancy_lemon_bees_005"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_LEMON = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(LemonMod.MODID, "small_lemon"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_LEMON_BEES_005 = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(LemonMod.MODID, "small_lemon_bees_005"));
}
