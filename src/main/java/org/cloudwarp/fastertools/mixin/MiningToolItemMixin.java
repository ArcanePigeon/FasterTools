package org.cloudwarp.fastertools.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.*;
import net.minecraft.tag.Tag;
import org.cloudwarp.fastertools.FasterTools;
import org.spongepowered.asm.mixin.*;
import net.minecraft.item.ToolItem;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MiningToolItem.class)
public class MiningToolItemMixin {
    @Inject(method = "getMiningSpeedMultiplier", at = @At("RETURN"), cancellable = true)
    private void getMiningSpeedMultiplier(CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(cir.getReturnValueF()*(FasterTools.toolSpeedModifier/100f));
        //return this.effectiveBlocks.contains(state.getBlock()) ? this.miningSpeed * (FasterTools.toolSpeedModifier / 100f) : (FasterTools.toolSpeedModifier / 100f);
    }

}
