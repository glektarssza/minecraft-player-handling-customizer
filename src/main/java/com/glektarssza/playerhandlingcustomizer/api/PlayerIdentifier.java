package com.glektarssza.playerhandlingcustomizer.api;

import java.util.UUID;

import javax.annotation.Nullable;

import com.glektarssza.playerhandlingcustomizer.PlayerHandlingCustomizer;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * A simple class that holds data that identifiers a player.
 */
public class PlayerIdentifier implements INBTSerializable<NBTTagCompound> {
    /**
     * The UUID of the player.
     *
     * This field may be {@code null} if the server/single player client is in
     * offline mode.
     */
    @Nullable
    private UUID uuid;

    /**
     * The name of the player.
     *
     * This field will always be populated.
     */
    private String name;

    /**
     * Create a new instance.
     *
     * @param uuid The UUID of the player.
     * @param name The name of the player.
     */
    public PlayerIdentifier(@Nullable UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    /**
     * Serialize this instance into NBT data.
     *
     * @return This instance as NBT data.
     */
    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        if (this.uuid != null) {
            nbt.setString("uuid", this.uuid.toString());
        }
        nbt.setString("name", this.name);
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
            PlayerHandlingCustomizer.LOGGER.warn(
                "Failed to deserialize player identifier from NBT data (missing name tag)");
            return;
        }
        if (nbt.hasKey("uuid", NBT.TAG_STRING)) {
            this.uuid = UUID.fromString(nbt.getString("uuid"));
        } else {
            this.uuid = null;
        }
        this.name = nbt.getString("name");
    }
}
