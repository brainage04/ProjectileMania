package com.github.brainage04.projectilemania.enchantment;

import com.github.brainage04.projectilemania.ProjectileMania;
import com.github.brainage04.projectilemania.enchantment.custom.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantments {
    public static Enchantment CANNON_MULTISHOT = new CannonMultishotEnchantment();
    public static Enchantment SUICIDE_BOMBER = new SuicideBomberEnchantment();
    public static Enchantment SNOWBALL_OVERLOAD = new SnowballOverloadEnchantment();
    public static Enchantment KNOCK_RANDOM = new KnockrandomEnchantment();
    public static Enchantment NO_U = new NoUEnchantment();
    public static Enchantment BOW_SHRAPNEL = new ShrapnelEnchantment(EnchantmentTarget.BOW);
    public static Enchantment CROSSBOW_SHRAPNEL = new ShrapnelEnchantment(EnchantmentTarget.CROSSBOW);
    public static Enchantment TRIDENT_SHRAPNEL = new ShrapnelEnchantment(EnchantmentTarget.TRIDENT);
    public static Enchantment BOW_I_BELIEVE_YOU_CAN_FLY = new IBelieveYouCanFlyEnchantment(EnchantmentTarget.BOW);
    public static Enchantment CROSSBOW_I_BELIEVE_YOU_CAN_FLY = new IBelieveYouCanFlyEnchantment(EnchantmentTarget.CROSSBOW);
    public static Enchantment TRIDENT_I_BELIEVE_YOU_CAN_FLY = new IBelieveYouCanFlyEnchantment(EnchantmentTarget.TRIDENT);
    public static Enchantment BOW_GET_OVER_HERE = new GetOverHereEnchantment(EnchantmentTarget.BOW);
    public static Enchantment CROSSBOW_GET_OVER_HERE = new GetOverHereEnchantment(EnchantmentTarget.CROSSBOW);
    public static Enchantment TRIDENT_GET_OVER_HERE = new GetOverHereEnchantment(EnchantmentTarget.TRIDENT);
    public static Enchantment BOW_FUCK_OFF = new FuckOffEnchantment(EnchantmentTarget.BOW);
    public static Enchantment CROSSBOW_FUCK_OFF = new FuckOffEnchantment(EnchantmentTarget.CROSSBOW);
    public static Enchantment TRIDENT_FUCK_OFF = new FuckOffEnchantment(EnchantmentTarget.TRIDENT);
    public static Enchantment BOW_RANDOM_PUNCH = new RandomPunchEnchantment(EnchantmentTarget.BOW);
    public static Enchantment CROSSBOW_RANDOM_PUNCH = new RandomPunchEnchantment(EnchantmentTarget.CROSSBOW);
    public static Enchantment TRIDENT_RANDOM_PUNCH = new RandomPunchEnchantment(EnchantmentTarget.TRIDENT);
    public static Enchantment AUTO_SMELT = new AutoSmeltEnchantment();
    public static Enchantment EXCAVATE = new ExcavateEnchantment();


    public static void registerProjectileEnchantments(String name, Enchantment[] enchantments) {
        if (enchantments.length != 3) return;

        Registry.register(Registries.ENCHANTMENT, new Identifier(ProjectileMania.MOD_ID, name + "_bow"), enchantments[0]);
        Registry.register(Registries.ENCHANTMENT, new Identifier(ProjectileMania.MOD_ID, name + "_crossbow"), enchantments[1]);
        Registry.register(Registries.ENCHANTMENT, new Identifier(ProjectileMania.MOD_ID, name + "_trident"), enchantments[2]);
    }

    public static void registerEnchantments() {
        ProjectileMania.LOGGER.info("Registering Enchantments for " + ProjectileMania.MOD_NAME + "...");

        Registry.register(Registries.ENCHANTMENT, new Identifier(ProjectileMania.MOD_ID, "cannon_multishot"), CANNON_MULTISHOT);
        Registry.register(Registries.ENCHANTMENT, new Identifier(ProjectileMania.MOD_ID, "suicide_bomber"), SUICIDE_BOMBER);
        Registry.register(Registries.ENCHANTMENT, new Identifier(ProjectileMania.MOD_ID, "snowball_overload"), SNOWBALL_OVERLOAD);
        Registry.register(Registries.ENCHANTMENT, new Identifier(ProjectileMania.MOD_ID, "knock_random"), KNOCK_RANDOM);
        Registry.register(Registries.ENCHANTMENT, new Identifier(ProjectileMania.MOD_ID, "no_u"), NO_U);
        Registry.register(Registries.ENCHANTMENT, new Identifier(ProjectileMania.MOD_ID, "auto_smelt"), AUTO_SMELT);
        Registry.register(Registries.ENCHANTMENT, new Identifier(ProjectileMania.MOD_ID, "excavate"), EXCAVATE);

        registerProjectileEnchantments("shrapnel", new Enchantment[]{BOW_SHRAPNEL, CROSSBOW_SHRAPNEL, TRIDENT_SHRAPNEL});
        registerProjectileEnchantments("i_believe_you_can_fly", new Enchantment[]{BOW_I_BELIEVE_YOU_CAN_FLY, CROSSBOW_I_BELIEVE_YOU_CAN_FLY, TRIDENT_I_BELIEVE_YOU_CAN_FLY});
        registerProjectileEnchantments("get_over_here", new Enchantment[]{BOW_GET_OVER_HERE, CROSSBOW_GET_OVER_HERE, TRIDENT_GET_OVER_HERE});
        registerProjectileEnchantments("fuck_off", new Enchantment[]{BOW_FUCK_OFF, CROSSBOW_FUCK_OFF, TRIDENT_FUCK_OFF});
        registerProjectileEnchantments("random_punch", new Enchantment[]{BOW_RANDOM_PUNCH, CROSSBOW_RANDOM_PUNCH, TRIDENT_RANDOM_PUNCH});

        ProjectileMania.LOGGER.info("Enchantments registered.");
    }
}
