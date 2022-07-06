package org.cloudwarp.fastertools;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;

public class FTEventHandler {

	private FTEventHandler (){}

	public static void registerEvents(){
		FTConfig config = FasterTools.getConfig();
		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
			PacketByteBuf data = PacketByteBufs.create();
			NbtCompound nbt = new NbtCompound();
			nbt.putFloat("tool_speed_modifier",config.toolSpeedModifier);
			data.writeNbt(nbt);
			ServerPlayNetworking.send(handler.player, FasterTools.id("faster_tools_config_update"), data);
		});
	}
}
