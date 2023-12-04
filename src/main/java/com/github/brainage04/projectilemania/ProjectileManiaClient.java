package com.github.brainage04.projectilemania;

import com.github.brainage04.projectilemania.entity.ModEntityTypes;
import com.github.brainage04.projectilemania.screen.InfiniteSpammerScreen;
import com.github.brainage04.projectilemania.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.EntityType;

public class ProjectileManiaClient implements ClientModInitializer {
	public void registerProjectileRenderer(EntityType entityType) {
		EntityRendererRegistry.register(entityType, (context) ->
				new FlyingItemEntityRenderer(context));
	}

	@Override
	public void onInitializeClient() {
		ProjectileMania.LOGGER.info(ProjectileMania.MOD_NAME + " client initialised.");

		registerProjectileRenderer(ModEntityTypes.infiniteSnowballProjectileEntityType);

		HandledScreens.register(ModScreenHandlers.INFINITE_SPAMMER_SCREEN_HANDLER, InfiniteSpammerScreen::new);
	}
}