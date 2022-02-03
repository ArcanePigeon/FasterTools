package org.cloudwarp.fastertools.mixin;

import net.minecraft.item.MiningToolItem;
import org.cloudwarp.fastertools.FasterTools;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MiningToolItem.class)
public class MiningToolItemMixin {
	@Inject(method = "getMiningSpeedMultiplier", at = @At("RETURN"), cancellable = true)
	private void getMiningSpeedMultiplier (CallbackInfoReturnable<Float> cir) {
		cir.setReturnValue(cir.getReturnValueF() * (FasterTools.toolSpeedModifier / 100f));
	}

}
