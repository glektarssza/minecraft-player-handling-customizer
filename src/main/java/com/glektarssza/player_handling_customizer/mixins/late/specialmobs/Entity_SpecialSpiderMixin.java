package com.glektarssza.player_handling_customizer.mixins.late.specialmobs;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import toast.specialMobs.entity.spider.Entity_SpecialSpider;

/**
 * Mixin for the {@code Entity_SpecialSpider} class.
 */
@Mixin(Entity_SpecialSpider.class)
public abstract class Entity_SpecialSpiderMixin extends Entity_SpecialSpider {
    /**
     * Constructor to shut Java up.
     */
    public Entity_SpecialSpiderMixin(World world) {
        super(world);
    }

    /**
     * Mixin for the {@code findPlayerToAttack} method.
     */
    @Inject(method = "findPlayerToAttack", at = @At(value = "INVOKE", target = "net.minecraft.entity.monster.EntitySpider.findPlayerToAttack()Lnet/minecraft/entity/Entity;"), cancellable = false, remap = false)
    public void findPlayerToAttack(CallbackInfoReturnable<Entity> cir) {
        this.fakeDarkness = false;
    }
}
