package com.github.gopherloaf.lemonmod.mixin;

import com.github.gopherloaf.lemonmod.world.level.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WalkNodeEvaluator.class)
public class MixinWalkNodeEvaluator {
    @Inject(method = "checkNeighbourBlocks", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos$MutableBlockPos;set(III)Lnet/minecraft/core/BlockPos$MutableBlockPos;", shift = At.Shift.AFTER), cancellable = true)
    private static void checkNeighbourBlocks(BlockGetter p_77608_, BlockPos.MutableBlockPos p_77609_, BlockPathTypes p_77610_, CallbackInfoReturnable<BlockPathTypes> cir) {
        BlockState blockstate = p_77608_.getBlockState(p_77609_);
        if (blockstate.is(ModBlocks.PINEAPPLE_BUSH.get())) {
            cir.setReturnValue(BlockPathTypes.DANGER_OTHER);
        }
    }

    @Inject(method = "getBlockPathTypeRaw", at = @At("HEAD"), cancellable = true)
    private static void getBlockPathTypeRaw(BlockGetter p_77644_, BlockPos p_77645_, CallbackInfoReturnable<BlockPathTypes> cir){
        BlockState blockstate = p_77644_.getBlockState(p_77645_);
        BlockPathTypes type = blockstate.getBlockPathType(p_77644_, p_77645_, (Mob)null);
        if (type != null) {
            cir.setReturnValue(type);
        } else {
            if (blockstate.is(ModBlocks.PINEAPPLE_BUSH.get())) {
                cir.setReturnValue(BlockPathTypes.DAMAGE_OTHER);
            }
        }
    }
}
