package org.cloudwarp.fastertools.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientLoginConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.networking.v1.ServerLoginConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.world.World;
import org.cloudwarp.fastertools.FasterTools;
import org.cloudwarp.fastertools.FasterToolsNetworking;
import org.cloudwarp.fastertools.FasterToolsUtils;
import org.cloudwarp.fastertools.ModGameRules;

@Environment(EnvType.CLIENT)
public class FasterToolsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(FasterToolsNetworking.TOOL_SPEED_MODIFIER, (client, handler, buf, responseSender) -> {
            int value = buf.readInt();
            client.execute(() -> FasterTools.toolSpeedModifier = value);
            //System.out.println("CLIENT: " + value + " " + FasterTools.toolSpeedModifier);
        });
    }
}
