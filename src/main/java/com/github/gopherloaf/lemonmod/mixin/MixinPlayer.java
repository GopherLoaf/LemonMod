package com.github.gopherloaf.lemonmod.mixin;

import com.github.gopherloaf.lemonmod.world.item.ModItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class MixinPlayer {
    private void hollowedBigLemonTick() {
        Player thisObject = (Player) (Object) this;
        ItemStack itemstack = thisObject.getItemBySlot(EquipmentSlot.HEAD);
        if (itemstack.is(ModItems.HOLLOWED_BIG_LEMON_ITEM.get()) && thisObject.getFoodData().getSaturationLevel() <= 0) {
            thisObject.addEffect(new MobEffectInstance(MobEffects.SATURATION, 1, 0, false, false, true));
            thisObject.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 20, 0, false, false, true));
        }

    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;turtleHelmetTick()V", shift = At.Shift.AFTER), cancellable = true)
    public void tick(CallbackInfo ci) {
        this.hollowedBigLemonTick();
    }
}
