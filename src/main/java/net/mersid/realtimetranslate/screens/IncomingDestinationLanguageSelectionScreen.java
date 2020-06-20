package net.mersid.realtimetranslate.screens;

import net.mersid.realtimetranslate.RealTimeTranslate;
import net.mersid.realtimetranslate.widgets.LanguageEntry;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

import java.util.function.Consumer;

public class IncomingDestinationLanguageSelectionScreen extends AbstractLanguageSelectionScreen {

	public IncomingDestinationLanguageSelectionScreen(Screen parent)
	{
		super(parent, new LiteralText("Configure Incoming Destination Language"), false, entry -> RealTimeTranslate.INSTANCE.configuration.incomingDestinationLanguage = entry.language.getName());
	}

	@Override
	public void init()
	{
		super.init();
		hint = "Incoming destination language is the language to translate incoming messages to";
	}
}
