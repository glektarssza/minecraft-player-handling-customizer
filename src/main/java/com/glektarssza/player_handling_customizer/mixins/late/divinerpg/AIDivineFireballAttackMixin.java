package com.glektarssza.player_handling_customizer.mixins.late.divinerpg;

import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import divinerpg.objects.entities.ai.AIDivineFireballAttack;

import com.glektarssza.player_handling_customizer.api.ITargetingImmunity;
import com.glektarssza.player_handling_customizer.utils.ImmunityUtils;
import com.glektarssza.player_handling_customizer.utils.PlayerUtils;

@Mixin(AIDivineFireballAttack.class)
public class AIDivineFireballAttackMixin {
    @Shadow(remap = false)
    private EntityLiving parentEntity;

    @Inject(method = "shouldExecute", at = @At("RETURN"), cancellable = true)
    public void shouldExecute(CallbackInfoReturnable<Boolean> cir) {
        EntityLiving attacker = this.parentEntity;
        EntityLivingBase target = (EntityLivingBase) this.parentEntity
            .getAttackTarget();
        if (!(target instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) target;
        List<ITargetingImmunity> immunities = PlayerUtils
            .getPlayerTargetingImmunities(player);
        if (ImmunityUtils.entityMatchesAnyTargetingImmunity(attacker,
            immunities) || PlayerUtils.getIsPlayerGloballyImmune(player)) {
            cir.setReturnValue(false);
        }
    }
}
