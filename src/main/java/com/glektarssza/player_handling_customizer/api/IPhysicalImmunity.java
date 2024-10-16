package com.glektarssza.player_handling_customizer.api;

import javax.annotation.Nullable;

/**
 * An interface that represents immunity from physical events.
 */
public interface IPhysicalImmunity extends IImmunity {
    /**
     * Get the type of damage this instance represents immunity from.
     *
     * @return The type of damage this instance represents immunity from.
     */
    String getDamageType();

    /**
     * Set the type of damage this instance represents immunity from.
     *
     * @param damageType The type of damage this instance represents immunity
     *        from.
     */
    void setDamageType(String damageType);

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

    /**
     * Get whether this instance applies to direct damage sources.
     *
     * @return Whether this instance applies to direct damage sources.
     */
    boolean getAppliesToDirectDamage();

    /**
     * Set whether this instance applies to direct damage sources.
     *
     * @param value Whether this instance applies to direct damage sources.
     */
    void setAppliesToDirectDamage(boolean value);

    /**
     * Get whether this instance applies to indirect damage sources.
     *
     * @return Whether this instance applies to indirect damage sources.
     */
    boolean getAppliesToIndirectDamage();

    /**
     * Set whether this instance applies to indirect damage sources.
     *
     * @param value Whether this instance applies to indirect damage sources.
     */
    void setAppliesToIndirectDamage(boolean value);
}
