package com.glektarssza.player_handling_customizer;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * The root mod class.
 */
@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.MOD_VERSION, dependencies = Tags.MOD_DEPENDENCIES, acceptableRemoteVersions = "*")
public class PlayerHandlingCustomizer {
    /**
     * The logger to use for the mod.
     */
    public static Logger LOGGER;

    /**
     * The mod singleton instance.
     */
    @Mod.Instance
    public static PlayerHandlingCustomizer instance;

    /**
     * Handle the Forge Mod Loader pre-initialization event.
     *
     * @param event The event to handle.
     */
    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        LOGGER = event.getModLog();
        LOGGER.info("Pre-initializing {}...", Tags.MOD_NAME);
        MinecraftForge.EVENT_BUS.register(this);
        LOGGER.info("Done pre-initializing {}!", Tags.MOD_NAME);
    }

    /**
     * Handle the Forge Mod Loader pre-initialization event.
     *
     * @param event The event to handle.
     */
    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        LOGGER.info("Initializing {}...", Tags.MOD_NAME);
        LOGGER.info("Synchronizing confiugration for {}...", Tags.MOD_NAME);
        ConfigManager.sync(Tags.MOD_ID, Type.INSTANCE);
        LOGGER.info("Done Initializing {}!", Tags.MOD_NAME);
    }

    /**
     * An event handler for when a configuration changes.
     *
     * @param event The event data.
     */
    @SubscribeEvent
    public void onConfigChange(OnConfigChangedEvent event) {
        if (event.getModID().equals(Tags.MOD_ID)) {
            LOGGER.info("Synchronizing confiugration for {}...", Tags.MOD_NAME);
            ConfigManager.sync(Tags.MOD_ID, Type.INSTANCE);
        }
    }
}
