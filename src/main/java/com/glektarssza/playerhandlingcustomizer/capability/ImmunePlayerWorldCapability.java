package com.glektarssza.playerhandlingcustomizer.capability;

import net.minecraft.nbt.NBTTagCompound;

/**
 * A class that attaches to the Minecraft world and provides the functionality
 * described in {@link IImmunePlayerWorldCapability}.
 */
public class ImmunePlayerWorldCapability
    implements IImmunePlayerWorldCapability {
    /**
     * Serialize this instance into NBT data.
     *
     * @return This instance as NBT data.
     */
    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        return nbt;
    }

    /**
     * Populate this instance from NBT data.
     *
     * @param nbt The NBT data to populate this instance from.
     */
    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        // TODO
    }
}
