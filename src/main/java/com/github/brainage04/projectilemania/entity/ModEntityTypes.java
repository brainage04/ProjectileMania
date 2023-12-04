package com.github.brainage04.projectilemania.entity;

import com.github.brainage04.projectilemania.ProjectileMania;
import com.github.brainage04.projectilemania.entity.custom.InfiniteSnowballEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntityTypes {
    public static final EntityType<InfiniteSnowballEntity> infiniteSnowballProjectileEntityType = FabricEntityTypeBuilder
            .<InfiniteSnowballEntity>create(SpawnGroup.MISC, InfiniteSnowballEntity::new)
            .dimensions(EntityDimensions.fixed(0.25F, 0.25F)) // dimensions in Minecraft units of the projectile
            .trackRangeChunks(8).trackedUpdateRate(10) // necessary for all thrown projectiles (as it prevents it from breaking, lol)
            .build();

    public static void registerModEntityTypes() {
        ProjectileMania.LOGGER.info("Registering Mod Entity Types for " + ProjectileMania.MOD_NAME + "...");

        Registry.register(
                Registries.ENTITY_TYPE,
                new Identifier(ProjectileMania.MOD_ID, "infinite_snowball_projectile_entity_type"),
                infiniteSnowballProjectileEntityType
        );

        ProjectileMania.LOGGER.info("Mod Entity Types registered.");
    }
}
