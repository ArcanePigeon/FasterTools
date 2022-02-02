package org.cloudwarp.fastertools.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import org.cloudwarp.fastertools.FasterToolsNetworking;

public class FasterToolsClient implements ClientModInitializer {
    public static int TOOL_SPEED_MODIFIER;
    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(FasterToolsNetworking.UPDATE_INTEGER_GAMERULE_PACKET, (client, handler, buf, responseSender) -> {
            int value = buf.readInt();
            client.execute(() -> TOOL_SPEED_MODIFIER = value);
        });
    }
}
