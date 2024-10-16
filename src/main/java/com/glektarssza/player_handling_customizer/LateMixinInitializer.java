package com.glektarssza.player_handling_customizer;

import java.util.ArrayList;
import java.util.List;

import zone.rong.mixinbooter.ILateMixinLoader;

/**
 * The late-stage mixin initializer.
 */
public class LateMixinInitializer implements ILateMixinLoader {
    @Override
    public List<String> getMixinConfigs() {
        List<String> mixinConfigs = new ArrayList<String>();
        // TODO: Check for loaded mods and apply appropriate mixin configs
        return mixinConfigs;
    }
}
