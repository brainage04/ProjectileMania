package com.github.brainage04.projectilemania.datagen;

import com.github.brainage04.projectilemania.block.ModBlocks;
import com.github.brainage04.projectilemania.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleState(ModBlocks.INFINITE_SPAMMER_BLOCK);

        blockStateModelGenerator.registerSimpleState(ModBlocks.COMPACT_TNT);
        blockStateModelGenerator.registerSimpleState(ModBlocks.INFINITE_TNT);
        blockStateModelGenerator.registerSimpleState(ModBlocks.IMPACT_TNT);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        // cannon item is using a custom model
        // tag stick is using a vanilla stick
        // hot potato is using a vanilla baked potato
        // grappling hook is using the fishing rod

        itemModelGenerator.register(ModItems.COMPACT_SNOWBALL, Models.GENERATED);
        itemModelGenerator.register(ModItems.INFINITE_SNOWBALL, Models.HANDHELD);

        itemModelGenerator.register(ModItems.COMPACT_EGG, Models.GENERATED);
        itemModelGenerator.register(ModItems.INFINITE_EGG, Models.HANDHELD);

        itemModelGenerator.register(ModItems.COMPACT_ARROW, Models.GENERATED);
        itemModelGenerator.register(ModItems.INFINITE_ARROW, Models.HANDHELD);

        itemModelGenerator.register(ModItems.COPPER_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COPPER_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COPPER_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COPPER_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COPPER_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COPPER_HELMET, Models.GENERATED);
        itemModelGenerator.register(ModItems.COPPER_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.COPPER_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.COPPER_BOOTS, Models.GENERATED);

        itemModelGenerator.register(ModItems.LAPIS_LAZULI_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.LAPIS_LAZULI_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.LAPIS_LAZULI_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.LAPIS_LAZULI_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.LAPIS_LAZULI_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.LAPIS_LAZULI_HELMET, Models.GENERATED);
        itemModelGenerator.register(ModItems.LAPIS_LAZULI_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.LAPIS_LAZULI_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.LAPIS_LAZULI_BOOTS, Models.GENERATED);

        itemModelGenerator.register(ModItems.REDSTONE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.REDSTONE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.REDSTONE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.REDSTONE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.REDSTONE_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.REDSTONE_HELMET, Models.GENERATED);
        itemModelGenerator.register(ModItems.REDSTONE_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.REDSTONE_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.REDSTONE_BOOTS, Models.GENERATED);

        itemModelGenerator.register(ModItems.EMERALD_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.EMERALD_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.EMERALD_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.EMERALD_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.EMERALD_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.EMERALD_HELMET, Models.GENERATED);
        itemModelGenerator.register(ModItems.EMERALD_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.EMERALD_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.EMERALD_BOOTS, Models.GENERATED);
    }
}
