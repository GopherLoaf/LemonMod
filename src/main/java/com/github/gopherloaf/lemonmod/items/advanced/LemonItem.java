package com.github.gopherloaf.lemonmod.items.advanced;

import com.github.gopherloaf.lemonmod.Config;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.font.providers.UnihexProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class LemonItem extends Item {
    protected int explosionSize;

    public LemonItem(Properties p_41383_) {
        super(p_41383_);
        explosionSize = Config.fishExplosionSize;
    }

    public ItemStack finishUsingItem(ItemStack p_41348_, Level p_41349_, LivingEntity p_41350_) {
        ItemStack itemstack = super.finishUsingItem(p_41348_, p_41349_, p_41350_);

        if (!p_41349_.isClientSide) {
            p_41350_.removeEffect(MobEffects.HUNGER);
        }

        if (p_41350_ instanceof AbstractFish $$4 && (p_41348_.getItem() instanceof IndirectlyCombustibleLemonItem || Config.explosiveLemons)) {
            if ($$4.isAlive()) {
                if (!p_41350_.level().isClientSide) {
                    CompoundTag compoundtag = p_41348_.getTagElement("Indirectly Combustible Lemon");
                    explosionSize = Config.fishExplosionSize;
                    if (compoundtag != null) {
                        if (compoundtag.contains("Explosion", 99)) {
                            explosionSize = explosionSize + (p_41348_.getTagElement("Indirectly Combustible Lemon").getByte("Explosion") * Config.combustibleExplosionIncrement);
                        }
                    }
                    p_41350_.level().explode(p_41350_, p_41350_.getX(), p_41350_.getY(), p_41350_.getZ(), explosionSize, Config.fireyLemons, Level.ExplosionInteraction.MOB);
                    if (!p_41350_.isInvulnerable()) {
                        ServerLevel serverlevel1 = ((ServerLevel) p_41350_.level()).getServer().getLevel(Level.NETHER);
                        if (Config.fishToHell && p_41350_.level().dimension().equals(Level.OVERWORLD) && serverlevel1 != null){
                            p_41350_.teleportTo(serverlevel1, p_41350_.getX() / 8d, p_41350_.getY(), p_41350_.getZ() / 8d, null, p_41350_.getYHeadRot(), p_41350_.getXRot());
                            if (p_41350_.level().dimension().equals(Level.OVERWORLD))
                                p_41350_.hurt(p_41350_.damageSources().mobAttack(p_41350_), Float.MAX_VALUE);
                        } else {
                            p_41350_.hurt(p_41350_.damageSources().mobAttack(p_41350_), Float.MAX_VALUE);
                        }
                    }
                }
            }
        }

        return itemstack;
    }

    public InteractionResult interactLivingEntity(ItemStack p_41398_, Player p_41399_, LivingEntity p_41400_, InteractionHand p_41401_) {
        if (p_41400_ instanceof AbstractFish $$4 && (p_41398_.getItem() instanceof IndirectlyCombustibleLemonItem || Config.explosiveLemons)) {
            if ($$4.isAlive()) {
                if (!p_41399_.level().isClientSide) {
                    p_41400_.removeEffect(MobEffects.HUNGER);
                    p_41400_.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 40, 0));
                    p_41398_.shrink(1);
                    if (Config.fishToPlayer) p_41400_.teleportTo(p_41399_.getX(), p_41399_.getY(), p_41399_.getZ());
                    CompoundTag compoundtag = p_41398_.getTagElement("Indirectly Combustible Lemon");
                    explosionSize = Config.fishExplosionSize;
                    if (compoundtag != null) {
                        if (compoundtag.contains("Explosion", 99)) {
                            explosionSize = explosionSize + (p_41398_.getTagElement("Indirectly Combustible Lemon").getByte("Explosion") * Config.combustibleExplosionIncrement);
                        }
                    }
                    p_41400_.level().explode(p_41400_, p_41400_.getX(), p_41400_.getY(), p_41400_.getZ(), explosionSize, Config.fireyLemons, Level.ExplosionInteraction.MOB);
                    if (p_41399_.isCreative() || !p_41400_.isInvulnerable()) {
                        ServerLevel serverlevel1 = ((ServerLevel) p_41400_.level()).getServer().getLevel(Level.NETHER);
                        if (Config.fishToHell && p_41400_.level().dimension().equals(Level.OVERWORLD) && serverlevel1 != null){
                            p_41400_.teleportTo(serverlevel1, p_41400_.getX() / 8d, p_41400_.getY(), p_41400_.getZ() / 8d, null, p_41400_.getYHeadRot(), p_41400_.getXRot());
                            if (p_41400_.level().dimension().equals(Level.OVERWORLD))
                                p_41400_.hurt(p_41400_.damageSources().playerAttack(p_41399_), Float.MAX_VALUE);
                        } else {
                            p_41400_.hurt(p_41400_.damageSources().playerAttack(p_41399_), Float.MAX_VALUE);
                        }
                    }
                }

                return InteractionResult.sidedSuccess(p_41399_.level().isClientSide);
            }
        }
        return InteractionResult.PASS;
    }
}