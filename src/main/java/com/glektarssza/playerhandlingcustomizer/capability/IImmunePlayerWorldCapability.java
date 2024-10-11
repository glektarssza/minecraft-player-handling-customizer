package com.glektarssza.playerhandlingcustomizer.capability;

import java.util.List;

import com.glektarssza.playerhandlingcustomizer.api.IPlayerImmunity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * An interface that defines required functionality for classes that store and
 * manage world-level data about immune players.
 */
public interface IImmunePlayerWorldCapability
    extends INBTSerializable<NBTTagCompound> {
    /**
     * Get a list of player immunities for the world.
     *
     * @return A list of player immunities for the world.
     */
    List<IPlayerImmunity> getPlayerImmunities();
}
