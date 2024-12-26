package com.github.gopherloaf.lemonmod.world.item.crafting;

import com.github.gopherloaf.lemonmod.world.item.ModItems;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
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

public class IndirectlyCombustibleLemonRecipe extends CustomRecipe {
    private static final Ingredient LEMON_INGREDIENT;
    private static final Ingredient TNT_INGREDIENT;

    public IndirectlyCombustibleLemonRecipe(ResourceLocation p_252125_, CraftingBookCategory p_249010_) {
        super(p_252125_, p_249010_);
    }

    public boolean matches(CraftingContainer craftingContainer, @NotNull Level level) {
        boolean $$2 = false;
        int $$3 = 0;

        for(int $$4 = 0; $$4 < craftingContainer.getContainerSize(); ++$$4) {
            ItemStack $$5 = craftingContainer.getItem($$4);
            if (!$$5.isEmpty()) {
                if (LEMON_INGREDIENT.test($$5)) {
                    if ($$2) {
                        return false;
                    }

                    $$2 = true;
                } else if (TNT_INGREDIENT.test($$5)) {
                    ++$$3;
                    if ($$3 > 20) {
                        return false;
                    }
                }
            }
        }

        return $$2 && $$3 >= 1;
    }

    public @NotNull ItemStack assemble(CraftingContainer craftingContainer, @NotNull RegistryAccess registryAccess) {
        ItemStack $$2 = new ItemStack(ModItems.INDIRECTLY_COMBUSTIBLE_LEMON.get(), 1);
        CompoundTag $$3 = $$2.getOrCreateTagElement("Indirectly Combustible Lemon");
        int $$5 = 0;

        for(int $$6 = 0; $$6 < craftingContainer.getContainerSize(); ++$$6) {
            ItemStack $$7 = craftingContainer.getItem($$6);
            if (!$$7.isEmpty()) {
                if (TNT_INGREDIENT.test($$7)) {
                    ++$$5;
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
        return new ItemStack(ModItems.INDIRECTLY_COMBUSTIBLE_LEMON.get());
    }

    public @NotNull RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializer.INDIRECTLY_COMBUSTIBLE_LEMON.get();
    }

    static {
        LEMON_INGREDIENT = Ingredient.of(new ItemLike[]{ModItems.LEMON.get()});
        TNT_INGREDIENT = Ingredient.of(new ItemLike[]{Items.TNT});
    }
}
