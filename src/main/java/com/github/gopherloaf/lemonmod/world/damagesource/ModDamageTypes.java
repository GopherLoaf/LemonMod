package com.github.gopherloaf.lemonmod.world.damagesource;

import com.github.gopherloaf.lemonmod.LemonMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

public interface ModDamageTypes {
    ResourceKey<DamageType> DIGESTION = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(LemonMod.MODID, "digestion"));
    ResourceKey<DamageType> PINEAPPLE_BUSH = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(LemonMod.MODID, "pineapple_bush"));
}
