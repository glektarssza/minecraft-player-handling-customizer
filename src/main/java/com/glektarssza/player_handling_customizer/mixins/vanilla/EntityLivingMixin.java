package com.glektarssza.player_handling_customizer.mixins.vanilla;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.glektarssza.player_handling_customizer.api.ITargetingImmunity;
import com.glektarssza.player_handling_customizer.utils.ImmunityUtils;
import com.glektarssza.player_handling_customizer.utils.PlayerUtils;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

/**
 * A mixin into the {@link EntityLiving} class.
 */
@Mixin(EntityLiving.class)
public class EntityLivingMixin {
    @Inject(method = "setAttackTarget", at = @At("HEAD"), cancellable = true)
    public void setAttackTarget(EntityLivingBase newTarget, CallbackInfo ci) {
        EntityLiving self = (EntityLiving) (Object) this;
        if (!(newTarget instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) newTarget;
        List<ITargetingImmunity> immunities = PlayerUtils
            .getPlayerTargetingImmunities(player);
        if (ImmunityUtils.entityMatchesAnyTargetingImmunity(self, immunities)) {
            ci.cancel();
        }
    }
}
