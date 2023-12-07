package com.github.brainage04.projectilemania.block.enchantments.custom;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SuicideBomberEnchantment extends Enchantment {
    public SuicideBomberEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST});
    }

    @Override
    public int getMinPower(int level) {
        return 1;
    }

    @Override
    public int getMaxLevel() {
        return 6;
    }

    @Override
    public void onUserDamaged(LivingEntity player, Entity attacker, int level) {
        float increment = 360f / level;
        double randomRotationAmount = (Math.random() * 30) - 15;

        World world = player.getWorld();
        Vec3d playerPos = player.getPos();

        for (int i = 0; i < level; i++) {
            double rot = ((increment * i) + randomRotationAmount) * Math.PI / 180;
            double x = playerPos.getX() + 5 * Math.sin(rot);
            double y = playerPos.getY() + player.getHeight() * 0.5f;
            double z = playerPos.getZ() + 5 * Math.cos(rot);

            TntEntity tntEntity = new TntEntity(world, x, y, z, player);

            Vec3d playerToAttackerVector = attacker.getPos().add(new Vec3d(x, y, z).negate()).add(new Vec3d(0, 1, 0));

            tntEntity.setVelocity(playerToAttackerVector.multiply(0.2));
            tntEntity.setFuse(10);
            world.spawnEntity(tntEntity);
        }

        super.onUserDamaged(player, attacker, level);
    }
}
