package net.mersid.realtimetranslate;

import net.mersid.realtimetranslate.screens.MainScreen;
import net.mersid.realtimetranslate.utils.ChatUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class NotEventHandler {

	public void onReceiveChat(Text chatText, int chatLineId)
	{
		System.out.println("Chat received (" + ChatUtils.isPlayerSentMessage(chatText.asFormattedString()) + "): " + chatText.asFormattedString());
	}

	public void onSendChat(String message)
	{

	}

	public void onKeyPress(long window, int keyCode, int scanCode, int action, int modifiers)
	{
		if (RealTimeTranslate.INSTANCE.keybind.isPressed())
		{
			MinecraftClient.getInstance().openScreen(new MainScreen());
		}
	}
}
