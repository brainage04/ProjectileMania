package com.github.brainage04.projectilemania.item;

import com.github.brainage04.projectilemania.ProjectileMania;
import com.github.brainage04.projectilemania.block.ModBlocks;
import com.github.brainage04.projectilemania.item.custom.CannonItem;
import com.github.brainage04.projectilemania.item.custom.HotPotatoItem;
import com.github.brainage04.projectilemania.item.custom.InfiniteAmmoItem;
import com.github.brainage04.projectilemania.item.custom.TagStickItem;
import com.github.brainage04.projectilemania.item.custom.equipment.armor.ModArmorItem;
import com.github.brainage04.projectilemania.item.custom.equipment.armor.ModArmorMaterials;
import com.github.brainage04.projectilemania.item.custom.equipment.tool.EmeraldToolMaterial;
import com.github.brainage04.projectilemania.item.custom.equipment.tool.LapisLazuliToolMaterial;
import com.github.brainage04.projectilemania.item.custom.equipment.tool.RedstoneToolMaterial;
import com.github.brainage04.projectilemania.item.custom.equipment.tool.copper.*;
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
    public static final Item COPPER_HELMET = registerItem("copper_helmet", new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item COPPER_CHESTPLATE = registerItem("copper_chestplate", new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item COPPER_LEGGINGS = registerItem("copper_leggings", new ModArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.LEGGINGS, new FabricItemSettings())); // only one piece (leggings) is used to reduce inventoryTick calls
    public static final Item COPPER_BOOTS = registerItem("copper_boots", new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    public static final ToolMaterial LAPIS_LAZULI_TOOL_MATERIAL = new LapisLazuliToolMaterial();
    public static final Item LAPIS_LAZULI_SWORD = registerItem("lapis_lazuli_sword", new SwordItem(LAPIS_LAZULI_TOOL_MATERIAL, 3, -2.4F, new FabricItemSettings()));
    public static final Item LAPIS_LAZULI_SHOVEL = registerItem("lapis_lazuli_shovel", new ShovelItem(LAPIS_LAZULI_TOOL_MATERIAL, 1.5F, -3.0F, new FabricItemSettings()));
    public static final Item LAPIS_LAZULI_PICKAXE = registerItem("lapis_lazuli_pickaxe", new PickaxeItem(LAPIS_LAZULI_TOOL_MATERIAL, 1, -2.8F, new FabricItemSettings()));
    public static final Item LAPIS_LAZULI_AXE = registerItem("lapis_lazuli_axe", new AxeItem(LAPIS_LAZULI_TOOL_MATERIAL, 6.0F, -3.1F, new FabricItemSettings()));
    public static final Item LAPIS_LAZULI_HOE = registerItem("lapis_lazuli_hoe", new HoeItem(LAPIS_LAZULI_TOOL_MATERIAL, -2, -1.0F, new FabricItemSettings()));
    public static final Item LAPIS_LAZULI_HELMET = registerItem("lapis_lazuli_helmet", new ArmorItem(ModArmorMaterials.LAPIS_LAZULI, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item LAPIS_LAZULI_CHESTPLATE = registerItem("lapis_lazuli_chestplate", new ArmorItem(ModArmorMaterials.LAPIS_LAZULI, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item LAPIS_LAZULI_LEGGINGS = registerItem("lapis_lazuli_leggings", new ModArmorItem(ModArmorMaterials.LAPIS_LAZULI, ArmorItem.Type.LEGGINGS, new FabricItemSettings())); // only one piece (leggings) is used to reduce inventoryTick calls
    public static final Item LAPIS_LAZULI_BOOTS = registerItem("lapis_lazuli_boots", new ArmorItem(ModArmorMaterials.LAPIS_LAZULI, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    public static final ToolMaterial REDSTONE_TOOL_MATERIAL = new RedstoneToolMaterial();
    public static final Item REDSTONE_SWORD = registerItem("redstone_sword", new SwordItem(REDSTONE_TOOL_MATERIAL, 3, -2.4F, new FabricItemSettings()));
    public static final Item REDSTONE_SHOVEL = registerItem("redstone_shovel", new ShovelItem(REDSTONE_TOOL_MATERIAL, 1.5F, -3.0F, new FabricItemSettings()));
    public static final Item REDSTONE_PICKAXE = registerItem("redstone_pickaxe", new PickaxeItem(REDSTONE_TOOL_MATERIAL, 1, -2.8F, new FabricItemSettings()));
    public static final Item REDSTONE_AXE = registerItem("redstone_axe", new AxeItem(REDSTONE_TOOL_MATERIAL, 6.0F, -3.1F, new FabricItemSettings()));
    public static final Item REDSTONE_HOE = registerItem("redstone_hoe", new HoeItem(REDSTONE_TOOL_MATERIAL, -2, -1.0F, new FabricItemSettings()));
    public static final Item REDSTONE_HELMET = registerItem("redstone_helmet", new ArmorItem(ModArmorMaterials.REDSTONE, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item REDSTONE_CHESTPLATE = registerItem("redstone_chestplate", new ArmorItem(ModArmorMaterials.REDSTONE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item REDSTONE_LEGGINGS = registerItem("redstone_leggings", new ModArmorItem(ModArmorMaterials.REDSTONE, ArmorItem.Type.LEGGINGS, new FabricItemSettings())); // only one piece (leggings) is used to reduce inventoryTick calls
    public static final Item REDSTONE_BOOTS = registerItem("redstone_boots", new ArmorItem(ModArmorMaterials.REDSTONE, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    public static final ToolMaterial EMERALD_TOOL_MATERIAL = new EmeraldToolMaterial();
    public static final Item EMERALD_SWORD = registerItem("emerald_sword", new SwordItem(EMERALD_TOOL_MATERIAL, 3, -2.4F, new FabricItemSettings()));
    public static final Item EMERALD_SHOVEL = registerItem("emerald_shovel", new ShovelItem(EMERALD_TOOL_MATERIAL, 1.5F, -3.0F, new FabricItemSettings()));
    public static final Item EMERALD_PICKAXE = registerItem("emerald_pickaxe", new PickaxeItem(EMERALD_TOOL_MATERIAL, 1, -2.8F, new FabricItemSettings()));
    public static final Item EMERALD_AXE = registerItem("emerald_axe", new AxeItem(EMERALD_TOOL_MATERIAL, 6.0F, -3.1F, new FabricItemSettings()));
    public static final Item EMERALD_HOE = registerItem("emerald_hoe", new HoeItem(EMERALD_TOOL_MATERIAL, -2, -1.0F, new FabricItemSettings()));
    public static final Item EMERALD_HELMET = registerItem("emerald_helmet", new ArmorItem(ModArmorMaterials.EMERALD, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item EMERALD_CHESTPLATE = registerItem("emerald_chestplate", new ArmorItem(ModArmorMaterials.EMERALD, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item EMERALD_LEGGINGS = registerItem("emerald_leggings", new ModArmorItem(ModArmorMaterials.EMERALD, ArmorItem.Type.LEGGINGS, new FabricItemSettings())); // only one piece (leggings) is used to reduce inventoryTick calls
    public static final Item EMERALD_BOOTS = registerItem("emerald_boots", new ArmorItem(ModArmorMaterials.EMERALD, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    private static void combatItemGroupAdditions(FabricItemGroupEntries entries) {
        entries.add(CANNON_ITEM);

        entries.add(INFINITE_SNOWBALL);

        entries.add(INFINITE_EGG);

        entries.add(INFINITE_ARROW);

        entries.add(ModBlocks.COMPACT_TNT);
        entries.add(ModBlocks.INFINITE_TNT);
        entries.add(ModBlocks.IMPACT_TNT);

        entries.add(TAG_STICK);
        entries.add(HOT_POTATO);

        entries.add(COPPER_SWORD);
        entries.add(COPPER_AXE);
        entries.add(COPPER_HELMET);
        entries.add(COPPER_CHESTPLATE);
        entries.add(COPPER_LEGGINGS);
        entries.add(COPPER_BOOTS);

        entries.add(REDSTONE_SWORD);
        entries.add(REDSTONE_AXE);
        entries.add(REDSTONE_HELMET);
        entries.add(REDSTONE_CHESTPLATE);
        entries.add(REDSTONE_LEGGINGS);
        entries.add(REDSTONE_BOOTS);

        entries.add(LAPIS_LAZULI_SWORD);
        entries.add(LAPIS_LAZULI_AXE);
        entries.add(LAPIS_LAZULI_HELMET);
        entries.add(LAPIS_LAZULI_CHESTPLATE);
        entries.add(LAPIS_LAZULI_LEGGINGS);
        entries.add(LAPIS_LAZULI_BOOTS);

        entries.add(EMERALD_SWORD);
        entries.add(EMERALD_AXE);
        entries.add(EMERALD_HELMET);
        entries.add(EMERALD_CHESTPLATE);
        entries.add(EMERALD_LEGGINGS);
        entries.add(EMERALD_BOOTS);
    }

    private static void ingredientsItemGroupAdditions(FabricItemGroupEntries entries) {
        entries.add(COMPACT_SNOWBALL);
        entries.add(COMPACT_EGG);
        entries.add(COMPACT_ARROW);
    }

    private static void redstoneItemGroupAdditions(FabricItemGroupEntries entries) {
        entries.add(ModBlocks.INFINITE_SPAMMER_BLOCK);
    }

    private static void toolsItemGroupAdditions(FabricItemGroupEntries entries) {
        entries.add(COPPER_SHOVEL);
        entries.add(COPPER_PICKAXE);
        entries.add(COPPER_HOE);

        entries.add(REDSTONE_SHOVEL);
        entries.add(REDSTONE_PICKAXE);
        entries.add(REDSTONE_HOE);

        entries.add(LAPIS_LAZULI_SHOVEL);
        entries.add(LAPIS_LAZULI_PICKAXE);
        entries.add(LAPIS_LAZULI_HOE);

        entries.add(EMERALD_SHOVEL);
        entries.add(EMERALD_PICKAXE);
        entries.add(EMERALD_HOE);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(ProjectileMania.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ProjectileMania.LOGGER.info("Registering Mod Items for " + ProjectileMania.MOD_NAME + "...");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::combatItemGroupAdditions);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::ingredientsItemGroupAdditions);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(ModItems::redstoneItemGroupAdditions);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::toolsItemGroupAdditions);

        ProjectileMania.LOGGER.info("Mod Items registered.");
    }
}
