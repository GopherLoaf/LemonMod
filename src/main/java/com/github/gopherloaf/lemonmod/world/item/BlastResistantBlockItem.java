package com.github.gopherloaf.lemonmod.world.item;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class BlastResistantBlockItem extends BlockItem {
    public BlastResistantBlockItem(Block p_40565_, Properties p_40566_) {
        super(p_40565_, p_40566_);
    }

    public boolean canBeHurtBy(DamageSource p_41387_) {
        return (super.canBeHurtBy(p_41387_)) && (!p_41387_.is(DamageTypeTags.IS_EXPLOSION));
    }
}
