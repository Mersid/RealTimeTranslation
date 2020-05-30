package net.mersid.realtimetranslate;

import net.mersid.realtimetranslate.screens.MainScreen;
import net.mersid.realtimetranslate.utils.ChatUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class NotEventHandler {

	public void onReceiveChat(Text chatText, int chatLineId)
	{
		if (ChatUtils.isPlayerSentMessage(chatText.asFormattedString()))
		{
			RealTimeTranslate.INSTANCE.yandexTranslator.translateWithFunctionAsync(ChatUtils.stripPlayerTag(chatText.asFormattedString()), translation -> {
				if (translation.wasSuccessful())
				{
					ChatUtils.putChatMessage(translation.getSuccessfulTranslation().getText());
				}
				else
				{
					ChatUtils.putChatMessage(translation.getUnsuccessfulTranslation().getMessage());
					RealTimeTranslate.INSTANCE.yandexKeyManager.rotateKey();
				}
			});
		}
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
