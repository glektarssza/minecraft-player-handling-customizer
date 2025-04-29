package com.glektarssza.player_handling_customizer.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import com.glektarssza.player_handling_customizer.PlayerHandlingCustomizer;

/**
 * The main configuration for the mod.
 */
public class Config {
    /**
     * The configuration instance.
     */
    private static Configuration configInstance;

    /**
     * The version of the configuration format.
     */
    public static final String CONFIG_VERSION = "1";

    /**
     * The general configuration category.
     */
    private static final String CATEGORY_GENERAL = "general";

    /**
     * A list of players who are globally immune.
     */
    public static String[] immunePlayers = new String[0];

    /**
     * Initialize the configuration.
     *
     * Multiple calls to this instance will have no effect.
     *
     * @param configDir The directory the configuration file will live in.
     * @param fileName The name of the file to save the configuration to.
     */
    public static void init(File configDir, String fileName) {
        if (configInstance != null) {
            return;
        }

        configInstance = new Configuration(new File(configDir, fileName),
            CONFIG_VERSION);

        configInstance
            .setCategoryComment(CATEGORY_GENERAL,
                "The general configuration category.")
            .setCategoryLanguageKey(CATEGORY_GENERAL,
                "player_handling_customizer.config.basic_category")
            .setCategoryRequiresMcRestart(CATEGORY_GENERAL, false);

        configInstance.get(CATEGORY_GENERAL, "immunePlayers", immunePlayers)
            .setLanguageKey("player_handling_customizer.config.immune_players")
            .setShowInGui(true)
            .setRequiresMcRestart(false)
            .setRequiresWorldRestart(false);
    }

    /**
     * Load the configuration data from disk.
     */
    public static void load() {
        if (configInstance == null) {
            PlayerHandlingCustomizer.LOGGER.error(
                "Cannot load configuration!");
            PlayerHandlingCustomizer.LOGGER
                .error("Configuration has not been initialized yet!");
            return;
        }
        immunePlayers = configInstance
            .get(CATEGORY_GENERAL, "immunePlayers", immunePlayers)
            .getStringList();
    }

    /**
     * Save the configuration data to disk.
     */
    public static void save() {
        if (configInstance == null) {
            PlayerHandlingCustomizer.LOGGER.error(
                "Cannot save configuration!");
            PlayerHandlingCustomizer.LOGGER
                .error("Configuration has not been initialized yet!");
            return;
        }
        if (configInstance.hasChanged()) {
            configInstance.save();
        }
    }

    /**
     * Synchronize the mod configuration.
     *
     * @param configDir The directory the configuration file will live in.
     * @param fileName The name of the file to save the configuration to.
     */
    public static void sync() {
        load();
        save();
    }
}
