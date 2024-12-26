package com.github.gopherloaf.lemonmod.world.item;

import com.github.gopherloaf.lemonmod.sounds.ModSoundEvents;
import com.github.gopherloaf.lemonmod.world.effect.ModMobEffects;
import com.github.gopherloaf.lemonmod.world.level.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class BigLemonBlock extends Block {
    public BigLemonBlock(BlockBehaviour.Properties p_55284_) {
        super(p_55284_);
    }

    public @NotNull InteractionResult use(@NotNull BlockState p_55289_, @NotNull Level p_55290_, @NotNull BlockPos p_55291_, Player p_55292_, @NotNull InteractionHand p_55293_, @NotNull BlockHitResult p_55294_) {
        ItemStack itemstack = p_55292_.getItemInHand(p_55293_);
        if (itemstack.canPerformAction(net.minecraftforge.common.ToolActions.SHEARS_CARVE)) {
            if (!p_55290_.isClientSide) {
                Direction direction = p_55294_.getDirection();
                Direction direction1 = direction.getAxis() == Direction.Axis.Y ? p_55292_.getDirection().getOpposite() : direction;
                p_55290_.playSound((Player)null, p_55291_, ModSoundEvents.BIG_LEMON_HOLLOW.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                p_55290_.setBlock(p_55291_, ModBlocks.HOLLOWED_BIG_LEMON.get().defaultBlockState(), 11);
                p_55292_.getFoodData().eat(12, 19.2F);
                p_55292_.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 80, 0));
                p_55292_.addEffect(new MobEffectInstance(ModMobEffects.SOUR.get(), 800, 4));
                itemstack.hurtAndBreak(1, p_55292_, (p_55287_) -> {
                    p_55287_.broadcastBreakEvent(p_55293_);
                });
                p_55290_.gameEvent(p_55292_, GameEvent.SHEAR, p_55291_);
                p_55292_.awardStat(Stats.ITEM_USED.get(Items.SHEARS));
            }

            return InteractionResult.sidedSuccess(p_55290_.isClientSide);
        } else {
            return super.use(p_55289_, p_55290_, p_55291_, p_55292_, p_55293_, p_55294_);
        }
    }
}
