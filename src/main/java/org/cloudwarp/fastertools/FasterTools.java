package org.cloudwarp.fastertools;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;

public class FasterTools implements ModInitializer {
    public static final String MOD_ID = "fastertools";
    public static ConfigHolder<FasterToolsConfig> configHolder;

    @Override
    public void onInitialize() {
        AutoConfig.register(FasterToolsConfig.class, JanksonConfigSerializer::new);
        configHolder = AutoConfig.getConfigHolder(FasterToolsConfig.class);
    }
    public static FasterToolsConfig getConfig() {
        return configHolder.getConfig();
    }

}
