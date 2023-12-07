package com.github.brainage04.projectilemania.util;

import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class InfiniteAmmoUtils {
    public enum InfiniteSoundType {
        SNOWBALL,
        EGG,
        ARROW,
        TNT
    }

    public static void playSound(World world, Vec3d pos, InfiniteSoundType soundType) {
        switch (soundType) {
            case SNOWBALL -> world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.PLAYERS, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
            case EGG -> world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_EGG_THROW, SoundCategory.PLAYERS, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
            case ARROW -> world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
            case TNT -> world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.PLAYERS, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
        }
    }

    public static void playSound(World world, double x, double y, double z, InfiniteSoundType soundType) {
        Vec3d pos = new Vec3d(x, y, z);

        playSound(world, pos, soundType);
    }

    public static final float ROLL = 0f;
    public static final float SPEED = 2.25f;
    public static final float DIVERGENCE = 0f;

    public static final float[] xOffsets = new float[]{1F, 0.0F, -1F, 0.0F};
    public static final float yVelocity = 1F / 16F;
    public static final float[] zOffsets = new float[]{0.0F, 1F, 0.0F, -1F};
}
