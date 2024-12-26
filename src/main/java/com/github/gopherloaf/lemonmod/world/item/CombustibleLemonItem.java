package com.github.gopherloaf.lemonmod.world.item;

import com.github.gopherloaf.lemonmod.Config;
import com.github.gopherloaf.lemonmod.misc.util.MiscUtil;
import com.github.gopherloaf.lemonmod.sounds.ModSoundEvents;
import com.github.gopherloaf.lemonmod.world.entity.projectile.ThrownCombustibleLemon;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class CombustibleLemonItem extends IndirectlyCombustibleLemonItem{
    public CombustibleLemonItem(Properties p_41383_) {
        super(p_41383_);
    }

    public @NotNull InteractionResultHolder<ItemStack> use(Level p_43303_, Player p_43304_, @NotNull InteractionHand p_43305_) {
        ItemStack itemstack = p_43304_.getItemInHand(p_43305_);
        p_43303_.playSound((Player)null, p_43304_.getX(), p_43304_.getY(), p_43304_.getZ(), ModSoundEvents.COMBUSTIBLE_LEMON_THROW.get(), SoundSource.NEUTRAL, 0.5F, 0.4F / (p_43303_.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!p_43303_.isClientSide) {
            ThrownCombustibleLemon throwncombustiblelemon = new ThrownCombustibleLemon(p_43303_, p_43304_);
            throwncombustiblelemon.setItem(itemstack);
            throwncombustiblelemon.shootFromRotation(p_43304_, p_43304_.getXRot(), p_43304_.getYRot(), -20.0F, 0.5F, 1.0F);
            p_43303_.addFreshEntity(throwncombustiblelemon);
        }

        p_43304_.awardStat(Stats.ITEM_USED.get(this));
        if (!p_43304_.getAbilities().instabuild) {
            itemstack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, p_43303_.isClientSide());
    }

    public ItemStack finishUsingItem(ItemStack p_41409_, Level p_41410_, LivingEntity p_41411_) {
        return this.isEdible() ? p_41411_.eat(p_41410_, p_41409_) : p_41409_;
    }

    public InteractionResult interactLivingEntity(ItemStack p_41398_, Player p_41399_, LivingEntity p_41400_, InteractionHand p_41401_) {
        return InteractionResult.PASS;
    }

    public void appendHoverText(ItemStack p_41211_, @Nullable Level p_41212_, List<Component> p_41213_, TooltipFlag p_41214_) {
        CompoundTag compoundtag = p_41211_.getTagElement("Indirectly Combustible Lemon");
        if (compoundtag != null) {
            if (compoundtag.contains("Explosion", 99)) {
                p_41213_.add(Component.translatable("item.lemonmod.indirectly_combustible_lemon.explosion").append(CommonComponents.SPACE).append(String.valueOf(MiscUtil.roughRounding(1.2 * Math.sqrt(compoundtag.getByte("Explosion") * Config.combustibleExplosionIncrement + Config.fishExplosionSize), 2))).withStyle(ChatFormatting.GRAY));
            }
        }

    }
}
