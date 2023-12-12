package com.github.brainage04.projectilemania.entity.custom;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ImpactTntEntity extends TntEntity {
    private static BlockPos[] neighbourBlocks = new BlockPos[]{
            new BlockPos(1, 0, 0),
            new BlockPos(-1, 0, 0),
            new BlockPos(0,1, 0),
            new BlockPos(0,-1, 0),
            new BlockPos(0, 0, 1),
            new BlockPos(0, 0, -1),
    };

    public ImpactTntEntity(EntityType<? extends TntEntity> entityType, World world) {
        super(entityType, world);
    }

    public ImpactTntEntity(World world, double x, double y, double z, @Nullable LivingEntity igniter) {
        super(world, x, y, z, igniter);
    }

    private void impactTntExplode() {
        this.discard();
        if (!this.getWorld().isClient) {
            this.getWorld().createExplosion(this, this.getX(), this.getBodyY(0.0625), this.getZ(), 4.0f, World.ExplosionSourceType.TNT);
        }
    }

    @Override
    public void tick() {
        if (!this.hasNoGravity()) {
            this.setVelocity(this.getVelocity().add(0.0, -0.04, 0.0));
        }
        this.move(MovementType.SELF, this.getVelocity());
        this.setVelocity(this.getVelocity().multiply(0.98));

        if (this.isOnGround()) impactTntExplode(); // explode on ground impact
        for (BlockPos blockPos : neighbourBlocks) { // explode on wall impact
            BlockPos desiredPos = this.getBlockPos().add(blockPos);
            Block desiredBlock = this.getWorld().getBlockState(desiredPos).getBlock();
            if (desiredBlock != Blocks.AIR) impactTntExplode();
        }

        int i = this.getFuse() - 1;
        this.setFuse(i);
        if (i <= 0) {
            impactTntExplode();
        } else {
            this.updateWaterState();
            if (this.getWorld().isClient) {
                this.getWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5, this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }
}
