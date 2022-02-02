package org.cloudwarp.fastertools;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class FasterToolsNetworking {
    public static final Identifier TOOL_SPEED_MODIFIER = FasterTools.id("tool_speed_modifier");
    public static final Identifier UPDATE_INTEGER_GAMERULE_PACKET = FasterTools.id("update_integer_gamerule_packet");

    public static void init() {
        ServerPlayNetworking.registerGlobalReceiver(TOOL_SPEED_MODIFIER, (server, player, handler, buf, responseSender) -> {
            int toolSpeedModifier = buf.readInt();
            server.execute(() -> ((FasterToolsUtils.ServerPlayerEntityExtensions) player).fasterTools$setToolSpeedModifier(toolSpeedModifier));
        });
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            sender.sendPacket(UPDATE_INTEGER_GAMERULE_PACKET, new PacketByteBuf(PacketByteBufs.create().writeString(ModGameRules.TOOL_SPEED_MODIFIER_RULE.getName()).writeInt(server.getGameRules().getInt(ModGameRules.TOOL_SPEED_MODIFIER_RULE))));
        });

    }
}
