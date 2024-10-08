package com.glektarssza.playerhandlingcustomizer;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The root mod class.
 */
@Mod(PlayerHandlingCustomizer.MOD_ID)
public class PlayerHandlingCustomizer {
    /**
     * The ID of the mod.
     */
    public static final String MOD_ID = "playerhandlingcustomizer";

    /**
     * The logger to use for the mod.
     */
    public static final Logger LOGGER = LogManager.getLogger();

    /**
     * Create a new instance.
     */
    public PlayerHandlingCustomizer() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::Init);
    }

    /**
     * Initialize the mod.
     *
     * @param event The event to handle.
     */
    public void Init(FMLCommonSetupEvent event) {
        LOGGER.info("Initializing PlayerHandlingCustomizer...");
        // TODO
    }
}
