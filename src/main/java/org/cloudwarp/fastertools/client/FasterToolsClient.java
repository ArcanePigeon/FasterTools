package org.cloudwarp.fastertools.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import org.cloudwarp.fastertools.FTConfig;
import org.cloudwarp.fastertools.FasterTools;

@Environment(EnvType.CLIENT)
public class FasterToolsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient () {
		ClientPlayNetworking.registerGlobalReceiver(FasterTools.id("faster_tools_config_update"), (client, networkHandler, data, sender) -> {
			NbtCompound tag = data.readNbt();
			client.execute(() -> FasterTools.toolSpeedModifier = tag.getFloat("tool_speed_modifier"));
		});
	}
}
