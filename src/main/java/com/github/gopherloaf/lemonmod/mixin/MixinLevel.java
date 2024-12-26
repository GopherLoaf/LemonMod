package com.github.gopherloaf.lemonmod.mixin;

import com.github.gopherloaf.lemonmod.world.entity.projectile.ThrownCombustibleLemon;
import com.github.gopherloaf.lemonmod.world.level.LemonExplosion;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Level.class)
public abstract class MixinLevel {
    @Shadow protected abstract Explosion.BlockInteraction getDestroyType(GameRules.Key<GameRules.BooleanValue> p_256250_);

    @ModifyVariable(method = "explode(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/level/ExplosionDamageCalculator;DDDFZLnet/minecraft/world/level/Level$ExplosionInteraction;Z)Lnet/minecraft/world/level/Explosion;", at = @At("STORE"), ordinal = 0)
    private Explosion explode(Explosion explosion, Entity p_256233_, DamageSource p_255861_, ExplosionDamageCalculator p_255867_, double p_256447_, double p_255732_, double p_255717_, float p_256013_, boolean p_256228_, Level.ExplosionInteraction p_255784_, boolean p_256377_) {
        if (p_256233_ != null && p_256233_ instanceof ThrownCombustibleLemon) {
            Level thisObject = (Level) (Object) this;
            ThrownCombustibleLemon thrownCombustibleLemon = (ThrownCombustibleLemon) p_256233_;
            Explosion.BlockInteraction explosion$blockinteraction1;
            switch (p_255784_) {
                case NONE:
                    explosion$blockinteraction1 = Explosion.BlockInteraction.KEEP;
                    break;
                case BLOCK:
                    explosion$blockinteraction1 = this.getDestroyType(GameRules.RULE_BLOCK_EXPLOSION_DROP_DECAY);
                    break;
                case MOB:
                    explosion$blockinteraction1 = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(thisObject, p_256233_) ? this.getDestroyType(GameRules.RULE_MOB_EXPLOSION_DROP_DECAY) : Explosion.BlockInteraction.KEEP;
                    break;
                case TNT:
                    explosion$blockinteraction1 = this.getDestroyType(GameRules.RULE_TNT_EXPLOSION_DROP_DECAY);
                    break;
                default:
                    throw new IncompatibleClassChangeError();
            }
            Explosion.BlockInteraction explosion$blockinteraction = explosion$blockinteraction1;
            return new LemonExplosion(thisObject, p_256233_, p_255861_, p_255867_, p_256447_, p_255732_, p_255717_, p_256013_, p_256228_, explosion$blockinteraction, thrownCombustibleLemon.getLaunch() + (thrownCombustibleLemon.isBazooka() ? 2 : 0), thrownCombustibleLemon.getOwner(), thrownCombustibleLemon.getSafeguard(), thrownCombustibleLemon.getTemperature());
        }
        return explosion;
    }
}
