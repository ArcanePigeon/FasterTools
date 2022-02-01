package org.cloudwarp.fastertools.mixin;

import com.google.common.collect.ImmutableMultimap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.tag.Tag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.item.ToolItem;

import java.util.function.Supplier;

@Mixin(MiningToolItem.class)
public class MiningToolItemMixin extends ToolItem implements Vanishable {
    @Shadow
    private Tag<Block> effectiveBlocks;
    @Shadow
    protected float miningSpeed;
    public MiningToolItemMixin(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    /**
     * @author ProbabilityPigeon
     */
    @Overwrite
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return this.effectiveBlocks.contains(state.getBlock()) ? this.miningSpeed*5f : 5f;
    }

}