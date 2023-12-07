package com.github.brainage04.projectilemania.block.enchantments.custom;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Vanishable;

public class CannonMultishotEnchantment extends Enchantment implements Vanishable {
    public CannonMultishotEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.VANISHABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinPower(int level) {
        return 1;
    }


    @Override
    public int getMaxLevel() {
        return 2;
    }
}
