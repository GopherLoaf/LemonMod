package com.github.gopherloaf.lemonmod.mixin;

import com.github.gopherloaf.lemonmod.world.effect.ModMobEffects;
import com.github.gopherloaf.lemonmod.world.item.ModItems;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class MixinLivingEntity {
    @Shadow protected ItemStack useItem = ItemStack.EMPTY;

    @Unique
    public int getUseDuration(LivingEntity entity, ItemStack item) {
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
                flag |= i <= this.getUseDuration(thisObject, this.useItem) - 7;
                cir.setReturnValue(flag && i % 4 == 0);
            }
        }
    }

    @Inject(method = "getTicksUsingItem", at = @At("HEAD"), cancellable = true)
    public void getTicksUsingItem(CallbackInfoReturnable<Integer> cir) {
        LivingEntity thisObject = (LivingEntity) (Object) this;
        if (thisObject.hasEffect(ModMobEffects.SOUR.get()) && this.useItem.isEdible()){
            if (!(thisObject instanceof Player player) || player.canEat(this.useItem.getFoodProperties(player).canAlwaysEat())){
                cir.setReturnValue(thisObject.isUsingItem() ? this.getUseDuration(thisObject, this.useItem) - thisObject.getUseItemRemainingTicks() : 0);
            }
        }
    }

    @ModifyVariable(method = "getDamageAfterMagicAbsorb", at = @At("HEAD"), ordinal = 0)
    private float getDamageAfterMagicAbsorb(float p_21194_, DamageSource p_21193_) {
        LivingEntity thisObject = (LivingEntity) (Object) this;
        if (!p_21193_.is(DamageTypeTags.BYPASSES_EFFECTS)){
            for (ItemStack itemstack : thisObject.getArmorSlots()) {
                if (itemstack.is(ModItems.LEMON_LEATHER_HELMET.get()) && p_21193_.is(DamageTypeTags.IS_EXPLOSION)){
                    p_21194_ = p_21194_ * 0.20F;
                }
                if (itemstack.is(ModItems.LEMON_LEATHER_CHESTPLATE.get()) && p_21193_.is(DamageTypeTags.IS_PROJECTILE)){
                    p_21194_ = p_21194_ * 0.20F;
                }
                if (itemstack.is(ModItems.LEMON_LEATHER_BOOTS.get()) && p_21193_.is(DamageTypeTags.IS_FALL)){
                    p_21194_ = p_21194_ * 0.36F;
                }
            }
        }
        return p_21194_;
    }
}
