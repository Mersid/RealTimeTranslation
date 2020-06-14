package net.mersid.realtimetranslate.screens;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.LiteralText;

public class IncomingSourceLanguageSelectionScreen extends AbstractLanguageSelectionScreen {

	public IncomingSourceLanguageSelectionScreen(Screen parent)
	{
		super(parent, new LiteralText("Configure Incoming Source Language"), true, languageEntry -> System.out.println(languageEntry.language.getName()));

	}

	@Override
	public void init()
	{
		hint = "Incoming source language is the language setting used when translating chat messages";
	}
}
