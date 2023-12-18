package com.github.brainage04.projectilemania.effect;

import com.github.brainage04.projectilemania.ProjectileMania;
import com.github.brainage04.projectilemania.effect.custom.ExperienceEffect;
import com.github.brainage04.projectilemania.effect.custom.TagEffect;
import com.github.brainage04.projectilemania.effect.custom.TagImmuneEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModStatusEffects {
    public static final StatusEffect TAG_EFFECT = new TagEffect();
    public static final StatusEffect TAG_IMMUNE_EFFECT = new TagImmuneEffect();
    public static final StatusEffect EXPERIENCE_EFFECT = new ExperienceEffect();

    public static void registerStatusEffects() {
        ProjectileMania.LOGGER.info("Registering Status Effects for " + ProjectileMania.MOD_NAME + "...");

        Registry.register(Registries.STATUS_EFFECT, new Identifier(ProjectileMania.MOD_ID, "tag_effect"), TAG_EFFECT);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(ProjectileMania.MOD_ID, "tag_immune_effect"), TAG_IMMUNE_EFFECT);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(ProjectileMania.MOD_ID, "experience_effect"), EXPERIENCE_EFFECT);

        ProjectileMania.LOGGER.info("Status Effects registered.");
    }
}
