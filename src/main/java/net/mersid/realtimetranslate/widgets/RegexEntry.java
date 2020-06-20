package net.mersid.realtimetranslate.widgets;

import net.minecraft.client.gui.widget.AlwaysSelectedEntryListWidget;

public class RegexEntry extends AlwaysSelectedEntryListWidget.Entry<RegexEntry> {

	private final RegexListWidget regexListWidget;
	public final String regex;

	public RegexEntry(RegexListWidget regexListWidget, String regex)
	{
		this.regexListWidget = regexListWidget;
		this.regex = regex;
	}

	@Override
	public void render(int index, int y, int x, int width, int height, int mouseX, int mouseY, boolean hovering, float delta)
	{

	}
}
