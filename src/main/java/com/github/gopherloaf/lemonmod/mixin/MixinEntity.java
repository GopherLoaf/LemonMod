package com.github.gopherloaf.lemonmod.mixin;

import com.github.gopherloaf.lemonmod.world.item.ModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class MixinEntity {
    @Inject(method = "makeStuckInBlock", at = @At("RETURN"), cancellable = true)
    public void makeStuckInBlock(BlockState p_20006_, Vec3 p_20007_, CallbackInfo ci) {
        Entity thisObject = (Entity) (Object) this;
        for (ItemStack itemstack : thisObject.getArmorSlots()) {
            if (itemstack.is(ModItems.LEMON_LEATHER_LEGGINGS.get())){
                thisObject.stuckSpeedMultiplier = new Vec3(0.0, 0.0, 0.0);
            }
        }
    }
}
