package com.github.brainage04.projectilemania.block.entity;

import com.github.brainage04.projectilemania.ProjectileMania;
import com.github.brainage04.projectilemania.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<InfiniteSpammerBlockEntity> INFINITE_SPAMMER_BLOCK_ENTITY =
            FabricBlockEntityTypeBuilder.create(InfiniteSpammerBlockEntity::new, ModBlocks.INFINITE_SPAMMER_BLOCK).build();

    public static void registerBlocKEntities() {
        ProjectileMania.LOGGER.info("Registering Block Entities for " + ProjectileMania.MOD_ID + "...");

        Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier(ProjectileMania.MOD_ID, "infinite_spammer_block_entity"),
                INFINITE_SPAMMER_BLOCK_ENTITY
        );

        ProjectileMania.LOGGER.info("Block Entities registered.");
    }
}
