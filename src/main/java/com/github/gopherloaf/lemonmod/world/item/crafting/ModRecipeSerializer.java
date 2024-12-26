package com.github.gopherloaf.lemonmod.world.item.crafting;

import com.github.gopherloaf.lemonmod.LemonMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeSerializer {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, LemonMod.MODID);

    public static final RegistryObject<RecipeSerializer<IndirectlyCombustibleLemonRecipe>> INDIRECTLY_COMBUSTIBLE_LEMON = RECIPE_SERIALIZERS.register("crafting_special_indirectly_combustible_lemon",() -> new SimpleCraftingRecipeSerializer(IndirectlyCombustibleLemonRecipe::new));
    public static final RegistryObject<RecipeSerializer<CombustibleLemonRecipe>> COMBUSTIBLE_LEMON = RECIPE_SERIALIZERS.register("crafting_special_combustible_lemon",() -> new SimpleCraftingRecipeSerializer(CombustibleLemonRecipe::new));

    public static void register(){
        // Register the Deferred Register to the mod event bus so item get registered
        RECIPE_SERIALIZERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
