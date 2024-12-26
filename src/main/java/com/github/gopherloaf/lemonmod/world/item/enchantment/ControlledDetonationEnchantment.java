package com.github.gopherloaf.lemonmod.world.item.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class ControlledDetonationEnchantment extends Enchantment {
    public ControlledDetonationEnchantment(Enchantment.Rarity p_45107_, EquipmentSlot... p_45108_) {
        super(p_45107_, ModEnchantmentCategory.LEMON_LAUNCHER, p_45108_);
    }

    public int getMinCost(int p_45223_) {
        return 25;
    }

    public int getMaxCost(int p_45227_) {
        return 50;
    }
}
