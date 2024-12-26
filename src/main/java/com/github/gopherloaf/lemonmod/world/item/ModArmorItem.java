package com.github.gopherloaf.lemonmod.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class ModArmorItem extends ArmorItem {
    public ModArmorItem(ArmorMaterial p_40386_, Type p_266831_, Properties p_40388_) {
        super(p_40386_, p_266831_, p_40388_);
    }

    public void appendHoverText(@NotNull ItemStack p_41211_, @Nullable Level p_41212_, @NotNull List<Component> p_41213_, @NotNull TooltipFlag p_41214_) {
        super.appendHoverText(p_41211_, p_41212_, p_41213_, p_41214_);
        if (this.type == ArmorItem.Type.HELMET) {
            p_41213_.add(Component.literal("80% Explosion Damage Reduction").withStyle(ChatFormatting.BLUE));
        }
        if (this.type == Type.CHESTPLATE) {
            p_41213_.add(Component.literal("80% Projectile Damage Reduction").withStyle(ChatFormatting.BLUE));
        }
        if (this.type == Type.LEGGINGS) {
            p_41213_.add(Component.literal("0% Stuck Speed Multiplier").withStyle(ChatFormatting.BLUE));
            p_41213_.add(Component.literal("+40% Explosion Knockback Velocity").withStyle(ChatFormatting.BLUE));
            p_41213_.add(Component.literal("-25% Sneak Speed Multiplier").withStyle(ChatFormatting.BLUE));
        }
        if (this.type == Type.BOOTS) {
            p_41213_.add(Component.literal("64% Fall Damage Reduction").withStyle(ChatFormatting.BLUE));
        }
    }
}
