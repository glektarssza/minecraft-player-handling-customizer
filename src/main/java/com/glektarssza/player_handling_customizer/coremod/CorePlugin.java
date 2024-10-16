package com.glektarssza.player_handling_customizer.coremod;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import zone.rong.mixinbooter.IEarlyMixinLoader;

/**
 * The core mod plugin for the mod.
 */
public class CorePlugin implements IFMLLoadingPlugin, IEarlyMixinLoader {
    @Override
    public String getAccessTransformerClass() {
        return null;
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
        // -- No operation
    }

    @Override
    public List<String> getMixinConfigs() {
        return Collections
            .singletonList("mixins.player_handling_customizer.vanilla.json");
    }
}
