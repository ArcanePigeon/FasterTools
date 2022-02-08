package org.cloudwarp.fastertools.mixin;

import net.fabricmc.fabric.impl.tool.attribute.handlers.ModdedToolsVanillaBlocksToolHandler;
import net.minecraft.util.TypedActionResult;
import org.cloudwarp.fastertools.FasterTools;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ModdedToolsVanillaBlocksToolHandler.class)
public class ModdedToolsVanillaBlocksToolHandlerMixin {
	@Inject(method = "getMiningSpeedMultiplier", at = @At("RETURN"), cancellable = true)
	private void getMiningSpeedMultiplier (CallbackInfoReturnable<TypedActionResult<Float>> cir) {
		if (cir.getReturnValue() == null) {
			return;
		}
		cir.setReturnValue(TypedActionResult.pass(cir.getReturnValue().getValue() * (FasterTools.doNonToolSpeedModification ? (FasterTools.toolSpeedModifier / 100f) : 1f)));
	}
}