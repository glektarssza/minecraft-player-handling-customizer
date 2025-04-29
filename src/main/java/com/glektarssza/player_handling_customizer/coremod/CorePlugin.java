package com.glektarssza.player_handling_customizer.coremod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.gtnewhorizon.gtnhmixins.IEarlyMixinLoader;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;

/**
 * The core mod plugin for the mod.
 */
@MCVersion("1.7.10")
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
    public String getMixinConfig() {
        return "mixins.player-handling-customizer.early.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedCoreMods) {
        List<String> mixins = new ArrayList<String>();
        // -- Currently no mixins defined
        return mixins;
    }
}
