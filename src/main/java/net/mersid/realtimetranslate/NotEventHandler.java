package net.mersid.realtimetranslate;

import net.mersid.realtimetranslate.configuration.Configuration;
import net.mersid.realtimetranslate.language.Language;
import net.mersid.realtimetranslate.language.LanguageManager;
import net.mersid.realtimetranslate.screens.MainScreen;
import net.mersid.realtimetranslate.translations.SuccessfulYandexTranslation;
import net.mersid.realtimetranslate.utils.ChatUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class NotEventHandler {

	public void onReceiveChat(Text chatText)
	{
		String strippedText = ChatUtils.stripFormatting(chatText.getString());
		if (!ChatUtils.isAlreadyTranslated(chatText.getString()) && ChatUtils.isPlayerSentMessage(strippedText))
		{
			Configuration cfg = RealTimeTranslate.INSTANCE.configuration;
			LanguageManager languageManager = RealTimeTranslate.INSTANCE.languageManager;
			Language src = languageManager.getLanguageByName(cfg.incomingSourceLanguage);
			Language dest = languageManager.getLanguageByName(cfg.incomingDestinationLanguage);

			RealTimeTranslate.INSTANCE.yandexTranslator.translateWithFunctionAsync(ChatUtils.stripPlayerTag(strippedText), src, dest, translation -> {
				if (translation.wasSuccessful())
				{
					SuccessfulYandexTranslation successfulTranslation = translation.getSuccessfulTranslation();
					if (!successfulTranslation.getSourceLang().equals(successfulTranslation.getDestinationLang()))
						ChatUtils.putChatMessage("§7Translated (" + successfulTranslation.getSourceLang().getName() + "): " + successfulTranslation.getText());
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
