package com.github.gopherloaf.lemonmod.init;

import com.github.gopherloaf.lemonmod.world.item.ModItems;
import com.github.gopherloaf.lemonmod.world.level.block.ModBlocks;
import com.google.common.collect.Maps;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.ComposterBlock;

public class InitListInject {
    private static void compostablesInject(){
        ComposterBlock.COMPOSTABLES.put(ModItems.LEMON.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModItems.LEMON_BAR.get(), 0.85F);
        ComposterBlock.COMPOSTABLES.put(ModItems.LEMON_LEAVES_ITEM.get(), 0.30F);
        ComposterBlock.COMPOSTABLES.put(ModItems.LEMON_SAPLING_ITEM.get(), 0.30F);
    }

    private static void strippablesInject(){
        AxeItem.STRIPPABLES = Maps.newHashMap(AxeItem.STRIPPABLES);
        AxeItem.STRIPPABLES.put(ModBlocks.LEMON_LOG.get(), ModBlocks.STRIPPED_LEMON_LOG.get());
        AxeItem.STRIPPABLES.put(ModBlocks.LEMON_WOOD.get(), ModBlocks.STRIPPED_LEMON_WOOD.get());
    }

    public static void init(){
        compostablesInject();
        strippablesInject();
    }
}