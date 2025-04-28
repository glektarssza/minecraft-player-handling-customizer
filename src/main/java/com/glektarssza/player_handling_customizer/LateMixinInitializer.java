package com.glektarssza.player_handling_customizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;

/**
 * The late-stage mixin initializer.
 */
public class LateMixinInitializer implements ILateMixinLoader {
    @Override
    public String getMixinConfig() {
        return "mixins.player-handling-customizer.late.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedMods) {
        List<String> mixinConfigs = new ArrayList<String>();
        if (loadedMods.contains("divinerpg")) {
            mixinConfigs
                .add("mixins.player-handling-customizer.divinerpg.json");
        }
        if (loadedMods.contains("Aether")) {
            mixinConfigs
                .add("mixins.player-handling-customizer.aether.json");
        }
        return mixinConfigs;
    }
}
