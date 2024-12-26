package com.github.gopherloaf.lemonmod.mixin;

import com.github.gopherloaf.lemonmod.world.item.ModItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(net.minecraft.world.item.enchantment.ProtectionEnchantment.class)
public class MixinProtectionEnchantment {
    @Inject(method = "getExplosionKnockbackAfterDampener", at = @At(value = "RETURN"), cancellable = true)
    private static void getExplosionKnockbackAfterDampener(LivingEntity p_45136_, double p_45137_, CallbackInfoReturnable<Double> cir) {
        for (ItemStack itemstack : p_45136_.getArmorSlots()) {
            if (itemstack.is(ModItems.LEMON_LEATHER_LEGGINGS.get())){
                cir.setReturnValue(cir.getReturnValueD() * 1.40);
            }
        }
    }

}
