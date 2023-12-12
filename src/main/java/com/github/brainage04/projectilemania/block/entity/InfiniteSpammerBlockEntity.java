package com.github.brainage04.projectilemania.block.entity;

import com.github.brainage04.projectilemania.ProjectileMania;
import com.github.brainage04.projectilemania.block.ModBlocks;
import com.github.brainage04.projectilemania.entity.custom.ImpactTntEntity;
import com.github.brainage04.projectilemania.item.ModItems;
import com.github.brainage04.projectilemania.screen.InfiniteSpammerScreenHandler;
import com.github.brainage04.projectilemania.util.InfiniteAmmoUtil;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.thrown.EggEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static com.github.brainage04.projectilemania.util.InfiniteAmmoUtil.*;

public class InfiniteSpammerBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;

    private boolean isShooting = false;
    private static final String isShootingKey = "infinite_spammer_block.is_shooting";

    public InfiniteSpammerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.INFINITE_SPAMMER_BLOCK_ENTITY, pos, state);
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block." + ProjectileMania.MOD_ID + ".infinite_spammer_block");
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

    public static int ticksSinceInit = 0;

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient) return;

        ItemStack itemStack = this.getStack(INPUT_SLOT);

        Vec3d centerPos = pos.toCenterPos();

        if (ticksSinceInit % 4 == 0) {
            if (itemStack.isOf(ModItems.INFINITE_SNOWBALL)) {
                InfiniteAmmoUtil.playSound(world, centerPos, InfiniteSoundType.SNOWBALL);
                for (int i = 0; i < 4; i++) {
                    SnowballEntity entity = new SnowballEntity(world, centerPos.getX() + xOffsets[i], centerPos.getY(), centerPos.getZ() + zOffsets[i]);
                    entity.setItem(itemStack);
                    entity.setVelocity(xOffsets[i], yVelocity, zOffsets[i], InfiniteAmmoUtil.SPEED, InfiniteAmmoUtil.DIVERGENCE);
                    world.spawnEntity(entity);
                }
            } else if (itemStack.isOf(ModItems.INFINITE_EGG)) {
                InfiniteAmmoUtil.playSound(world, centerPos, InfiniteSoundType.EGG);
                for (int i = 0; i < 4; i++) {
                    EggEntity entity = new EggEntity(world, centerPos.getX() + xOffsets[i], centerPos.getY(), centerPos.getZ() + zOffsets[i]);
                    entity.setItem(itemStack);
                    entity.setVelocity(xOffsets[i], yVelocity, zOffsets[i], InfiniteAmmoUtil.SPEED, InfiniteAmmoUtil.DIVERGENCE);
                    world.spawnEntity(entity);
                }
            } else if (itemStack.isOf(ModItems.INFINITE_ARROW)) {
                InfiniteAmmoUtil.playSound(world, centerPos, InfiniteSoundType.ARROW);
                for (int i = 0; i < 4; i++) {
                    ArrowEntity entity = new ArrowEntity(world, centerPos.getX() + xOffsets[i], centerPos.getY(), centerPos.getZ() + zOffsets[i], new ItemStack(Items.ARROW));
                    entity.setVelocity(xOffsets[i], yVelocity, zOffsets[i], InfiniteAmmoUtil.SPEED, InfiniteAmmoUtil.DIVERGENCE);
                    world.spawnEntity(entity);
                }
            } else if (itemStack.isOf(ModBlocks.INFINITE_TNT.asItem())) {
                InfiniteAmmoUtil.playSound(world, centerPos, InfiniteSoundType.TNT);
                for (int i = 0; i < 4; i++) {
                    TntEntity entity = new TntEntity(world, centerPos.getX() + xOffsets[i], centerPos.getY(), centerPos.getZ() + zOffsets[i], null);
                    Vec3d velocity = new Vec3d(xOffsets[i], yVelocity, zOffsets[i]).normalize().multiply(InfiniteAmmoUtil.SPEED);
                    entity.setVelocity(velocity);
                    entity.setFuse(80);
                    world.spawnEntity(entity);
                }
            } else if (itemStack.isOf(ModBlocks.IMPACT_TNT.asItem())) {
                InfiniteAmmoUtil.playSound(world, centerPos, InfiniteSoundType.TNT);
                for (int i = 0; i < 4; i++) {
                    ImpactTntEntity entity = new ImpactTntEntity(world, centerPos.getX() + xOffsets[i], centerPos.getY(), centerPos.getZ() + zOffsets[i], null);
                    Vec3d velocity = new Vec3d(xOffsets[i], yVelocity, zOffsets[i]).normalize().multiply(InfiniteAmmoUtil.SPEED);
                    entity.setVelocity(velocity);
                    entity.setFuse(80);
                    world.spawnEntity(entity);

                    itemStack.decrement(1);
                }
            }
        }

        markDirty(world, pos, state);

        ticksSinceInit++;
    }
}
