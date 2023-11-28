package com.github.brainage04.projectilemania;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectileMania implements ModInitializer {
	public static final String MOD_ID = "projectilemania";
	public static final String MOD_NAME = "ProjectileMania";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info(MOD_NAME + " main initialised.");
	}
}