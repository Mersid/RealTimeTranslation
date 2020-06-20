package net.mersid.realtimetranslate.widgets;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.AlwaysSelectedEntryListWidget;

public class RegexListWidget extends AlwaysSelectedEntryListWidget<RegexListWidget> {
	public RegexListWidget(MinecraftClient client, int width, int height, int top, int bottom, int itemHeight)
	{
		super(client, width, height, top, bottom, itemHeight);
	}
}
