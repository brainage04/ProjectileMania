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
        // tag stick is using a vanilla stick texture

        itemModelGenerator.register(ModItems.COMPACT_SNOWBALL, Models.GENERATED);
        itemModelGenerator.register(ModItems.INFINITE_SNOWBALL, Models.GENERATED);

        itemModelGenerator.register(ModItems.COMPACT_EGG, Models.GENERATED);
        itemModelGenerator.register(ModItems.INFINITE_EGG, Models.GENERATED);

        itemModelGenerator.register(ModItems.COMPACT_ARROW, Models.GENERATED);
        itemModelGenerator.register(ModItems.INFINITE_ARROW, Models.GENERATED);

        itemModelGenerator.register(ModItems.COPPER_HELMET, Models.GENERATED);
        itemModelGenerator.register(ModItems.COPPER_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.COPPER_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.COPPER_BOOTS, Models.GENERATED);
    }
}
