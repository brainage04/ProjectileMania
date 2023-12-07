package com.github.brainage04.projectilemania;

import com.github.brainage04.projectilemania.screen.InfiniteSpammerScreen;
import com.github.brainage04.projectilemania.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class ProjectileManiaClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ProjectileMania.LOGGER.info(ProjectileMania.MOD_NAME + " client initialised.");

		ClientTickEvents.END_CLIENT_TICK.register((world) -> {

		});

		HandledScreens.register(ModScreenHandlers.INFINITE_SPAMMER_SCREEN_HANDLER, InfiniteSpammerScreen::new);
	}
}