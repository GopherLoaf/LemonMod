package com.github.gopherloaf.lemonmod.world.item;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.Item;

public class BlastResistantItem extends Item {
    public BlastResistantItem(Properties p_41383_) {
        super(p_41383_);
    }

    public boolean canBeHurtBy(DamageSource p_41387_) {
        return (super.canBeHurtBy(p_41387_)) && (!p_41387_.is(DamageTypeTags.IS_EXPLOSION));
    }
}
