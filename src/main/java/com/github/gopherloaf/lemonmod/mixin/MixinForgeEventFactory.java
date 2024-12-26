package com.github.gopherloaf.lemonmod.mixin;

import com.github.gopherloaf.lemonmod.world.effect.ModMobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.ForgeEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ForgeEventFactory.class)
public class MixinForgeEventFactory {
    @ModifyVariable(method = "onItemUseStart", at = @At("HEAD"), ordinal = 0, remap = false)
    private static int onItemUseStart(int duration, LivingEntity entity, ItemStack item){
        if (entity.hasEffect(ModMobEffects.SOUR.get()) && item.isEdible()){
            if (!(entity instanceof Player player) || player.canEat(item.getFoodProperties(player).canAlwaysEat())){
                duration = (int) (duration * (1.15 + (0.15 * entity.getEffect(ModMobEffects.SOUR.get()).getAmplifier())));
            }
        }
        return duration;
    }
}
