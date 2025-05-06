package com.glektarssza.player_handling_customizer.mixins.late.thaumcraft;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;

import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.glektarssza.player_handling_customizer.api.ITargetingImmunity;
import com.glektarssza.player_handling_customizer.utils.ImmunityUtils;
import com.glektarssza.player_handling_customizer.utils.PlayerUtils;

import thaumcraft.common.entities.monster.EntityWisp;

/**
 * Mixin for the {@code EntityWisp} class.
 */
@Mixin(value = EntityWisp.class, remap = false)
public class EntityWispMixin {
    /**
     * A shadow of the {@code targetedEntity} field.
     */
    @Shadow(remap = false)
    private Entity targetedEntity;

    /**
     * Mixin for the {@code attackEntityFrom} and
     * {@code updateEntityActionState} methods.
     */
    @Inject(method = {
        "attackEntityFrom",
        "updateEntityActionState"
    }, at = @At(value = "FIELD", target = "Lthaumcraft/common/entities/monster/EntityWisp;targetedEntity:Lnet/minecraft/entity/Entity;", opcode = Opcodes.PUTFIELD, shift = Shift.AFTER, remap = false), cancellable = true, remap = false)
    public void overrideTargetedEntity(DamageSource damageSource, float amount,
        CallbackInfoReturnable<Boolean> cir) {
        EntityWisp self = (EntityWisp) (Object) this;
        EntityLiving attacker = self;
        if (this.targetedEntity == null) {
            return;
        }
        if (!(targetedEntity instanceof EntityLivingBase)) {
            return;
        }
        EntityLivingBase target = (EntityLivingBase) targetedEntity;
        if (!(target instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) target;
        List<ITargetingImmunity> immunities = PlayerUtils
            .getPlayerTargetingImmunities(player);
        if (ImmunityUtils.entityMatchesAnyTargetingImmunity(attacker,
            immunities) || PlayerUtils.getIsPlayerGloballyImmune(player)) {
            this.targetedEntity = null;
        }
    }
}
