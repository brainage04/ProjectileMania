package com.github.brainage04.projectilemania.enchantment.custom;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;

public class AutoSmeltEnchantment extends Enchantment {
    public AutoSmeltEnchantment() {
        super(Enchantment.Rarity.RARE, EnchantmentTarget.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinPower(int level) {
        return 1;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other) && !other.equals(Enchantments.SILK_TOUCH);
    }
}
