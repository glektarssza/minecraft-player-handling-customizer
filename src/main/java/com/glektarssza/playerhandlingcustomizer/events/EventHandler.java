package com.glektarssza.playerhandlingcustomizer.events;

import java.util.List;

import com.glektarssza.playerhandlingcustomizer.api.IImmunity;
import com.glektarssza.playerhandlingcustomizer.api.IPlayerImmunity;
import com.glektarssza.playerhandlingcustomizer.capability.IImmunePlayerWorldCapability;
import com.glektarssza.playerhandlingcustomizer.capability.ImmunePlayerWorldCapabilityHandler;
import com.glektarssza.playerhandlingcustomizer.utils.ListUtils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * The main mod event handler.
 */
@Mod.EventBusSubscriber
public class EventHandler {
    /**
     * Handle entities being damaged.
     *
     * @param event The event to handle.
     */
    @SubscribeEvent
    public static void onLivingDamageEvent(LivingDamageEvent event) {
        Entity entity = event.getEntity();
        if (entity == null) {
            return;
        }
        if (!(entity instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) entity;
        World world = entity.getEntityWorld();
        if (world == null) {
            return;
        }
        IImmunePlayerWorldCapability worldCap = world.getCapability(
            ImmunePlayerWorldCapabilityHandler.IMMUNE_WORLD_CAPABILITY, null);
        if (worldCap == null) {
            return;
        }
        List<IPlayerImmunity> immunities = worldCap.getPlayerImmunities();
        IPlayerImmunity immunity = ListUtils.find(immunities,
            (playerImmunity) -> playerImmunity.getPlayerIdentifier()
                .matches(player));
        if (immunity == null) {
            return;
        }
        IImmunity matchingImmunity = ListUtils.find(
            immunity.getDamageEventImmunities(),
            (damageImmunity) -> damageImmunity.matches(event.getSource()));
        if (matchingImmunity != null && event.isCancelable()) {
            event.setCanceled(true);
        }
    }
}
