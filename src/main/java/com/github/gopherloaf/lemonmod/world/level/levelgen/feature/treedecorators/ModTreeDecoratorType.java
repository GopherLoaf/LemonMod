package com.github.gopherloaf.lemonmod.world.level.levelgen.feature.treedecorators;

import com.github.gopherloaf.lemonmod.LemonMod;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModTreeDecoratorType<P extends TreeDecorator> extends TreeDecoratorType {
    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATOR_TYPES = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, LemonMod.MODID);

    public static final RegistryObject<TreeDecoratorType<BigLemonDecorator>> BIG_LEMON = TREE_DECORATOR_TYPES.register("big_lemon", () -> new TreeDecoratorType<>(BigLemonDecorator.CODEC));

    public ModTreeDecoratorType(Codec p_70050_) {
        super(p_70050_);
    }

    public static void register(){
        // Register the Deferred Register to the mod event bus so item get registered
        TREE_DECORATOR_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
