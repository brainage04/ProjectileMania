package com.github.brainage04.projectilemania.item.custom;

import com.github.brainage04.projectilemania.effect.ModStatusEffects;
import com.github.brainage04.projectilemania.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;


public class HotPotatoItem extends Item {
    private static int ticksHeld = 0;
    private static int tickLimit = 100;

    public HotPotatoItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (world.isClient) return;

        if (!entity.isPlayer()) return;

        ticksHeld++;

        if (ticksHeld % 10 == 0) {
            ((PlayerEntity) entity).sendMessage(Text.of(((tickLimit - ticksHeld) / 20.0) + " seconds remaining"), true);
        }

        if (ticksHeld >= tickLimit) {
            ticksHeld = 0;

            stack.decrement(stack.getCount()); // remove item
            TntEntity tntEntity = new TntEntity(world, entity.getX(), entity.getY(), entity.getZ(), null);
            tntEntity.setFuse(0);
            world.spawnEntity(tntEntity);
        }

        ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(ModStatusEffects.TAG_EFFECT, 10, 0), null);
        ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 10, 0), null);

        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.isPlayer() || !attacker.isPlayer()) return false;

        if (target.hasStatusEffect(ModStatusEffects.TAG_IMMUNE_EFFECT)) {
            float duration = target.getStatusEffect(ModStatusEffects.TAG_IMMUNE_EFFECT).getDuration();

            ((PlayerEntity) target).sendMessage(Text.of("No takesies backsies! Try again in " + (duration / 20) + " seconds."), true);

            return false;
        }

        ((PlayerEntity) target).giveItemStack(new ItemStack(ModItems.HOT_POTATO)); // give hit player the item
        ((PlayerEntity) target).sendMessage(Text.of("You have been given the hot potato!"), false);

        stack.decrement(stack.getCount()); // remove item from the attacker (and replace tag effect with tag immune effect)
        attacker.removeStatusEffect(ModStatusEffects.TAG_EFFECT);
        attacker.addStatusEffect(new StatusEffectInstance(ModStatusEffects.TAG_IMMUNE_EFFECT, 100, 0), null);

        return super.postHit(stack, target, attacker);
    }
}
