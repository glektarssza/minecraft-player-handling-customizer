package com.glektarssza.playerhandlingcustomizer.impl;

import javax.annotation.Nullable;

import com.glektarssza.playerhandlingcustomizer.api.IKnockbackImmunity;

/**
 * A concrete implementation of the {@link IKnockbackImmunity} interface.
 */
public class KnockbackImmunity implements IKnockbackImmunity {
    /**
     * The entity source that this instance grants immunity to damage from.
     */
    private String entityType;

    /**
     * Whether this instance applies to direct knockback sources.
     */
    private boolean appliesToDirect;

    /**
     * Whether this instance applies to indirect knockback sources.
     */
    private boolean appliesToIndirect;

    /**
     * Create a new instance.
     */
    public KnockbackImmunity() {
        this.entityType = null;
        this.appliesToDirect = false;
        this.appliesToIndirect = false;
    }

    /**
     * Create a new instance.
     *
     * @param entityType The entity type to create the new instance with.
     */
    public KnockbackImmunity(String entityType) {
        this.entityType = entityType;
        this.appliesToDirect = false;
        this.appliesToIndirect = false;
    }

    /**
     * Create a new instance.
     *
     * @param entityType The entity type to create the new instance with.
     * @param appliesToDirect Whether the new instances applies to direct damage
     *        sources.
     */
    public KnockbackImmunity(String entityType, boolean appliesToDirect) {
        this.entityType = entityType;
        this.appliesToDirect = appliesToDirect;
        this.appliesToIndirect = false;
    }

    /**
     * Create a new instance.
     *
     * @param entityType The entity type to create the new instance with.
     * @param appliesToDirect Whether the new instances applies to direct
     *        knockback sources.
     * @param appliesToIndirect Whether the new instances applies to indirect
     *        knockback sources.
     */
    public KnockbackImmunity(String entityType, boolean appliesToDirect,
        boolean appliesToIndirect) {
        this.entityType = entityType;
        this.appliesToDirect = appliesToDirect;
        this.appliesToIndirect = false;
    }

    /**
     * Get whether this instance has an entity type from which this instance
     * grants immunity from.
     *
     * @return Whether this instance has an entity type from which this instance
     *         grants immunity from.
     */
    @Override
    public boolean hasEntityType() {
        return this.entityType != null;
    }

    /**
     * Get the entity source that this instance grants immunity to damage from.
     *
     * @return The entity source that this instance grants immunity to damage
     *         from.
     */
    @Override
    @Nullable
    public String getEntityType() {
        return this.entityType;
    }

    /**
     * Set the entity type that this instance grants immunity to damage from.
     *
     * @param entityType The entity type that this instance grants immunity to
     *        damage from.
     */
    @Override
    public void setEntityType(@Nullable String entityType) {
        this.entityType = entityType;
    }

    /**
     * Get whether this instance applies to direct damage sources.
     *
     * @return Whether this instance applies to direct damage sources.
     */
    @Override
    public boolean getAppliesToDirectDamage() {
        return this.appliesToDirect;
    }

    /**
     * Set whether this instance applies to direct damage sources.
     *
     * @param value Whether this instance applies to direct damage sources.
     */
    @Override
    public void setAppliesToDirectDamage(boolean value) {
        this.appliesToDirect = value;
    }

    /**
     * Get whether this instance applies to indirect damage sources.
     *
     * @return Whether this instance applies to indirect damage sources.
     */
    @Override
    public boolean getAppliesToIndirectDamage() {
        return this.appliesToIndirect;
    }

    /**
     * Set whether this instance applies to indirect damage sources.
     *
     * @param value Whether this instance applies to indirect damage sources.
     */
    @Override
    public void setAppliesToIndirectDamage(boolean value) {
        this.appliesToIndirect = value;
    }
}
