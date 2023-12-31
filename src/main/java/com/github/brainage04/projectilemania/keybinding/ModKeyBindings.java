package com.github.brainage04.projectilemania.keybinding;

import com.github.brainage04.projectilemania.ProjectileMania;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class ModKeyBindings {
    public static final String MAIN_CATEGORY = "key_category." + ProjectileMania.MOD_ID + "." + ProjectileMania.MOD_ID;

    public static final String GAMMA_TRANSLATION_KEY = "key." + ProjectileMania.MOD_ID + ".gamma";
    public static final String VEIN_MINER_TRANSLATION_KEY = "key." + ProjectileMania.MOD_ID + ".vein_miner";

    // keybinds
    public static KeyBinding gammaKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            GAMMA_TRANSLATION_KEY,
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_KP_ADD,
            MAIN_CATEGORY
    ));

    public static KeyBinding veinMinerKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            VEIN_MINER_TRANSLATION_KEY,
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_GRAVE_ACCENT,
            MAIN_CATEGORY
    ));

    public static double oldGamma = -1.0; // default value for new installs
    public static final double newGamma = 5.0; // should make the game very bright

    public static void registerKeyBindingEvents() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (gammaKeybind.isPressed()) {
                if (oldGamma == -1.0) {
                    oldGamma = MinecraftClient.getInstance().options.getGamma().getValue(); // get value before first use
                }

                if (client.options.getGamma().getValue() == newGamma) {
                    client.options.getGamma().setValue(oldGamma);
                } else {
                    oldGamma = client.options.getGamma().getValue();
                    client.options.getGamma().setValue(newGamma);
                }

                client.player.sendMessage(Text.literal("Gamma cycled from " + oldGamma + " to " + newGamma + "."), false);
            }
        });
    }
}
