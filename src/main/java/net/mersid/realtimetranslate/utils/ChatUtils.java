package net.mersid.realtimetranslate.utils;

import com.sun.istack.internal.Nullable;
import net.mersid.realtimetranslate.RealTimeTranslate;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.text.LiteralText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ChatUtils {
	;

	public static void putChatMessage(String message)
	{
		MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(new LiteralText(message));
	}

	public static void sendChatMessagePacket(String message)
	{
		assert MinecraftClient.getInstance().getNetworkHandler() != null;
		MinecraftClient.getInstance().getNetworkHandler().sendPacket(new ChatMessageC2SPacket(message));
	}

	/**
	 * Uses regexes in the configuration file to attempt to retrieve the name of the player, given a chat message.
	 * Returns null if the name cannot be retrieved (does not exist, no appropriate regex, etc)
	 */
	public static @Nullable String getChatMessageSender(String message)
	{
		for (Pattern pattern : RealTimeTranslate.INSTANCE.configuration.regexes)
		{
			Matcher matcher = pattern.matcher(message);
			if (matcher.find())
				return matcher.group();
		}
		return null;
	}

	public static boolean isPlayerSentMessage(String message)
	{
		for (Pattern pattern : RealTimeTranslate.INSTANCE.configuration.regexes)
		{
			Matcher matcher = pattern.matcher(message);
			if (matcher.find())
				return true;
		}
		return false;
	}
}
