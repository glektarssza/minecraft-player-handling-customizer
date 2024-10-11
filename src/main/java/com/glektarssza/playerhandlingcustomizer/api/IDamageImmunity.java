package com.glektarssza.playerhandlingcustomizer.api;

import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * A definition of a damage immunity.
 */
public interface IDamageImmunity extends INBTSerializable<NBTTagCompound> {
    /**
     * Whether this instance has a specific damage type attached to it.
     *
     * @return {@code true} if this instance has a specific damage type attached
     *         to it; {@code false} otherwise.
     */
    boolean hasDamageType();

    /**
     * Get the damage type that this instance represents immunity from.
     *
     * @return The damage type that this instance represents immunity from.
     */
    @Nullable
    String getDamageType();

    /**
     * Whether this instance has a specific entity type attached to it.
     *
     * @return {@code true} if this instance has a specific entity type attached
     *         to it; {@code false} otherwise.
     */
    boolean hasEntityType();

    /**
     * Get the entity type that this instance represents immunity to damage
     * from.
     *
     * @return The entity type that this instance represents immunity to damage
     *         from.
     */
    @Nullable
    String getEntityType();

    /**
     * Whether the immunity granted by this instance is to direct damage,
     * indirect damage, or both.
     *
     * @return The direction of damage this immunity is for.
     */
    DamageDirection getDamageDirection();

    /**
     * Test this immunity against a given damage source.
     *
     * @param damageSource The damage source to test this immunity against.
     *
     * @return {@code true} if this instance matches the given damage source;
     *         {@code false} otherwise.
     */
    boolean matches(DamageSource damageSource);
}
