package com.github.brainage04.projectilemania.item.custom.tool.copper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.world.World;

public class CopperPickaxeItem extends PickaxeItem {
    public CopperPickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        PlayerEntity player = ((PlayerEntity) entity);

        if (selected) player.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 20, 0));

        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
