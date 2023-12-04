package com.github.brainage04.projectilemania.item.custom;

import com.github.brainage04.projectilemania.entity.custom.InfiniteSnowballEntity;
import com.github.brainage04.projectilemania.item.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SnowballCannonItem extends Item {
    boolean isAutomatic = false;
    boolean isShooting = false;
    private static final int spreadDegrees = 2;

    public SnowballCannonItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();

        if (isAutomatic) {
            isAutomatic = false;
            player.sendMessage(Text.of("Mode: Automatic"), true);
            context.getWorld().playSound(player, player.getBlockPos(), SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.MASTER, 0.5F, 1.0F);
        } else {
            isAutomatic = true;
            player.sendMessage(Text.of("Mode: Manual"), true);
            context.getWorld().playSound(player, player.getBlockPos(), SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.MASTER, 0.5F, 0.5F);
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand); // creates a new ItemStack instance of the user's itemStack in-hand

        if (isAutomatic) {
            isShooting = !isShooting;

            if (isShooting) {
                player.sendMessage(Text.of("Weapon: Disabled"), true);
            } else {
                player.sendMessage(Text.of("Weapon: Enabled"), true);
            }
            world.playSound(player, player.getBlockPos(), SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.MASTER, 0.5F, 0.5F);

            // tick here
        } else {
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.MASTER, 0.5F, 0.5F); // plays a globalSoundEvent

            if (!world.isClient) {
                for (int i = 0; i < 3; i++) {
                    InfiniteSnowballEntity snowballEntity = new InfiniteSnowballEntity(player, world);
                    snowballEntity.setItem(new ItemStack(ModItems.INFINITE_SNOWBALL));
                    snowballEntity.setVelocity(player, player.getPitch(), player.getYaw() - spreadDegrees + i * spreadDegrees, 0.0F, 3F, 0.01F);
                    world.spawnEntity(snowballEntity);
                } // shoot 3 snowballs
            }

            player.incrementStat(Stats.USED.getOrCreateStat(this));
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }
}
