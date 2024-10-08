package com.glektarssza.playerhandlingcustomizer;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.Logger;

/**
 * The root mod class.
 */
@Mod(modid = PlayerHandlingCustomizer.MOD_ID, name = PlayerHandlingCustomizer.MOD_NAME, version = PlayerHandlingCustomizer.MOD_VERSION, dependencies = PlayerHandlingCustomizer.MOD_DEPENDENCIES)
public class PlayerHandlingCustomizer {
    /**
     * The ID of the mod.
     */
    public static final String MOD_ID = "${mod_id}";

    /**
     * The human-readable name of the mod.
     */
    public static final String MOD_NAME = "${mod_name}";

    /**
     * The human-readable version of the mod.
     */
    public static final String MOD_VERSION = "${mod_version}";

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
     * Initialize the mod.
     *
     * @param event The event to handle.
     */
    @Mod.EventHandler
    public void Init(FMLPreInitializationEvent event) {
        LOGGER = event.getModLog();
        LOGGER.info("Initializing PlayerHandlingCustomizer...");
        // TODO
    }
}
