package com.github.gopherloaf.lemonmod.init;

import com.github.gopherloaf.lemonmod.items.ModItems;
import net.minecraft.world.level.block.ComposterBlock;

public class LemonListInject {
    private static void compostablesInject(){
        ComposterBlock.COMPOSTABLES.put(ModItems.LEMON.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModItems.LEMON_BAR.get(), 0.85F);
    }

    public static void init(){
        compostablesInject();
    }
}