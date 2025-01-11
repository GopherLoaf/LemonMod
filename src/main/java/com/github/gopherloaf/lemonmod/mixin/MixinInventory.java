package com.github.gopherloaf.lemonmod.mixin;

import com.github.gopherloaf.lemonmod.world.item.ModArmorItem;
import com.github.gopherloaf.lemonmod.world.item.ModArmorMaterials;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Consumer;

@Mixin(Inventory.class)
public class MixinInventory {
    @Redirect(method = "hurtArmor", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;hurtAndBreak(ILnet/minecraft/world/entity/LivingEntity;Ljava/util/function/Consumer;)V"))
    public <T extends LivingEntity> void hurtArmor(ItemStack itemstack, int i, T p_41623_, Consumer<T> p_41624_, DamageSource p_150073_, float p_150074_, int[] p_150075_){
        if ((!p_150073_.is(DamageTypeTags.IS_EXPLOSION) || !(itemstack.getItem() instanceof ModArmorItem modArmorItem && modArmorItem.getMaterial() == ModArmorMaterials.LEMON_LEATHER))) {
            itemstack.hurtAndBreak((int)i, p_41623_, p_41624_);
        }
    }
}
