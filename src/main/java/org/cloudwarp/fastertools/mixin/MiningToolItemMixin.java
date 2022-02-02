package org.cloudwarp.fastertools.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.Tag;
import org.cloudwarp.fastertools.FasterToolsUtils;
import org.cloudwarp.fastertools.client.FasterToolsClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.item.ToolItem;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MiningToolItem.class)
public class MiningToolItemMixin extends ToolItem implements Vanishable, FasterToolsUtils.ServerPlayerEntityExtensions {
    @Unique
    private int fasterTools$toolSpeedModifier = 150;
    @Shadow
    private Tag<Block> effectiveBlocks;
    @Shadow
    protected float miningSpeed;

    public MiningToolItemMixin(ToolMaterial material, Settings settings) {
        super(material, settings);
        throw new IllegalStateException();
    }

    @Override
    public void fasterTools$setToolSpeedModifier(int value) {
        System.out.println(value);
        fasterTools$toolSpeedModifier = value;
    }

    /**
     * @author ProbabilityPigeon
     * @reason To increase mining speed.
     */
    @Overwrite
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return this.effectiveBlocks.contains(state.getBlock()) ? this.miningSpeed * (fasterTools$toolSpeedModifier / 100f) : (fasterTools$toolSpeedModifier / 100f);
    }

}
