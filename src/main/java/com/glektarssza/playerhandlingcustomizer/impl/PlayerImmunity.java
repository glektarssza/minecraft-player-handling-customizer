package com.glektarssza.playerhandlingcustomizer.impl;

import java.util.List;

import com.glektarssza.playerhandlingcustomizer.api.IImmunity;
import com.glektarssza.playerhandlingcustomizer.api.IPlayerIdentifier;
import com.glektarssza.playerhandlingcustomizer.api.IPlayerImmunity;

/**
 * A concrete implementation of the {@link IPlayerImmunity} interface.
 */
public class PlayerImmunity implements IPlayerImmunity {
    /**
     * The player identifier for this instance.
     */
    private IPlayerIdentifier playerIdentifier;

    /**
     * The list of immunities the player has to damage events.
     */
    private List<IImmunity> damageEventImmunities;

    /**
     * The list of immunities the player has to hurt events.
     */
    private List<IImmunity> hurtEventImmunities;

    /**
     * The list of immunities the player has to knockback events.
     */
    private List<IImmunity> knockbackEventImmunities;

    /**
     * The list of immunities the player has to being targeted by certain
     * entities.
     */
    private List<String> entityTargetingImmunities;

    /**
     * Create a new instance.
     *
     * @param playerIdentifier The player identifier for this immunity set.
     * @param damageImmunities The damage events the player will be immune to.
     * @param hurtImmunities The hurt events the player will be immune to.
     * @param knockbackImmunities The knockback events the player will be immune
     *        to.
     * @param targetingImmunities The entities the player will be immune to
     *        being targeted by.
     */
    public PlayerImmunity(IPlayerIdentifier playerIdentifier,
        List<IImmunity> damageImmunities, List<IImmunity> hurtImmunities,
        List<IImmunity> knockbackImmunities, List<String> targetingImmunities) {
        this.playerIdentifier = playerIdentifier;
        this.damageEventImmunities = damageImmunities;
        this.hurtEventImmunities = hurtImmunities;
        this.knockbackEventImmunities = knockbackImmunities;
        this.entityTargetingImmunities = targetingImmunities;
    }

    /**
     * Get the identifier of the player this instance applies to.
     *
     * @return The identifier of the player this instance applies to.
     */
    @Override
    public IPlayerIdentifier getPlayerIdentifier() {
        return this.playerIdentifier;
    }

    /**
     * Whether this immunity covers any damage-related events.
     *
     * @return {@code true} if this immunity covers any damage-related events;
     *         {@code false} otherwise.
     */
    @Override
    public boolean hasAnyDamageEventImmunities() {
        return this.damageEventImmunities.size() > 0;
    }

    /**
     * Whether this immunity covers any hurt-related events.
     *
     * @return {@code true} if this immunity covers any hurt-related events;
     *         {@code false} otherwise.
     */
    @Override
    public boolean hasAnyHurtEventImmunities() {
        return this.hurtEventImmunities.size() > 0;
    }

    /**
     * Whether this immunity covers any knockback-related events.
     *
     * @return {@code true} if this immunity covers any knockback-related
     *         events; {@code false} otherwise.
     */
    @Override
    public boolean hasAnyKnockbackEventImmunities() {
        return this.knockbackEventImmunities.size() > 0;
    }

    /**
     * Whether this immunity covers any targeting-related events.
     *
     * @return {@code true} if this immunity covers any targeting-related
     *         events; {@code false} otherwise.
     */
    @Override
    public boolean hasAnyTargetingEventImmunities() {
        return this.entityTargetingImmunities.size() > 0;
    }

    /**
     * Get the list of damage event-related immunities the player has.
     *
     * @return The list of damage event-related immunities the player has.
     */
    @Override
    public List<IImmunity> getDamageEventImmunities() {
        return this.damageEventImmunities;
    }

    /**
     * Get the list of damage event-related immunities the player has.
     *
     * @return The list of damage event-related immunities the player has.
     */
    @Override
    public List<IImmunity> getHurtEventImmunities() {
        return this.hurtEventImmunities;
    }

    /**
     * Get the list of damage event-related immunities the player has.
     *
     * @return The list of damage event-related immunities the player has.
     */
    @Override
    public List<IImmunity> getKnockbackEventImmunities() {
        return this.knockbackEventImmunities;
    }

    /**
     * Get the list of entities that the player is immune to being targeted by.
     *
     * @return The list of entities that the player is immune to being targeted
     *         by.
     */
    @Override
    public List<String> getTargetingEventImmunities() {
        return this.entityTargetingImmunities;
    }
}
