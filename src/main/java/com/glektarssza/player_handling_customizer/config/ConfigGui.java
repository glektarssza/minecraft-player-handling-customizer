package com.glektarssza.player_handling_customizer.config;

import java.util.Arrays;

import net.minecraft.client.gui.GuiScreen;

import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Property;

import com.glektarssza.player_handling_customizer.Tags;

import cpw.mods.fml.client.config.GuiConfig;

/**
 * The configuration GUI.
 */
public class ConfigGui extends GuiConfig {
    /**
     * Create a new instance.
     *
     * @param parentScreen The parent screen.
     */
    public ConfigGui(GuiScreen parentScreen) {
        super(parentScreen,
            Arrays.asList(
                ConfigElement.getTypedElement(new Property("immunePlayers",
                    Config.getImmunePlayers(),
                    Property.Type.STRING))),
            Tags.MOD_ID,
            false, false,
            String.format("%s.cfg", Tags.MOD_ID));
    }

    @Override
    public void onGuiClosed() {
        Config.save();
        super.onGuiClosed();
    }
}
