package org.cloudwarp.fastertools;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameRules;

import java.util.List;

public class ModGameRules {
	public static final GameRules.Key<GameRules.IntRule> TOOL_SPEED_MODIFIER_RULE =
			GameRuleRegistry.register("toolSpeedModifier", GameRules.Category.MISC, GameRuleFactory.createIntRule(150, (server, rule) -> {
				List<ServerPlayerEntity> players = server.getPlayerManager().getPlayerList();
				PacketByteBuf buffer = new PacketByteBuf(PacketByteBufs.create().writeInt(rule.get()));
				FasterTools.toolSpeedModifier = rule.get();
				for (ServerPlayerEntity player : players) {
					ServerPlayNetworking.send(player, FasterToolsNetworking.TOOL_SPEED_MODIFIER, buffer);
				}
			}));
	public static final GameRules.Key<GameRules.BooleanRule> NON_TOOL_MODIFICATION_RULE =
			GameRuleRegistry.register("doNonToolSpeedModification", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false, (server, rule) -> {
				List<ServerPlayerEntity> players = server.getPlayerManager().getPlayerList();
				PacketByteBuf buffer = new PacketByteBuf(PacketByteBufs.create().writeBoolean(rule.get()));
				FasterTools.doNonToolSpeedModification = rule.get();
				for (ServerPlayerEntity player : players) {
					ServerPlayNetworking.send(player, FasterToolsNetworking.NON_TOOL_MODIFICATION, buffer);
				}
			}));

	public static void init () {
	}
}
