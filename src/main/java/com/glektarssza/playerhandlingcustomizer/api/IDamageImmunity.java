package com.glektarssza.playerhandlingcustomizer.api;

import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants.NBT;

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
     * Get the type of immunity represented by this instance.
     *
     * @return The type of immunity represented by this instance.
     */
    @Override
    default ImmunityType getImmunityType() {
        return ImmunityType.Damage;
    }

    /**
     * Serialize this instance into NBT data.
     *
     * @return NBT data representing this instance.
     */
    @Override
    default NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("immunityType", "damage");
        nbt.setString("damageType", this.getDamageType());
        if (this.hasEntityType()) {
            nbt.setString("entityType", this.getEntityType());
        }
        return nbt;
    }

    /**
     * Deserialize NBT data into this instance.
     *
     * @param nbt The NBT data to deserialize into this instance.
     */
    @Override
    default void deserializeNBT(NBTTagCompound nbt) {
        if (!nbt.hasKey("immunityType", NBT.TAG_STRING)) {
            return;
        }
        String immunityType = nbt.getString("immunityType");
        if (immunityType != "damage") {
            return;
        }
        if (!nbt.hasKey("damageType", NBT.TAG_STRING)) {
            return;
        }
        this.setDamageType(nbt.getString("damageType"));
        if (nbt.hasKey("entityType", NBT.TAG_STRING)) {
            this.setEntityType(nbt.getString("entityType"));
        } else {
            this.setEntityType(null);
        }
    }
}
