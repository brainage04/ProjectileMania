package com.github.brainage04.projectilemania.enchantments.custom;

import com.github.brainage04.projectilemania.util.MathUtils;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class ShrapnelEnchantment extends Enchantment {
    public ShrapnelEnchantment(EnchantmentTarget enchantmentTarget) {
        super(Rarity.UNCOMMON, enchantmentTarget, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinPower(int level) {
        return 1;
    }

    @Override
    public int getMaxLevel() {
        return 10;
    }

    @Override
    public void onTargetDamaged(LivingEntity player, Entity target, int level) {
        float increment = 360f / level;
        double height = target.getY() + target.getHeight() * 0.8f;
        float angleOffset = (float) MathUtils.randomRange(5, 10);

        World world = player.getWorld();

        for (int i = 0; i < level; i++) {
            ArrowEntity arrowEntity = new ArrowEntity(world, target.getX(), height, target.getZ(), new ItemStack(Items.ARROW));
            arrowEntity.setVelocity(target, (float) MathUtils.randomRange(5, 10), (increment * i + angleOffset) % 360, 0.0F, 2F, 0.01F);
            world.spawnEntity(arrowEntity);
        }

        super.onTargetDamaged(player, target, level);
    }
}
