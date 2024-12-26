package com.github.gopherloaf.lemonmod.world.item.enchantment;

import com.github.gopherloaf.lemonmod.LemonMod;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, LemonMod.MODID);

    public static final RegistryObject<Enchantment> MULTI_LEMON_LAUNCHER = ENCHANTMENTS.register("multi_lemon_launcher", () -> new MultiLemonLauncherEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND));
    public static final RegistryObject<Enchantment> BAZOOKA = ENCHANTMENTS.register("bazooka", () -> new BazookaEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND));
    public static final RegistryObject<Enchantment> LAUNCH = ENCHANTMENTS.register("launch", () -> new LaunchEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    public static final RegistryObject<Enchantment> SAFEGUARD = ENCHANTMENTS.register("safeguard", () -> new SafeguardEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlot.MAINHAND));
    public static final RegistryObject<Enchantment> CONTROLLED_DETONATION = ENCHANTMENTS.register("controlled_detonation", () -> new ControlledDetonationEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND));

    public static void register(){
        // Register the Deferred Register to the mod event bus so item get registered
        ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
