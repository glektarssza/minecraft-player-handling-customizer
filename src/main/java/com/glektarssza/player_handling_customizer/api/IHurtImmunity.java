package com.glektarssza.player_handling_customizer.api;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;

import net.minecraftforge.common.util.Constants.NBT;

/**
 * An interface that represents immunity from hurt events.
 */
public interface IHurtImmunity extends IPhysicalImmunity {
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
        nbt.setTag(
            "immunityType", ImmunityType.toNBTString(this.getImmunityType())
        );
        nbt.setString("damageType", this.getDamageType());
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
        ImmunityType type = ImmunityType.fromNBTString((NBTTagString
        )nbt.getTag("immunityType"));
        if (type != ImmunityType.Hurt) {
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
