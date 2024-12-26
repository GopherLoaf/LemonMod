package com.github.gopherloaf.lemonmod.misc.util;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.event.village.VillagerTradesEvent;
import org.jetbrains.annotations.NotNull;

public class TradeUtil {
    public static void addVillagerTrades(VillagerTradesEvent event, int profLevel, VillagerTrades.ItemListing... trades) {
        for (VillagerTrades.ItemListing trade : trades) {
            event.getTrades().get(profLevel).add(trade);
        }
    }

    public static void addVillagerTrades(VillagerTradesEvent event, VillagerProfession profession, int profLevel, VillagerTrades.ItemListing... trades) {
        if (event.getType().equals(profession)) {
            addVillagerTrades(event, profLevel, trades);
        }
    }
    /**
     * To Here.
     */

    public static class ItemsForEmeralds implements VillagerTrades.ItemListing {
        private final Item item;
        private final int amount;
        private final int cost;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public ItemsForEmeralds(ItemLike item, int amount, int cost, int maxUses, int villagerXp) {
            this.item = item.asItem();
            this.amount = amount;
            this.cost = cost;
            this.maxUses = maxUses;
            this.villagerXp = villagerXp;
            this.priceMultiplier = 0.05F;
        }

        public MerchantOffer getOffer(@NotNull Entity p_219682_, @NotNull RandomSource p_219683_) {
            ItemStack itemstack = new ItemStack(this.item, this.amount);
            return new MerchantOffer(new ItemStack(Items.EMERALD, this.cost), itemstack, this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    public static class EmeraldsForItems implements VillagerTrades.ItemListing {
        private final Item item;
        private final int amount;
        private final int cost;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public EmeraldsForItems(ItemLike item, int amount, int cost, int maxUses, int villagerXp) {
            this.item = item.asItem();
            this.amount = amount;
            this.cost = cost;
            this.maxUses = maxUses;
            this.villagerXp = villagerXp;
            this.priceMultiplier = 0.05F;
        }

        public MerchantOffer getOffer(@NotNull Entity p_219682_, @NotNull RandomSource p_219683_) {
            ItemStack itemstack = new ItemStack(this.item, this.cost);
            return new MerchantOffer(itemstack, new ItemStack(Items.EMERALD, this.amount), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }
}
