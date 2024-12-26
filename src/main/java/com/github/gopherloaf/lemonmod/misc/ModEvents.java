package com.github.gopherloaf.lemonmod.misc;

import com.github.gopherloaf.lemonmod.LemonMod;
import com.github.gopherloaf.lemonmod.misc.util.TradeUtil;
import com.github.gopherloaf.lemonmod.world.item.ModItems;
import com.github.gopherloaf.lemonmod.world.level.block.ModBlocks;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = LemonMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {
    @SubscribeEvent
    public static void addVillagerTrade(VillagerTradesEvent event){
        TradeUtil.addVillagerTrades(event, VillagerProfession.FARMER, 2, new TradeUtil.ItemsForEmeralds(ModItems.LEMON.get(), 4, 1, 16, 5));
        TradeUtil.addVillagerTrades(event, VillagerProfession.FARMER, 3, new TradeUtil.ItemsForEmeralds(ModItems.LEMON_BAR.get(), 9, 3, 12, 10));
        TradeUtil.addVillagerTrades(event, VillagerProfession.FARMER, 3, new TradeUtil.EmeraldsForItems(ModItems.PINEAPPLE.get(), 1, 12, 12, 20));
        TradeUtil.addVillagerTrades(event, VillagerProfession.FARMER, 5, new TradeUtil.ItemsForEmeralds(ModItems.GLISTERING_PINEAPPLE.get(), 3, 2, 12, 30));
        TradeUtil.addVillagerTrades(event, VillagerProfession.FARMER, 5, new TradeUtil.EmeraldsForItems(ModBlocks.BIG_LEMON.get(), 1, 1, 12, 60));
    }

    @SubscribeEvent
    public static void addWanderTrade(WandererTradesEvent event){
        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
        List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();
        genericTrades.add(new TradeUtil.ItemsForEmeralds(ModBlocks.LEMON_SAPLING.get(), 1, 5, 8, 1));
        rareTrades.add(new TradeUtil.ItemsForEmeralds(ModBlocks.BIG_LEMON.get(), 1, 5, 5, 1));
    }
}
