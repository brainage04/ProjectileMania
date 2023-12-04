package com.github.brainage04.projectilemania.block;

import com.github.brainage04.projectilemania.ProjectileMania;
import com.github.brainage04.projectilemania.block.custom.InfiniteSpammerBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block INFINITE_SPAMMER_BLOCK = registerBlock("infinite_spammer_block",
            new InfiniteSpammerBlock(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(ProjectileMania.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(ProjectileMania.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        ProjectileMania.LOGGER.info("Registering Mod Blocks for " + ProjectileMania.MOD_NAME + "...");



        ProjectileMania.LOGGER.info("Mod Blocks registered.");
    }
}
