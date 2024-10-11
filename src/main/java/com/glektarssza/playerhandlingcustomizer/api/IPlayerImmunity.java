package com.glektarssza.playerhandlingcustomizer.api;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * An interface defining what kinds of immunities a player has.
 */
public interface IPlayerImmunity extends INBTSerializable<NBTTagCompound> {
    /**
     * Get the identifier of the player this instance applies to.
     *
     * @return The identifier of the player this instance applies to.
     */
    IPlayerIdentifier getPlayerIdentifier();

    /**
     * Whether this immunity covers any damage-related events.
     *
     * @return {@code true} if this immunity covers any damage-related events;
     *         {@code false} otherwise.
     */
    boolean hasAnyDamageEventImmunities();

    /**
     * Whether this immunity covers any hurt-related events.
     *
     * @return {@code true} if this immunity covers any hurt-related events;
     *         {@code false} otherwise.
     */
    boolean hasAnyHurtEventImmunities();

    /**
     * Whether this immunity covers any knockback-related events.
     *
     * @return {@code true} if this immunity covers any knockback-related
     *         events; {@code false} otherwise.
     */
    boolean hasAnyKnockbackEventImmunities();

    /**
     * Whether this immunity covers any targeting-related events.
     *
     * @return {@code true} if this immunity covers any targeting-related
     *         events; {@code false} otherwise.
     */
    boolean hasAnyTargetingEventImmunities();
}
