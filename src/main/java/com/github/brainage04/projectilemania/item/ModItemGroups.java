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
    public static final ItemGroup PROJECTILE_MANIA_GROUP = FabricItemGroup
            .builder()
            .displayName(Text.translatable("item_group." + ProjectileMania.MOD_ID + "_group"))
            .icon(() -> new ItemStack(ModItems.CANNON_ITEM)).entries((displayContext, entries) -> {
                entries.add(ModBlocks.INFINITE_SPAMMER_BLOCK);
                entries.add(ModItems.CANNON_ITEM);

                entries.add(ModItems.COMPACT_SNOWBALL);
                entries.add(ModItems.INFINITE_SNOWBALL);

                entries.add(ModItems.COMPACT_EGG);
                entries.add(ModItems.INFINITE_EGG);

                entries.add(ModItems.COMPACT_ARROW);
                entries.add(ModItems.INFINITE_ARROW);

                entries.add(ModBlocks.COMPACT_TNT);
                entries.add(ModBlocks.INFINITE_TNT);
                entries.add(ModBlocks.IMPACT_TNT);

                entries.add(ModItems.TAG_STICK);
                entries.add(ModItems.HOT_POTATO);

                entries.add(ModItems.COPPER_HELMET);
                entries.add(ModItems.COPPER_CHESTPLATE);
                entries.add(ModItems.COPPER_LEGGINGS);
                entries.add(ModItems.COPPER_BOOTS);
            })
            .build();

    public static void registerItemGroups() {
        ProjectileMania.LOGGER.info("Registering Item Groups for " + ProjectileMania.MOD_ID + "...");

        Registry.register(
                Registries.ITEM_GROUP,
                new Identifier(ProjectileMania.MOD_ID, ProjectileMania.MOD_ID + "_group"),
                PROJECTILE_MANIA_GROUP
        );

        ProjectileMania.LOGGER.info("Item Groups registered.");
    }
}
