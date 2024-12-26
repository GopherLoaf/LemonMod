package com.github.gopherloaf.lemonmod.world.item;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class LemonJuiceBottleItem extends BottleFoodItem {
    public LemonJuiceBottleItem(Properties p_41383_) {
        super(p_41383_);
    }

    public ItemStack finishUsingItem(ItemStack p_41348_, Level p_41349_, LivingEntity p_41350_) {
        if (!p_41349_.isClientSide) {
            p_41350_.removeEffect(MobEffects.HUNGER);
        }

        return super.finishUsingItem(p_41348_, p_41349_, p_41350_);
    }
}