package com.github.brainage04.projectilemania.event;

import com.github.brainage04.projectilemania.item.ModItems;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.collection.DefaultedList;

public class ServerTickHandler implements ServerTickEvents.StartTick {

    @Override
    public void onStartTick(MinecraftServer server) {
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            DefaultedList<ItemStack> armor = player.getInventory().armor;

            boolean b1 = armor.get(0).isOf(ModItems.COPPER_HELMET);
            boolean b2 = armor.get(1).isOf(ModItems.COPPER_CHESTPLATE);
            boolean b3 = armor.get(2).isOf(ModItems.COPPER_LEGGINGS);
            boolean b4 = armor.get(3).isOf(ModItems.COPPER_BOOTS);

            if (b1 && b2 && b3 && b4) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20, 0));
            }
        }
    }
}
