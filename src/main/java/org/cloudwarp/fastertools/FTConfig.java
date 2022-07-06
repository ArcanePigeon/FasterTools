package org.cloudwarp.fastertools;

import blue.endless.jankson.Comment;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = FasterTools.MOD_ID)
public class FTConfig implements ConfigData {
	@ConfigEntry.Gui.Tooltip()
	public float toolSpeedModifier = 1.25F;

}