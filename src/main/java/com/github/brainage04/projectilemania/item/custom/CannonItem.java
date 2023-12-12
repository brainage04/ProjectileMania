package com.github.brainage04.projectilemania.item.custom;

import com.github.brainage04.projectilemania.block.ModBlocks;
import com.github.brainage04.projectilemania.enchantment.ModEnchantments;
import com.github.brainage04.projectilemania.entity.custom.ImpactTntEntity;
import com.github.brainage04.projectilemania.item.ModItems;
import com.github.brainage04.projectilemania.util.InfiniteAmmoUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.thrown.EggEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.*;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class CannonItem extends Item {
    public static final Item[] validItems = new Item[]{ModItems.INFINITE_SNOWBALL, ModItems.INFINITE_EGG, ModItems.INFINITE_ARROW, ModBlocks.INFINITE_TNT.asItem(), ModBlocks.IMPACT_TNT.asItem()};

    public static boolean hasValidItem(ItemStack heldItem) {
        for (Item validItem : validItems) {
            if (heldItem.isOf(validItem)) return true;
        }

        return false;
    }

    private static final int spreadDegrees = 3;

    public CannonItem(Settings settings) {
        super(settings);
    }

    private static void shoot(World world, PlayerEntity player, ItemStack itemStack, ItemStack itemInHand) {
        int multishotLevel = EnchantmentHelper.getLevel(ModEnchantments.CANNON_MULTISHOT, itemInHand);
        int projectileAmount = 1 + 2 * multishotLevel; // shoot 1 + (2 * multishot level) projectiles
        float yaw = player.getYaw() - spreadDegrees * multishotLevel; // evenly space out projectiles

        if (itemStack.isOf(ModItems.INFINITE_SNOWBALL)) {
            for (int i = 0; i < projectileAmount; i++) {
                InfiniteAmmoUtil.playSound(world, player.getPos(), InfiniteAmmoUtil.InfiniteSoundType.SNOWBALL);

                SnowballEntity entity = new SnowballEntity(world, player);
                entity.setItem(itemStack);
                entity.setVelocity(player, player.getPitch(), yaw + spreadDegrees * i, 0.0F, 1.5F, 0.01F);
                world.spawnEntity(entity);
            }
        } else if (itemStack.isOf(ModItems.INFINITE_EGG)) {
            for (int i = 0; i < projectileAmount; i++) {
                InfiniteAmmoUtil.playSound(world, player.getPos(), InfiniteAmmoUtil.InfiniteSoundType.EGG);

                EggEntity entity = new EggEntity(world, player);
                entity.setItem(itemStack);
                entity.setVelocity(player, player.getPitch(), yaw + spreadDegrees * i, 0.0F, 1.5F, 0.01F);
                world.spawnEntity(entity);
            }
        } else if (itemStack.isOf(ModItems.INFINITE_ARROW)) {
            for (int i = 0; i < projectileAmount; i++) {
                InfiniteAmmoUtil.playSound(world, player.getPos(), InfiniteAmmoUtil.InfiniteSoundType.ARROW);

                ArrowEntity entity = new ArrowEntity(world, player, new ItemStack(Items.ARROW));
                entity.setVelocity(player, player.getPitch(), yaw + spreadDegrees * i, 0.0F, 1.5F, 0.01F);
                world.spawnEntity(entity);
            }
        } else if (itemStack.isOf(ModBlocks.INFINITE_TNT.asItem())) {
            for (int i = 0; i < projectileAmount; i++) {
                InfiniteAmmoUtil.playSound(world, player.getPos(), InfiniteAmmoUtil.InfiniteSoundType.TNT);

                TntEntity entity = new TntEntity(world, player.getX(), player.getY(), player.getZ(), null);
                float f = -MathHelper.sin(player.getYaw() * ((float)Math.PI / 180)) * MathHelper.cos(player.getPitch() * ((float)Math.PI / 180));
                float g = -MathHelper.sin(player.getPitch() * ((float)Math.PI / 180));
                float h = MathHelper.cos(player.getYaw() * ((float)Math.PI / 180)) * MathHelper.cos(player.getPitch() * ((float)Math.PI / 180));
                Vec3d velocity = new Vec3d(f, g, h).normalize().multiply(InfiniteAmmoUtil.SPEED);
                entity.setVelocity(velocity);
                entity.setFuse(80);
                world.spawnEntity(entity);
            }
        } else if (itemStack.isOf(ModBlocks.IMPACT_TNT.asItem())) {
            for (int i = 0; i < projectileAmount; i++) {
                InfiniteAmmoUtil.playSound(world, player.getPos(), InfiniteAmmoUtil.InfiniteSoundType.TNT);

                ImpactTntEntity entity = new ImpactTntEntity(world, player.getX(), player.getY(), player.getZ(), null);
                float f = -MathHelper.sin(player.getYaw() * ((float)Math.PI / 180)) * MathHelper.cos(player.getPitch() * ((float)Math.PI / 180));
                float g = -MathHelper.sin(player.getPitch() * ((float)Math.PI / 180));
                float h = MathHelper.cos(player.getYaw() * ((float)Math.PI / 180)) * MathHelper.cos(player.getPitch() * ((float)Math.PI / 180));
                Vec3d velocity = new Vec3d(f, g, h).normalize().multiply(InfiniteAmmoUtil.SPEED);
                entity.setVelocity(velocity);
                entity.setFuse(80);
                world.spawnEntity(entity);
            }

            if (!player.isCreative()) itemStack.decrement(1);
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemInHand = player.getStackInHand(hand); // creates a new ItemStack instance of the user's itemStack in-hand

        if (!world.isClient) {
            ItemStack offhand = player.getOffHandStack();

            if (!offhand.isEmpty() && hasValidItem(offhand)) { // prioritise the offhand slot as ammunition first
                shoot(world, player, offhand, itemInHand);
            } else { // for each main slot (where any item type can be stored), if any of them contain any valid items, use the first one found as ammunition
                for (int i = 0; i < player.getInventory().main.size(); i++) {
                    ItemStack currentItem = player.getInventory().main.get(i);

                    if (hasValidItem(currentItem)) {
                        shoot(world, player, currentItem, itemInHand);
                        break; // no point continuing once first valid item is found
                    }
                }
            }
        }

        player.incrementStat(Stats.USED.getOrCreateStat(this));

        return TypedActionResult.success(itemInHand, world.isClient());
    }

    /*
    private static final String ITEM_KEY = "Item";

    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (clickType == ClickType.RIGHT) {
            NbtCompound nbtCompound = stack.getOrCreateNbt();

            if (!nbtCompound.contains(ITEM_KEY)) {
                nbtCompound.put(ITEM_KEY, new NbtList());
            }

            NbtList nbtList = nbtCompound.getList(ITEM_KEY, NbtElement.COMPOUND_TYPE);

            if (otherStack.isEmpty()) { // if right clicked on nothing, eject current item
                ItemStack itemInside = ItemStack.fromNbt(nbtList.getCompound(0)); // item inside this item

                if (itemInside != null) {
                    slot.setStack(itemInside); // set inventory slot to item inside this item

                    nbtList.clear(); // clear item inside this item
                }
            } else { // if right clicked on item, check if it is valid
                boolean validItem = false;

                for (Item item : validItems) {
                    if (otherStack.isOf(item)) validItem = true;
                }

                if (validItem) { // if it is valid, swap inside and outside items
                    ItemStack itemInside = ItemStack.fromNbt(nbtList.getCompound(0)); // item inside this item

                    if (itemInside != null) {
                        ItemStack itemOutSide = slot.getStack();
                        NbtCompound itemOutsideCompound = new NbtCompound();
                        itemOutSide.writeNbt(itemOutsideCompound); // get item inside the inventory and convert to NbtCompound

                        slot.setStack(itemInside); // set inventory slot to item inside this item

                        nbtList.clear();
                        nbtList.add(itemOutsideCompound); // clear item inside this item and set to item inside the inventory (from before it was set 3 lines ago)
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }
     */
}
