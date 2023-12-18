package com.github.brainage04.projectilemania.datagen;

import com.github.brainage04.projectilemania.ProjectileMania;
import com.github.brainage04.projectilemania.block.ModBlocks;
import com.github.brainage04.projectilemania.effect.ModStatusEffects;
import com.github.brainage04.projectilemania.enchantment.ModEnchantments;
import com.github.brainage04.projectilemania.item.ModItemGroups;
import com.github.brainage04.projectilemania.item.ModItems;
import com.github.brainage04.projectilemania.keybinding.ModKeyBindings;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;

import java.nio.file.Path;

public class ModEnglishLangProvider extends FabricLanguageProvider {
    public ModEnglishLangProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    private static void generateIdenticalEnchantmentTranslations(Enchantment[] enchantments, String enchantmentName, TranslationBuilder translationBuilder) {
        for (Enchantment enchantment : enchantments) {
            translationBuilder.add(enchantment, enchantmentName);
        }
    }

    private static void generateEquipmentTranslations(Item sword, Item axe, Item shovel, Item pickaxe, Item hoe, Item helmet, Item chestplate, Item leggings, Item boots, String materialName, TranslationBuilder translationBuilder) {
        translationBuilder.add(sword, materialName + " Sword");
        translationBuilder.add(axe, materialName + " Axe");
        translationBuilder.add(shovel, materialName + " Shovel");
        translationBuilder.add(pickaxe, materialName + " Pickaxe");
        translationBuilder.add(hoe, materialName + " Hoe");
        translationBuilder.add(helmet, materialName + " Helmet");
        translationBuilder.add(chestplate, materialName + " Chestplate");
        translationBuilder.add(leggings, materialName + " Leggings");
        translationBuilder.add(boots, materialName + " Boots");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        // blocks
        translationBuilder.add(ModBlocks.INFINITE_SPAMMER_BLOCK, "Infinite Spammer Block");
        translationBuilder.add(ModBlocks.COMPACT_TNT, "Compact TNT");
        translationBuilder.add(ModBlocks.INFINITE_TNT, "Infinite TNT");
        translationBuilder.add(ModBlocks.IMPACT_TNT, "Impact TNT");

        // effects
        translationBuilder.add(ModStatusEffects.EXPERIENCE_EFFECT, "Experience");
        translationBuilder.add(ModStatusEffects.TAGGED_EFFECT, "Tagged");
        translationBuilder.add(ModStatusEffects.TAG_IMMUNE_EFFECT, "Tag Immune");

        // enchantments
        generateIdenticalEnchantmentTranslations(new Enchantment[]{
                ModEnchantments.BOW_FUCK_OFF,
                ModEnchantments.CROSSBOW_FUCK_OFF,
                ModEnchantments.TRIDENT_FUCK_OFF
        }, "Fuck Off", translationBuilder);

        generateIdenticalEnchantmentTranslations(new Enchantment[]{
                ModEnchantments.BOW_SHRAPNEL,
                ModEnchantments.CROSSBOW_SHRAPNEL,
                ModEnchantments.TRIDENT_SHRAPNEL
        }, "Shrapnel", translationBuilder);

        generateIdenticalEnchantmentTranslations(new Enchantment[]{
                ModEnchantments.BOW_GET_OVER_HERE,
                ModEnchantments.CROSSBOW_GET_OVER_HERE,
                ModEnchantments.TRIDENT_GET_OVER_HERE
        }, "Get Over Here", translationBuilder);

        generateIdenticalEnchantmentTranslations(new Enchantment[]{
                ModEnchantments.BOW_RANDOM_PUNCH,
                ModEnchantments.CROSSBOW_RANDOM_PUNCH,
                ModEnchantments.TRIDENT_RANDOM_PUNCH
        }, "Random Punch", translationBuilder);

        generateIdenticalEnchantmentTranslations(new Enchantment[]{
                ModEnchantments.BOW_I_BELIEVE_YOU_CAN_FLY,
                ModEnchantments.CROSSBOW_I_BELIEVE_YOU_CAN_FLY,
                ModEnchantments.TRIDENT_I_BELIEVE_YOU_CAN_FLY
        }, "I Believe You Can Fly", translationBuilder);

        translationBuilder.add(ModEnchantments.CANNON_MULTISHOT, "Cannon Multishot");
        translationBuilder.add(ModEnchantments.SUICIDE_BOMBER, "Suicide Bomber");
        translationBuilder.add(ModEnchantments.SNOWBALL_OVERLOAD, "Snowball Overload");
        translationBuilder.add(ModEnchantments.KNOCK_RANDOM, "Knockrandom");
        translationBuilder.add(ModEnchantments.NO_U, "No U");
        translationBuilder.add(ModEnchantments.AUTO_SMELT, "Auto Smelt");
        translationBuilder.add(ModEnchantments.EXCAVATE, "Excavate");

        // item groups
        translationBuilder.add(ModItemGroups.MAIN_GROUP_TRANSLATION_KEY, "Projectile Mania"); // "item_group.projectilemania_group": "Projectile Mania",

        // items
        translationBuilder.add(ModItems.COMPACT_SNOWBALL, "Compact Snowball");
        translationBuilder.add(ModItems.INFINITE_SNOWBALL, "Infinite Snowball");

        translationBuilder.add(ModItems.COMPACT_EGG, "Compact Egg");
        translationBuilder.add(ModItems.INFINITE_EGG, "Infinite Egg");

        translationBuilder.add(ModItems.COMPACT_ARROW, "Compact Arrow");
        translationBuilder.add(ModItems.INFINITE_ARROW, "Infinite Arrow");

        translationBuilder.add(ModItems.CANNON_ITEM, "Cannon Item");
        translationBuilder.add(ModItems.TAG_STICK, "Tag Stick");
        translationBuilder.add(ModItems.HOT_POTATO, "Hot Potato");
        translationBuilder.add(ModItems.GRAPPLING_HOOK, "Grappling Hook");

        generateEquipmentTranslations(
                ModItems.COPPER_SWORD,
                ModItems.COPPER_SHOVEL,
                ModItems.COPPER_PICKAXE,
                ModItems.COPPER_AXE,
                ModItems.COPPER_HOE,
                ModItems.COPPER_HELMET,
                ModItems.COPPER_CHESTPLATE,
                ModItems.COPPER_LEGGINGS,
                ModItems.COPPER_BOOTS,
                "Copper",
                translationBuilder
        );

        generateEquipmentTranslations(
                ModItems.REDSTONE_SWORD,
                ModItems.REDSTONE_SHOVEL,
                ModItems.REDSTONE_PICKAXE,
                ModItems.REDSTONE_AXE,
                ModItems.REDSTONE_HOE,
                ModItems.REDSTONE_HELMET,
                ModItems.REDSTONE_CHESTPLATE,
                ModItems.REDSTONE_LEGGINGS,
                ModItems.REDSTONE_BOOTS,
                "Redstone",
                translationBuilder
        );

        generateEquipmentTranslations(
                ModItems.LAPIS_LAZULI_SWORD,
                ModItems.LAPIS_LAZULI_SHOVEL,
                ModItems.LAPIS_LAZULI_PICKAXE,
                ModItems.LAPIS_LAZULI_AXE,
                ModItems.LAPIS_LAZULI_HOE,
                ModItems.LAPIS_LAZULI_HELMET,
                ModItems.LAPIS_LAZULI_CHESTPLATE,
                ModItems.LAPIS_LAZULI_LEGGINGS,
                ModItems.LAPIS_LAZULI_BOOTS,
                "Lapis Lazuli",
                translationBuilder
        );

        generateEquipmentTranslations(
                ModItems.EMERALD_SWORD,
                ModItems.EMERALD_SHOVEL,
                ModItems.EMERALD_PICKAXE,
                ModItems.EMERALD_AXE,
                ModItems.EMERALD_HOE,
                ModItems.EMERALD_HELMET,
                ModItems.EMERALD_CHESTPLATE,
                ModItems.EMERALD_LEGGINGS,
                ModItems.EMERALD_BOOTS,
                "Emerald",
                translationBuilder
        );

        // key category
        translationBuilder.add(ModKeyBindings.MAIN_CATEGORY, "Projectile Mania"); // "key_category.projectilemania.projectilemania": "Projectile Mania",

        // keys
        translationBuilder.add(ModKeyBindings.GAMMA_TRANSLATION_KEY, "Gamma");
        translationBuilder.add(ModKeyBindings.VEIN_MINER_TRANSLATION_KEY, "Vein Miner");

        // remaining translations
        try { // Load an existing language file.
            Path existingFilePath = dataOutput.getModContainer().findPath("assets/" + ProjectileMania.MOD_ID + "/lang/en_us.existing.json").get();
            translationBuilder.add(existingFilePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add existing language file", e);
        }
    }
}
