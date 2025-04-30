package com.glektarssza.player_handling_customizer.mixins.late.specialmobs;

import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.glektarssza.player_handling_customizer.api.ITargetingImmunity;
import com.glektarssza.player_handling_customizer.utils.ImmunityUtils;
import com.glektarssza.player_handling_customizer.utils.PlayerUtils;

import toast.specialMobs.entity.ghast.Entity_SpecialGhast;

@Mixin(Entity_SpecialGhast.class)
public abstract class Entity_SpecialGhastMixin extends Entity_SpecialGhast {
    /**
     * Constructor to shut Java up.
     */
    public Entity_SpecialGhastMixin(World world) {
        super(world);
    }

    @Inject(method = "updateEntityTarget", at = @At("TAIL"), cancellable = true, remap = false)
    public void updateEntityTarget(CallbackInfo ci) {
        EntityLiving attacker = this;
        EntityLivingBase target = null;
        if (this.targetedEntity == null) {
            return;
        }
        if (!(this.targetedEntity instanceof EntityLivingBase)) {
            return;
        }
        target = (EntityLivingBase) this.targetedEntity;
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
