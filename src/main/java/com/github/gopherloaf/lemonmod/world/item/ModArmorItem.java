package com.github.gopherloaf.lemonmod.world.item;

import com.google.common.collect.ImmutableMultimap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class ModArmorItem extends ArmorItem {
    public ModArmorItem(ArmorMaterial p_40386_, Type p_266831_, Properties p_40388_) {
        super(p_40386_, p_266831_, p_40388_);

        if (p_40386_ == ModArmorMaterials.LEMON_LEATHER && p_266831_ == Type.LEGGINGS){
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            UUID uuid = UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D");
            builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", (double)this.getDefense(), AttributeModifier.Operation.ADDITION));
            builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", (double)this.getToughness(), AttributeModifier.Operation.ADDITION));
            if (this.knockbackResistance > 0) {
                builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Armor knockback resistance", (double)this.knockbackResistance, AttributeModifier.Operation.ADDITION));
            }
            builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "Armor movement speed", (double)0.25F, AttributeModifier.Operation.MULTIPLY_TOTAL));
            builder.put(Attributes.FLYING_SPEED, new AttributeModifier(uuid, "Armor flying speed", (double)0.25F, AttributeModifier.Operation.MULTIPLY_TOTAL));

            defaultModifiers = builder.build();
        }
    }

    public void appendHoverText(@NotNull ItemStack p_41211_, @Nullable Level p_41212_, @NotNull List<Component> p_41213_, @NotNull TooltipFlag p_41214_) {
        super.appendHoverText(p_41211_, p_41212_, p_41213_, p_41214_);
        if (this.type == ArmorItem.Type.HELMET) {
            p_41213_.add(Component.literal("80% Explosion Damage Reduction").withStyle(ChatFormatting.BLUE));
        }
        if (this.type == Type.CHESTPLATE) {
            p_41213_.add(Component.literal("64% Projectile Damage Reduction").withStyle(ChatFormatting.BLUE));
            p_41213_.add(Component.literal("64% Fire Damage Reduction").withStyle(ChatFormatting.BLUE));
        }
        if (this.type == Type.LEGGINGS) {
            p_41213_.add(Component.literal("0% Stuck Speed Multiplier").withStyle(ChatFormatting.BLUE));
            p_41213_.add(Component.literal("+40% Explosion Knockback Velocity").withStyle(ChatFormatting.BLUE));
            p_41213_.add(Component.literal("-25% Sneak Speed Multiplier").withStyle(ChatFormatting.BLUE));
        }
        if (this.type == Type.BOOTS) {
            p_41213_.add(Component.literal("80% Fall Damage Reduction").withStyle(ChatFormatting.BLUE));
        }
    }
}
