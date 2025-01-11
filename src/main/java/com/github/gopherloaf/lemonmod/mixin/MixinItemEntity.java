package com.github.gopherloaf.lemonmod.mixin;

import com.github.gopherloaf.lemonmod.world.item.ModItems;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.item.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEntity.class)
public class MixinItemEntity {
    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    public void hurt (DamageSource p_32013_, float p_32014_, CallbackInfoReturnable<Boolean> cir) {
        ItemEntity thisObject = (ItemEntity) (Object) this;
        if (thisObject.isInvulnerableTo(p_32013_)) {
            cir.setReturnValue(false);
        } else if (!thisObject.getItem().isEmpty() && (thisObject.getItem().is(ModItems.LEMON_RIND.get()) || thisObject.getItem().is(ModItems.LEMON_LEATHER.get()) || thisObject.getItem().is(ModItems.LEMON_LEATHER_HELMET.get()) || thisObject.getItem().is(ModItems.LEMON_LEATHER_CHESTPLATE.get()) || thisObject.getItem().is(ModItems.LEMON_LEATHER_LEGGINGS.get()) || thisObject.getItem().is(ModItems.LEMON_LEATHER_BOOTS.get()) || thisObject.getItem().is(ModItems.LEMON_LAUNCHER.get()) || thisObject.getItem().is(ModItems.BIG_LEMON_ITEM.get()) || thisObject.getItem().is(ModItems.HOLLOWED_BIG_LEMON_ITEM.get()) || thisObject.getItem().is(ModItems.BIG_LEMON_LANTERN_ITEM.get())) && p_32013_.is(DamageTypeTags.IS_EXPLOSION)) {
            cir.setReturnValue(false);
        }
    }
}
