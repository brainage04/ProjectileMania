package com.github.brainage04.projectilemania.datagen;

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
        // infinite spammer block is using a custom model
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.COMPACT_SNOWBALL, Models.GENERATED);
        itemModelGenerator.register(ModItems.INFINITE_SNOWBALL, Models.GENERATED);
        itemModelGenerator.register(ModItems.SNOWBALL_CANNON, Models.GENERATED);

        itemModelGenerator.register(ModItems.COMPACT_ARROW, Models.GENERATED);
        itemModelGenerator.register(ModItems.INFINITE_ARROW, Models.GENERATED);
        itemModelGenerator.register(ModItems.ARROW_CANNON, Models.GENERATED);
    }
}
