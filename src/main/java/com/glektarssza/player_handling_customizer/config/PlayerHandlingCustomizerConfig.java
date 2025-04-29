package com.glektarssza.player_handling_customizer.config;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import com.glektarssza.player_handling_customizer.PlayerHandlingCustomizer;

/**
 * The main configuration for the mod.
 */
public class PlayerHandlingCustomizerConfig {
    /**
     * The version of the configuration format.
     */
    public static final String CONFIG_VERSION = "1";

    /**
     * The general configuration category.
     */
    public static final String CATEGORY_GENERAL = "general";

    /**
     * A list of players who are globally immune.
     */
    public static String[] immunePlayers = new String[0];

    /**
     * Synchronize the mod configuration.
     *
     * @param configDir The directory the configuration file will live in.
     * @param fileName The name of the file to save the configuration to.
     */
    public static void synchronizeConfig(File configDir, String fileName) {
        Configuration config = new Configuration(new File(configDir, fileName),
            CONFIG_VERSION);
        // -- Read config from disk
        config.load();

        // -- Load actual data
        config
            .setCategoryComment(CATEGORY_GENERAL,
                "The general configuration category.")
            .setCategoryLanguageKey(CATEGORY_GENERAL,
                "player_handling_customizer.config.basic_category")
            .setCategoryRequiresMcRestart(CATEGORY_GENERAL, false);

        Property immunePlayersProp = config.get("immunePlayers",
            CATEGORY_GENERAL, immunePlayers);
        immunePlayersProp
            .setLanguageKey("player_handling_customizer.config.immune_players")
            .setShowInGui(true)
            .setRequiresMcRestart(false)
            .setRequiresWorldRestart(false);
        immunePlayers = immunePlayersProp.getStringList();

        // -- Check if config has changed as a result of loading actual data
        // -- (e.g. we populated the defaults for the first time) and save if so
        if (config.hasChanged()) {
            config.save();
            PlayerHandlingCustomizer.LOGGER.warn(
                "Configuration file \"%s\" has been modified, check it if something isn't working!",
                fileName);
        }
    }
}
