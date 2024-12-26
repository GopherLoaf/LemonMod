package com.github.gopherloaf.lemonmod.world.item.enchantment;

import com.github.gopherloaf.lemonmod.world.item.ModItems;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ModEnchantmentCategory {
    public static final EnchantmentCategory LEMON_LAUNCHER = EnchantmentCategory.create("lemon_launcher", (item -> item == ModItems.LEMON_LAUNCHER.get()));
}
