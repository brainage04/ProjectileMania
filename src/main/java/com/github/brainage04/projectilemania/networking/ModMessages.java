package com.github.brainage04.projectilemania.networking;

import com.github.brainage04.projectilemania.ProjectileMania;
import com.github.brainage04.projectilemania.networking.packet.VeinMinerC2SPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModMessages {
    public static final Identifier VEIN_MINER_PACKET = new Identifier(ProjectileMania.MOD_ID, "vein_miner_packet");

    public static void registerC2SPackets() { // client to server
        ServerPlayNetworking.registerGlobalReceiver(VEIN_MINER_PACKET, VeinMinerC2SPacket::receive);
    }

    public static void registerS2CPackets() { // server to client

    }

    public static void sendVeinMinerPacket(BlockPos blockPos, Identifier blockID) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeBlockPos(blockPos);
        buf.writeIdentifier(blockID);

        ClientPlayNetworking.send(ModMessages.VEIN_MINER_PACKET, buf);
    }
}
