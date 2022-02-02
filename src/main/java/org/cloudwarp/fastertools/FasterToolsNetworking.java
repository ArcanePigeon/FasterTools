package org.cloudwarp.fastertools;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class FasterToolsNetworking {
    public static final Identifier TOOL_SPEED_MODIFIER = FasterTools.id("tool_speed_modifier");

    public static void init() {
        ServerPlayNetworking.registerGlobalReceiver(TOOL_SPEED_MODIFIER, (server, player, handler, buf, responseSender) -> {
            int toolSpeedModifier = buf.readInt();
            server.execute(() -> ((FasterToolsUtils.ServerPlayerEntityExtensions) player).fasterTools$setToolSpeedModifier(toolSpeedModifier));
        });
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            sender.sendPacket(TOOL_SPEED_MODIFIER, new PacketByteBuf(PacketByteBufs.create().writeInt(server.getGameRules().getInt(ModGameRules.TOOL_SPEED_MODIFIER_RULE))));
        });

    }
}
