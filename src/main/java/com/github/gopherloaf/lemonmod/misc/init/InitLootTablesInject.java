package com.github.gopherloaf.lemonmod.misc.init;

import com.github.gopherloaf.lemonmod.LemonMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LemonMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class InitLootTablesInject {
    @SubscribeEvent
    public static void InjectLootTables(LootTableLoadEvent evt) {
        String[] lootTables = {
                "minecraft:archaeology/ocean_ruin_cold",
                "minecraft:archaeology/ocean_ruin_warm",
                "minecraft:chests/abandoned_mineshaft",
                "minecraft:chests/ancient_city",
                "minecraft:chests/bastion_hoglin_stable",
                "minecraft:chests/bastion_other",
                "minecraft:chests/bastion_treasure",
                "minecraft:chests/buried_treasure",
                "minecraft:chests/desert_pyramid",
                "minecraft:chests/end_city_treasure",
                "minecraft:chests/ruined_portal",
                "minecraft:chests/shipwreck_supply",
                "minecraft:chests/shipwreck_treasure",
                "minecraft:chests/simple_dungeon",
                "minecraft:chests/spawn_bonus_chest",
                "minecraft:chests/stronghold_corridor",
                "minecraft:chests/stronghold_crossing",
                "minecraft:chests/underwater_ruin_big",
                "minecraft:chests/underwater_ruin_small",
                "minecraft:chests/woodland_mansion"
        };
        for (int i = 0; i < lootTables.length; i++){
            if (evt.getName().toString().equals(lootTables[i])) {
                evt.getTable().addPool(LootPool.lootPool().add(LootTableReference.lootTableReference(new ResourceLocation(LemonMod.MODID, "inject/minecraft/" + evt.getName().toString().substring("minecraft:".length())))).name("lemon_inject_pool").build());
            }
        }
    }
}
