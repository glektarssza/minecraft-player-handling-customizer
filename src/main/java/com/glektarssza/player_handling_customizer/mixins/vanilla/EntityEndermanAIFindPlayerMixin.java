package com.glektarssza.player_handling_customizer.mixins.vanilla;

import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityEnderman.AIFindPlayer;
import net.minecraft.entity.player.EntityPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.glektarssza.player_handling_customizer.api.ITargetingImmunity;
import com.glektarssza.player_handling_customizer.utils.ImmunityUtils;
import com.glektarssza.player_handling_customizer.utils.PlayerUtils;

@Mixin(AIFindPlayer.class)
public class EntityEndermanAIFindPlayerMixin {
    @Inject(method = "Lnet/minecraft/entity/monster/EntityEnderman$AIFindPlayer;shouldExecute()Z", at = @At("TAIL"), cancellable = true)
    public void shouldExecute(CallbackInfoReturnable<Boolean> cir) {
        AIFindPlayer self = (AIFindPlayer) (Object) this;
        EntityLiving attacker = (EntityLiving) self.enderman;
        EntityPlayer player = self.player;
        if (player == null) {
            return;
        }
        List<ITargetingImmunity> immunities = PlayerUtils
            .getPlayerTargetingImmunities(player);
        if (ImmunityUtils.entityMatchesAnyTargetingImmunity(attacker,
            immunities) || PlayerUtils.getIsPlayerGloballyImmune(player)) {
            cir.setReturnValue(false);
        }
    }
}
