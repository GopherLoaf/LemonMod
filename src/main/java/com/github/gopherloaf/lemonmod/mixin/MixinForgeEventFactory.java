package com.github.gopherloaf.lemonmod.mixin;

import com.github.gopherloaf.lemonmod.world.effect.ModMobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ForgeEventFactory.class)
public class MixinForgeEventFactory {
    @Inject(method = "onItemUseStart", at = @At("HEAD"), cancellable = true, remap = false)
    private static void onItemUseStart(LivingEntity entity, ItemStack item, int duration, CallbackInfoReturnable<Integer> cir){
        if (entity.hasEffect(ModMobEffects.SOUR.get()) && item.isEdible()){
            if (!(entity instanceof Player player) || player.canEat(item.getFoodProperties(player).canAlwaysEat())){
                LivingEntityUseItemEvent event = new LivingEntityUseItemEvent.Start(entity, item, (int) (duration * (1.15 + (0.15 * entity.getEffect(ModMobEffects.SOUR.get()).getAmplifier()))));
                cir.setReturnValue(MinecraftForge.EVENT_BUS.post(event) ? -1 : event.getDuration());
            }
        }
    }
}
