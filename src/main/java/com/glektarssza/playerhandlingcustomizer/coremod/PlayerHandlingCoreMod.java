package com.glektarssza.playerhandlingcustomizer.coremod;

import com.glektarssza.playerhandlingcustomizer.Tags;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import zone.rong.mixinbooter.IEarlyMixinLoader;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.12.2")
@IFMLLoadingPlugin.Name(Tags.MOD_NAME + " Core Plugin")
@IFMLLoadingPlugin.TransformerExclusions("com.glektarssza.playerhandlingcustomizer.coremod")
public class PlayerHandlingCoreMod implements IFMLLoadingPlugin, IEarlyMixinLoader {
    @Nullable
    @Override
    public String[] getASMTransformerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
        //-- noop
    }

    @Nullable
    @Override
    public String getAccessTransformerClass() {
        return null;
    }

    @Override
    public List<String> getMixinConfigs() {
        return Collections.singletonList("mixins.player_handling_customizer.json");
    }
}
