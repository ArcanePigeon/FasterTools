package org.cloudwarp.fastertools.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import org.cloudwarp.fastertools.FasterTools;
import org.cloudwarp.fastertools.FasterToolsNetworking;

@Environment(EnvType.CLIENT)
public class FasterToolsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient () {
		ClientPlayNetworking.registerGlobalReceiver(FasterToolsNetworking.TOOL_SPEED_MODIFIER, (client, handler, buf, responseSender) -> {
			int value = buf.readInt();
			client.execute(() -> FasterTools.toolSpeedModifier = value);
		});
	}
}
