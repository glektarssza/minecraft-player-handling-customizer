package com.glektarssza.player_handling_customizer.config;

import java.util.Arrays;

import com.falsepattern.gasstation.Tags;

import net.minecraft.client.gui.GuiScreen;

import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Property;

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
                    Config.immunePlayers,
                    Property.Type.STRING))),
            Tags.MODID,
            false, false,
            String.format("%s.cfg", Tags.MODID));
    }

    @Override
    public void onGuiClosed() {
        Config.save();
        super.onGuiClosed();
    }
}
