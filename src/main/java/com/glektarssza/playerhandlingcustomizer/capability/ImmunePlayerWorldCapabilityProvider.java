package com.glektarssza.playerhandlingcustomizer.capability;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * A class which acts as a basic capability provider.
 */
public class ImmunePlayerWorldCapabilityProvider
    implements ICapabilityProvider, INBTSerializable<NBTTagCompound> {
    /**
     * The world capability instance.
     */
    private final IImmunePlayerWorldCapability immunePlayerWorldCapability;

    /**
     * Create a new instance.
     *
     * @param capability The capability instance to wrap.
     */
    public ImmunePlayerWorldCapabilityProvider(
        IImmunePlayerWorldCapability capability) {
        this.immunePlayerWorldCapability = capability;
    }

    /**
     * Get whether this instance has a capability.
     *
     * @return {@code true} if this instance has a capability; {@code false}
     *         otherwise.
     */
    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability,
        @Nullable EnumFacing facing) {
        return capability == ImmunePlayerWorldCapabilityHandler.IMMUNE_WORLD_CAPABILITY;
    }

    /**
     * Get the capability wrapped by this instance.
     *
     * @return The capability wrapped by this instance if it wraps one;
     *         {@code null} otherwise.
     */
    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability,
        @Nullable EnumFacing facing) {
        if (this.hasCapability(capability, facing)) {
            return ImmunePlayerWorldCapabilityHandler.IMMUNE_WORLD_CAPABILITY
                .cast(this.immunePlayerWorldCapability);
        }
        return null;
    }

    /**
     * Serialize this instance into NBT data.
     *
     * @return This instance as NBT data.
     */
    @Override
    public NBTTagCompound serializeNBT() {
        return this.immunePlayerWorldCapability.serializeNBT();
    }

    /**
     * Populate this instance from NBT data.
     *
     * @param nbt The NBT data to populate this instance from.
     */
    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        this.immunePlayerWorldCapability.deserializeNBT(nbt);

    }
}
