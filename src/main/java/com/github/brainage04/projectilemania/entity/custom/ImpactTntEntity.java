package com.github.brainage04.projectilemania.entity.custom;

import net.minecraft.entity.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ImpactTntEntity extends TntEntity {
    public ImpactTntEntity(EntityType<? extends TntEntity> entityType, World world) {
        super(entityType, world);
    }

    public ImpactTntEntity(World world, double x, double y, double z, @Nullable LivingEntity igniter) {
        super(world, x, y, z, igniter);
    }

    @Override
    public void tick() {
        if (!this.hasNoGravity()) {
            this.setVelocity(this.getVelocity().add(0.0, -0.04, 0.0));
        }
        this.move(MovementType.SELF, this.getVelocity());
        this.setVelocity(this.getVelocity().multiply(0.98));
        if (this.isOnGround()) { // explode on impact
            /*
            this.setVelocity(this.getVelocity().multiply(0.7, -0.5, 0.7));
             */

            this.discard();
            if (!this.getWorld().isClient) {
                this.explode();
            }
        }
        int i = this.getFuse() - 1;
        this.setFuse(i);
        if (i <= 0) { // don't explode if fuse is less than or equal to 0
/*
            this.discard();
            if (!this.getWorld().isClient) {
                this.explode();
            }
 */
        } else {
            this.updateWaterState();
            if (this.getWorld().isClient) {
                this.getWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5, this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }

    private void explode() {
        this.getWorld().createExplosion(this, this.getX(), this.getBodyY(0.0625), this.getZ(), 4.0f, World.ExplosionSourceType.TNT);
    }
}
