package com.glektarssza.player_handling_customizer.api;

import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.common.util.Constants.NBT;

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

    /**
     * Get the type of immunity represented by this instance.
     *
     * @return The type of immunity represented by this instance.
     */
    @Override
    default ImmunityType getImmunityType() {
        return ImmunityType.Hurt;
    }

    /**
     * Serialize this instance into NBT data.
     *
     * @return NBT data representing this instance.
     */
    @Override
    default NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setTag("immunityType",
            ImmunityType.toNBTString(this.getImmunityType()));
        if (this.hasEntityType()) {
            nbt.setString("entityType", this.getEntityType());
        }
        nbt.setBoolean("appliesToDirect", this.getAppliesToDirectDamage());
        nbt.setBoolean("appliesToIndirect", this.getAppliesToIndirectDamage());
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
        ImmunityType type = ImmunityType
            .fromNBTString((NBTTagString) nbt.getTag("immunityType"));
        if (type != ImmunityType.Knockback) {
            return;
        }
        if (nbt.hasKey("entityType", NBT.TAG_STRING)) {
            this.setEntityType(nbt.getString("entityType"));
        } else {
            this.setEntityType(null);
        }
        if (nbt.hasKey("appliesToDirect", NBT.TAG_ANY_NUMERIC)) {
            this.setAppliesToDirectDamage(nbt.getBoolean("appliesToDirect"));
        } else {
            this.setAppliesToDirectDamage(false);
        }
        if (nbt.hasKey("appliesToIndirect", NBT.TAG_ANY_NUMERIC)) {
            this.setAppliesToDirectDamage(nbt.getBoolean("appliesToIndirect"));
        } else {
            this.setAppliesToDirectDamage(false);
        }
    }
}
