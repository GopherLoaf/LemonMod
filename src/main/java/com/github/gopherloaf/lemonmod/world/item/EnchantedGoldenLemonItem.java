package com.github.gopherloaf.lemonmod.world.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class EnchantedGoldenLemonItem extends Item {
    public EnchantedGoldenLemonItem(Item.Properties p_41170_) {
        super(p_41170_);
    }

    public boolean isFoil(@NotNull ItemStack p_41172_) {
        return true;
    }
}
