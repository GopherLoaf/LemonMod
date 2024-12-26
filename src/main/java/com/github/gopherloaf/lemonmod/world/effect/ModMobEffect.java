package com.github.gopherloaf.lemonmod.world.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class ModMobEffect extends MobEffect {
    public ModMobEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    public void applyEffectTick(@NotNull LivingEntity p_19467_, int p_19468_) {
        if (this == ModMobEffects.SATURATION.get()) {
            MobEffectInstance saturation = p_19467_.getEffect(this);
            p_19467_.addEffect(new MobEffectInstance(MobEffects.SATURATION, saturation.getDuration(), saturation.getAmplifier()));
            p_19467_.removeEffect(this);
        }
    }

    public boolean isDurationEffectTick(int p_19455_, int p_19456_) {
        return this == ModMobEffects.SATURATION.get();
    }
}
