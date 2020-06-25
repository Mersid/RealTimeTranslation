package net.mersid.realtimetranslate.screens;

import net.mersid.realtimetranslate.RealTimeTranslate;
import net.mersid.realtimetranslate.widgets.LanguageEntry;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

import java.util.function.Consumer;

public class OutgoingDestinationLanguageSelectionScreen extends AbstractLanguageSelectionScreen {

	public OutgoingDestinationLanguageSelectionScreen(Screen parent)
	{
		super(parent, new LiteralText("Configure Outgoing Destination Language"), false, entry -> RealTimeTranslate.INSTANCE.configuration.outgoingDestinationLanguage = entry.language.getName());
	}

	@Override
	public void init()
	{
		super.init();
		hint = "Outgoing destination language is the language to translate your messages to";
		languageSelectionListWidget.setSelected(getLanguageEntryByName(RealTimeTranslate.INSTANCE.configuration.outgoingDestinationLanguage));
	}
}
