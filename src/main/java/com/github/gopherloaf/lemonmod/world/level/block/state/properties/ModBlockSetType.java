package com.github.gopherloaf.lemonmod.world.level.block.state.properties;

import com.github.gopherloaf.lemonmod.LemonMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class ModBlockSetType {
    public static final BlockSetType LEMON = BlockSetType.register(new BlockSetType(new ResourceLocation(LemonMod.MODID, "lemon").toString()));
}
