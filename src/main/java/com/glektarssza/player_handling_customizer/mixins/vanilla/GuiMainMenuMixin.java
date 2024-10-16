package com.glektarssza.player_handling_customizer.mixins.vanilla;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.glektarssza.player_handling_customizer.PlayerHandlingCustomizer;

import net.minecraft.client.gui.GuiMainMenu;

@Mixin(GuiMainMenu.class)
public class GuiMainMenuMixin {
    @Inject(method = "initGui", at = @At("HEAD"))
    public void initGui(CallbackInfo ci) {
        PlayerHandlingCustomizer.LOGGER.info("Hello from our mixin!");
    }
}
