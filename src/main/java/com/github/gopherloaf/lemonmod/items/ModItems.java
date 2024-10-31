package com.github.gopherloaf.lemonmod.items;

import com.github.gopherloaf.lemonmod.LemonMod;
import com.github.gopherloaf.lemonmod.blocks.ModBlocks;
import com.github.gopherloaf.lemonmod.items.advanced.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    // Create a Deferred Register to hold Items which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LemonMod.MODID);

    //Creates a new item
    public static final RegistryObject<Item> LEMON_BOAT = ITEMS.register("lemon_boat", () -> new BoatItem(false, net.minecraft.world.entity.vehicle.Boat.Type.BIRCH, (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> LEMON_CHEST_BOAT = ITEMS.register("lemon_chest_boat", () -> new BoatItem(true, net.minecraft.world.entity.vehicle.Boat.Type.BIRCH, (new Item.Properties()).stacksTo(1)));
    // Creates a new food item with the id "examplemod:example_id", nutrition 1 and saturation 2
    public static final RegistryObject<Item> LEMON = ITEMS.register("lemon", () -> new LemonItem(new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(4).saturationMod(4.8f).effect(new MobEffectInstance(MobEffects.BLINDNESS, 40, 0), 1.0F).build())));
    public static final RegistryObject<Item> LEMON_BAR = ITEMS.register("lemon_bar", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(2).saturationMod(0.4f).build())));
    public static final RegistryObject<Item> LEMON_JUICE_BOTTLE = ITEMS.register("lemon_juice_bottle", () -> new LemonJuiceBottleItem(new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEat().nutrition(2).saturationMod(4.8f).effect(new MobEffectInstance(MobEffects.BLINDNESS, 40, 0), 1.0F).build()).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
    public static final RegistryObject<Item> LEMONADE_BOTTLE = ITEMS.register("lemonade_bottle", () -> new LemonadeBottleItem(new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEat().nutrition(2).saturationMod(0.8f).build()).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
    public static final RegistryObject<Item> INDIRECTLY_COMBUSTIBLE_LEMON = ITEMS.register("indirectly_combustible_lemon", () -> new IndirectlyCombustibleLemonItem(new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(4).saturationMod(4.8f).effect(new MobEffectInstance(MobEffects.BLINDNESS, 40, 0), 1.0F).build())));
    // Creates a new BlockItem with the id "examplemod:example_block", combining the namespace and path
    public static final RegistryObject<Item> LEMON_PLANKS_ITEM = ITEMS.register("lemon_planks", () -> new BlockItem(ModBlocks.LEMON_PLANKS.get(), new Item.Properties()));
    public static final RegistryObject<Item> LEMON_SAPLING_ITEM = ITEMS.register("lemon_sapling", () -> new BlockItem(ModBlocks.LEMON_SAPLING.get(), new Item.Properties()));
    public static final RegistryObject<Item> LEMON_LOG_ITEM = ITEMS.register("lemon_log", () -> new BlockItem(ModBlocks.LEMON_LOG.get(), new Item.Properties()));
    public static final RegistryObject<Item> STRIPPED_LEMON_LOG_ITEM = ITEMS.register("stripped_lemon_log", () -> new BlockItem(ModBlocks.STRIPPED_LEMON_LOG.get(), new Item.Properties()));
    public static final RegistryObject<Item> LEMON_WOOD_ITEM = ITEMS.register("lemon_wood", () -> new BlockItem(ModBlocks.LEMON_WOOD.get(), new Item.Properties()));
    public static final RegistryObject<Item> STRIPPED_LEMON_WOOD_ITEM = ITEMS.register("stripped_lemon_wood", () -> new BlockItem(ModBlocks.STRIPPED_LEMON_WOOD.get(), new Item.Properties()));
    public static final RegistryObject<Item> LEMON_LEAVES_ITEM = ITEMS.register("lemon_leaves", () -> new BlockItem(ModBlocks.LEMON_LEAVES.get(), new Item.Properties()));
    public static final RegistryObject<Item> LEMON_SIGN_ITEM = ITEMS.register("lemon_sign", () -> new BlockItem(ModBlocks.LEMON_PLANKS.get(), new Item.Properties()));
    public static final RegistryObject<Item> LEMON_HANGING_SIGN_ITEM = ITEMS.register("lemon_hanging_sign", () -> new BlockItem(ModBlocks.LEMON_SAPLING.get(), new Item.Properties()));
    public static final RegistryObject<Item> LEMON_PRESSURE_PLATE_ITEM = ITEMS.register("lemon_pressure_plate", () -> new BlockItem(ModBlocks.LEMON_LOG.get(), new Item.Properties()));
    public static final RegistryObject<Item> LEMON_TRAPDOOR_ITEM = ITEMS.register("lemon_trapdoor", () -> new BlockItem(ModBlocks.STRIPPED_LEMON_LOG.get(), new Item.Properties()));
    public static final RegistryObject<Item> LEMON_STAIRS_ITEM = ITEMS.register("lemon_stairs", () -> new BlockItem(ModBlocks.LEMON_WOOD.get(), new Item.Properties()));
    public static final RegistryObject<Item> LEMON_BUTTON_ITEM = ITEMS.register("lemon_button", () -> new BlockItem(ModBlocks.LEMON_PLANKS.get(), new Item.Properties()));
    public static final RegistryObject<Item> LEMON_SLAB_ITEM = ITEMS.register("lemon_slab", () -> new BlockItem(ModBlocks.LEMON_SAPLING.get(), new Item.Properties()));
    public static final RegistryObject<Item> LEMON_FENCE_GATE_ITEM = ITEMS.register("lemon_fence_gate", () -> new BlockItem(ModBlocks.LEMON_LOG.get(), new Item.Properties()));
    public static final RegistryObject<Item> LEMON_FENCE_ITEM = ITEMS.register("lemon_fence", () -> new BlockItem(ModBlocks.STRIPPED_LEMON_LOG.get(), new Item.Properties()));
    public static final RegistryObject<Item> LEMON_DOOR_ITEM = ITEMS.register("lemon_door", () -> new BlockItem(ModBlocks.LEMON_WOOD.get(), new Item.Properties()));

    public static void register(){
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
