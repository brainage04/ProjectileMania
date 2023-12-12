package com.github.brainage04.projectilemania.enchantment.custom;

import com.github.brainage04.projectilemania.enchantment.ModEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;

public class GetOverHereEnchantment extends Enchantment {
    public GetOverHereEnchantment(EnchantmentTarget enchantmentTarget) {
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
        Vec3d difference = target.getPos().add(player.getPos().negate()).negate(); // vector from target towards player

        if (difference.y < 0) difference.multiply(1, -1, 1); // ensure y value is always positive
        if (difference.distanceTo(new Vec3d(0, 0, 0)) > 5) difference.normalize().multiply(5); // ensure magnitude is 5 units or less

        target.addVelocity(difference.multiply(0.1 * level));

        super.onTargetDamaged(player, target, level);
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return other != ModEnchantments.BOW_FUCK_OFF && other != ModEnchantments.CROSSBOW_FUCK_OFF && other != ModEnchantments.TRIDENT_FUCK_OFF;
    }
}
