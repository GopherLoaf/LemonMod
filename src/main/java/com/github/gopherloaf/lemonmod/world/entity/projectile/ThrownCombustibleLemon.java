package com.github.gopherloaf.lemonmod.world.entity.projectile;

import com.github.gopherloaf.lemonmod.Config;
import com.github.gopherloaf.lemonmod.misc.util.MiscUtil;
import com.github.gopherloaf.lemonmod.world.entity.ModEntityType;
import com.github.gopherloaf.lemonmod.world.item.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class ThrownCombustibleLemon extends ThrowableItemProjectile implements ItemSupplier {
    protected int launch;
    protected int safeguard;
    protected boolean controlled;
    protected boolean bazooka;
    protected boolean multi;
    protected boolean launcher;
    protected int temperature;

    public ThrownCombustibleLemon(EntityType<? extends ThrownCombustibleLemon> p_37527_, Level p_37528_) {
        super(p_37527_, p_37528_);
        launch = 0;
        safeguard = 0;
        controlled = false;
        bazooka = false;
        multi = false;
        launcher = false;
        temperature = 2;
    }

    public ThrownCombustibleLemon(Level p_37535_, LivingEntity p_37536_) {
        super(ModEntityType.COMBUSTIBLE_LEMON.get(), p_37536_, p_37535_);
        launch = 0;
        safeguard = 0;
        controlled = false;
        bazooka = false;
        multi = false;
        launcher = false;
        temperature = 2;
    }

    public ThrownCombustibleLemon(Level p_37530_, double p_37531_, double p_37532_, double p_37533_) {
        super(ModEntityType.COMBUSTIBLE_LEMON.get(), p_37531_, p_37532_, p_37533_, p_37530_);
        launch = 0;
        safeguard = 0;
        controlled = false;
        bazooka = false;
        multi = false;
        launcher = false;
        temperature = 2;
    }

    public ThrownCombustibleLemon(Level p_37535_, LivingEntity p_37536_, boolean launched, int l, int s, boolean c, boolean b, boolean m, int t) {
        super(ModEntityType.COMBUSTIBLE_LEMON.get(), p_37536_, p_37535_);
        launch = l;
        safeguard = s;
        controlled = c;
        bazooka = b;
        multi = m;
        launcher = launched;
        temperature = t;
    }

    protected @NotNull Item getDefaultItem() {
        return ModItems.COMBUSTIBLE_LEMON.get();
    }

    protected float getGravity() {
        return 0.05F;
    }

    protected void onHitBlock(@NotNull BlockHitResult p_36755_) {
        super.onHitBlock(p_36755_);
        explode(p_36755_);
    }

    protected void onHitEntity(@NotNull EntityHitResult p_36757_) {
        super.onHitEntity(p_36757_);
        if (!this.level().isClientSide) {
            Entity entity = p_36757_.getEntity();
            entity.hurt(this.damageSources().thrown(this, this.getOwner()), 6.0F);
        }
        explode(p_36757_);
    }

    protected void explode(HitResult p_36756_){
        if (!this.level().isClientSide) {
            Entity entity;
            ItemStack itemstack = this.getItem();
            CompoundTag compoundtag = itemstack.getTagElement("Indirectly Combustible Lemon");
            float explosionSize = 0;
            if (compoundtag != null) {
                if (compoundtag.contains("Explosion", 99)) {
                    explosionSize = (float) MiscUtil.roughRounding(1.2 * Math.sqrt(compoundtag.getByte("Explosion") * Config.combustibleExplosionIncrement + Config.fishExplosionSize), 2);
                }
            }
            if (!this.multi){
                if (this.bazooka) {
                    if (p_36756_.getType() == HitResult.Type.ENTITY) {
                        Entity entity1 = ((EntityHitResult) p_36756_).getEntity();
                        if (!entity1.onGround()){
                            explosionSize = explosionSize * (15F/13F);
                        } else {
                            explosionSize = explosionSize * (6F/13F);
                            entity1.setDeltaMovement(entity1.getDeltaMovement().add(new Vec3(0, 0.84, 0)));
                        }
                    } else {
                        explosionSize = explosionSize * (6F/13F);
                    }
                } else {
                    if (this.launcher){
                        explosionSize = explosionSize * (16F/13F);
                    }
                }
            }
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), explosionSize, Config.fireyLemons && this.temperature > 1, this.getOwner() instanceof Mob ? Level.ExplosionInteraction.MOB : this.controlled ? Level.ExplosionInteraction.TNT : Level.ExplosionInteraction.BLOCK);
            this.discard();
        }
    }

    public int getLaunch() {
        return this.launch;
    }

    public int getSafeguard() {
        return this.safeguard;
    }

    public boolean isControlled() {
        return this.controlled;
    }

    public boolean isBazooka() {
        return this.bazooka;
    }

    public boolean isMulti() {
        return this.multi;
    }

    public int getTemperature() {
        return this.temperature;
    }
}
