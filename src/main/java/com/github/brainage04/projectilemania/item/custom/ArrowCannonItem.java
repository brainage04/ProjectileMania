package com.github.brainage04.projectilemania.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ArrowCannonItem extends Item {
    private static final int spreadDegrees = 2;

    public ArrowCannonItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand); // creates a new ItemStack instance of the user's itemStack in-hand

        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.MASTER, 0.5F, 0.5F); // plays a globalSoundEvent

        if (!world.isClient) {
            for (int i = 0; i < 3; i++) {
                ArrowEntity arrowEntity = new ArrowEntity(world, player);
                arrowEntity.setVelocity(player, player.getPitch(), player.getYaw() - spreadDegrees + i * spreadDegrees, 0.0F, 3F, 0.01F);
                world.spawnEntity(arrowEntity);
            } // shoot 3 snowballs
        }

        player.incrementStat(Stats.USED.getOrCreateStat(this));

        return TypedActionResult.success(itemStack, world.isClient());
    }
}
