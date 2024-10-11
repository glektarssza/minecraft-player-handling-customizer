package com.glektarssza.playerhandlingcustomizer.api;

import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * An interface defining a player identifier.
 */
public interface IPlayerIdentifier extends INBTSerializable<NBTTagCompound> {
    /**
     * Get the UUID of the player represented by this instance.
     *
     * This method may return {@code null} if the server/client/LAN world is
     * running in offline mode.
     *
     * @return The UUID of the player represented by this instance, if
     *         available; {@code null} otherwise.
     */
    @Nullable
    UUID getUUID();

    /**
     * Get the name of the player represented by this instance.
     *
     * @return The name of the player represented by this instance.
     */
    String getName();
}
