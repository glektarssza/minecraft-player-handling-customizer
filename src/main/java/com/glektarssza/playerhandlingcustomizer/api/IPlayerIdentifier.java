package com.glektarssza.playerhandlingcustomizer.api;

import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * An interface defining a player identifier.
 */
public interface IPlayerIdentifier extends INBTSerializable<NBTTagCompound> {
    /**
     * Whether this instance has a UUID.
     *
     * @return {@code true} if this instance has a UUID; {@code false}
     *         otherwise.
     */
    boolean hasUUID();

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

    /**
     * Test if a player matches this instance.
     *
     * @param player The player to test against.
     *
     * @return {@code true} if this instance matches the player; {@code false}
     *         otherwise.
     */
    boolean matches(EntityPlayer player);
}
