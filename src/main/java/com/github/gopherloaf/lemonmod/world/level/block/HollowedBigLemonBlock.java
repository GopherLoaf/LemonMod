package com.github.gopherloaf.lemonmod.world.level.block;

import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockPattern;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class HollowedBigLemonBlock extends HorizontalDirectionalBlock {
    @Nullable
    private BlockPattern snowGolemBase;
    @Nullable
    private BlockPattern snowGolemFull;
    @Nullable
    private BlockPattern ironGolemBase;
    @Nullable
    private BlockPattern ironGolemFull;
    private static final Predicate<BlockState> PUMPKINS_PREDICATE = (p_51396_) -> {
        return p_51396_ != null && (p_51396_.is(ModBlocks.HOLLOWED_BIG_LEMON.get()) || p_51396_.is(ModBlocks.BIG_LEMON_LANTERN.get()));
    };

    public HollowedBigLemonBlock(BlockBehaviour.Properties p_51375_) {
        super(p_51375_);
    }
}
