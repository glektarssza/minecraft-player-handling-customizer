package com.glektarssza.player_handling_customizer.mixins.late.specialmobs;

import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import toast.specialMobs.entity.spider.Entity_SpecialSpider;

/**
 * Mixin for the {@code Entity_SpecialSpider} class.
 */
@Mixin(Entity_SpecialSpider.class)
public class Entity_SpecialSpiderMixin {
    /**
     * A shadow of the {@code fakeDarkness} field.
     */
    @Shadow
    private boolean fakeDarkness;

    /**
     * Mixin for the {@code findPlayerToAttack} method.
     */
    @Inject(method = "findPlayerToAttack", at = @At(value = "INVOKE", target = "net.minecraft.entity.monster.EntitySpider.findPlayerToAttack()Lnet/minecraft/entity/Entity;"), cancellable = false, remap = false)
    public void findPlayerToAttack(CallbackInfoReturnable<Entity> cir) {
        this.fakeDarkness = false;
    }
}
