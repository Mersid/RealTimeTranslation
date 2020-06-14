package net.mersid.realtimetranslate.widgets;

import net.mersid.realtimetranslate.RealTimeTranslate;
import net.mersid.realtimetranslate.language.Language;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.AlwaysSelectedEntryListWidget;

import java.util.function.Consumer;

public class LanguageSelectionListWidget extends AlwaysSelectedEntryListWidget<LanguageEntry> {

	private final Consumer<LanguageEntry> callback;

	// Names of ints from super.super (EntryListWidget)
	public LanguageSelectionListWidget(MinecraftClient client, int width, int height, int top, int bottom, int itemHeight, boolean auto, Consumer<LanguageEntry> callback)
	{
		super(client, width, height, top, bottom, itemHeight);
		this.callback = callback;

		for (Language language : RealTimeTranslate.INSTANCE.languageManager.languages)
		{
			if (!auto && language == Language.AUTO)
				continue;

			addEntry(new LanguageEntry(this, language));
		}

	}

	@Override
	protected int getScrollbarPosition()
	{
		return super.getScrollbarPosition() + 20;
	}

	@Override
	public int getRowWidth()
	{
		return super.getRowWidth() + 50;
	}

	@Override
	public void setSelected(LanguageEntry languageEntry)
	{
		super.setSelected(languageEntry);
		//callback.accept(languageEntry);
	}
}
