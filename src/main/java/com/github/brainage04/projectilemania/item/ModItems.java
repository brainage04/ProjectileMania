package com.github.brainage04.projectilemania.item;

import com.github.brainage04.projectilemania.ProjectileMania;
import com.github.brainage04.projectilemania.block.ModBlocks;
import com.github.brainage04.projectilemania.item.custom.CannonItem;
import com.github.brainage04.projectilemania.item.custom.HotPotatoItem;
import com.github.brainage04.projectilemania.item.custom.InfiniteAmmoItem;
import com.github.brainage04.projectilemania.item.custom.TagStickItem;
import com.github.brainage04.projectilemania.item.custom.equipment.armor.ModArmorItem;
import com.github.brainage04.projectilemania.item.custom.equipment.armor.ModArmorMaterials;
import com.github.brainage04.projectilemania.item.custom.tool.copper.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
    public static final Item COMPACT_SNOWBALL = registerItem("compact_snowball", new Item(new FabricItemSettings().maxCount(16)));
    public static final Item INFINITE_SNOWBALL = registerItem("infinite_snowball", new InfiniteAmmoItem(new FabricItemSettings().maxCount(1)));

    public static final Item COMPACT_EGG = registerItem("compact_egg", new EggItem(new FabricItemSettings().maxCount(16)));
    public static final Item INFINITE_EGG = registerItem("infinite_egg", new InfiniteAmmoItem(new FabricItemSettings().maxCount(1)));

    public static final Item COMPACT_ARROW = registerItem("compact_arrow", new Item(new FabricItemSettings()));
    public static final Item INFINITE_ARROW = registerItem("infinite_arrow", new InfiniteAmmoItem(new FabricItemSettings().maxCount(1)));

    public static final Item CANNON_ITEM = registerItem("cannon_item", new CannonItem(new FabricItemSettings().maxCount(1)));
    public static final Item TAG_STICK = registerItem("tag_stick", new TagStickItem(new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    public static final Item HOT_POTATO = registerItem("hot_potato", new HotPotatoItem(new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));

    public static final ToolMaterial COPPER_TOOL_MATERIAL = new CopperToolMaterial();
    public static final Item COPPER_SWORD = registerItem("copper_sword", new SwordItem(COPPER_TOOL_MATERIAL, 3, -2.4F, new FabricItemSettings()));
    public static final Item COPPER_SHOVEL = registerItem("copper_shovel", new CopperShovelItem(COPPER_TOOL_MATERIAL, 1.5F, -3.0F, new FabricItemSettings()));
    public static final Item COPPER_PICKAXE = registerItem("copper_pickaxe", new CopperPickaxeItem(COPPER_TOOL_MATERIAL, 1, -2.8F, new FabricItemSettings()));
    public static final Item COPPER_AXE = registerItem("copper_axe", new CopperAxeItem(COPPER_TOOL_MATERIAL, 6.0F, -3.1F, new FabricItemSettings()));
    public static final Item COPPER_HOE = registerItem("copper_hoe", new CopperHoeItem(COPPER_TOOL_MATERIAL, -2, -1.0F, new FabricItemSettings()));
    public static final Item COPPER_HELMET = registerItem("copper_helmet", new ModArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item COPPER_CHESTPLATE = registerItem("copper_chestplate", new ModArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item COPPER_LEGGINGS = registerItem("copper_leggings", new ModArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item COPPER_BOOTS = registerItem("copper_boots", new ModArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    private static void addItemsToIngredientTabItemGroup(FabricItemGroupEntries entries) {
        entries.add(ModBlocks.INFINITE_SPAMMER_BLOCK);
        entries.add(CANNON_ITEM);

        entries.add(COMPACT_SNOWBALL);
        entries.add(INFINITE_SNOWBALL);

        entries.add(COMPACT_EGG);
        entries.add(INFINITE_EGG);

        entries.add(COMPACT_ARROW);
        entries.add(INFINITE_ARROW);

        entries.add(ModBlocks.COMPACT_TNT);
        entries.add(ModBlocks.INFINITE_TNT);
        entries.add(ModBlocks.IMPACT_TNT);

        entries.add(TAG_STICK);
        entries.add(HOT_POTATO);

        entries.add(COPPER_SWORD);
        entries.add(COPPER_SHOVEL);
        entries.add(COPPER_PICKAXE);
        entries.add(COPPER_AXE);
        entries.add(COPPER_HOE);
        entries.add(COPPER_HELMET);
        entries.add(COPPER_CHESTPLATE);
        entries.add(COPPER_LEGGINGS);
        entries.add(COPPER_BOOTS);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(ProjectileMania.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ProjectileMania.LOGGER.info("Registering Mod Items for " + ProjectileMania.MOD_NAME + "...");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToIngredientTabItemGroup);

        ProjectileMania.LOGGER.info("Mod Items registered.");
    }
}
