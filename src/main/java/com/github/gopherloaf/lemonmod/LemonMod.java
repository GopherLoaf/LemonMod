package com.github.gopherloaf.lemonmod;

import com.github.gopherloaf.lemonmod.world.effect.ModMobEffects;
import com.github.gopherloaf.lemonmod.world.level.block.ModBlocks;
import com.github.gopherloaf.lemonmod.crafting.ModRecipeSerializer;
import com.github.gopherloaf.lemonmod.init.InitListInject;
import com.github.gopherloaf.lemonmod.world.item.IndirectlyCombustibleLemonItem;
import com.github.gopherloaf.lemonmod.world.entity.ModEntityType;
import com.github.gopherloaf.lemonmod.world.level.block.entity.ModBlockEntityType;
import com.github.gopherloaf.lemonmod.world.level.block.state.properties.ModWoodType;
import com.google.common.collect.Maps;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import com.github.gopherloaf.lemonmod.world.item.ModItems;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(LemonMod.MODID)
public class LemonMod
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "lemonmod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // Creates a creative tab with the id "examplemod:example_tab" for the example item, that is placed after the combat tab
    public static final RegistryObject<CreativeModeTab> LEMON_TAB = CREATIVE_MODE_TABS.register("lemon_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> ModItems.LEMON.get().getDefaultInstance())
            .title(Component.translatable("creativetab.lemon_tab"))
            .displayItems((parameters, output) -> {
                output.accept(ModItems.LEMON.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
                output.accept(ModItems.LEMON_BAR.get());
                output.accept(ModItems.LEMON_JUICE_BOTTLE.get());
                output.accept(ModItems.LEMONADE_BOTTLE.get());
                output.accept(ModItems.LEMON_BOAT.get());
                output.accept(ModItems.LEMON_CHEST_BOAT.get());
                ItemStack itemstack = new ItemStack(ModItems.INDIRECTLY_COMBUSTIBLE_LEMON.get());
                IndirectlyCombustibleLemonItem.setExplosionSize(itemstack, (byte) 1);
                output.accept(itemstack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                output.accept(ModItems.LEMON_LOG_ITEM.get());
                output.accept(ModItems.LEMON_LEAVES_ITEM.get());
                output.accept(ModItems.LEMON_SAPLING_ITEM.get());
                output.accept(ModItems.LEMON_WOOD_ITEM.get());
                output.accept(ModItems.STRIPPED_LEMON_LOG_ITEM.get());
                output.accept(ModItems.STRIPPED_LEMON_WOOD_ITEM.get());
                output.accept(ModItems.LEMON_PLANKS_ITEM.get());
                output.accept(ModItems.LEMON_STAIRS_ITEM.get());
                output.accept(ModItems.LEMON_SLAB_ITEM.get());
                output.accept(ModItems.LEMON_FENCE_ITEM.get());
                output.accept(ModItems.LEMON_FENCE_GATE_ITEM.get());
                output.accept(ModItems.LEMON_DOOR_ITEM.get());
                output.accept(ModItems.LEMON_TRAPDOOR_ITEM.get());
                output.accept(ModItems.LEMON_PRESSURE_PLATE_ITEM.get());
                output.accept(ModItems.LEMON_BUTTON_ITEM.get());
                output.accept(ModItems.LEMON_SIGN_ITEM.get());
                output.accept(ModItems.LEMON_HANGING_SIGN_ITEM.get());
            }).build());

    public LemonMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so item get registered
        ModItems.register();
        // Register the Deferred Register to the mod event bus so block get registered
        ModBlocks.register();
        ModRecipeSerializer.register();
        ModEntityType.register();
        ModBlockEntityType.register();
        ModMobEffects.register();
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            InitListInject.init();
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.LEMON_SAPLING.getId(), ModBlocks.POTTED_LEMON_SAPLING);
            WoodType.register(ModWoodType.LEMON);
        });

        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
    }

    // Add the example block item to the building loot_tables tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS){
            event.accept(ModItems.LEMON);
            event.accept(ModItems.LEMON_BAR);
            event.accept(ModItems.LEMON_JUICE_BOTTLE);
            event.accept(ModItems.LEMONADE_BOTTLE);
            ItemStack itemstack = new ItemStack(ModItems.INDIRECTLY_COMBUSTIBLE_LEMON.get());
            IndirectlyCombustibleLemonItem.setExplosionSize(itemstack, (byte) 1);
            event.accept(itemstack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS){
            event.accept(ModItems.LEMON_LOG_ITEM);
            event.accept(ModItems.LEMON_LEAVES_ITEM);
            event.accept(ModItems.LEMON_SAPLING_ITEM);
        }
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            event.accept(ModItems.LEMON_LOG_ITEM);
            event.accept(ModItems.LEMON_WOOD_ITEM);
            event.accept(ModItems.STRIPPED_LEMON_LOG_ITEM);
            event.accept(ModItems.STRIPPED_LEMON_WOOD_ITEM);
            event.accept(ModItems.LEMON_PLANKS_ITEM);
            event.accept(ModItems.LEMON_STAIRS_ITEM);
            event.accept(ModItems.LEMON_SLAB_ITEM);
            event.accept(ModItems.LEMON_FENCE_ITEM);
            event.accept(ModItems.LEMON_FENCE_GATE_ITEM);
            event.accept(ModItems.LEMON_DOOR_ITEM);
            event.accept(ModItems.LEMON_TRAPDOOR_ITEM);
            event.accept(ModItems.LEMON_PRESSURE_PLATE_ITEM);
            event.accept(ModItems.LEMON_BUTTON_ITEM);
        }
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS){
            event.accept(ModItems.LEMON_SIGN_ITEM);
            event.accept(ModItems.LEMON_HANGING_SIGN_ITEM);
        }
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES){
            event.accept(ModItems.LEMON_BOAT);
            event.accept(ModItems.LEMON_CHEST_BOAT);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
