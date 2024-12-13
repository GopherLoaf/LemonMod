package com.github.gopherloaf.lemonmod.mixin;

import com.github.gopherloaf.lemonmod.world.effect.ModMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(LivingEntity.class)
public class MixinLivingEntity {
    @Shadow protected ItemStack useItem = ItemStack.EMPTY;

    public int getUseDuration(LivingEntity entity, ItemStack item, int duration) {
        return (int) (item.getUseDuration() * (1.15 + (0.15 * entity.getEffect(ModMobEffects.SOUR.get()).getAmplifier())));
    }

    @Inject(method = "shouldTriggerItemUseEffects", at = @At("HEAD"), cancellable = true)
    private void shouldTriggerItemUseEffects(CallbackInfoReturnable<Boolean> cir) {
        LivingEntity thisObject = (LivingEntity) (Object) this;
        if (thisObject.hasEffect(ModMobEffects.SOUR.get()) && this.useItem.isEdible()){
            if (!(thisObject instanceof Player player) || player.canEat(this.useItem.getFoodProperties(player).canAlwaysEat())){
                int i = thisObject.getUseItemRemainingTicks();
                FoodProperties foodproperties = this.useItem.getFoodProperties(thisObject);
                boolean flag = foodproperties != null && foodproperties.isFastFood();
                flag |= i <= this.getUseDuration(thisObject, this.useItem, this.useItem.getUseDuration()) - 7;
                cir.setReturnValue(flag && i % 4 == 0);
            }
        }
    }

    @Inject(method = "getTicksUsingItem", at = @At("HEAD"), cancellable = true)
    public void getTicksUsingItem(CallbackInfoReturnable<Integer> cir) {
        LivingEntity thisObject = (LivingEntity) (Object) this;
        if (thisObject.hasEffect(ModMobEffects.SOUR.get()) && this.useItem.isEdible()){
            if (!(thisObject instanceof Player player) || player.canEat(this.useItem.getFoodProperties(player).canAlwaysEat())){
                cir.setReturnValue(thisObject.isUsingItem() ? this.getUseDuration(thisObject, this.useItem, this.useItem.getUseDuration()) - thisObject.getUseItemRemainingTicks() : 0);
            }
        }
    }
}
