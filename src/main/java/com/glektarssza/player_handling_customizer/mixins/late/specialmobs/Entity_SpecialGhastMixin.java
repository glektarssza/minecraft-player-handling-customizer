package com.glektarssza.player_handling_customizer.mixins.late.specialmobs;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.glektarssza.player_handling_customizer.api.ITargetingImmunity;
import com.glektarssza.player_handling_customizer.utils.ImmunityUtils;
import com.glektarssza.player_handling_customizer.utils.PlayerUtils;

import toast.specialMobs.entity.ghast.Entity_SpecialGhast;

@Mixin(value = Entity_SpecialGhast.class, remap = false)
public class Entity_SpecialGhastMixin {
    @Inject(method = "updateEntityTarget", at = @At("TAIL"), cancellable = true, remap = false)
    public void updateEntityTarget(CallbackInfo ci) {
        Entity_SpecialGhast self = (Entity_SpecialGhast) (Object) this;
        EntityLiving attacker = self;
        EntityLivingBase target = null;
        if (self.targetedEntity == null) {
            return;
        }
        if (!(self.targetedEntity instanceof EntityLivingBase)) {
            return;
        }
        target = (EntityLivingBase) self.targetedEntity;
        if (!(target instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) target;
        List<ITargetingImmunity> immunities = PlayerUtils
            .getPlayerTargetingImmunities(player);
        if (ImmunityUtils.entityMatchesAnyTargetingImmunity(attacker,
            immunities) || PlayerUtils.getIsPlayerGloballyImmune(player)) {
            self.targetedEntity = null;
        }
    }
}
