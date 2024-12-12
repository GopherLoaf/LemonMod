package com.github.gopherloaf.lemonmod.init;

import com.github.gopherloaf.lemonmod.LemonMod;
import com.github.gopherloaf.lemonmod.client.renderer.entity.ModBoatRenderer;
import com.github.gopherloaf.lemonmod.world.entity.ModEntityType;
import com.github.gopherloaf.lemonmod.world.entity.vehicle.ModBoat;
import com.github.gopherloaf.lemonmod.world.level.block.ModBlocks;
import com.github.gopherloaf.lemonmod.world.level.block.entity.ModBlockEntityType;
import com.github.gopherloaf.lemonmod.world.level.block.state.properties.ModWoodType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = LemonMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class InitClientEvents {
    @SubscribeEvent
    public static void clientInit(FMLClientSetupEvent event){
        event.enqueueWork(() -> {
            Sheets.addWoodType(ModWoodType.LEMON);
        });
    }

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        for(ModBoat.Type boatType : ModBoat.Type.values()) {
            event.registerLayerDefinition(ModBoatRenderer.createBoatModelName(boatType), BoatModel::createBodyModel);
            event.registerLayerDefinition(ModBoatRenderer.createChestBoatModelName(boatType), ChestBoatModel::createBodyModel);
        }
    }

    @SubscribeEvent
    public static void onRegisterRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntityType.SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityType.HANGING_SIGN.get(), HangingSignRenderer::new);
        event.registerEntityRenderer(ModEntityType.MOD_BOAT.get(), (render) -> new ModBoatRenderer(render, false));
        event.registerEntityRenderer(ModEntityType.MOD_CHEST_BOAT.get(), (render) -> new ModBoatRenderer(render, true));
    }

    @SubscribeEvent
    public static void colorBlock(RegisterColorHandlersEvent.Block event){
        event.register(
                (state, lightReader, pos, color) ->
                        lightReader != null && pos != null ?
                                BiomeColors.getAverageFoliageColor(lightReader, pos) :
                                FoliageColor.getDefaultColor(), ModBlocks.LEMON_LEAVES.get());
    }

    @SubscribeEvent
    public static void colorItem(RegisterColorHandlersEvent.Item event){
        event.register((itemStack, i) -> {
            BlockState blockstate = ((BlockItem)itemStack.getItem()).getBlock().defaultBlockState();
            return event.getBlockColors().getColor(blockstate, null, null, i);
        }, ModBlocks.LEMON_LEAVES.get());
    }
}
