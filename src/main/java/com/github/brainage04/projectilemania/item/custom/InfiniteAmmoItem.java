package com.github.brainage04.projectilemania.item.custom;

import com.github.brainage04.projectilemania.item.ModItems;
import com.github.brainage04.projectilemania.util.InfiniteAmmoUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.thrown.EggEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class InfiniteAmmoItem extends Item {
    public InfiniteAmmoItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (!world.isClient) {
            if (itemStack.isOf(ModItems.INFINITE_SNOWBALL)) {
                InfiniteAmmoUtils.playSound(world, player.getPos(), InfiniteAmmoUtils.InfiniteSoundType.EGG);

                SnowballEntity entity = new SnowballEntity(world, player);
                entity.setItem(itemStack);
                entity.setVelocity(player, player.getPitch(), player.getYaw(), InfiniteAmmoUtils.ROLL, InfiniteAmmoUtils.SPEED, InfiniteAmmoUtils.DIVERGENCE);
                world.spawnEntity(entity);
            } else if (itemStack.isOf(ModItems.INFINITE_EGG)) {
                InfiniteAmmoUtils.playSound(world, player.getPos(), InfiniteAmmoUtils.InfiniteSoundType.SNOWBALL);

                EggEntity entity = new EggEntity(world, player);
                entity.setItem(itemStack);
                entity.setVelocity(player, player.getPitch(), player.getYaw(), InfiniteAmmoUtils.ROLL, InfiniteAmmoUtils.SPEED, InfiniteAmmoUtils.DIVERGENCE);
                world.spawnEntity(entity);
            } else if (itemStack.isOf(ModItems.INFINITE_ARROW)) {
                InfiniteAmmoUtils.playSound(world, player.getPos(), InfiniteAmmoUtils.InfiniteSoundType.ARROW);

                ArrowEntity entity = new ArrowEntity(world, player, new ItemStack(Items.ARROW));
                entity.setVelocity(player, player.getPitch(), player.getYaw(), InfiniteAmmoUtils.ROLL, InfiniteAmmoUtils.SPEED, InfiniteAmmoUtils.DIVERGENCE);
                world.spawnEntity(entity);
            }
        }

        player.incrementStat(Stats.USED.getOrCreateStat(this));

        return TypedActionResult.success(itemStack, world.isClient());
    }
}
