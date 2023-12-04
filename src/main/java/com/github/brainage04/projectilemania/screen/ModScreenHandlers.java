package com.github.brainage04.projectilemania.screen;

import com.github.brainage04.projectilemania.ProjectileMania;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static final ScreenHandlerType<InfiniteSpammerScreenHandler> INFINITE_SPAMMER_SCREEN_HANDLER = new ExtendedScreenHandlerType<>(InfiniteSpammerScreenHandler::new);

    public static void registerScreenHandlers() {
        ProjectileMania.LOGGER.info("Registering Block Entities for " + ProjectileMania.MOD_ID + "...");

        Registry.register(
                Registries.SCREEN_HANDLER,
                new Identifier(ProjectileMania.MOD_ID, "infinite_spammer_screen_handler"),
                INFINITE_SPAMMER_SCREEN_HANDLER
        );

        ProjectileMania.LOGGER.info("Block Entities registered.");
    }
}
