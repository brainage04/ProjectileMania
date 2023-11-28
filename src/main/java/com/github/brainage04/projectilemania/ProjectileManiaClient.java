package com.github.brainage04.projectilemania;

import net.fabricmc.api.ClientModInitializer;

import static com.github.brainage04.projectilemania.ProjectileMania.MOD_NAME;

public class ProjectileManiaClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ProjectileMania.LOGGER.info(MOD_NAME + " client initialised.");
	}
}