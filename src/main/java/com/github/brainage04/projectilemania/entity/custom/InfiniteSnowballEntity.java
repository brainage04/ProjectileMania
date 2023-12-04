package com.github.brainage04.projectilemania.entity.custom;

import com.github.brainage04.projectilemania.entity.ModEntityTypes;
import com.github.brainage04.projectilemania.item.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class InfiniteSnowballEntity extends ThrownItemEntity {
    public InfiniteSnowballEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public InfiniteSnowballEntity(LivingEntity livingEntity, World world) {
        super(ModEntityTypes.infiniteSnowballProjectileEntityType, livingEntity, world);
    }

    public InfiniteSnowballEntity(World world, double x, double y, double z) {
        super(ModEntityTypes.infiniteSnowballProjectileEntityType, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.INFINITE_SNOWBALL;
    }

    private ParticleEffect getParticleParameters() { // Not entirely sure, but probably has do to with the snowball's particles. (OPTIONAL)
        ItemStack itemStack = this.getItem();
        return itemStack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void handleStatus(byte status) { // Also not entirely sure, but probably also has to do with the particles. This method (as well as the previous one) are optional, so if you don't understand, don't include this one.
        if (status == 3) {
            ParticleEffect particleEffect = this.getParticleParameters();

            for (int i = 0; i < 8; i++) {
                this.getWorld().addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }

    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) { // called on collision with an entity
        super.onEntityHit(entityHitResult);

        Entity entity = entityHitResult.getEntity(); // sets a new Entity instance as the EntityHitResult (victim)

        entity.damage(this.getDamageSources().mobProjectile(this, (LivingEntity) this.getOwner()), 0.0f); // deals knockback (no damage)
    }

    @Override
    protected void onCollision(HitResult hitResult) { // called on collision with a block
        super.onCollision(hitResult);

        if (!this.getWorld().isClient()) { // checks if the world is client
            this.getWorld().sendEntityStatus(this, (byte)3); // particle?
            this.kill(); // kills the projectile
        }

    }
}
