package com.github.gopherloaf.lemonmod.world.item.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class LaunchEnchantment extends Enchantment {
    public LaunchEnchantment(Rarity p_45107_, EquipmentSlot... p_45108_) {
        super(p_45107_, ModEnchantmentCategory.LEMON_LAUNCHER, p_45108_);
    }

    public int getMinCost(int p_44598_) {
        return 12 + (p_44598_ - 1) * 20;
    }

    public int getMaxCost(int p_44600_) {
        return this.getMinCost(p_44600_) + 25;
    }

    public int getMaxLevel() {
        return 2;
    }
}
