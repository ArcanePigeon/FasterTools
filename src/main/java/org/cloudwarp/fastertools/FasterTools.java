package org.cloudwarp.fastertools;

import net.fabricmc.api.ModInitializer;

public class FasterTools implements ModInitializer {
    public static float toolSpeedModifier;
    @Override
    public void onInitialize() {
        Config.load();
        toolSpeedModifier = Config.getToolSpeedModifier() / 100f + 1f;
    }
    public static float getToolSpeedModifier(){
        return toolSpeedModifier;
    }

}
