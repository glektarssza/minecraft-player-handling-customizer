package com.glektarssza.playerhandlingcustomizer.api;

import javax.annotation.Nullable;

/**
 * An interface that represents immunity from knockback events.
 */
public interface IKnockbackImmunity extends IImmunity {
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
