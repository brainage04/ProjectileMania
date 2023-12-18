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

    private static void generateSwordAndToolRecipes(Item sword, Item axe, Item shovel, Item pickaxe, Item hoe, Item ingredient, RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, sword, 1)
                .pattern("C")
                .pattern("C")
                .pattern("S")
                .input('C', ingredient)
                .input('S', Items.STICK)
                .criterion(hasItem(ingredient), conditionsFromItem(ingredient))
                .offerTo(exporter, new Identifier(getRecipeName(sword)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, axe, 1)
                .pattern("CC")
                .pattern("CS")
                .pattern(" S")
                .input('C', ingredient)
                .input('S', Items.STICK)
                .criterion(hasItem(ingredient), conditionsFromItem(ingredient))
                .offerTo(exporter, new Identifier(getRecipeName(axe)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, shovel, 1)
                .pattern("C")
                .pattern("S")
                .pattern("S")
                .input('C', ingredient)
                .input('S', Items.STICK)
                .criterion(hasItem(ingredient), conditionsFromItem(ingredient))
                .offerTo(exporter, new Identifier(getRecipeName(shovel)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, pickaxe, 1)
                .pattern("CCC")
                .pattern(" S ")
                .pattern(" S ")
                .input('C', ingredient)
                .input('S', Items.STICK)
                .criterion(hasItem(ingredient), conditionsFromItem(ingredient))
                .offerTo(exporter, new Identifier(getRecipeName(pickaxe)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, hoe, 1)
                .pattern("CC")
                .pattern(" S")
                .pattern(" S")
                .input('C', ingredient)
                .input('S', Items.STICK)
                .criterion(hasItem(ingredient), conditionsFromItem(ingredient))
                .offerTo(exporter, new Identifier(getRecipeName(hoe)));
    }

    private static void generateArmorRecipes(Item helmet, Item chestplate, Item leggings, Item boots, Item ingredient, RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, helmet, 1)
                .pattern("CCC")
                .pattern("C C")
                .input('C', ingredient)
                .criterion(hasItem(ingredient), conditionsFromItem(ingredient))
                .offerTo(exporter, new Identifier(getRecipeName(helmet)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, chestplate, 1)
                .pattern("C C")
                .pattern("CCC")
                .pattern("CCC")
                .input('C', ingredient)
                .criterion(hasItem(ingredient), conditionsFromItem(ingredient))
                .offerTo(exporter, new Identifier(getRecipeName(chestplate)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, leggings, 1)
                .pattern("CCC")
                .pattern("C C")
                .pattern("C C")
                .input('C', ingredient)
                .criterion(hasItem(ingredient), conditionsFromItem(ingredient))
                .offerTo(exporter, new Identifier(getRecipeName(leggings)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, boots, 1)
                .pattern("C C")
                .pattern("C C")
                .input('C', ingredient)
                .criterion(hasItem(ingredient), conditionsFromItem(ingredient))
                .offerTo(exporter, new Identifier(getRecipeName(boots)));
    }

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

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.HOT_POTATO, 1)
                .pattern("T")
                .pattern("B")
                .pattern("T")
                .input('T', Items.TNT)
                .input('B', Items.BAKED_POTATO)
                .criterion(hasItem(Items.BAKED_POTATO), conditionsFromItem(Items.BAKED_POTATO))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HOT_POTATO)));

        // copper equipment
        generateArmorRecipes(
                ModItems.COPPER_HELMET,
                ModItems.COPPER_CHESTPLATE,
                ModItems.COPPER_LEGGINGS,
                ModItems.COPPER_BOOTS,
                Items.COPPER_BLOCK,
                exporter
        );
        generateSwordAndToolRecipes(
                ModItems.COPPER_SWORD,
                ModItems.COPPER_AXE,
                ModItems.COPPER_SHOVEL,
                ModItems.COPPER_PICKAXE,
                ModItems.COPPER_HOE,
                Items.COPPER_BLOCK,
                exporter
        );
    }
}
