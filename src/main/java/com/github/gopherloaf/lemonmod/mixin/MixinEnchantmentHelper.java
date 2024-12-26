package com.github.gopherloaf.lemonmod.mixin;

import com.github.gopherloaf.lemonmod.world.item.ModItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentHelper.class)
public class MixinEnchantmentHelper {
    @Inject(method = "getSneakingSpeedBonus", at = @At("RETURN"), cancellable = true)
    private static void getSneakingSpeedBonus(LivingEntity p_220303_, CallbackInfoReturnable<Float> cir) {
        for (ItemStack itemstack : p_220303_.getArmorSlots()) {
            if (itemstack.is(ModItems.LEMON_LEATHER_LEGGINGS.get())){
                cir.setReturnValue(cir.getReturnValueF() + 0.25F);
            }
        }
    }
}
