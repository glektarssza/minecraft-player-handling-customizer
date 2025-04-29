package com.glektarssza.player_handling_customizer.mixins.early.vanilla;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.player.EntityPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import com.glektarssza.player_handling_customizer.api.ITargetingImmunity;
import com.glektarssza.player_handling_customizer.utils.ImmunityUtils;
import com.glektarssza.player_handling_customizer.utils.PlayerUtils;

@Mixin(EntityGhast.class)
public class EntityGhastMixin {
    @Shadow
    private Entity targetedEntity;

    @Inject(method = "updateEntityActionState", at = @At(value = "INVOKE_ASSIGN", target = "net.minecraft.world.World.getClosestVulnerablePlayerToEntity(Lnet/minecraft/entity/Entity;D)Lnet/minecraft/entity/player/EntityPlayer;"), cancellable = false)
    public void overrideTargetedEntity() {
        EntityGhast self = (EntityGhast) (Object) this;
        EntityLiving attacker = self;
        Entity target = this.targetedEntity;
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
            this.targetedEntity = null;
        }
    }
}
