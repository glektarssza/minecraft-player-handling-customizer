package com.glektarssza.playerhandlingcustomizer.mixins;

import com.glektarssza.playerhandlingcustomizer.PlayerHandlingCustomizer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.GuiMainMenu;

import org.apache.logging.log4j.Logger;

@Mixin(GuiMainMenu.class)
public class TempMixin {
    @Inject(method = "initGui", at = @At("HEAD"))
    public void initGui(CallbackInfo ci) {
        PlayerHandlingCustomizer.LOGGER.info("Hello from our mod!");
    }
}
