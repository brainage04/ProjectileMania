package com.github.brainage04.projectilemania.enchantment.custom;

import com.github.brainage04.projectilemania.util.MathUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;

public class RandomPunchEnchantment extends Enchantment {
    public RandomPunchEnchantment(EnchantmentTarget enchantmentTarget) {
        super(Rarity.RARE, enchantmentTarget, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    @Override
    public int getMinPower(int level) {
        return 1;
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        Vec3d random = new Vec3d(MathUtil.randomRange(-1, 1), MathUtil.randomRange(-1, 1), MathUtil.randomRange(-1, 1)).normalize().multiply(2);

        target.addVelocity(random);

        super.onTargetDamaged(user, target, level);
    }
}
