package com.glektarssza.player_handling_customizer.mixins.early.vanilla;

import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;

import org.spongepowered.asm.mixin.Mixin;

/**
 * Mixin for the {@code EntityAIOwnerHurtTarget} class.
 */
@Mixin(EntityAIOwnerHurtTarget.class)
public class EntityAIOwnerHurtTargetMixin {
    // -- Right now this does nothing as it inherits from EntityAITarget
}
