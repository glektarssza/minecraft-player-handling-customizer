package com.glektarssza.playerhandlingcustomizer.api;

import javax.annotation.Nullable;

/**
 * An interface that represents immunity from damage events.
 */
public interface IDamageImmunity extends IImmunity {
    /**
     * Get the type of damage this instance represents immunity from.
     *
     * @return The type of damage this instance represents immunity from.
     */
    String getDamageType();

    /**
     * Get whether this instance has an entity type from which this instance
     * grants immunity from.
     *
     * @return Whether this instance has an entity type from which this instance
     *         grants immunity from.
     */
    boolean hasEntityType();

    /**
     * Get the entity source that this instance grants immunity to damage from.
     *
     * @return The entity source that this instance grants immunity to damage
     *         from.
     */
    @Nullable
    String getEntityType();
}
