package com.github.brainage04.projectilemania.mixin;

import com.github.brainage04.projectilemania.keybinding.ModKeyBindings;
import com.github.brainage04.projectilemania.networking.ModMessages;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(ClientPlayerInteractionManager.class)
public abstract class MixinClientPlayerInteractionManager {
    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(method = "breakBlock", at = @At(value = "HEAD"))
    private void VEIN_MINER$BREAKBLOCK(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (ModKeyBindings.veinMinerKeybind.isPressed()) {
            ModMessages.sendVeinMinerPacket(pos, Registries.BLOCK.getId(this.client.world.getBlockState(pos).getBlock()));
        }
    }
}