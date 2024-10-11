package com.glektarssza.playerhandlingcustomizer.capability;

import javax.annotation.Nullable;

import com.glektarssza.playerhandlingcustomizer.Tags;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * The class that handles setting up and managing the world capability.
 */
@Mod.EventBusSubscriber
public class ImmunePlayerWorldCapabilityHandler {
    /**
     * The resource location of the world capability.
     */
    private static final ResourceLocation IMMUNE_WORLD_CAPABILITY_RESOURCE_LOCATION = new ResourceLocation(
        Tags.MOD_ID, "immunue_world_capability");

    /**
     * The world capability.
     */
    @Nullable
    @CapabilityInject(IImmunePlayerWorldCapability.class)
    public static Capability<IImmunePlayerWorldCapability> IMMUNE_WORLD_CAPABILITY = null;

    /**
     * Handle the {@link AttachCapabilitiesEvent} event.
     *
     * @param event The event to handle.
     */
    @SubscribeEvent
    public static void attachToWorld(AttachCapabilitiesEvent<World> event) {
        // TODO: Use world capability provider once created
        event.addCapability(IMMUNE_WORLD_CAPABILITY_RESOURCE_LOCATION,
            new ImmunePlayerWorldCapabilityProvider(
                new ImmunePlayerWorldCapability()));
    }

    /**
     * Register the world capability.
     */
    public static void register() {
        CapabilityManager.INSTANCE.register(IImmunePlayerWorldCapability.class,
            new Capability.IStorage<IImmunePlayerWorldCapability>() {
                @Nullable
                @Override
                public NBTBase writeNBT(
                    Capability<IImmunePlayerWorldCapability> capability,
                    IImmunePlayerWorldCapability instance, EnumFacing side) {
                    return instance.serializeNBT();
                }

                @Override
                public void readNBT(
                    Capability<IImmunePlayerWorldCapability> capability,
                    IImmunePlayerWorldCapability instance, EnumFacing side,
                    NBTBase nbt) {
                    if (nbt instanceof NBTTagCompound) {
                        instance.deserializeNBT((NBTTagCompound) nbt);
                    }
                }
            }, ImmunePlayerWorldCapability::new);
    }
}
