package com.github.gopherloaf.lemonmod.world.item;

import com.github.gopherloaf.lemonmod.LemonMod;
import com.github.gopherloaf.lemonmod.world.effect.ModMobEffects;
import com.github.gopherloaf.lemonmod.world.entity.vehicle.ModBoat;
import com.github.gopherloaf.lemonmod.world.level.block.ModBlocks;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    // Create a Deferred Register to hold Items which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LemonMod.MODID);

    //Creates a new item
    public static final RegistryObject<Item> LEMON_BOAT = ITEMS.register("lemon_boat", () -> new ModBoatItem(false, ModBoat.Type.LEMON, (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> LEMON_CHEST_BOAT = ITEMS.register("lemon_chest_boat", () -> new ModBoatItem(true, ModBoat.Type.LEMON, (new Item.Properties()).stacksTo(1)));
    // Creates a new food item with the id "examplemod:example_id", nutrition 1 and saturation 2
    public static final RegistryObject<Item> LEMON = ITEMS.register("lemon", () -> new LemonItem(new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(4).saturationMod(9.6f).effect(new MobEffectInstance(MobEffects.BLINDNESS, 40, 0), 1.0F).effect(new MobEffectInstance(ModMobEffects.SOUR.get(), 600, 3), 1.0F).build())));
    public static final RegistryObject<Item> LEMON_BAR = ITEMS.register("lemon_bar", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(2).saturationMod(0.8f).build())));
    public static final RegistryObject<Item> LEMON_JUICE_BOTTLE = ITEMS.register("lemon_juice_bottle", () -> new LemonJuiceBottleItem(new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEat().nutrition(2).saturationMod(9.6f).effect(new MobEffectInstance(MobEffects.BLINDNESS, 80, 0), 1.0F).effect(new MobEffectInstance(ModMobEffects.SOUR.get(), 1200, 4), 1.0F).build()).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
    public static final RegistryObject<Item> LEMONADE_BOTTLE = ITEMS.register("lemonade_bottle", () -> new LemonadeBottleItem(new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEat().nutrition(2).saturationMod(1.6f).effect(new MobEffectInstance(ModMobEffects.SOUR.get(), 60, 0), 1.0F).build()).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
    public static final RegistryObject<Item> INDIRECTLY_COMBUSTIBLE_LEMON = ITEMS.register("indirectly_combustible_lemon", () -> new IndirectlyCombustibleLemonItem(new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(4).saturationMod(9.6f).effect(new MobEffectInstance(MobEffects.BLINDNESS, 40, 0), 1.0F).effect(new MobEffectInstance(ModMobEffects.SOUR.get(), 600, 3), 1.0F).effect(new MobEffectInstance(MobEffects.HUNGER, 300, 2), 1.0F).effect(new MobEffectInstance(MobEffects.CONFUSION, 300, 0), 1.0F).effect(new MobEffectInstance(MobEffects.POISON, 1200, 1), 1.0F).build())));
    // Creates a new BlockItem with the id "examplemod:example_block", combining the namespace and path
    public static final RegistryObject<Item> LEMON_PLANKS_ITEM = ITEMS.register("lemon_planks", () -> new BlockItem(ModBlocks.LEMON_PLANKS.get(), new Item.Properties()));
    public static final RegistryObject<Item> LEMON_SAPLING_ITEM = ITEMS.register("lemon_sapling", () -> new BlockItem(ModBlocks.LEMON_SAPLING.get(), new Item.Properties()));
    public static final RegistryObject<Item> LEMON_LOG_ITEM = ITEMS.register("lemon_log", () -> new BlockItem(ModBlocks.LEMON_LOG.get(), new Item.Properties()));
    public static final RegistryObject<Item> STRIPPED_LEMON_LOG_ITEM = ITEMS.register("stripped_lemon_log", () -> new BlockItem(ModBlocks.STRIPPED_LEMON_LOG.get(), new Item.Properties()));
    public static final RegistryObject<Item> LEMON_WOOD_ITEM = ITEMS.register("lemon_wood", () -> new BlockItem(ModBlocks.LEMON_WOOD.get(), new Item.Properties()));
    public static final RegistryObject<Item> STRIPPED_LEMON_WOOD_ITEM = ITEMS.register("stripped_lemon_wood", () -> new BlockItem(ModBlocks.STRIPPED_LEMON_WOOD.get(), new Item.Properties()));
    public static final RegistryObject<Item> LEMON_LEAVES_ITEM = ITEMS.register("lemon_leaves", () -> new BlockItem(ModBlocks.LEMON_LEAVES.get(), new Item.Properties()));
    public static final RegistryObject<Item> LEMON_SIGN_ITEM = ITEMS.register("lemon_sign", () -> new SignItem((new Item.Properties()).stacksTo(16), ModBlocks.LEMON_SIGN.get(), ModBlocks.LEMON_WALL_SIGN.get()));
    public static final RegistryObject<Item> LEMON_HANGING_SIGN_ITEM = ITEMS.register("lemon_hanging_sign", () -> new HangingSignItem(ModBlocks.LEMON_HANGING_SIGN.get(), ModBlocks.LEMON_WALL_HANGING_SIGN.get(), (new Item.Properties()).stacksTo(16)));
    public static final RegistryObject<Item> LEMON_PRESSURE_PLATE_ITEM = ITEMS.register("lemon_pressure_plate", () -> new BlockItem(ModBlocks.LEMON_PRESSURE_PLATE.get(), new Item.Properties()));
    public static final RegistryObject<Item> LEMON_TRAPDOOR_ITEM = ITEMS.register("lemon_trapdoor", () -> new BlockItem(ModBlocks.LEMON_TRAPDOOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> LEMON_STAIRS_ITEM = ITEMS.register("lemon_stairs", () -> new BlockItem(ModBlocks.LEMON_STAIRS.get(), new Item.Properties()));
    public static final RegistryObject<Item> LEMON_BUTTON_ITEM = ITEMS.register("lemon_button", () -> new BlockItem(ModBlocks.LEMON_BUTTON.get(), new Item.Properties()));
    public static final RegistryObject<Item> LEMON_SLAB_ITEM = ITEMS.register("lemon_slab", () -> new BlockItem(ModBlocks.LEMON_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<Item> LEMON_FENCE_GATE_ITEM = ITEMS.register("lemon_fence_gate", () -> new BlockItem(ModBlocks.LEMON_FENCE_GATE.get(), new Item.Properties()));
    public static final RegistryObject<Item> LEMON_FENCE_ITEM = ITEMS.register("lemon_fence", () -> new BlockItem(ModBlocks.LEMON_FENCE.get(), new Item.Properties()));
    public static final RegistryObject<Item> LEMON_DOOR_ITEM = ITEMS.register("lemon_door", () -> (BlockItem) (new DoubleHighBlockItem(ModBlocks.LEMON_DOOR.get(), new Item.Properties())));

    public static void register(){
        // Register the Deferred Register to the mod event bus so item get registered
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
