package com.github.brainage04.projectilemania.item.custom;

import com.github.brainage04.projectilemania.item.ModItems;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.thrown.EggEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class InfiniteAmmoItem extends Item {
    public InfiniteAmmoItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();

        if (item == ModItems.INFINITE_SNOWBALL) {
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.PLAYERS, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
            if (!world.isClient) {
                EggEntity entity = new EggEntity(world, player);
                entity.setItem(itemStack);
                entity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0f, 2.25f, 0.1f);
                world.spawnEntity(entity);
            }
        } else if (item == ModItems.INFINITE_EGG) {
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_EGG_THROW, SoundCategory.PLAYERS, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
            if (!world.isClient) {
                SnowballEntity entity = new SnowballEntity(world, player);
                entity.setItem(itemStack);
                entity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0f, 2.25f, 0.1f);
                world.spawnEntity(entity);
            }
        } else if (item == ModItems.INFINITE_ARROW) {
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
            if (!world.isClient) {
                ArrowEntity entity = new ArrowEntity(world, player, new ItemStack(Items.ARROW));
                entity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0f, 2.25f, 0.1f);
                world.spawnEntity(entity);
            }
        } else if (item == ModItems.INFINITE_TNT) {
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
            if (!world.isClient) {
                TntEntity entity = new TntEntity(world, player.getX(), player.getY(), player.getZ(), player);
                float f = -MathHelper.sin(player.getYaw() * ((float)Math.PI / 180)) * MathHelper.cos(player.getPitch() * ((float)Math.PI / 180));
                float g = -MathHelper.sin(player.getPitch() * ((float)Math.PI / 180));
                float h = MathHelper.cos(player.getYaw() * ((float)Math.PI / 180)) * MathHelper.cos(player.getPitch() * ((float)Math.PI / 180));
                entity.setVelocity(f, g, h);
                entity.setFuse(80);
                world.spawnEntity(entity);
            }
        }

        player.incrementStat(Stats.USED.getOrCreateStat(this));

        return TypedActionResult.success(itemStack, world.isClient());
    }
}
