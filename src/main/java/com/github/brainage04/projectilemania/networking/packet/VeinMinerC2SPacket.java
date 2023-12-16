package com.github.brainage04.projectilemania.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;

public class VeinMinerC2SPacket {
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

    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        BlockPos pos = buf.readBlockPos();
        Identifier blockID = buf.readIdentifier();

        ArrayList<BlockPos> matchingBlockPositions = new ArrayList<>();
        matchingBlockPositions.add(pos); // start with the original block broken position as the first position

        int matchingBlocksFound = 0;

        for (int i = 0; i < maxIterations; i++) { // for no more than 10 iterations:
            ArrayList<BlockPos> tempMatchingBlockPositions = new ArrayList<>(); // temporary array list used to avoid ConcurrentModificationExceptions

            for (BlockPos position : matchingBlockPositions) { // check all stored blocks of the same type as the first one:
                for (int[] offset: surroundingBlockOffsets) { // by checking all neighbouring blocks (including diagonals):
                    BlockPos adjacentBlockPos = position.add(offset[0], offset[1], offset[2]); // get adjacent block position

                    if (tempMatchingBlockPositions.contains(adjacentBlockPos) || matchingBlockPositions.contains(adjacentBlockPos)) continue; // skip already checked block positions

                    if (blockID.equals(Registries.BLOCK.getId(player.getServerWorld().getBlockState(adjacentBlockPos).getBlock()))) {
                        matchingBlocksFound++;
                        tempMatchingBlockPositions.add(adjacentBlockPos); // we want to check this block for adjacent blocks in future iterations
                    }

                    if (matchingBlocksFound >= maxBlocks) break;
                }
            }

            matchingBlockPositions.addAll(tempMatchingBlockPositions);
        }

        for (BlockPos matchingBlock : matchingBlockPositions) { // break all blocks
            player.getServerWorld().breakBlock(matchingBlock, !player.isCreative(), player);
        }
    }

    /*
            int excavateLevel = EnchantmentHelper.getLevel(ModEnchantments.EXCAVATE, player.getMainHandStack());

        if (EnchantmentHelper.getLevel(ModEnchantments.EXCAVATE, player.getMainHandStack()) >= 1) {
            player.sendMessage(Text.literal("Excavate activated."), false);

            ArrayList<BlockPos> blockPosArrayList = excavateBlockTargets(excavateLevel);

            for (BlockPos blockPos: blockPosArrayList) {
                player.getServerWorld().breakBlock(blockPos, !player.isCreative(), player);
            }
        }
     */
}
