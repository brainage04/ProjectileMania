package com.github.brainage04.projectilemania.item;

import com.github.brainage04.projectilemania.ProjectileMania;
import com.github.brainage04.projectilemania.item.custom.CannonItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item COMPACT_SNOWBALL = registerItem("compact_snowball", new Item(new FabricItemSettings().maxCount(16)));
    public static final Item INFINITE_SNOWBALL = registerItem("infinite_snowball", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item COMPACT_ARROW = registerItem("compact_arrow", new Item(new FabricItemSettings()));
    public static final Item INFINITE_ARROW = registerItem("infinite_arrow", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item CANNON_ITEM = registerItem("cannon_item", new CannonItem(new FabricItemSettings().maxCount(1)));

    private static void addItemsToIngredientTabItemGroup(FabricItemGroupEntries entries) {
        entries.add(INFINITE_SNOWBALL);
        entries.add(COMPACT_SNOWBALL);

        entries.add(COMPACT_ARROW);
        entries.add(INFINITE_ARROW);

        entries.add(CANNON_ITEM);
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
