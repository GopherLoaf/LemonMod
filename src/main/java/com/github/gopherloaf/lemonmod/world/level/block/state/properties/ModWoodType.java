package com.github.gopherloaf.lemonmod.world.level.block.state.properties;

import com.github.gopherloaf.lemonmod.LemonMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodType {
    public static final WoodType LEMON = WoodType.register(new WoodType(new ResourceLocation(LemonMod.MODID, "lemon").toString(), ModBlockSetType.LEMON));
}
