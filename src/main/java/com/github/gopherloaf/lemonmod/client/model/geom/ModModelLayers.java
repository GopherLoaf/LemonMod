package com.github.gopherloaf.lemonmod.client.model.geom;

import com.github.gopherloaf.lemonmod.LemonMod;
import com.github.gopherloaf.lemonmod.world.entity.vehicle.ModBoat;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    private static ModelLayerLocation createLocation(String p_171301_, String p_171302_) {
        return new ModelLayerLocation(new ResourceLocation(LemonMod.MODID, p_171301_), p_171302_);
    }

    public static ModelLayerLocation createBoatModelName(ModBoat.Type p_171290_) {
        return createLocation("boat/" + p_171290_.getName(), "main");
    }

    public static ModelLayerLocation createChestBoatModelName(ModBoat.Type p_233551_) {
        return createLocation("chest_boat/" + p_233551_.getName(), "main");
    }
}
