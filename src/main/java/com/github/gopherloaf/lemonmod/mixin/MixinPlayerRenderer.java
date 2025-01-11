package com.github.gopherloaf.lemonmod.mixin;

import com.github.gopherloaf.lemonmod.world.item.LemonLauncherItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerRenderer.class)
public class MixinPlayerRenderer {
    @Inject(method = "getArmPose", at = @At("HEAD"), cancellable = true)
    private static void getArmPose(AbstractClientPlayer p_117795_, InteractionHand p_117796_, CallbackInfoReturnable<HumanoidModel.ArmPose> cir){
        ItemStack itemstack = p_117795_.getItemInHand(p_117796_);
        if (itemstack.isEmpty()) {
        } else {
            if (p_117795_.getUsedItemHand() == p_117796_ && p_117795_.getUseItemRemainingTicks() > 0) {
                UseAnim useanim = itemstack.getUseAnimation();
                if (itemstack.getItem() instanceof LemonLauncherItem){
                    cir.setReturnValue(HumanoidModel.ArmPose.THROW_SPEAR);
                }
            } else if (!p_117795_.swinging && itemstack.getItem() instanceof LemonLauncherItem && LemonLauncherItem.isCharged(itemstack)) {
                cir.setReturnValue(HumanoidModel.ArmPose.CROSSBOW_HOLD);
            }
        }
    }
}
