package net.mersid.realtimetranslate.screens;

import net.mersid.realtimetranslate.RealTimeTranslate;
import net.mersid.realtimetranslate.widgets.LanguageEntry;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

import java.util.function.Consumer;

public class OutgoingSourceLanguageSelectionScreen extends AbstractLanguageSelectionScreen {

	public OutgoingSourceLanguageSelectionScreen(Screen parent)
	{
		super(parent, new LiteralText("Configure Outgoing Source Language"), true, entry -> RealTimeTranslate.INSTANCE.configuration.outgoingSourceLanguage = entry.language.getName());
	}

	@Override
	public void init()
	{
		super.init();
		hint = "Outgoing source language is the language to translate your text from";
		languageSelectionListWidget.setSelected(getLanguageEntryByName(RealTimeTranslate.INSTANCE.configuration.outgoingSourceLanguage));
	}
}
