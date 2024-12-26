package com.github.gopherloaf.lemonmod.world.item.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class SafeguardEnchantment extends Enchantment {
    public SafeguardEnchantment(Rarity p_45107_, EquipmentSlot... p_45108_) {
        super(p_45107_, ModEnchantmentCategory.LEMON_LAUNCHER, p_45108_);
    }

    public int getMinCost(int p_45244_) {
        return 5 + p_45244_ * 7;
    }

    public int getMaxCost(int p_45248_) {
        return 50;
    }

    public int getMaxLevel() {
        return 4;
    }
}
