package org.cloudwarp.fastertools;

import com.google.common.base.CaseFormat;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Consumer;

public class Config {
    public static final Path PROPERTIES_PATH = FabricLoader.getInstance().getConfigDir().resolve("fastertools.properties");
    private static final Properties config = new Properties() {
        @Override
        public @NotNull Set<Map.Entry<Object, Object>> entrySet() {
            Iterator<Map.Entry<Object, Object>> iterator = super.entrySet().stream().sorted(Comparator.comparing(o -> o.getKey().toString())).iterator();

            Set<Map.Entry<Object, Object>> temp = new LinkedHashSet<>(super.entrySet().size());
            while (iterator.hasNext())
                temp.add(iterator.next());

            return temp;
        }
    };

    public static int toolSpeedModifier;

    public static void load() {
        if (Files.isRegularFile(PROPERTIES_PATH)) {
            try {
                config.load(Files.newBufferedReader(PROPERTIES_PATH));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // define default properties
            setToolSpeedModifier(DefaultConfig.TOOL_SPEED_MODIFIER);
            Config.save();
            return;
        }
        parseProperty("tool_speed_modifier", Config::setToolSpeedModifier, DefaultConfig.TOOL_SPEED_MODIFIER);
        Config.save();
    }
    private static void parseProperty(String property, Consumer<Integer> setter, Integer defaultValue) {
        try {
            setter.accept(Integer.parseInt(config.getProperty(property)));
        } catch (Exception e) {
            setter.accept(defaultValue);
        }
    }
    public static void save() {
        try {
            config.store(Files.newBufferedWriter(Config.PROPERTIES_PATH), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getToolSpeedModifier() {
        return toolSpeedModifier;
    }

    public static void setToolSpeedModifier(int value) {
        toolSpeedModifier = value;
        config.setProperty("tool_speed_modifier", Integer.toString(value));
    }
}
