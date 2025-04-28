package com.glektarssza.player_handling_customizer.config;

import java.nio.file.Paths;

import net.minecraft.client.Minecraft;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

/**
 * The main configuration for the mod.
 */
public class PlayerHandlingCustomizerConfig extends Configuration {
    private ConfigCategory basicCategory;
    public Property immunePlayers;

    public PlayerHandlingCustomizerConfig() {
        super(Paths.get(Minecraft.getMinecraft().mcDataDir.getAbsolutePath(),
            "config", "player_handling_customizer.cfg").toFile(), "1.0");
        this.basicCategory = this.getCategory("basic");
        this.basicCategory.setComment("The basic configuration options.");
        this.basicCategory.setLanguageKey(
            "player_handling_customizer.config.basic_category");
        this.basicCategory.setRequiresWorldRestart(false);
        this.immunePlayers = this.get("basic", "immune_players", new String[0]);
        this.immunePlayers.comment = "A list of player names or UUIDs who are considered always immune.";
        this.immunePlayers
            .setLanguageKey("player_handling_customizer.config.immune_players");
        this.immunePlayers.setRequiresMcRestart(false);
    }

    public void sync() {
        try {
            this.load();
        } catch (Exception _ex) {
            this.save();
        }
    }
}
