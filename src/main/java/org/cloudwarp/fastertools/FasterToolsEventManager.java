package org.cloudwarp.fastertools;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;

public class FasterToolsEventManager {
	private FasterToolsEventManager(){}

	public static void registerEvents(){
		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
			PacketByteBuf data = PacketByteBufs.create();
			data.writeNbt(Config.getInstance().toNbtCompound());
			ServerPlayNetworking.send(handler.player, FasterTools.id("faster_tools_config_update"), data);
		});
	}
}
