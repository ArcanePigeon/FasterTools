package org.cloudwarp.fastertools;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Consumer;

@Config(name = FasterTools.MOD_ID)
public class FasterToolsConfig implements ConfigData {
    public int toolSpeedModifier = 50;
}
