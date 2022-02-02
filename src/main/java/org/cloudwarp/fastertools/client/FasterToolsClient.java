package org.cloudwarp.fastertools.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.world.World;
import org.cloudwarp.fastertools.FasterToolsNetworking;
import org.cloudwarp.fastertools.FasterToolsUtils;

@Environment(EnvType.CLIENT)
public class FasterToolsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        World world = MinecraftClient.getInstance().world;
        if(world != null){
            FasterToolsUtils.world = world;
        }
        ServerWorldEvents.LOAD.register((server, w) -> {
            if(w != null){
                FasterToolsUtils.world = w;
            }
        });
        System.out.println("Client " + FasterToolsUtils.world);
    }
}
