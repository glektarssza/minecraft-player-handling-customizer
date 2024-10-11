package com.glektarssza.playerhandlingcustomizer.impl;

import java.util.UUID;

import javax.annotation.Nullable;

import com.glektarssza.playerhandlingcustomizer.api.IPlayerIdentifier;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants.NBT;

/**
 * A concrete implementation of the {@link IPlayerIdentifier} interface.
 */
public class PlayerIdentifier implements IPlayerIdentifier {
    /**
     * The UUID of the player.
     */
    @Nullable
    private UUID uuid;

    /**
     * The name of the player.
     */
    private String name;

    /**
     * Create a new instance.
     *
     * @param uuid The UUID of the player.
     * @param name The name of the player.
     */
    public PlayerIdentifier() {
        this.uuid = null;
        this.name = "";
    }

    /**
     * Whether this instance has a UUID.
     *
     * @return {@code true} if this instance has a UUID; {@code false}
     *         otherwise.
     */
    @Override
    public boolean hasUUID() {
        return this.uuid != null;
    }

    /**
     * Get the UUID of the player represented by this instance.
     *
     * This method may return {@code null} if the server/client/LAN world is
     * running in offline mode.
     *
     * @return The UUID of the player represented by this instance, if
     *         available; {@code null} otherwise.
     */
    @Override
    @Nullable
    public UUID getUUID() {
        return this.uuid;
    }

    /**
     * Get the name of the player represented by this instance.
     *
     * @return The name of the player represented by this instance.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Serialize this instance into NBT data.
     *
     * @return This instance as NBT data.
     */
    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("name", this.name);
        if (this.hasUUID()) {
            nbt.setString("uuid", this.uuid.toString());
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
        if (!nbt.hasKey("name", NBT.TAG_STRING)) {
            return;
        }
        this.name = nbt.getString("name");
        if (nbt.hasKey("uuid", NBT.TAG_STRING)) {
            String uuid = nbt.getString("uuid");
            try {
                this.uuid = UUID.fromString(uuid);
            } catch (Exception ex) {
                // -- Do nothing
            }
        }
    }
}
