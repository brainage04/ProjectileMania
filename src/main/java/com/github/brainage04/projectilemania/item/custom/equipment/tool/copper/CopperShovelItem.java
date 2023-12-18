package com.github.brainage04.projectilemania.item.custom.equipment.tool.copper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.world.World;

import static com.github.brainage04.projectilemania.item.custom.equipment.tool.copper.CopperToolMaterial.copperToolEffect;

public class CopperShovelItem extends ShovelItem {
    public CopperShovelItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        PlayerEntity player = ((PlayerEntity) entity);

        if (selected) player.addStatusEffect(new StatusEffectInstance(copperToolEffect.getEffectType(), copperToolEffect.getDuration(), copperToolEffect.getAmplifier()));

        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
