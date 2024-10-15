package com.glektarssza.playerhandlingcustomizer.api;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * An interface that defines an immunity of some kind.
 */
public interface IImmunity extends INBTSerializable<NBTTagCompound> {
    /**
     * Get the type of immunity represented by this instance.
     *
     * @return The type of immunity represented by this instance.
     */
    ImmunityType getImmunityType();
}
