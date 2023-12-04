package com.github.brainage04.projectilemania.block.entity;

import com.github.brainage04.projectilemania.entity.custom.InfiniteSnowballEntity;
import com.github.brainage04.projectilemania.item.ModItems;
import com.github.brainage04.projectilemania.screen.InfiniteSpammerScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class InfiniteSpammerBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;

    private boolean isShooting = false;
    private static final String isShootingKey = "infinite_spammer_block.is_shooting";

    private static final float[] xOffsets = new float[]{0.625F, 0.0F, -0.625F, 0.0F};
    private static final float[] zOffsets = new float[]{0.0F, 0.625F, 0.0F, -0.625F};
    private static final float[] xVelOffsets = new float[]{1F, 0.0F, -1F, 0.0F};
    private static final float[] zVelOffsets = new float[]{0.0F, 1F, 0.0F, -1F};
    private static final float yVelocity = 1F / 16F;

    public boolean isShooting() {
        return this.isShooting;
    }

    public void toggleShooting() {
        this.isShooting = !this.isShooting;
    }

    public InfiniteSpammerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.INFINITE_SPAMMER_BLOCK_ENTITY, pos, state);
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.projectilemania.infinite_spammer_block");
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putBoolean(isShootingKey, isShooting);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        isShooting = nbt.getBoolean(isShootingKey);
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new InfiniteSpammerScreenHandler(syncId, playerInventory, this);
    }



    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) {
            return;
        }

        //if (!isShooting) return;

        if (hasValidItem()) {
            Vec3d centerPos = pos.toCenterPos();

            for (int i = 0; i < 4; i++) {
                InfiniteSnowballEntity snowballEntity = new InfiniteSnowballEntity(world, centerPos.x + xOffsets[i], pos.getY() + 0.375F, centerPos.z + zOffsets[i]);
                snowballEntity.setItem(new ItemStack(ModItems.INFINITE_SNOWBALL));
                snowballEntity.setVelocity(xVelOffsets[i], yVelocity, zVelOffsets[i], 3F, 0.0F);
                world.spawnEntity(snowballEntity);
            } // shoot 4 snowballs every tick the block is active

            world.playSound(null, centerPos.x, centerPos.y, centerPos.z, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.MASTER, 0.5F, 0.5F); // plays a globalSoundEvent
        } else {
            markDirty(world, pos, state);
        }
    }

    public static final Item[] validItems = new Item[]{ModItems.INFINITE_SNOWBALL};

    public boolean hasValidItem() {
        ItemStack itemStack = this.getStack(INPUT_SLOT);

        for (Item validItem : validItems) {
            if (itemStack.isOf(validItem)) return true;
        }

        return false;
    }
}
