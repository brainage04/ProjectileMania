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
    private static final Item[] eggItems = new Item[]{Items.EGG, ModItems.COMPACT_EGG, ModItems.INFINITE_EGG};
    private static final Item[] arrowItems = new Item[]{Items.ARROW, ModItems.COMPACT_ARROW, ModItems.INFINITE_ARROW};
    private static final Item[] tntItems = new Item[]{Items.TNT, ModBlocks.COMPACT_TNT.asItem(), ModBlocks.INFINITE_TNT.asItem()};

    private static final Item[][] allItems = new Item[][]{snowballItems, eggItems, arrowItems, tntItems};

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
                .criterion(hasItem(Items.TNT), conditionsFromItem(Items.TNT))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.INFINITE_SPAMMER_BLOCK)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModBlocks.IMPACT_TNT, 2)
                .input(Items.TNT)
                .input(Items.GUNPOWDER)
                .input(Items.SAND)
                .criterion(hasItem(Items.TNT), conditionsFromItem(Items.TNT))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.IMPACT_TNT)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.TAG_STICK, 1)
                .pattern("S")
                .pattern("S")
                .pattern("S")
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TAG_STICK)));

        // copper tools
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.COPPER_SWORD, 1)
                .pattern("C")
                .pattern("C")
                .pattern("S")
                .input('C', Items.COPPER_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.COPPER_SWORD)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.COPPER_AXE, 1)
                .pattern("CC")
                .pattern("CS")
                .pattern(" S")
                .input('C', Items.COPPER_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.COPPER_AXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.COPPER_SHOVEL, 1)
                .pattern("C")
                .pattern("S")
                .pattern("S")
                .input('C', Items.COPPER_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.COPPER_SHOVEL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.COPPER_PICKAXE, 1)
                .pattern("CCC")
                .pattern(" S ")
                .pattern(" S ")
                .input('C', Items.COPPER_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.COPPER_PICKAXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.COPPER_HOE, 1)
                .pattern("CC")
                .pattern(" S")
                .pattern(" S")
                .input('C', Items.COPPER_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.COPPER_HOE)));

        // copper armor
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.COPPER_HELMET, 1)
                .pattern("CCC")
                .pattern("C C")
                .input('C', Items.COPPER_INGOT)
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.COPPER_HELMET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.COPPER_CHESTPLATE, 1)
                .pattern("C C")
                .pattern("CCC")
                .pattern("CCC")
                .input('C', Items.COPPER_INGOT)
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.COPPER_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.COPPER_LEGGINGS, 1)
                .pattern("CCC")
                .pattern("C C")
                .pattern("C C")
                .input('C', Items.COPPER_INGOT)
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.COPPER_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.COPPER_BOOTS, 1)
                .pattern("C C")
                .pattern("C C")
                .input('C', Items.COPPER_INGOT)
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.COPPER_BOOTS)));
    }
}
