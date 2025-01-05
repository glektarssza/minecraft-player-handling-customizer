package com.glektarssza.player_handling_customizer.api;

import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * An interface that defines an immunity of some kind.
 */
public interface IImmunity extends INBTSerializable<NBTTagCompound> {
    /**
     * Get the type of immunity represented by this instance.
     *
     * @return The type of immunity represented by this instance.
     */
    ImmunityType getImmunityType();

    /**
     * Get whether this instance has an entity type from which this instance
     * grants immunity from.
     *
     * @return Whether this instance has an entity type from which this instance
     *         grants immunity from.
     */
    boolean hasEntityType();

    /**
     * Get the entity type that this instance grants immunity to damage from.
     *
     * @return The entity type that this instance grants immunity to damage
     *         from.
     */
    @Nullable
    String getEntityType();

    /**
     * Set the entity type that this instance grants immunity to damage from.
     *
     * @param entityType The entity type that this instance grants immunity to
     *        damage from.
     */
    void setEntityType(@Nullable String entityType);
}
