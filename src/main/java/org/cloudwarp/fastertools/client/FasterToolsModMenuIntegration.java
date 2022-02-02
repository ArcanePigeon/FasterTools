package org.cloudwarp.fastertools.client;
import com.google.common.base.CaseFormat;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import org.cloudwarp.fastertools.Config;
import org.cloudwarp.fastertools.DefaultConfig;

import java.util.HashMap;

public class FasterToolsModMenuIntegration implements ModMenuApi {
    private ConfigBuilder builder;
    private ConfigEntryBuilder entryBuilder;

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            // load config
            Config.load();

            // create the config
            builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(new TranslatableText("title.fastertools.config"));
            builder.setSavingRunnable(Config::save);

            entryBuilder = builder.entryBuilder();

            // config categories and entries
            GenerateGeneralSettings();

            // build and return the config screen
            return builder.build();
        };
    }

    private void GenerateGeneralSettings() {
        ConfigCategory general = builder.getOrCreateCategory(new TranslatableText("category.illuminations.general"));

        general.addEntry(entryBuilder
                .startIntField(new TranslatableText("option.fastertools.toolSpeedModifier"), Config.getToolSpeedModifier())
                .setTooltip(
                        new TranslatableText("option.tooltip.fastertools.toolSpeedModifier"),
                        new TranslatableText("option.tooltip.fastertools.toolSpeedModifier.lowest"),
                        new TranslatableText("option.tooltip.fastertools.toolSpeedModifier.highest"))
                .setSaveConsumer(Config::setToolSpeedModifier)
                .setDefaultValue(DefaultConfig.TOOL_SPEED_MODIFIER)
                .build());
    }
}
