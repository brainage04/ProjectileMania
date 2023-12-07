package com.github.brainage04.projectilemania.block.enchantments.custom;

import com.github.brainage04.projectilemania.util.MathUtils;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;

public class KnockrandomEnchantment extends Enchantment {
    public KnockrandomEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
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
        Vec3d random = new Vec3d(MathUtils.randomRange(-1, 1), MathUtils.randomRange(-1, 1), MathUtils.randomRange(-1, 1)).normalize().multiply(2);

        target.addVelocity(random);

        super.onTargetDamaged(user, target, level);
    }
}