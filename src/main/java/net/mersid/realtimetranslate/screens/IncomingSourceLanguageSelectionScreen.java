package net.mersid.realtimetranslate.screens;

import net.mersid.realtimetranslate.RealTimeTranslate;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.LiteralText;

public class IncomingSourceLanguageSelectionScreen extends AbstractLanguageSelectionScreen {

	public IncomingSourceLanguageSelectionScreen(Screen parent)
	{
		super(parent, new LiteralText("Configure Incoming Source Language"), true, entry -> RealTimeTranslate.INSTANCE.configuration.incomingSourceLanguage = entry.language.getName());
	}

	@Override
	public void init()
	{
		super.init(); // Forgot this once, any code under super's init didn't run.
		hint = "Incoming source language is the language setting used when translating chat messages";
		languageSelectionListWidget.setSelected(getLanguageEntryByName(RealTimeTranslate.INSTANCE.configuration.incomingSourceLanguage));
	}

}
