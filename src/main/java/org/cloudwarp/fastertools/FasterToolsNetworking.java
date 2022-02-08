package org.cloudwarp.fastertools;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class FasterToolsNetworking {
	public static final Identifier TOOL_SPEED_MODIFIER = FasterTools.id("tool_speed_modifier");
	public static final Identifier NON_TOOL_MODIFICATION = FasterTools.id("non_tool_modification");

	public static void init () {
		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
			sender.sendPacket(TOOL_SPEED_MODIFIER, new PacketByteBuf(PacketByteBufs.create().writeInt(server.getGameRules().getInt(ModGameRules.TOOL_SPEED_MODIFIER_RULE))));
		});
		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
			sender.sendPacket(NON_TOOL_MODIFICATION, new PacketByteBuf(PacketByteBufs.create().writeBoolean(server.getGameRules().getBoolean(ModGameRules.NON_TOOL_MODIFICATION_RULE))));
		});
	}
}
