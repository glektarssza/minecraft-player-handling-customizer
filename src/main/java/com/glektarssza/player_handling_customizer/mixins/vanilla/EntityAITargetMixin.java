package com.glektarssza.player_handling_customizer.mixins.vanilla;

import java.util.List;

import javax.annotation.Nullable;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.glektarssza.player_handling_customizer.api.ITargetingImmunity;
import com.glektarssza.player_handling_customizer.utils.ImmunityUtils;
import com.glektarssza.player_handling_customizer.utils.PlayerUtils;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.player.EntityPlayer;

@Mixin(EntityAITarget.class)
public class EntityAITargetMixin {
    @Inject(
        method =
            "Lnet/minecraft/entity/ai/EntityAITarget;isSuitableTarget(Lnet/minecraft/entity/EntityLiving;Lnet/minecraft/entity/EntityLivingBase;ZZ)Z",
        at = @At("TAIL"),
        cancellable = true
    )
    private static void
    isSuitableTarget(
        EntityLiving attacker,
        @Nullable EntityLivingBase target,
        boolean includeInvincibles,
        boolean checkSight,
        CallbackInfoReturnable<Boolean> cir
    ) {
        if (!(target instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer)target;
        List<ITargetingImmunity> immunities =
            PlayerUtils.getPlayerTargetingImmunities(player);
        if (ImmunityUtils.entityMatchesAnyTargetingImmunity(
                attacker, immunities
            ) ||
            PlayerUtils.getIsPlayerGloballyImmune(player)) {
            cir.setReturnValue(false);
        }
    }
}
