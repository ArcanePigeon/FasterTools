package org.cloudwarp.fastertools.server;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.world.World;
import org.cloudwarp.fastertools.FasterToolsUtils;

@Environment(EnvType.SERVER)
public class FasterToolsServer implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        ServerWorldEvents.LOAD.register((server, world) -> {
            if(world != null){
                FasterToolsUtils.world = world;
            }
        });
        System.out.println("Server " + FasterToolsUtils.world);
    }
}
