package org.cloudwarp.fastertools;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FasterTools implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "fastertools";
	public static ConfigHolder<FTConfig> configHolder;
	public static float toolSpeedModifier = 1.25f;

	public static Identifier id (String path) {
		return new Identifier(MOD_ID, path);
	}

	@Override
	public void onInitialize () {
		LOGGER.info("[Faster-Tools] is initializing.");
		AutoConfig.register(FTConfig.class, Toml4jConfigSerializer::new);
		configHolder = AutoConfig.getConfigHolder(FTConfig.class);
		toolSpeedModifier = getConfig().toolSpeedModifier;
		FTEventHandler.registerEvents();
		LOGGER.info("[Faster-Tools] has successfully been initialized.");
		LOGGER.info("[Faster-Tools] if you have any issues or questions feel free to join my Discord: https://discord.gg/fvcFxTg6sB");
	}

	public static FTConfig getConfig () {
		return configHolder.getConfig();
	}
}
