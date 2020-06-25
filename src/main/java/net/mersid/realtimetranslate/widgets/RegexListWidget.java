package net.mersid.realtimetranslate.widgets;

import net.mersid.realtimetranslate.RealTimeTranslate;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.AlwaysSelectedEntryListWidget;

public class RegexListWidget extends AlwaysSelectedEntryListWidget<RegexEntry> {
	public RegexListWidget(MinecraftClient client, int width, int height, int top, int bottom, int itemHeight)
	{
		super(client, width, height, top, bottom, itemHeight);

		for (String regex : RealTimeTranslate.INSTANCE.configuration.regexList)
			addEntry(new RegexEntry(this, regex));
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
	public void setSelected(RegexEntry regexEntry)
	{
		super.setSelected(regexEntry);
	}
}
