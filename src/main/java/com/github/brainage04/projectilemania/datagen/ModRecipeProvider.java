package com.github.brainage04.projectilemania.datagen;

import com.github.brainage04.projectilemania.block.ModBlocks;
import com.github.brainage04.projectilemania.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    private static final Item[] snowballItems = new Item[]{Items.SNOWBALL, ModItems.COMPACT_SNOWBALL, ModItems.INFINITE_SNOWBALL};
    private static final Item[] arrowItems = new Item[]{Items.ARROW, ModItems.COMPACT_ARROW, ModItems.INFINITE_ARROW};

    private static final Item[][] allItems = new Item[][]{snowballItems, arrowItems};

    @Override
    public void generate(RecipeExporter exporter) {
        for (Item[] itemGroup: allItems) {
            offerReversibleCompactingRecipes(exporter, RecipeCategory.COMBAT, itemGroup[0], RecipeCategory.MISC, itemGroup[1]);
            offerCompactingRecipe(exporter, RecipeCategory.COMBAT, itemGroup[2], itemGroup[1]);
        }

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.CANNON_ITEM, 1)
                .pattern("QQQ")
                .pattern("SDS")
                .pattern("TST")
                .input('Q', Items.QUARTZ_BLOCK)
                .input('D', Items.DISPENSER)
                .input('S', Items.STRING)
                .input('T', Items.TNT)
                .criterion(hasItem(Items.TNT), conditionsFromItem(Items.TNT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.CANNON_ITEM)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModBlocks.INFINITE_SPAMMER_BLOCK, 1)
                .pattern(" D ")
                .pattern("DTD")
                .pattern(" D ")
                .input('D', Items.DISPENSER)
                .input('T', Items.TNT)
                .criterion(hasItem(ModItems.INFINITE_ARROW), conditionsFromItem(ModItems.INFINITE_ARROW))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.INFINITE_SPAMMER_BLOCK)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModBlocks.IMPACT_TNT_BLOCK, 2)
                .input(Items.TNT)
                .input(Items.GUNPOWDER)
                .input(Items.SAND)
                .criterion(hasItem(Items.TNT), conditionsFromItem(Items.TNT))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.IMPACT_TNT_BLOCK)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.TAG_STICK, 1)
                .pattern("S")
                .pattern("S")
                .pattern("S")
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TAG_STICK)));
    }
}
