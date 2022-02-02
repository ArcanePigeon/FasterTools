package org.cloudwarp.fastertools;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class FasterTools implements ModInitializer {
    public static final String MOD_ID = "fastertools";
    public static float toolSpeedModifier;
    @Override
    public void onInitialize() {
        ModGameRules.init();
        FasterToolsNetworking.init();
    }
    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }
}
