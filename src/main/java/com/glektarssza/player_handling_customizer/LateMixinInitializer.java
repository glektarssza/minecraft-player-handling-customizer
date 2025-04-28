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
        List<String> mixins = new ArrayList<String>();
        if (loadedMods.contains("divinerpg")) {
            mixins
                .add("divinerpg.AIDivineFireballAttackMixin");
            mixins
                .add("divinerpg.EntityFroshMixin");
        }
        if (loadedMods.contains("Aether")) {
            mixins
                .add("aether.ZephyrAIShootTargetMixin");
        }
        return mixins;
    }
}
