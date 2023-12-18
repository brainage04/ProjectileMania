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
    public static final String MAIN_GROUP_TRANSLATION_KEY = "item_group." + ProjectileMania.MOD_ID + "_group";

    public static final ItemGroup MAIN_GROUP = FabricItemGroup
            .builder()
            .displayName(Text.translatable(MAIN_GROUP_TRANSLATION_KEY))
            .icon(() -> new ItemStack(ModItems.CANNON_ITEM)).entries((displayContext, entries) -> {
                entries.add(ModBlocks.INFINITE_SPAMMER_BLOCK);
                entries.add(ModItems.CANNON_ITEM);

                entries.add(ModItems.COMPACT_SNOWBALL);
                entries.add(ModItems.COMPACT_EGG);
                entries.add(ModItems.COMPACT_ARROW);
                entries.add(ModBlocks.COMPACT_TNT);
                entries.add(ModItems.INFINITE_SNOWBALL);
                entries.add(ModItems.INFINITE_EGG);
                entries.add(ModItems.INFINITE_ARROW);
                entries.add(ModBlocks.INFINITE_TNT);

                entries.add(ModBlocks.IMPACT_TNT);

                entries.add(ModItems.TAG_STICK);
                entries.add(ModItems.HOT_POTATO);
                entries.add(ModItems.GRAPPLING_HOOK);
            })
            .build();

    public static final String EQUIPMENT_GROUP_TRANSLATION_KEY = "item_group." + ProjectileMania.MOD_ID + "_group";

    public static final ItemGroup EQUIPMENT_GROUP = FabricItemGroup
            .builder()
            .displayName(Text.translatable(EQUIPMENT_GROUP_TRANSLATION_KEY))
            .icon(() -> new ItemStack(ModItems.LAPIS_LAZULI_SWORD)).entries((displayContext, entries) -> {
                entries.add(ModItems.COPPER_SWORD);
                entries.add(ModItems.COPPER_SHOVEL);
                entries.add(ModItems.COPPER_PICKAXE);
                entries.add(ModItems.COPPER_AXE);
                entries.add(ModItems.COPPER_HOE);
                entries.add(ModItems.COPPER_HELMET);
                entries.add(ModItems.COPPER_CHESTPLATE);
                entries.add(ModItems.COPPER_LEGGINGS);
                entries.add(ModItems.COPPER_BOOTS);

                entries.add(ModItems.REDSTONE_SWORD);
                entries.add(ModItems.REDSTONE_SHOVEL);
                entries.add(ModItems.REDSTONE_PICKAXE);
                entries.add(ModItems.REDSTONE_AXE);
                entries.add(ModItems.REDSTONE_HOE);
                entries.add(ModItems.REDSTONE_HELMET);
                entries.add(ModItems.REDSTONE_CHESTPLATE);
                entries.add(ModItems.REDSTONE_LEGGINGS);
                entries.add(ModItems.REDSTONE_BOOTS);

                entries.add(ModItems.LAPIS_LAZULI_SWORD);
                entries.add(ModItems.LAPIS_LAZULI_SHOVEL);
                entries.add(ModItems.LAPIS_LAZULI_PICKAXE);
                entries.add(ModItems.LAPIS_LAZULI_AXE);
                entries.add(ModItems.LAPIS_LAZULI_HOE);
                entries.add(ModItems.LAPIS_LAZULI_HELMET);
                entries.add(ModItems.LAPIS_LAZULI_CHESTPLATE);
                entries.add(ModItems.LAPIS_LAZULI_LEGGINGS);
                entries.add(ModItems.LAPIS_LAZULI_BOOTS);

                entries.add(ModItems.EMERALD_SWORD);
                entries.add(ModItems.EMERALD_SHOVEL);
                entries.add(ModItems.EMERALD_PICKAXE);
                entries.add(ModItems.EMERALD_AXE);
                entries.add(ModItems.EMERALD_HOE);
                entries.add(ModItems.EMERALD_HELMET);
                entries.add(ModItems.EMERALD_CHESTPLATE);
                entries.add(ModItems.EMERALD_LEGGINGS);
                entries.add(ModItems.EMERALD_BOOTS);
            })
            .build();

    public static void registerItemGroups() {
        ProjectileMania.LOGGER.info("Registering Item Groups for " + ProjectileMania.MOD_ID + "...");

        Registry.register(
                Registries.ITEM_GROUP,
                new Identifier(ProjectileMania.MOD_ID,"main_group"),
                MAIN_GROUP
        );
        Registry.register(
                Registries.ITEM_GROUP,
                new Identifier(ProjectileMania.MOD_ID,"equipment_group"),
                EQUIPMENT_GROUP
        );

        ProjectileMania.LOGGER.info("Item Groups registered.");
    }
}
