package com.github.gopherloaf.lemonmod.world.level.block.grower;

import com.github.gopherloaf.lemonmod.data.worldgen.features.ModTreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class LemonTreeGrower extends AbstractTreeGrower {
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource p_256119_, boolean p_256536_) {
        int random = p_256119_.nextInt(10);
        if (random == 0) {
            return p_256536_ ? ModTreeFeatures.FANCY_LEMON_BEES_005 : ModTreeFeatures.FANCY_LEMON;
        } else if (random < 5){
            return p_256536_ ? ModTreeFeatures.LEMON_BEES_005 : ModTreeFeatures.LEMON;
        } else {
            return p_256536_ ? ModTreeFeatures.SMALL_LEMON_BEES_005 : ModTreeFeatures.SMALL_LEMON;
        }
    }
}