package com.github.gopherloaf.lemonmod.world.item;

import com.github.gopherloaf.lemonmod.world.damagesource.ModDamageTypes;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public class PineappleBlockItem extends ItemNameBlockItem {
    public PineappleBlockItem(Block p_41579_, Item.Properties p_41580_) {
        super(p_41579_, p_41580_);
    }

    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack p_41348_, @NotNull Level p_41349_, @NotNull LivingEntity p_41350_) {
        super.finishUsingItem(p_41348_, p_41349_, p_41350_);
        if (p_41350_ instanceof ServerPlayer $$3) {
            CriteriaTriggers.CONSUME_ITEM.trigger($$3, p_41348_);
            $$3.awardStat(Stats.ITEM_USED.get(this));
        }

        if (!p_41349_.isClientSide) {
            p_41350_.hurt(p_41349_.damageSources().source(ModDamageTypes.DIGESTION), 1.0F);
        }

        return p_41348_;
    }
}
