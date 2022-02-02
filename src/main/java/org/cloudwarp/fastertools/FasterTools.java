package org.cloudwarp.fastertools;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class FasterTools implements ModInitializer {
    public static final String MOD_ID = "fastertools";
    @Override
    public void onInitialize() {
        FasterToolsNetworking.init();
    }
    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }
}
