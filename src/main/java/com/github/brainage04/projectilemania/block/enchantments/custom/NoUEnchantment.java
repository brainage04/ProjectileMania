package com.github.brainage04.projectilemania.block.enchantments.custom;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class NoUEnchantment extends Enchantment {
    public NoUEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.BREAKABLE, new EquipmentSlot[]{EquipmentSlot.OFFHAND});
    }

    @Override
    public int getMinPower(int level) {
        return 1;
    }

    @Override
    public int getMaxLevel() {
        return 3;
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
            double y = playerPos.getY() + player.getHeight() * 0.8f;
            double z = playerPos.getZ() + 5 * Math.cos(rot);

            ArrowEntity arrowEntity = new ArrowEntity(world, x, y, z, new ItemStack(Items.ARROW));

            Vec3d playerToAttackerVector = attacker.getPos().add(new Vec3d(x, y, z).negate());

            arrowEntity.setVelocity(playerToAttackerVector.multiply(1));
            world.spawnEntity(arrowEntity);
        }

        super.onUserDamaged(player, attacker, level);
    }
}
