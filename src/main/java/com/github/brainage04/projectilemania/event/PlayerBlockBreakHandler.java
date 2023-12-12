package com.github.brainage04.projectilemania.event;

import com.github.brainage04.projectilemania.enchantment.ModEnchantments;
import com.github.brainage04.projectilemania.keybinding.ModKeyBindings;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class PlayerBlockBreakHandler implements PlayerBlockBreakEvents.After {
    private static final int maxIterations = 10;
    private static final int maxBlocks = 64;

    private static final int[][] surroundingBlockOffsets = { // 26
            {1, 1, 1},
            {0, 1, 1},
            {-1, 1, 1},
            {1, 1, 0},
            {0, 1, 0},
            {-1, 1, 0},
            {1, 1, -1},
            {0, 1, -1},
            {-1, 1, -1}, // top

            {1, 0, 1},
            {0, 0, 1},
            {-1, 0, 1},
            {1, 0, 0},
            //{0, 0, 0}, // current position
            {-1, 0, 0},
            {1, 0, -1},
            {0, 0, -1},
            {-1, 0, -1}, // middle

            {1, -1, 1},
            {0, -1, 1},
            {-1, -1, 1},
            {1, -1, 0},
            {0, -1, 0},
            {-1, -1, 0},
            {1, -1, -1},
            {0, -1, -1},
            {-1, -1, -1}, // bottom
    };

    private static final int[][] excavationBlockOffsets = { // X = left/right, Y = up/down, Z = forward/backward
            {0, 1, 0},
            {0, -1, 0}, // level 1

            {1, 0, 0},
            {-1, 0, 0}, // level 2

            {1, 1, 0},
            {-1, 1, 0},
            {1, -1, 0},
            {-1, -1, 0}, // level 3

            {1, 1, 1},
            {0, 1, 1},
            {-1, 1, 1},
            {1, 0, 1},
            {0, 0, 1},
            {-1, 0, 1},
            {1, -1, 1},
            {0, -1, 1},
            {-1, -1, 1}, // level 4

            {1, 1, 2},
            {0, 1, 2},
            {-1, 1, 2},
            {1, 0, 2},
            {0, 0, 2},
            {-1, 0, 2},
            {1, -1, 2},
            {0, -1, 2},
            {-1, -1, 2}, // level 5
    };

    private static ArrayList<BlockPos> excavateBlockTargets(int level) {
        ArrayList<BlockPos> blockPosArrayList = new ArrayList<>();

        int indicesToAdd = 0;

        switch (level) {
            case 1 -> indicesToAdd = 2;
            case 2 -> indicesToAdd = 4;
            case 3 -> indicesToAdd = 8;
            case 4 -> indicesToAdd = 17;
            case 5 -> indicesToAdd = 26;
        }

        for (int i = 0; i < indicesToAdd; i++) {
            blockPosArrayList.add(new BlockPos(excavationBlockOffsets[i][0], excavationBlockOffsets[i][1], excavationBlockOffsets[i][2]));
        }

        return blockPosArrayList;
    }

    @Override
    public void afterBlockBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity) {
        int excavateLevel = EnchantmentHelper.getLevel(ModEnchantments.EXCAVATE, player.getMainHandStack());

        if (EnchantmentHelper.getLevel(ModEnchantments.EXCAVATE, player.getMainHandStack()) >= 1) {
            player.sendMessage(Text.literal("Excavate activated."), false);

            ArrayList<BlockPos> blockPosArrayList = excavateBlockTargets(excavateLevel);

            for (BlockPos blockPos: blockPosArrayList) {
                world.breakBlock(blockPos, !player.isCreative(), player);
            }
        }

        if (ModKeyBindings.veinMinerKeybind.isPressed()) {
            player.sendMessage(Text.literal("Vein miner activated."), false);

            ArrayList<BlockPos> matchingBlockPositions = new ArrayList<>();
            matchingBlockPositions.add(pos); // start with the original block broken position as the first position

            Block block = state.getBlock();

            int matchingBlocksFound = 0;

            for (int i = 0; i < maxIterations; i++) { // for no more than 10 iterations:
                for (BlockPos position : matchingBlockPositions) { // check all stored blocks of the same type as the first one:
                    for (int[] offset: surroundingBlockOffsets) { // by checking all neighbouring blocks (including diagonals):
                        BlockPos adjacentBlockPos = position.add(offset[0], offset[1], offset[2]);
                        Block adjacentBlock = world.getBlockState(adjacentBlockPos).getBlock();

                        if (block == adjacentBlock) {
                            matchingBlocksFound++;
                            matchingBlockPositions.add(adjacentBlockPos); // we want to check this block for adjacent blocks in future iterations
                        }

                        if (matchingBlocksFound >= maxBlocks) break;
                    }
                }
            }

            for (BlockPos matchingBlock : matchingBlockPositions) { // break all blocks
                world.breakBlock(matchingBlock, !player.isCreative(), player);
            }
        }
    }
}
