package com.github.gopherloaf.lemonmod.world.item;

import com.github.gopherloaf.lemonmod.Config;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class IndirectlyCombustibleLemonItem extends LemonItem {
    public IndirectlyCombustibleLemonItem(Properties p_41383_) {
        super(p_41383_);
    }

    public ItemStack finishUsingItem(ItemStack p_41348_, Level p_41349_, LivingEntity p_41350_) {
        return super.finishUsingItem(p_41348_, p_41349_, p_41350_);
    }

    public InteractionResult interactLivingEntity(ItemStack p_41398_, Player p_41399_, LivingEntity p_41400_, InteractionHand p_41401_) {
        return super.interactLivingEntity(p_41398_, p_41399_, p_41400_, p_41401_);
    }

    public void appendHoverText(ItemStack p_41211_, @Nullable Level p_41212_, List<Component> p_41213_, TooltipFlag p_41214_) {
        CompoundTag compoundtag = p_41211_.getTagElement("Indirectly Combustible Lemon");
        if (compoundtag != null) {
            if (compoundtag.contains("Explosion", 99)) {
                p_41213_.add(Component.translatable("item.lemonmod.indirectly_combustible_lemon.explosion").append(CommonComponents.SPACE).append(String.valueOf(compoundtag.getByte("Explosion") * Config.combustibleExplosionIncrement + Config.fishExplosionSize)).withStyle(ChatFormatting.GRAY));
            }
        }

    }

    public static void setExplosionSize(ItemStack p_260106_, byte p_260332_) {
        p_260106_.getOrCreateTagElement("Indirectly Combustible Lemon").putByte("Explosion", p_260332_);
    }

    public ItemStack getDefaultInstance() {
        ItemStack itemstack = new ItemStack(this);
        setExplosionSize(itemstack, (byte)1);
        return itemstack;
    }
}