package com.github.gopherloaf.lemonmod.world.item.crafting;

import com.github.gopherloaf.lemonmod.world.item.ModItems;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class CombustibleLemonRecipe extends CustomRecipe {
    private static final Ingredient INDIRECTLY_COMBUSTIBLE_LEMON_INGREDIENT;
    private static final Ingredient FISHES_INGREDIENT;
    private static final Ingredient GUNPOWDER_INGREDIENT;

    public CombustibleLemonRecipe(ResourceLocation p_252125_, CraftingBookCategory p_249010_) {
        super(p_252125_, p_249010_);
    }

    public boolean matches(CraftingContainer craftingContainer, @NotNull Level level) {
        boolean $$2 = false;
        boolean $$3 = false;
        boolean $$4 = false;

        for(int $$5 = 0; $$5 < craftingContainer.getContainerSize(); ++$$5) {
            ItemStack $$6 = craftingContainer.getItem($$5);
            if (!$$6.isEmpty()) {
                if (INDIRECTLY_COMBUSTIBLE_LEMON_INGREDIENT.test($$6)) {
                    if ($$2) {
                        return false;
                    }

                    $$2 = true;
                } else if (FISHES_INGREDIENT.test($$6)) {
                    if ($$3) {
                        return false;
                    }

                    $$3 = true;
                } else if (GUNPOWDER_INGREDIENT.test($$6)) {
                    if ($$4) {
                        return false;
                    }

                    $$4 = true;
                }

            }
        }

        return $$2 && $$3 && $$4;
    }

    public @NotNull ItemStack assemble(CraftingContainer craftingContainer, @NotNull RegistryAccess registryAccess) {
        ItemStack $$2 = new ItemStack(ModItems.COMBUSTIBLE_LEMON.get(), 1);
        CompoundTag $$3 = $$2.getOrCreateTagElement("Indirectly Combustible Lemon");
        int $$5 = 0;

        for(int $$6 = 0; $$6 < craftingContainer.getContainerSize(); ++$$6) {
            ItemStack $$7 = craftingContainer.getItem($$6);
            if (!$$7.isEmpty()) {
                if (INDIRECTLY_COMBUSTIBLE_LEMON_INGREDIENT.test($$7)) {
                    CompoundTag compoundtag = $$7.getTagElement("Indirectly Combustible Lemon");
                    if (compoundtag != null) {
                        if (compoundtag.contains("Explosion", 99)) {
                            $$5 += $$7.getTagElement("Indirectly Combustible Lemon").getByte("Explosion");
                        }
                    }
                }
            }
        }

        $$3.putByte("Explosion", (byte)$$5);

        return $$2;
    }

    public boolean canCraftInDimensions(int i, int i1) {
        return i * i1 >= 2;
    }

    public @NotNull ItemStack getResultItem(@NotNull RegistryAccess p_267261_) {
        return new ItemStack(ModItems.COMBUSTIBLE_LEMON.get());
    }

    public @NotNull RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializer.COMBUSTIBLE_LEMON.get();
    }

    static {
        INDIRECTLY_COMBUSTIBLE_LEMON_INGREDIENT = Ingredient.of(new ItemLike[]{ModItems.INDIRECTLY_COMBUSTIBLE_LEMON.get()});
        FISHES_INGREDIENT = Ingredient.of(ItemTags.FISHES);
        GUNPOWDER_INGREDIENT = Ingredient.of(new ItemLike[]{Items.GUNPOWDER});
    }
}
