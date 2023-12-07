package com.github.brainage04.projectilemania.block.enchantments.custom;

import com.github.brainage04.projectilemania.util.MathUtils;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SnowballOverloadEnchantment extends Enchantment {
    public SnowballOverloadEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.ARMOR_LEGS, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
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
    public void onUserDamaged(LivingEntity player, Entity attacker, int level) {
        float amount = level * 10;

        World world = player.getWorld();
        Vec3d playerPos = player.getPos();

        for (int i = 0; i < amount; i++) {
            SnowballEntity snowballEntity = new SnowballEntity(world, player);
            Vec3d positionVector = new Vec3d(playerPos.x + MathUtils.randomRange(-1, 1) * level, playerPos.y + MathUtils.randomRange(0, 1) * level, playerPos.z + MathUtils.randomRange(-1, 1) * level);
            snowballEntity.setPosition(positionVector);
            Vec3d velocityVector = new Vec3d(MathUtils.randomRange(-1, 1), MathUtils.randomRange(-1, 1), MathUtils.randomRange(-1, 1));
            snowballEntity.setVelocity(velocityVector);
        }

        super.onUserDamaged(player, attacker, level);
    }
}
