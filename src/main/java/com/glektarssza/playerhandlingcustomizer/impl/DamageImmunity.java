package com.glektarssza.playerhandlingcustomizer.impl;

import javax.annotation.Nullable;

import com.glektarssza.playerhandlingcustomizer.api.DamageDirection;
import com.glektarssza.playerhandlingcustomizer.api.IDamageImmunity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.util.Constants.NBT;

/**
 * A concrete implementation of the {@link IDamageImmunity} interface.
 */
public class DamageImmunity implements IDamageImmunity {
    /**
     * The type of damage this instance represents immunity to.
     */
    @Nullable
    private String damageType;

    /**
     * The type of entity this instance represents immunity to damage from.
     */
    @Nullable
    private String entityType;

    /**
     * The damage direction.
     */
    private DamageDirection damageDirection;

    /**
     * Create a new instance.
     *
     * @param damageType The type of damage the new instance will grant immunity
     *        from.
     * @param entityType The type of entity the instance will grant immunity to
     *        damage from.
     * @param damageDirection The type of damage (direct or indirect) the new
     *        instance will grant immunity from.
     */
    public DamageImmunity(@Nullable String damageType,
        @Nullable String entityType, DamageDirection damageDirection) {
        this.damageType = damageType;
        this.entityType = entityType;
        this.damageDirection = damageDirection;
    }

    /**
     * Whether this instance has a specific damage type attached to it.
     *
     * @return {@code true} if this instance has a specific damage type attached
     *         to it; {@code false} otherwise.
     */
    @Override
    public boolean hasDamageType() {
        return this.damageType != null;
    }

    /**
     * Get the damage type that this instance represents immunity from.
     *
     * @return The damage type that this instance represents immunity from.
     */
    @Nullable
    @Override
    public String getDamageType() {
        return this.damageType;
    }

    /**
     * Whether this instance has a specific entity type attached to it.
     *
     * @return {@code true} if this instance has a specific entity type attached
     *         to it; {@code false} otherwise.
     */
    @Override
    public boolean hasEntityType() {
        return this.entityType != null;
    }

    /**
     * Get the entity type that this instance represents immunity to damage
     * from.
     *
     * @return The entity type that this instance represents immunity to damage
     *         from.
     */
    @Override
    @Nullable
    public String getEntityType() {
        return this.entityType;
    }

    /**
     * Whether the immunity granted by this instance is to direct damage,
     * indirect damage, or both.
     *
     * @return The direction of damage this immunity is for.
     */
    @Override
    public DamageDirection getDamageDirection() {
        return this.damageDirection;
    }

    /**
     * Test this immunity against a given damage source.
     *
     * @param damageSource The damage source to test this immunity against.
     *
     * @return {@code true} if this instance matches the given damage source;
     *         {@code false} otherwise.
     */
    @Override
    public boolean matches(DamageSource damageSource) {
        return false;
    }

    /**
     * Serialize this instance into NBT data.
     *
     * @return This instance as NBT data.
     */
    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        if (this.hasDamageType()) {
            nbt.setString("damageType", this.damageType);
        }
        if (this.hasEntityType()) {
            nbt.setString("entityType", this.entityType);
        }
        NBTTagString damageDir = DamageDirection.toNBT(this.damageDirection);
        if (damageDir != null) {
            nbt.setTag("damageDirection", damageDir);
        }
        return nbt;
    }

    /**
     * Populate this instance from NBT data.
     *
     * @param nbt The NBT data to populate this instance from.
     */
    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        if (nbt == null) {
            return;
        }
        if (nbt.hasKey("damageType", NBT.TAG_STRING)) {
            this.damageType = nbt.getString("damageType");
        } else {
            this.damageType = null;
        }
        if (nbt.hasKey("entityType", NBT.TAG_STRING)) {
            this.entityType = nbt.getString("damageType");
        } else {
            this.entityType = null;
        }
        if (nbt.hasKey("damageDirection", NBT.TAG_STRING)) {
            DamageDirection dir = DamageDirection
                .tryFromNBT(nbt.getTag("damageDirection"));
            if (dir != null) {
                this.damageDirection = dir;
            }
        }
    }
}
