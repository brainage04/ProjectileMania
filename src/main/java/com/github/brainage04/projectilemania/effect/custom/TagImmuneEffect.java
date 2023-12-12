package com.github.brainage04.projectilemania.effect.custom;

import com.github.brainage04.projectilemania.util.MathUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.ParticleTypes;

public class TagImmuneEffect extends StatusEffect {
    public TagImmuneEffect() {
        super(StatusEffectCategory.NEUTRAL, 0x880000);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        for (int i = 0; i < 10; i++) {
            double x = entity.getX() + MathUtil.randomRange(-0.5, 0.5);
            double y = entity.getY() + entity.getHeight() + MathUtil.randomRange(0.75, 1);
            double z = entity.getZ() + MathUtil.randomRange(-0.5, 0.5);
            entity.getWorld().addParticle(ParticleTypes.ENCHANTED_HIT, x, y, z, 0, MathUtil.randomRange(0.8, 1.2), 0);
        }
    }
}
