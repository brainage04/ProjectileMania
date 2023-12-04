package com.github.brainage04.projectilemania.datagen;

import com.github.brainage04.projectilemania.block.ModBlocks;
import com.github.brainage04.projectilemania.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    private static final Item[] snowballItems = new Item[]{Items.SNOWBALL, ModItems.COMPACT_SNOWBALL, ModItems.INFINITE_SNOWBALL, ModItems.SNOWBALL_CANNON};
    private static final Item[] arrowItems = new Item[]{Items.ARROW, ModItems.COMPACT_ARROW, ModItems.INFINITE_ARROW, ModItems.ARROW_CANNON};

    private static final Item[][] allItems = new Item[][]{snowballItems, arrowItems};

    @Override
    public void generate(RecipeExporter exporter) {
        for (Item[] itemGroup: allItems) {
            offerReversibleCompactingRecipes(exporter, RecipeCategory.COMBAT, itemGroup[0], RecipeCategory.MISC, itemGroup[1]);
            offerCompactingRecipe(exporter, RecipeCategory.COMBAT, itemGroup[2], itemGroup[1]);

            ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, itemGroup[3], 1)
                    .pattern("AAA")
                    .pattern("SBS")
                    .pattern(" S ")
                    .input('A', itemGroup[2])
                    .input('B', Items.DISPENSER)
                    .input('S', Items.STRING)
                    .criterion(hasItem(itemGroup[2]), conditionsFromItem(itemGroup[2]))
                    .offerTo(exporter, new Identifier(getRecipeName(itemGroup[3])));
        }

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModBlocks.INFINITE_SPAMMER_BLOCK, 1)
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .input('A', Items.DISPENSER)
                .input('B', ModItems.INFINITE_ARROW)
                .criterion(hasItem(ModItems.INFINITE_ARROW), conditionsFromItem(ModItems.INFINITE_ARROW))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.INFINITE_SPAMMER_BLOCK)));
    }
}
