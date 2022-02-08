package org.cloudwarp.fastertools.mixin;

import net.fabricmc.fabric.impl.tool.attribute.ToolManagerImpl;
import net.minecraft.util.TypedActionResult;
import org.cloudwarp.fastertools.FasterTools;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net/fabricmc/fabric/impl/tool/attribute/ToolManagerImpl$1")
public class ToolManagerImplMixin {
	@Inject(method = "getMiningSpeedMultiplier", at = @At("RETURN"), cancellable = true, remap = false)
	private void getMiningSpeedMultiplier (CallbackInfoReturnable<TypedActionResult<Float>> cir) {
		if (cir.getReturnValue() == null) {
			return;
		}
		cir.setReturnValue(TypedActionResult.pass(cir.getReturnValue().getValue() * (FasterTools.doNonToolSpeedModification ? (FasterTools.toolSpeedModifier / 100f) : 1f)));
	}
}

@Mixin(ToolManagerImpl.class)
class ToolManagerImplHandleMixin {
	@Inject(method = "handleBreakingSpeedIgnoresVanilla", at = @At("RETURN"), cancellable = true)
	private static void handleBreakingSpeedIgnoresVanilla (CallbackInfoReturnable<Float> cir) {
		if (cir.getReturnValue() == null) {
			return;
		}
		cir.setReturnValue(cir.getReturnValue() * (FasterTools.doNonToolSpeedModification ? (FasterTools.toolSpeedModifier / 100f) : 1f));
	}
}

