package com.glektarssza.player_handling_customizer.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.Type;

import com.glektarssza.player_handling_customizer.Tags;

/**
 * The main configuration for the mod.
 */
@Config(modid = Tags.MOD_ID, name = Tags.MOD_ID, type = Type.INSTANCE)
public class PlayerHandlingCustomizerConfig {
    /**
     * A list of players, by UUID of username, who are immune to being targeted.
     */
    @LangKey("player_handling_customizer.config.immune_players")
    @Comment({
        "A list of players who are immune to being targeted.",
        "This can be either the username or UUID of the player."
    })
    public static String[] immunePlayers = {};
}
