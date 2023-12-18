package com.github.brainage04.projectilemania.effect.custom;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class ExperienceEffect extends StatusEffect {
    public ExperienceEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0x00FF00);
    }
}
