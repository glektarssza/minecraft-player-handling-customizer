package com.glektarssza.player_handling_customizer.mixins.early.vanilla;

import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.player.EntityPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.glektarssza.player_handling_customizer.api.ITargetingImmunity;
import com.glektarssza.player_handling_customizer.utils.ImmunityUtils;
import com.glektarssza.player_handling_customizer.utils.PlayerUtils;

/**
 * Mixin for the {@code EntityAIArrowAttackMixin} class.
 */
@Mixin(EntityAIArrowAttack.class)
public class EntityAIArrowAttackMixin {
    /**
     * A shadow of the {@code entityHost} field.
     */
    @Shadow
    private EntityLiving entityHost;

    /**
     * Mixin for the {@code shouldExecute} method.
     */
    @SuppressWarnings("unused")
    @Inject(method = "shouldExecute", at = @At("TAIL"), cancellable = true)
    private void shouldExecute(CallbackInfoReturnable<Boolean> cir) {
        EntityAIArrowAttack self = (EntityAIArrowAttack) (Object) this;
        EntityLiving attacker = entityHost;
        EntityLivingBase target = this.entityHost.getAttackTarget();
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
