package com.github.gopherloaf.lemonmod.mixin;

import com.github.gopherloaf.lemonmod.world.item.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractHorse.class)
public abstract class MixinAbstractHorse {
    @Shadow protected abstract void eating();

    @Inject(method = "addBehaviourGoals", at = @At("HEAD"), cancellable = true)
    protected void addBehaviourGoals(CallbackInfo ci) {
        AbstractHorse thisObject = (AbstractHorse) (Object) this;
        thisObject.goalSelector.addGoal(3, new TemptGoal(thisObject, 1.25, Ingredient.of(new ItemLike[]{ModItems.GOLDEN_LEMON.get(), ModItems.ENCHANTED_GOLDEN_LEMON.get()}), false));
    }

    @Inject(method = "handleEating", at = @At("HEAD"), cancellable = true)
    protected void handleEating(Player p_30593_, ItemStack p_30594_, CallbackInfoReturnable<Boolean> cir){
        AbstractHorse thisObject = (AbstractHorse) (Object) this;
        if (p_30594_.is(ModItems.LEMON.get()) || p_30594_.is(ModItems.GOLDEN_LEMON.get()) || p_30594_.is(ModItems.ENCHANTED_GOLDEN_LEMON.get())) {
            boolean flag = false;
            float f = 0.0F;
            int i = 0;
            int j = 0;
            if (p_30594_.is(ModItems.LEMON.get())) {
                f = 3.0F;
                i = 60;
                j = 3;
            } else if (p_30594_.is(ModItems.GOLDEN_LEMON.get()) || p_30594_.is(ModItems.ENCHANTED_GOLDEN_LEMON.get())) {
                f = 10.0F;
                i = 240;
                j = 10;
                if (!thisObject.level().isClientSide && thisObject.isTamed() && thisObject.getAge() == 0 && !thisObject.isInLove()) {
                    flag = true;
                    thisObject.setInLove(p_30593_);
                }
            }

            if (thisObject.getHealth() < thisObject.getMaxHealth() && f > 0.0F) {
                thisObject.heal(f);
                flag = true;
            }

            if (thisObject.isBaby() && i > 0) {
                thisObject.level().addParticle(ParticleTypes.HAPPY_VILLAGER, thisObject.getRandomX(1.0), thisObject.getRandomY() + 0.5, thisObject.getRandomZ(1.0), 0.0, 0.0, 0.0);
                if (!thisObject.level().isClientSide) {
                    thisObject.ageUp(i);
                }

                flag = true;
            }

            if (j > 0 && (flag || !thisObject.isTamed()) && thisObject.getTemper() < thisObject.getMaxTemper()) {
                flag = true;
                if (!thisObject.level().isClientSide) {
                    thisObject.modifyTemper(j);
                }
            }

            if (flag) {
                this.eating();
                thisObject.gameEvent(GameEvent.EAT);
            }

            cir.setReturnValue(flag);
        }
    }
}
