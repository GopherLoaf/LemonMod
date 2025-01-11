package com.github.gopherloaf.lemonmod.world.item;

import com.github.gopherloaf.lemonmod.Config;
import com.github.gopherloaf.lemonmod.misc.util.MiscUtil;
import com.github.gopherloaf.lemonmod.sounds.ModSoundEvents;
import com.github.gopherloaf.lemonmod.world.entity.projectile.ThrownCombustibleLemon;
import com.github.gopherloaf.lemonmod.world.item.enchantment.ModEnchantments;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public class LemonLauncherItem extends ProjectileWeaponItem implements Vanishable {
    private boolean startSoundPlayed = false;
    private boolean midLoadSoundPlayed = false;
    public static final Predicate<ItemStack> COMBUSTIBLE_LEMON_ONLY = (p_43017_) -> {
        return p_43017_.is(ModItems.COMBUSTIBLE_LEMON.get());
    };
    public static final Predicate<ItemStack> LAVA_OR_POWDER_SNOW = (p_43017_) -> {
        return p_43017_.is(Items.LAVA_BUCKET) || p_43017_.is(Items.POWDER_SNOW_BUCKET);
    };

    public LemonLauncherItem(Properties p_40850_) {
        super(p_40850_);
    }

    public @NotNull Predicate<ItemStack> getAllSupportedProjectiles() {
        return COMBUSTIBLE_LEMON_ONLY;
    }

    public int getDefaultProjectileRange() {
        return 8;
    }

    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level p_40920_, Player p_40921_, @NotNull InteractionHand p_40922_) {
        ItemStack itemstack = p_40921_.getItemInHand(p_40922_);
        ItemStack itemstack1 = getHeldProjectile(p_40921_, LAVA_OR_POWDER_SNOW);
        byte temperature = (byte) 2;
        CompoundTag compoundtag1 = itemstack.getTagElement("Lemon Launcher");
        if (compoundtag1 != null && compoundtag1.contains("Temperature", 99)) {
            temperature = compoundtag1.getByte("Temperature");
        }
        if (itemstack1.is(Items.LAVA_BUCKET) && temperature < 2){
            if (!p_40920_.isClientSide) {
                temperature++;
                LemonLauncherItem.setTemperature(itemstack, temperature);
                itemstack1.shrink(1);
                if (itemstack1.isEmpty()) {
                    p_40921_.setItemInHand(p_40922_ == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND, new ItemStack(Items.BUCKET));
                }
            }
            return InteractionResultHolder.consume(itemstack);
        } else if (itemstack1.is(Items.POWDER_SNOW_BUCKET) && temperature > 0){
            if (!p_40920_.isClientSide) {
                temperature--;
                LemonLauncherItem.setTemperature(itemstack, temperature);
                itemstack1.shrink(1);
                if (itemstack1.isEmpty()) {
                    p_40921_.setItemInHand(p_40922_ == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND, new ItemStack(Items.BUCKET));
                }
            }
            return InteractionResultHolder.consume(itemstack);
        } else if (isCharged(itemstack)) {
            performShooting(p_40920_, p_40921_, p_40922_, itemstack, getShootingPower(itemstack), 1.0F);
            setCharged(itemstack, false);
            return InteractionResultHolder.consume(itemstack);
        } else if (!p_40921_.getProjectile(itemstack).isEmpty()) {
            if (!isCharged(itemstack)) {
                this.startSoundPlayed = false;
                this.midLoadSoundPlayed = false;
                p_40921_.startUsingItem(p_40922_);
            }

            return InteractionResultHolder.consume(itemstack);
        } else {
            return InteractionResultHolder.fail(itemstack);
        }
    }

    public static boolean isCharged(ItemStack p_40933_) {
        CompoundTag compoundtag = p_40933_.getTag();
        return compoundtag != null && compoundtag.getBoolean("Charged");
    }

    public static void performShooting(Level p_40888_, LivingEntity p_40889_, InteractionHand p_40890_, ItemStack p_40891_, float p_40892_, float p_40893_) {
        if (p_40889_ instanceof Player player && net.minecraftforge.event.ForgeEventFactory.onArrowLoose(p_40891_, p_40889_.level(), player, 1, true) < 0) return;
        ItemStack itemstack = getChargedProjectiles(p_40891_);

        if (!itemstack.isEmpty()) {
            shootProjectile(p_40888_, p_40889_, p_40890_, p_40891_, itemstack, 1.0F, p_40892_, p_40893_, 0.0F);
            if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.MULTI_LEMON_LAUNCHER.get(), p_40891_) > 0) {
                shootProjectile(p_40888_, p_40889_, p_40890_, p_40891_, itemstack, 1.0F, p_40892_, p_40893_, -10.0F);
                shootProjectile(p_40888_, p_40889_, p_40890_, p_40891_, itemstack, 1.0F, p_40892_, p_40893_, 10.0F);
                shootProjectile(p_40888_, p_40889_, p_40890_, p_40891_, itemstack, 1.0F, p_40892_, p_40893_, -20.0F);
                shootProjectile(p_40888_, p_40889_, p_40890_, p_40891_, itemstack, 1.0F, p_40892_, p_40893_, 20.0F);
                shootProjectile(p_40888_, p_40889_, p_40890_, p_40891_, itemstack, 1.0F, p_40892_, p_40893_, -30.0F);
                shootProjectile(p_40888_, p_40889_, p_40890_, p_40891_, itemstack, 1.0F, p_40892_, p_40893_, 30.0F);
            }
        }

        onCrossbowShot(p_40889_, p_40891_);
    }

    private static ItemStack getChargedProjectiles(ItemStack p_40942_) {
        ItemStack itemstack = null;
        CompoundTag compoundtag = p_40942_.getTag();
        if (compoundtag != null && compoundtag.contains("ChargedProjectiles", 9)) {
            ListTag listtag = compoundtag.getList("ChargedProjectiles", 10);
            if (listtag != null) {
                itemstack = ItemStack.of(listtag.getCompound(0));
            }
        }

        return itemstack;
    }

    private static void shootProjectile(Level p_40895_, LivingEntity p_40896_, InteractionHand p_40897_, ItemStack p_40898_, ItemStack p_40899_, float p_40900_, float p_40902_, float p_40903_, float p_40904_) {
        if (!p_40895_.isClientSide) {
            ThrownCombustibleLemon throwncombustiblelemon = getThrownCombustibleLemon(p_40895_, p_40896_, p_40898_, p_40899_);

            Vec3 vec31 = p_40896_.getUpVector(1.0F);
            Quaternionf quaternionf = (new Quaternionf()).setAngleAxis((double)(p_40904_ * ((float)Math.PI / 180F)), vec31.x, vec31.y, vec31.z);
            Vec3 vec3 = p_40896_.getViewVector(1.0F);
            Vector3f vector3f = vec3.toVector3f().rotate(quaternionf);
            throwncombustiblelemon.shoot((double)vector3f.x(), (double)vector3f.y(), (double)vector3f.z(), p_40902_, p_40903_);

            CompoundTag compoundtag = throwncombustiblelemon.getItem().getTagElement("Indirectly Combustible Lemon");
            float explosionSize = 0;
            if (compoundtag != null && compoundtag.contains("Explosion", 99)) {
                explosionSize = (float) MiscUtil.roughRounding(1.2 * Math.sqrt(explosionSize + (throwncombustiblelemon.getItem().getTagElement("Indirectly Combustible Lemon").getByte("Explosion") * Config.combustibleExplosionIncrement)), 2);
            } else {
                explosionSize = 1;
            }

            p_40898_.hurtAndBreak((int) explosionSize, p_40896_, (p_40858_) -> {
                p_40858_.broadcastBreakEvent(p_40897_);
            });
            p_40895_.addFreshEntity(throwncombustiblelemon);
            p_40895_.playSound((Player)null, p_40896_.getX(), p_40896_.getY(), p_40896_.getZ(), SoundEvents.ENDER_DRAGON_SHOOT, SoundSource.PLAYERS, 1.0F, p_40900_);
            p_40895_.playSound((Player)null, p_40896_.getX(), p_40896_.getY(), p_40896_.getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1.0F, p_40900_);
            p_40895_.playSound((Player)null, p_40896_.getX(), p_40896_.getY(), p_40896_.getZ(), SoundEvents.LEVER_CLICK, SoundSource.PLAYERS, 1.0F, p_40900_);
            p_40895_.playSound((Player)null, p_40896_.getX(), p_40896_.getY(), p_40896_.getZ(), SoundEvents.PISTON_EXTEND, SoundSource.PLAYERS, 1.0F, p_40900_);
        }
    }

    private static ThrownCombustibleLemon getThrownCombustibleLemon(Level p_40915_, LivingEntity p_40916_, ItemStack p_40917_, ItemStack p_40918_) {
        byte temperature = (byte) 2;
        CompoundTag compoundtag1 = p_40917_.getTagElement("Lemon Launcher");
        if (compoundtag1 != null && compoundtag1.contains("Temperature", 99)) {
            temperature = compoundtag1.getByte("Temperature");
        }
        ThrownCombustibleLemon throwncombustiblelemon = new ThrownCombustibleLemon(p_40915_, p_40916_, true, EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.LAUNCH.get(), p_40917_) + 1, EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.SAFEGUARD.get(), p_40917_), EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.CONTROLLED_DETONATION.get(), p_40917_) > 0, EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.BAZOOKA.get(), p_40917_) > 0, EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.MULTI_LEMON_LAUNCHER.get(), p_40917_) > 0, temperature);
        throwncombustiblelemon.setItem(p_40918_);

        return throwncombustiblelemon;
    }

    private static float getShootingPower(ItemStack p_40946_) {
        return 3.15F;
    }

    private static void onCrossbowShot(LivingEntity p_40907_, ItemStack p_40908_) {
        if (p_40907_ instanceof ServerPlayer serverplayer) {
            serverplayer.awardStat(Stats.ITEM_USED.get(p_40908_.getItem()));
        }

        clearChargedProjectiles(p_40908_);
    }

    private static void clearChargedProjectiles(ItemStack p_40944_) {
        CompoundTag compoundtag = p_40944_.getTag();
        if (compoundtag != null) {
            ListTag listtag = compoundtag.getList("ChargedProjectiles", 9);
            listtag.clear();
            compoundtag.put("ChargedProjectiles", listtag);
        }

    }

    public static void setCharged(ItemStack p_40885_, boolean p_40886_) {
        CompoundTag compoundtag = p_40885_.getOrCreateTag();
        compoundtag.putBoolean("Charged", p_40886_);
    }

    public void releaseUsing(ItemStack p_40875_, Level p_40876_, LivingEntity p_40877_, int p_40878_) {
        int i = this.getUseDuration(p_40875_) - p_40878_;
        float f = getPowerForTime(i, p_40875_);
        if (f >= 1.0F && !isCharged(p_40875_) && tryLoadProjectiles(p_40877_, p_40875_)) {
            setCharged(p_40875_, true);
            SoundSource soundsource = p_40877_ instanceof Player ? SoundSource.PLAYERS : SoundSource.HOSTILE;
            p_40876_.playSound((Player)null, p_40877_.getX(), p_40877_.getY(), p_40877_.getZ(), SoundEvents.PISTON_CONTRACT, soundsource, 1.0F, 1.0F / (p_40876_.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F);
            p_40876_.playSound((Player)null, p_40877_.getX(), p_40877_.getY(), p_40877_.getZ(), SoundEvents.LEVER_CLICK, soundsource, 1.0F, 1.0F / (p_40876_.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F);
            p_40876_.playSound((Player)null, p_40877_.getX(), p_40877_.getY(), p_40877_.getZ(), SoundEvents.NOTE_BLOCK_IMITATE_ENDER_DRAGON.get(), soundsource, getChargeDuration(p_40875_) / 30F, 1.0F / (p_40876_.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F);
        }

    }

    public static float getPowerForTime(int p_40854_, ItemStack p_40855_) {
        float f = (float)p_40854_ / (float)getChargeDuration(p_40855_);
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    public static int getChargeDuration(ItemStack p_40940_) {
        return EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.BAZOOKA.get(), p_40940_) > 0 ? 1 : (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.MULTI_LEMON_LAUNCHER.get(), p_40940_) > 0 ? 40 : 30);
    }

    private static boolean tryLoadProjectiles(LivingEntity p_40860_, ItemStack p_40861_) {
        boolean flag = p_40860_ instanceof Player && ((Player)p_40860_).getAbilities().instabuild;
        ItemStack itemstack = p_40860_.getProjectile(p_40861_);

        if (!loadProjectile(p_40860_, p_40861_, itemstack, flag)) {
            return false;
        }

        return true;
    }

    private static boolean loadProjectile(LivingEntity p_40863_, ItemStack p_40864_, ItemStack p_40865_, boolean p_40867_) {
        if (p_40865_.isEmpty()) {
            return false;
        } else {
            boolean flag = p_40867_ && p_40865_.getItem() instanceof CombustibleLemonItem;
            ItemStack itemstack;
            if (!flag && !p_40867_) {
                itemstack = p_40865_.split(1);
                if (p_40865_.isEmpty() && p_40863_ instanceof Player) {
                    ((Player)p_40863_).getInventory().removeItem(p_40865_);
                }
            } else {
                CompoundTag compoundtag = p_40865_.getTagElement("Indirectly Combustible Lemon");
                if (compoundtag != null && compoundtag.contains("Explosion", 99)) {
                    itemstack = p_40865_.copy();
                } else {
                    itemstack = new ItemStack(ModItems.COMBUSTIBLE_LEMON.get());
                    CombustibleLemonItem.setExplosionSize(itemstack, (byte) 1);
                }
            }

            addChargedProjectile(p_40864_, itemstack);
            return true;
        }
    }

    private static void addChargedProjectile(ItemStack p_40929_, ItemStack p_40930_) {
        CompoundTag compoundtag = p_40929_.getOrCreateTag();
        ListTag listtag;
        if (compoundtag.contains("ChargedProjectiles", 9)) {
            listtag = compoundtag.getList("ChargedProjectiles", 10);
        } else {
            listtag = new ListTag();
        }

        CompoundTag compoundtag1 = new CompoundTag();
        p_40930_.save(compoundtag1);
        listtag.add(compoundtag1);
        compoundtag.put("ChargedProjectiles", listtag);
    }

    public void onUseTick(Level p_40910_, LivingEntity p_40911_, ItemStack p_40912_, int p_40913_) {
        if (!p_40910_.isClientSide) {
            int i = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.BAZOOKA.get(), p_40912_) > 0 ? 1 : (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.MULTI_LEMON_LAUNCHER.get(), p_40912_) > 0 ? 2 : 0);
            SoundEvent soundevent = this.getStartSound(i);
            SoundEvent soundevent1 = i == 0 || i == 2 ? ModSoundEvents.LEMON_LAUNCHER_LOADING_MIDDLE.get() : null;
            float f = (float)(p_40912_.getUseDuration() - p_40913_) / (float)getChargeDuration(p_40912_);
            if (f < 0.2F) {
                this.startSoundPlayed = false;
                this.midLoadSoundPlayed = false;
            }

            if (f >= 0.2F && !this.startSoundPlayed) {
                this.startSoundPlayed = true;
                p_40910_.playSound((Player)null, p_40911_.getX(), p_40911_.getY(), p_40911_.getZ(), SoundEvents.PISTON_CONTRACT, SoundSource.PLAYERS, 0.5F, 1.0F);
                p_40910_.playSound((Player)null, p_40911_.getX(), p_40911_.getY(), p_40911_.getZ(), SoundEvents.LEVER_CLICK, SoundSource.PLAYERS, 0.5F, 1.0F);
            }

            if (f >= 0.5F && soundevent1 != null && !this.midLoadSoundPlayed) {
                this.midLoadSoundPlayed = true;
                p_40910_.playSound((Player)null, p_40911_.getX(), p_40911_.getY(), p_40911_.getZ(), SoundEvents.PISTON_CONTRACT, SoundSource.PLAYERS, 0.5F, 1.0F);
                p_40910_.playSound((Player)null, p_40911_.getX(), p_40911_.getY(), p_40911_.getZ(), SoundEvents.LEVER_CLICK, SoundSource.PLAYERS, 0.5F, 1.0F);
            }
        }

    }

    private SoundEvent getStartSound(int p_40852_) {
        switch (p_40852_) {
            case 1:
                return ModSoundEvents.LEMON_LAUNCHER_BAZOOKA.get();
            case 2:
                return ModSoundEvents.LEMON_LAUNCHER_MULTI.get();
            default:
                return ModSoundEvents.LEMON_LAUNCHER_LOADING_START.get();
        }
    }

    public int getUseDuration(ItemStack p_40938_) {
        return getChargeDuration(p_40938_) + 3;
    }

    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack p_40935_) {
        return UseAnim.NONE;
    }

    public void appendHoverText(ItemStack p_40880_, @Nullable Level p_40881_, @NotNull List<Component> p_40882_, @NotNull TooltipFlag p_40883_) {
        CompoundTag compoundtag1 = p_40880_.getTagElement("Lemon Launcher");
        if (compoundtag1 != null && compoundtag1.contains("Temperature", 99)) {
            switch (compoundtag1.getByte("Temperature")) {
                case 0:
                    p_40882_.add(Component.translatable("item.lemonmod.lemon_launcher.cold").withStyle(ChatFormatting.AQUA));
                    break;
                case 1:
                    p_40882_.add(Component.translatable("item.lemonmod.lemon_launcher.warm").withStyle(ChatFormatting.GRAY));
                    break;
                case 2:
                    p_40882_.add(Component.translatable("item.lemonmod.lemon_launcher.hot").withStyle(ChatFormatting.GOLD));
                    break;
                default:
                    p_40882_.add(Component.literal("???").withStyle(ChatFormatting.OBFUSCATED));
                    break;
            }
        }
        ItemStack itemstack = getChargedProjectiles(p_40880_);
        if (isCharged(p_40880_) && !(itemstack == null)) {
            CompoundTag compoundtag = itemstack.getTagElement("Indirectly Combustible Lemon");
            if (compoundtag != null) {
                if (compoundtag.contains("Explosion", 99)) {
                    p_40882_.add(Component.translatable("item.lemonmod.indirectly_combustible_lemon.explosion").append(CommonComponents.SPACE).append(String.valueOf(MiscUtil.roughRounding(1.2 * Math.sqrt(compoundtag.getByte("Explosion") * Config.combustibleExplosionIncrement + Config.fishExplosionSize), 2))).withStyle(ChatFormatting.GRAY));
                }
            }

        }
    }

    public boolean useOnRelease(ItemStack p_150801_) {
        return p_150801_.is(this);
    }

    public static void setTemperature(ItemStack p_260106_, byte p_260332_) {
        p_260106_.getOrCreateTagElement("Lemon Launcher").putByte("Temperature", p_260332_);
    }

    public void onCraftedBy(@NotNull ItemStack p_41447_, @NotNull Level p_41448_, @NotNull Player p_41449_) {
        LemonLauncherItem.setTemperature(p_41447_, (byte) 2);
    }

    public @NotNull ItemStack getDefaultInstance() {
        ItemStack itemstack = new ItemStack(this);
        setTemperature(itemstack, (byte)2);
        return itemstack;
    }

}
