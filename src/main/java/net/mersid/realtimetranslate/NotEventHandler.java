package net.mersid.realtimetranslate;

import net.mersid.realtimetranslate.screens.MainScreen;
import net.mersid.realtimetranslate.translations.SuccessfulYandexTranslation;
import net.mersid.realtimetranslate.utils.ChatUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class NotEventHandler {

	public void onReceiveChat(Text chatText, int chatLineId)
	{
		String strippedText = ChatUtils.stripFormatting(chatText.getString());
		if (!ChatUtils.isAlreadyTranslated(chatText.getString()) && ChatUtils.isPlayerSentMessage(strippedText))
		{
			RealTimeTranslate.INSTANCE.yandexTranslator.translateWithFunctionAsync(ChatUtils.stripPlayerTag(strippedText), translation -> {
				if (translation.wasSuccessful() && translation.getSuccessfulTranslation().getSourceLang() != translation.getSuccessfulTranslation().getDestinationLang())
				{
					SuccessfulYandexTranslation successfulTranslation = translation.getSuccessfulTranslation();
					ChatUtils.putChatMessage("§7Translated (" + successfulTranslation.getSourceLang().getDisplayLanguage() + "): " + successfulTranslation.getText());
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
