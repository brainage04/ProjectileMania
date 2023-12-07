package com.github.brainage04.projectilemania.block.enchantments.custom;

import com.github.brainage04.projectilemania.block.enchantments.ModEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;

public class FuckOffEnchantment extends Enchantment {
    public FuckOffEnchantment(EnchantmentTarget enchantmentTarget) {
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
        Vec3d difference = target.getPos().add(player.getPos().negate());

        target.addVelocity(difference.multiply(0.04 * level));

        super.onTargetDamaged(player, target, level);
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return other != ModEnchantments.BOW_GET_OVER_HERE && other != ModEnchantments.CROSSBOW_GET_OVER_HERE && other != ModEnchantments.TRIDENT_GET_OVER_HERE;
    }
}
