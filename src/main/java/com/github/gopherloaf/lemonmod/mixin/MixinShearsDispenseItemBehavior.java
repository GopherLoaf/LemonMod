package com.github.gopherloaf.lemonmod.mixin;

import com.github.gopherloaf.lemonmod.sounds.ModSoundEvents;
import com.github.gopherloaf.lemonmod.world.level.block.ModBlocks;
import com.github.gopherloaf.lemonmod.world.level.block.PineappleBushBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.ShearsDispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShearsDispenseItemBehavior.class)
public class MixinShearsDispenseItemBehavior {
    @Unique
    private static boolean tryShearPineappleBush(ServerLevel p_123577_, BlockPos p_123578_){
        BlockState $$2 = p_123577_.getBlockState(p_123578_);
        if ($$2.is(ModBlocks.PINEAPPLE_BUSH.get())) {
            int $$3 = (Integer)$$2.getValue(PineappleBushBlock.AGE);
            if ($$3 >= PineappleBushBlock.MAX_AGE) {
                p_123577_.playSound((Player)null, p_123578_, ModSoundEvents.PINEAPPLE_BUSH_SHEAR.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                PineappleBushBlock.dropPineapple(p_123577_, p_123578_);
                BlockState blockstate = $$2.setValue(PineappleBushBlock.AGE, Integer.valueOf(1));
                p_123577_.setBlock(p_123578_, blockstate, 2);
                p_123577_.gameEvent(GameEvent.BLOCK_CHANGE, p_123578_, GameEvent.Context.of((Entity)null, blockstate));
                p_123577_.gameEvent((Entity)null, GameEvent.SHEAR, p_123578_);
                return true;
            }
        }

        return false;
    }

    @Inject(method = "execute", at = @At("HEAD"), cancellable = true)
    protected void execute(BlockSource p_123580_, ItemStack p_123581_, CallbackInfoReturnable<ItemStack> cir){
        ShearsDispenseItemBehavior thisObject = (ShearsDispenseItemBehavior) (Object) this;
        ServerLevel $$2 = p_123580_.getLevel();
        if (!$$2.isClientSide()) {
            BlockPos $$3 = p_123580_.getPos().relative((Direction)p_123580_.getBlockState().getValue(DispenserBlock.FACING));
            thisObject.setSuccess(tryShearPineappleBush($$2, $$3));
            if (thisObject.isSuccess()){
                if (p_123581_.hurt(1, $$2.getRandom(), (ServerPlayer)null)) {
                    p_123581_.setCount(0);
                }
                cir.setReturnValue(p_123581_);
            }
        }
    }
}
