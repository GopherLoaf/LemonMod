package com.github.gopherloaf.lemonmod.world.level.block;

import com.github.gopherloaf.lemonmod.sounds.ModSoundEvents;
import com.github.gopherloaf.lemonmod.world.damagesource.ModDamageTypes;
import com.github.gopherloaf.lemonmod.world.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class PineappleBushBlock extends BushBlock implements BonemealableBlock {
    private static final float HURT_SPEED_THRESHOLD = 0.003F;
    public static final int MAX_AGE = 3;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    private static final VoxelShape SAPLING_SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);
    private static final VoxelShape MID_GROWTH_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    public PineappleBushBlock(BlockBehaviour.Properties p_57249_) {
        super(p_57249_);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(0)));
    }

    public @NotNull ItemStack getCloneItemStack(@NotNull BlockGetter p_57256_, @NotNull BlockPos p_57257_, @NotNull BlockState p_57258_) {
        return new ItemStack(ModItems.PINEAPPLE.get());
    }

    public @NotNull VoxelShape getShape(BlockState p_57291_, @NotNull BlockGetter p_57292_, @NotNull BlockPos p_57293_, @NotNull CollisionContext p_57294_) {
        if (p_57291_.getValue(AGE) == 0) {
            return SAPLING_SHAPE;
        } else {
            return p_57291_.getValue(AGE) < 3 ? MID_GROWTH_SHAPE : super.getShape(p_57291_, p_57292_, p_57293_, p_57294_);
        }
    }

    public boolean isRandomlyTicking(BlockState p_57284_) {
        return p_57284_.getValue(AGE) < 3;
    }

    public void randomTick(BlockState p_222563_, @NotNull ServerLevel p_222564_, @NotNull BlockPos p_222565_, @NotNull RandomSource p_222566_) {
        int i = p_222563_.getValue(AGE);
        if (i < 3 && p_222564_.getRawBrightness(p_222565_.above(), 0) >= 9 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(p_222564_, p_222565_, p_222563_, p_222566_.nextInt(5) == 0)) {
            BlockState blockstate = p_222563_.setValue(AGE, Integer.valueOf(i + 1));
            p_222564_.setBlock(p_222565_, blockstate, 2);
            p_222564_.gameEvent(GameEvent.BLOCK_CHANGE, p_222565_, GameEvent.Context.of(blockstate));
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(p_222564_, p_222565_, p_222563_);
        }

    }

    public void entityInside(@NotNull BlockState p_57270_, @NotNull Level p_57271_, @NotNull BlockPos p_57272_, @NotNull Entity p_57273_) {
        if (p_57273_ instanceof LivingEntity && p_57273_.getType() != EntityType.BEE) {
            if (!p_57271_.isClientSide && p_57270_.getValue(AGE) > 0 && (p_57273_.xOld != p_57273_.getX() || p_57273_.zOld != p_57273_.getZ())) {
                double d0 = Math.abs(p_57273_.getX() - p_57273_.xOld);
                double d1 = Math.abs(p_57273_.getZ() - p_57273_.zOld);
                if (d0 >= (double)0.225F || d1 >= (double)0.225F) {
                    p_57273_.hurt(p_57271_.damageSources().source(ModDamageTypes.PINEAPPLE_BUSH), 1.0F);
                }
            }

        }
    }

    public static void dropPineapple(Level p_49601_, BlockPos p_49602_) {
        popResource(p_49601_, p_49602_, new ItemStack(ModItems.PINEAPPLE.get(), 1));
    }

    public @NotNull InteractionResult use(BlockState p_57275_, @NotNull Level p_57276_, @NotNull BlockPos p_57277_, Player p_57278_, @NotNull InteractionHand p_57279_, @NotNull BlockHitResult p_57280_) {
        int i = p_57275_.getValue(AGE);
        boolean flag = i == 3;
        ItemStack itemstack = p_57278_.getItemInHand(p_57279_);
        if (!flag && itemstack.is(Items.BONE_MEAL)) {
            return InteractionResult.PASS;
        } else if (itemstack.canPerformAction(net.minecraftforge.common.ToolActions.SHEARS_HARVEST) && i > 2) {
            dropPineapple(p_57276_, p_57277_);
            p_57276_.playSound((Player)null, p_57277_, ModSoundEvents.PINEAPPLE_BUSH_SHEAR.get(), SoundSource.BLOCKS, 1.0F, 0.8F + p_57276_.random.nextFloat() * 0.4F);
            BlockState blockstate = p_57275_.setValue(AGE, Integer.valueOf(1));
            p_57276_.setBlock(p_57277_, blockstate, 2);
            p_57276_.gameEvent(GameEvent.BLOCK_CHANGE, p_57277_, GameEvent.Context.of(p_57278_, blockstate));
            itemstack.hurtAndBreak(1, p_57278_, (p_49571_) -> {
                p_49571_.broadcastBreakEvent(p_57279_);
            });
            p_57276_.gameEvent(p_57278_, GameEvent.SHEAR, p_57277_);
            if (!p_57276_.isClientSide()) {
                p_57278_.awardStat(Stats.ITEM_USED.get(itemstack.getItem()));
            }
            return InteractionResult.sidedSuccess(p_57276_.isClientSide);
        } else {
            return super.use(p_57275_, p_57276_, p_57277_, p_57278_, p_57279_, p_57280_);
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_57282_) {
        p_57282_.add(AGE);
    }

    public boolean isValidBonemealTarget(@NotNull LevelReader p_256056_, @NotNull BlockPos p_57261_, BlockState p_57262_, boolean p_57263_) {
        return p_57262_.getValue(AGE) < 3;
    }

    public boolean isBonemealSuccess(@NotNull Level p_222558_, @NotNull RandomSource p_222559_, @NotNull BlockPos p_222560_, @NotNull BlockState p_222561_) {
        return true;
    }

    public void performBonemeal(ServerLevel p_222553_, @NotNull RandomSource p_222554_, @NotNull BlockPos p_222555_, BlockState p_222556_) {
        int i = Math.min(3, p_222556_.getValue(AGE) + 1);
        p_222553_.setBlock(p_222555_, p_222556_.setValue(AGE, Integer.valueOf(i)), 2);
    }
}
