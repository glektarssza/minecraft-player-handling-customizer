package com.glektarssza.player_handling_customizer;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.fml.common.Loader;

import zone.rong.mixinbooter.ILateMixinLoader;

/**
 * The late-stage mixin initializer.
 */
public class LateMixinInitializer implements ILateMixinLoader {
    @Override
    public List<String> getMixinConfigs() {
        List<String> mixinConfigs = new ArrayList<String>();
        if (Loader.isModLoaded("divinerpg")) {
            mixinConfigs
                .add("mixins.player-handling-customizer.divinerpg.json");
        }
        if (Loader.isModLoaded("Aether")) {
            mixinConfigs
                .add("mixins.player-handling-customizer.aether.json");
        }
        return mixinConfigs;
    }
}
