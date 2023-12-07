package com.github.brainage04.projectilemania.item;

import com.github.brainage04.projectilemania.ProjectileMania;
import com.github.brainage04.projectilemania.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup PROJECTILE_INGREDIENTS = FabricItemGroup
            .builder()
            .displayName(Text.translatable("itemgroup.projectile_ingredients"))
            .icon(() -> new ItemStack(ModItems.COMPACT_SNOWBALL)).entries((displayContext, entries) -> {
                entries.add(ModItems.COMPACT_SNOWBALL);
                entries.add(ModItems.COMPACT_ARROW);
            })
            .build();


    public static final ItemGroup INFINITE_PROJECTILES = FabricItemGroup
            .builder()
            .displayName(Text.translatable("itemgroup.infinite_projectiles"))
            .icon(() -> new ItemStack(ModItems.INFINITE_SNOWBALL)).entries((displayContext, entries) -> {
                entries.add(ModItems.INFINITE_SNOWBALL);
                entries.add(ModItems.INFINITE_ARROW);
            })
            .build();

    public static final ItemGroup INFINITE_PROJECTILE_DEVICES = FabricItemGroup
            .builder()
            .displayName(Text.translatable("itemgroup.infinite_projectile_devices"))
            .icon(() -> new ItemStack(ModBlocks.INFINITE_SPAMMER_BLOCK)).entries((displayContext, entries) -> {
                entries.add(ModBlocks.INFINITE_SPAMMER_BLOCK);
                entries.add(ModItems.CANNON_ITEM);
            })
            .build();

    public static final ItemGroup PROJECTILE_MANIA_GROUP = FabricItemGroup
            .builder()
            .displayName(Text.translatable("itemgroup.projectile_mania_group"))
            .icon(() -> new ItemStack(ModItems.CANNON_ITEM)).entries((displayContext, entries) -> {
                entries.add(ModItems.COMPACT_SNOWBALL);
                entries.add(ModItems.COMPACT_ARROW);
                entries.add(ModItems.INFINITE_SNOWBALL);
                entries.add(ModItems.INFINITE_ARROW);
                entries.add(ModBlocks.INFINITE_SPAMMER_BLOCK);
                entries.add(ModItems.CANNON_ITEM);
            })
            .build();

    public static void registerItemGroups() {
        ProjectileMania.LOGGER.info("Registering Item Groups for " + ProjectileMania.MOD_ID + "...");

        Registry.register(
                Registries.ITEM_GROUP,
                new Identifier(ProjectileMania.MOD_ID, "projectile_ingredients"),
                PROJECTILE_INGREDIENTS
        );
        Registry.register(
                Registries.ITEM_GROUP,
                new Identifier(ProjectileMania.MOD_ID, "infinite_projectiles"),
                INFINITE_PROJECTILES
        );
        Registry.register(
                Registries.ITEM_GROUP,
                new Identifier(ProjectileMania.MOD_ID, "infinite_projectile_devices"),
                INFINITE_PROJECTILE_DEVICES
        );
        Registry.register(
                Registries.ITEM_GROUP,
                new Identifier(ProjectileMania.MOD_ID, "projectile_mania_group"),
                PROJECTILE_MANIA_GROUP
        );

        ProjectileMania.LOGGER.info("Item Groups registered.");
    }
}
