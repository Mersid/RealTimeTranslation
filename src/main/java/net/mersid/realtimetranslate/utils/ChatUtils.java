package net.mersid.realtimetranslate.utils;

import net.mersid.realtimetranslate.RealTimeTranslate;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.text.LiteralText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

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

	public static boolean isPlayerSentMessage(String message)
	{
		int i = 0;
		for (String regex : RealTimeTranslate.INSTANCE.configuration.regexList)
		{
			regex = "^" + regex; // Match from beginning of string only
			Matcher matcher = Pattern.compile(regex).matcher(message);
			if (matcher.find())
			{
				System.out.println("Match found: " + i + " / " + regex);
				return true;
			}
			i++;
		}
		return false;
	}

	public static String stripPlayerTag(String message)
	{
		int i = 0;
		for (String regex : RealTimeTranslate.INSTANCE.configuration.regexList)
		{
			regex = "^" + regex; // Match from beginning of string only
			Matcher matcher = Pattern.compile(regex).matcher(message);
			if (matcher.find())
			{
				message = matcher.replaceFirst("");
				break;
			}
			i++;
		}
		return message;
	}

	public static String stripFormatting(String message)
	{
		return message.replaceAll("§.", "");
	}

	/**
	 * Returns true if message starts with "Translated" - yes, the open bracket is intentional.
	 */
	public static boolean isAlreadyTranslated(String message)
	{
		Matcher m = Pattern.compile("§7Translated \\(").matcher(message);
		return m.find();
	}
}
