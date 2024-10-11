package com.glektarssza.playerhandlingcustomizer.capability;

import java.util.ArrayList;
import java.util.List;

import com.glektarssza.playerhandlingcustomizer.api.IPlayerImmunity;
import com.glektarssza.playerhandlingcustomizer.impl.PlayerImmunity;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants.NBT;

/**
 * A class that attaches to the Minecraft world and provides the functionality
 * described in {@link IImmunePlayerWorldCapability}.
 */
public class ImmunePlayerWorldCapability
    implements IImmunePlayerWorldCapability {
    /**
     * A list of player immunities for the world.
     */
    private List<IPlayerImmunity> playerImmunities;

    /**
     * Create a new instance.
     */
    public ImmunePlayerWorldCapability() {
        this.playerImmunities = new ArrayList<IPlayerImmunity>();
    }

    /**
     * Get a list of player immunities for the world.
     *
     * @return A list of player immunities for the world.
     */
    @Override
    public List<IPlayerImmunity> getPlayerImmunities() {
        return this.playerImmunities;
    }

    /**
     * Serialize this instance into NBT data.
     *
     * @return This instance as NBT data.
     */
    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        NBTTagList playerImmunities = new NBTTagList();
        for (IPlayerImmunity immunity : this.playerImmunities) {
            playerImmunities.appendTag(immunity.serializeNBT());
        }
        nbt.setTag("playerImmunities", playerImmunities);
        return nbt;
    }

    /**
     * Populate this instance from NBT data.
     *
     * @param nbt The NBT data to populate this instance from.
     */
    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        if (nbt == null) {
            return;
        }
        NBTTagList playerImmunities = nbt.getTagList("playerImmunities",
            NBT.TAG_COMPOUND);
        this.playerImmunities.clear();
        for (NBTBase immunity : playerImmunities) {
            if (immunity.getId() != NBT.TAG_COMPOUND) {
                continue;
            }
            IPlayerImmunity i = new PlayerImmunity();
            i.deserializeNBT((NBTTagCompound) immunity);
        }
    }
}
