package com.glektarssza.player_handling_customizer;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import net.minecraft.command.CommandReload;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.CommandEvent;
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
     * The maximum number of times to emit a warning before silencing it.
     */
    public static final int WARNING_EMIT_LIMIT = 20;

    /**
     * A map for tracking how many times a particular warning has been emitted.
     */
    public static final Map<String, Integer> WARNING_LIMIT_TRACKER = new HashMap<>();

    /**
     * The mod singleton instance.
     */
    @Mod.Instance
    public static PlayerHandlingCustomizer instance;

    /**
     * Check if a given warning category should be emitted.
     *
     * @param category The warning category to check.
     *
     * @return {@code true} if the given warning category should be emitted;
     *         {@code false} otherwise.
     */
    public static boolean shouldEmitWarning(String category) {
        WARNING_LIMIT_TRACKER.putIfAbsent(category, WARNING_EMIT_LIMIT);
        return WARNING_LIMIT_TRACKER.get(category) > 0;
    }

    /**
     * Track that a given warning category was emitted.
     *
     * @param category The warning category to check.
     */
    public static void trackEmitWarning(String category) {
        WARNING_LIMIT_TRACKER.putIfAbsent(category, WARNING_EMIT_LIMIT);
        int limit = WARNING_LIMIT_TRACKER.compute(category, (_k, v) -> v - 1);
        if (limit <= 0) {
            PlayerHandlingCustomizer.LOGGER
                .warn(
                    String.format(
                        "Too many identical warnings logged for category \"%s\"! Silencing further warnings on this issue!",
                        category));
        }
    }

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
        LOGGER.info("Synchronizing configuration for {}...", Tags.MOD_NAME);
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
            LOGGER.info("Synchronizing configuration for {}...", Tags.MOD_NAME);
            ConfigManager.sync(Tags.MOD_ID, Type.INSTANCE);
        }
    }

    /**
     * An event handler for when a command is issued.
     *
     * @param event The event data.
     */
    @SubscribeEvent
    public void onCommand(CommandEvent event) {
        if (event.getCommand() instanceof CommandReload) {
            LOGGER.info("Synchronizing confiugration for {}...", Tags.MOD_NAME);
            ConfigManager.sync(Tags.MOD_ID, Type.INSTANCE);
        }
    }
}
