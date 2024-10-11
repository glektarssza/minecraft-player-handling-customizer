package com.glektarssza.playerhandlingcustomizer.capability;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * An interface that defines required functionality for classes that store and
 * manage world-level data about immune players.
 */
public interface IImmunePlayerWorldCapability
    extends INBTSerializable<NBTTagCompound> {
}
