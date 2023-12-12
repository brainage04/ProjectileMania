package com.github.brainage04.projectilemania;

import com.github.brainage04.projectilemania.enchantment.ModEnchantments;
import com.github.brainage04.projectilemania.block.entity.ModBlockEntities;
import com.github.brainage04.projectilemania.block.ModBlocks;
import com.github.brainage04.projectilemania.effect.ModStatusEffects;
import com.github.brainage04.projectilemania.event.PlayerBlockBreakHandler;
import com.github.brainage04.projectilemania.item.ModItemGroups;
import com.github.brainage04.projectilemania.item.ModItems;
import com.github.brainage04.projectilemania.screen.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectileMania implements ModInitializer {
	public static final String MOD_ID = "projectilemania";
	public static final String MOD_NAME = "ProjectileMania";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		//ModEntityTypes.registerModEntityTypes();
		ModBlockEntities.registerBlocKEntities();
		ModScreenHandlers.registerScreenHandlers();
		ModEnchantments.registerEnchantments();
		ModStatusEffects.registerStatusEffects();

		PlayerBlockBreakEvents.AFTER.register(new PlayerBlockBreakHandler());

		LOGGER.info(MOD_NAME + " main initialised.");
	}
}