package com.github.gopherloaf.lemonmod.misc.init;

import com.github.gopherloaf.lemonmod.LemonMod;
import com.github.gopherloaf.lemonmod.common.brewing.ProperBrewingRecipe;
import com.github.gopherloaf.lemonmod.world.entity.projectile.ThrownCombustibleLemon;
import com.github.gopherloaf.lemonmod.world.item.LemonLauncherItem;
import com.github.gopherloaf.lemonmod.world.item.ModArmorMaterials;
import com.github.gopherloaf.lemonmod.world.item.ModItems;
import com.github.gopherloaf.lemonmod.world.item.alchemy.ModPotions;
import com.github.gopherloaf.lemonmod.world.level.block.HollowedBigLemonBlock;
import com.github.gopherloaf.lemonmod.world.level.block.ModBlocks;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static net.minecraft.world.entity.animal.horse.AbstractHorse.FOOD_ITEMS;

public class Init {
    private static void compostables(){
        ComposterBlock.COMPOSTABLES.put(ModItems.LEMON.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModItems.LEMON_BAR.get(), 0.85F);
        ComposterBlock.COMPOSTABLES.put(ModItems.LEMON_LEAVES_ITEM.get(), 0.30F);
        ComposterBlock.COMPOSTABLES.put(ModItems.LEMON_SAPLING_ITEM.get(), 0.30F);
        ComposterBlock.COMPOSTABLES.put(ModItems.BIG_LEMON_ITEM.get(), 0.85F);
        ComposterBlock.COMPOSTABLES.put(ModItems.HOLLOWED_BIG_LEMON_ITEM.get(), 1.0F);
    }

    private static void strippables(){
        AxeItem.STRIPPABLES = Maps.newHashMap(AxeItem.STRIPPABLES);
        AxeItem.STRIPPABLES.put(ModBlocks.LEMON_LOG.get(), ModBlocks.STRIPPED_LEMON_LOG.get());
        AxeItem.STRIPPABLES.put(ModBlocks.LEMON_WOOD.get(), ModBlocks.STRIPPED_LEMON_WOOD.get());
    }

    private static void foodItems(){
        ArrayList<Ingredient> MOD_FOOD_ITEMS = new ArrayList<Ingredient>();
        MOD_FOOD_ITEMS.add(FOOD_ITEMS);
        MOD_FOOD_ITEMS.add(Ingredient.of(new ItemLike[]{ModItems.LEMON.get(), ModItems.GOLDEN_LEMON.get(), ModItems.ENCHANTED_GOLDEN_LEMON.get()}));
        FOOD_ITEMS = Ingredient.merge(MOD_FOOD_ITEMS);
    }

    public static ItemStack createPotion(Potion potion){
        return  PotionUtils.setPotion(new ItemStack(Items.POTION), potion);
    }

    private static void brewingRecipeRegistry(){
        BrewingRecipeRegistry.addRecipe(new ProperBrewingRecipe(Ingredient.of(createPotion(Potions.AWKWARD)), Ingredient.of(ModItems.SOUR_CANDIES.get()), createPotion(ModPotions.SOUR.get())));
        BrewingRecipeRegistry.addRecipe(new ProperBrewingRecipe(Ingredient.of(createPotion(ModPotions.SOUR.get())), Ingredient.of(Items.REDSTONE), createPotion(ModPotions.LONG_SOUR.get())));
        BrewingRecipeRegistry.addRecipe(new ProperBrewingRecipe(Ingredient.of(createPotion(ModPotions.SOUR.get())), Ingredient.of(Items.GLOWSTONE), createPotion(ModPotions.STRONG_SOUR.get())));
        BrewingRecipeRegistry.addRecipe(new ProperBrewingRecipe(Ingredient.of(createPotion(Potions.AWKWARD)), Ingredient.of(ModItems.GLISTERING_PINEAPPLE.get()), createPotion(ModPotions.WITHER.get())));
        BrewingRecipeRegistry.addRecipe(new ProperBrewingRecipe(Ingredient.of(createPotion(ModPotions.WITHER.get())), Ingredient.of(Items.REDSTONE), createPotion(ModPotions.LONG_WITHER.get())));
        BrewingRecipeRegistry.addRecipe(new ProperBrewingRecipe(Ingredient.of(createPotion(ModPotions.WITHER.get())), Ingredient.of(Items.GLOWSTONE), createPotion(ModPotions.STRONG_WITHER.get())));
        BrewingRecipeRegistry.addRecipe(new ProperBrewingRecipe(Ingredient.of(createPotion(Potions.AWKWARD)), Ingredient.of(ModItems.LEMON_JUICE_BOTTLE.get()), createPotion(ModPotions.SATURATION.get())));
        BrewingRecipeRegistry.addRecipe(new ProperBrewingRecipe(Ingredient.of(createPotion(ModPotions.SATURATION.get())), Ingredient.of(Items.GLOWSTONE), createPotion(ModPotions.STRONG_SATURATION.get())));
        BrewingRecipeRegistry.addRecipe(new ProperBrewingRecipe(Ingredient.of(createPotion(ModPotions.SATURATION.get())), Ingredient.of(Items.FERMENTED_SPIDER_EYE), createPotion(ModPotions.HUNGER.get())));
        BrewingRecipeRegistry.addRecipe(new ProperBrewingRecipe(Ingredient.of(createPotion(ModPotions.HUNGER.get())), Ingredient.of(Items.REDSTONE), createPotion(ModPotions.LONG_HUNGER.get())));
        BrewingRecipeRegistry.addRecipe(new ProperBrewingRecipe(Ingredient.of(createPotion(ModPotions.HUNGER.get())), Ingredient.of(Items.GLOWSTONE), createPotion(ModPotions.STRONG_HUNGER.get())));
        BrewingRecipeRegistry.addRecipe(new ProperBrewingRecipe(Ingredient.of(createPotion(Potions.AWKWARD)), Ingredient.of(ModBlocks.BIG_LEMON.get()), createPotion(ModPotions.LEMON_LEGEND.get())));
        BrewingRecipeRegistry.addRecipe(new ProperBrewingRecipe(Ingredient.of(createPotion(ModPotions.LEMON_LEGEND.get())), Ingredient.of(Items.REDSTONE), createPotion(ModPotions.LONG_LEMON_LEGEND.get())));
        BrewingRecipeRegistry.addRecipe(new ProperBrewingRecipe(Ingredient.of(createPotion(ModPotions.LEMON_LEGEND.get())), Ingredient.of(Items.GLOWSTONE), createPotion(ModPotions.STRONG_LEMON_LEGEND.get())));
        BrewingRecipeRegistry.addRecipe(new ProperBrewingRecipe(Ingredient.of(createPotion(ModPotions.SOUR.get())), Ingredient.of(Items.FERMENTED_SPIDER_EYE), createPotion(ModPotions.BLINDNESS.get())));
        BrewingRecipeRegistry.addRecipe(new ProperBrewingRecipe(Ingredient.of(createPotion(ModPotions.BLINDNESS.get())), Ingredient.of(Items.REDSTONE), createPotion(ModPotions.LONG_BLINDNESS.get())));

        BrewingRecipeRegistry.addRecipe(new ProperBrewingRecipe(Ingredient.of(createPotion(Potions.WATER)), Ingredient.of(ModItems.GLISTERING_PINEAPPLE.get()), createPotion(Potions.MUNDANE)));
        BrewingRecipeRegistry.addRecipe(new ProperBrewingRecipe(Ingredient.of(createPotion(Potions.WATER)), Ingredient.of(ModItems.LEMON_JUICE_BOTTLE.get()), createPotion(Potions.MUNDANE)));
    }

    private static void dispenserBlockRegisterBehavior() {
        DispenserBlock.registerBehavior(ModItems.COMBUSTIBLE_LEMON.get(), new DispenseItemBehavior(){
            public @NotNull ItemStack dispense(@NotNull BlockSource p_123491_, @NotNull ItemStack p_123492_) {
                return (new AbstractProjectileDispenseBehavior() {
                    protected @NotNull Projectile getProjectile(@NotNull Level p_123501_, @NotNull Position p_123502_, @NotNull ItemStack p_123503_) {
                        return Util.make(new ThrownCombustibleLemon(p_123501_, p_123502_.x(), p_123502_.y(), p_123502_.z()), (p_123499_) -> {
                            p_123499_.setItem(p_123503_);
                        });
                    }

                    protected float getUncertainty() {
                        return super.getUncertainty() * 0.5F;
                    }

                    protected float getPower() {
                        return super.getPower() * 1.25F;
                    }
                }).dispense(p_123491_, p_123492_);
            }
        });
        DispenserBlock.registerBehavior(ModBlocks.HOLLOWED_BIG_LEMON.get(), new OptionalDispenseItemBehavior() {
            protected @NotNull ItemStack execute(@NotNull BlockSource p_123437_, @NotNull ItemStack p_123438_) {
                Level level = p_123437_.getLevel();
                BlockPos blockpos = p_123437_.getPos().relative(p_123437_.getBlockState().getValue(DispenserBlock.FACING));
                HollowedBigLemonBlock hollowedbiglemonblock = (HollowedBigLemonBlock)ModBlocks.HOLLOWED_BIG_LEMON.get();
                if (false) {
                    if (!level.isClientSide) {
                        level.setBlock(blockpos, hollowedbiglemonblock.defaultBlockState(), 3);
                        level.gameEvent((Entity)null, GameEvent.BLOCK_PLACE, blockpos);
                    }

                    p_123438_.shrink(1);
                    this.setSuccess(true);
                } else {
                    this.setSuccess(ArmorItem.dispenseArmor(p_123437_, p_123438_));
                }

                return p_123438_;
            }
        });
    }

    private static void itemPropertiesRegister() {
        ItemProperties.register(ModItems.LEMON_LAUNCHER.get(),
                new ResourceLocation(LemonMod.MODID, "pulling"), (stack, level, living, id) -> {
                    return living != null && living.isUsingItem() && living.getUseItem() == stack ? 1.0F : 0.0F;
                });
        ItemProperties.register(ModItems.LEMON_LAUNCHER.get(),
                new ResourceLocation(LemonMod.MODID, "charged"), (stack, level, living, id) -> {
                    return living != null && LemonLauncherItem.isCharged(stack) ? 1.0F : 0.0F;
                });
        ItemProperties.register(ModItems.LEMON_LAUNCHER.get(),
                new ResourceLocation(LemonMod.MODID, "pull"), (stack, level, living, id) -> {
                    float f = 0.0F;
                    if (living != null && living.isUsingItem() && living.getUseItem() == stack){
                        LemonLauncherItem lemonLauncherItem = (LemonLauncherItem) living.getUseItem().getItem();
                        int i = lemonLauncherItem.getUseDuration(living.getUseItem()) - living.getUseItemRemainingTicks();
                        f = LemonLauncherItem.getPowerForTime(i, living.getUseItem());
                    }
                    return Math.min(f, 1.0F);
                });
    }

    public static void init(){
        compostables();
        strippables();
        foodItems();
        brewingRecipeRegistry();
        dispenserBlockRegisterBehavior();
        itemPropertiesRegister();
    }
}