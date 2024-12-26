package com.github.gopherloaf.lemonmod.world.item.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jetbrains.annotations.NotNull;

public class MultiLemonLauncherEnchantment extends Enchantment {
    public MultiLemonLauncherEnchantment(Enchantment.Rarity p_45107_, EquipmentSlot... p_45108_) {
        super(p_45107_, ModEnchantmentCategory.LEMON_LAUNCHER, p_45108_);
    }

    public int getMinCost(int p_45102_) {
        return p_45102_ * 25;
    }

    public int getMaxCost(int p_45105_) {
        return this.getMinCost(p_45105_) + 50;
    }

    public boolean isTreasureOnly() {
        return true;
    }

    public boolean isTradeable() {
        return false;
    }

    public boolean isDiscoverable() {
        return false;
    }

    public boolean checkCompatibility(@NotNull Enchantment p_44608_) {
        return super.checkCompatibility(p_44608_) && p_44608_ != ModEnchantments.BAZOOKA.get();
    }
}
