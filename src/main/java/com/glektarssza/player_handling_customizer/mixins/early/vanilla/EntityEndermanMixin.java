package com.glektarssza.player_handling_customizer.mixins.early.vanilla;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.glektarssza.player_handling_customizer.api.ITargetingImmunity;
import com.glektarssza.player_handling_customizer.utils.ImmunityUtils;
import com.glektarssza.player_handling_customizer.utils.PlayerUtils;

@Mixin(EntityEnderman.class)
public class EntityEndermanMixin {
    @Inject(method = "Lnet/minecraft/entity/monster/EntityEnderman;findPlayerToAttack()Lnet/minecraft/entity/Entity;", at = @At("TAIL"), cancellable = true)
    public void shouldExecute(CallbackInfoReturnable<Entity> cir) {
        EntityEnderman self = (EntityEnderman) (Object) this;
        EntityLiving attacker = (EntityLiving) self;
        Entity returnValue = cir.getReturnValue();
        EntityPlayer player = null;
        if (returnValue instanceof EntityPlayer) {
            player = (EntityPlayer) returnValue;
        }
        if (player == null) {
            return;
        }
        List<ITargetingImmunity> immunities = PlayerUtils
            .getPlayerTargetingImmunities(player);
        if (ImmunityUtils.entityMatchesAnyTargetingImmunity(attacker,
            immunities) || PlayerUtils.getIsPlayerGloballyImmune(player)) {
            cir.setReturnValue(null);
        }
    }
}
