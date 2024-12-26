package com.github.gopherloaf.lemonmod.world.item;

import com.github.gopherloaf.lemonmod.LemonMod;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

public class ModSmithingTemplateItem extends Item {
    private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
    private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;
    private static final Component LEMON_LEATHER_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", new ResourceLocation("lemon_leather_upgrade"))).withStyle(TITLE_FORMAT);
    private static final Component LEMON_LEATHER_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.lemon_leather_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component LEMON_LEATHER_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.lemon_leather_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component LEMON_LEATHER_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.lemon_leather_upgrade.base_slot_description")));
    private static final Component LEMON_LEATHER_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.lemon_leather_upgrade.additions_slot_description")));
    private static final ResourceLocation EMPTY_SLOT_HELMET = new ResourceLocation("item/empty_armor_slot_helmet");
    private static final ResourceLocation EMPTY_SLOT_CHESTPLATE = new ResourceLocation("item/empty_armor_slot_chestplate");
    private static final ResourceLocation EMPTY_SLOT_LEGGINGS = new ResourceLocation("item/empty_armor_slot_leggings");
    private static final ResourceLocation EMPTY_SLOT_BOOTS = new ResourceLocation("item/empty_armor_slot_boots");
    private static final ResourceLocation EMPTY_SLOT_LEMON_LEATHER = new ResourceLocation(LemonMod.MODID, "item/lemon");

    public ModSmithingTemplateItem(Properties p_41383_) {
        super(p_41383_);
    }

    public static SmithingTemplateItem createLemonUpgradeTemplate() {
        return new SmithingTemplateItem(LEMON_LEATHER_UPGRADE_APPLIES_TO, LEMON_LEATHER_UPGRADE_INGREDIENTS, LEMON_LEATHER_UPGRADE, LEMON_LEATHER_UPGRADE_BASE_SLOT_DESCRIPTION, LEMON_LEATHER_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, createLemonUpgradeIconList(), createLemonUpgradeMaterialList());
    }

    private static List<ResourceLocation> createLemonUpgradeIconList() {
        return List.of(EMPTY_SLOT_HELMET, EMPTY_SLOT_CHESTPLATE, EMPTY_SLOT_LEGGINGS, EMPTY_SLOT_BOOTS);
    }

    private static List<ResourceLocation> createLemonUpgradeMaterialList() {
        return List.of(EMPTY_SLOT_LEMON_LEATHER);
    }
}
