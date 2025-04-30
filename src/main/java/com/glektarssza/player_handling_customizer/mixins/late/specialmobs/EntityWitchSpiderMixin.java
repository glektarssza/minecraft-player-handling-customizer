package com.glektarssza.player_handling_customizer.mixins.late.specialmobs;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.glektarssza.player_handling_customizer.api.ITargetingImmunity;
import com.glektarssza.player_handling_customizer.utils.ImmunityUtils;
import com.glektarssza.player_handling_customizer.utils.PlayerUtils;

import toast.specialMobs.entity.spider.EntityWitchSpider;

/**
 * Mixin for the {@code EntityWitchSpider} class.
 */
@Mixin(value = EntityWitchSpider.class, remap = false)
public class EntityWitchSpiderMixin {
    @Inject(method = "attackEntityFrom", at = @At("TAIL"), cancellable = false, remap = false)
    public void attackEntityFrom(DamageSource damageSource, float damage,
        CallbackInfoReturnable<Boolean> cir) {
        EntityWitchSpider self = (EntityWitchSpider) (Object) this;
        EntityLivingBase attacker = self;
        EntityLivingBase target = self.getAttackTarget();
        if (target == null) {
            return;
        }
        if (!(target instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) target;
        List<ITargetingImmunity> immunities = PlayerUtils
            .getPlayerTargetingImmunities(player);
        if (ImmunityUtils.entityMatchesAnyTargetingImmunity(attacker,
            immunities) || PlayerUtils.getIsPlayerGloballyImmune(player)) {
            self.setTarget(null);
        }
    }
}
