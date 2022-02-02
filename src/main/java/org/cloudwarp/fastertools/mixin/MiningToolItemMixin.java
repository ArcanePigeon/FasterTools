package org.cloudwarp.fastertools.mixin;

import net.fabricmc.fabric.api.gamerule.v1.FabricGameRuleVisitor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tag.Tag;
import net.minecraft.world.World;
import org.cloudwarp.fastertools.FasterTools;
import org.cloudwarp.fastertools.client.FasterToolsClient;
import org.spongepowered.asm.mixin.FabricUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.item.ToolItem;

@Mixin(MiningToolItem.class)
public class MiningToolItemMixin extends ToolItem implements Vanishable {
    @Shadow
    private Tag<Block> effectiveBlocks;
    @Shadow
    protected float miningSpeed;
    public MiningToolItemMixin(ToolMaterial material, Settings settings) {
        super(material, settings);
        throw new IllegalStateException();
    }

    /**
     * @author ProbabilityPigeon
     * @reason To increase mining speed.
     */
    @Overwrite
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return this.effectiveBlocks.contains(state.getBlock()) ? this.miningSpeed * (FasterToolsClient.TOOL_SPEED_MODIFIER / 100f) : (FasterToolsClient.TOOL_SPEED_MODIFIER / 100f);
    }

}
