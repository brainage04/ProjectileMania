package com.github.brainage04.projectilemania.block.enchantments.custom;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class IBelieveYouCanFlyEnchantment extends Enchantment {
    public IBelieveYouCanFlyEnchantment(EnchantmentTarget enchantmentTarget) {
        super(Rarity.VERY_RARE, enchantmentTarget, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinPower(int level) {
        return 1;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public void onTargetDamaged(LivingEntity player, Entity target, int level) {
        ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, level * 20, level));

        super.onTargetDamaged(player, target, level);
    }
}
