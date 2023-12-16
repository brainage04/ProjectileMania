package com.github.brainage04.projectilemania;

import com.github.brainage04.projectilemania.keybinding.ModKeyBindings;
import com.github.brainage04.projectilemania.networking.ModMessages;
import com.github.brainage04.projectilemania.screen.InfiniteSpammerScreen;
import com.github.brainage04.projectilemania.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class ProjectileManiaClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ProjectileMania.LOGGER.info(ProjectileMania.MOD_NAME + " client initialised.");

		ModKeyBindings.registerKeyBindingEvents();
		ModMessages.registerS2CPackets();

		HandledScreens.register(ModScreenHandlers.INFINITE_SPAMMER_SCREEN_HANDLER, InfiniteSpammerScreen::new);
	}
}