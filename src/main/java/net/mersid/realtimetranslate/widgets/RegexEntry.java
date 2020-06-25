package net.mersid.realtimetranslate.widgets;

import net.minecraft.client.MinecraftClient;
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
		MinecraftClient mc = MinecraftClient.getInstance();
		assert mc.currentScreen != null;

		regexListWidget.drawCenteredString(mc.textRenderer, regex, mc.currentScreen.width / 2, y + 1, 0xFFFFFF);
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button)
	{
		if (button == 0)
		{
			this.onPressed();
			return true;
		}
		else
		{
			return false;
		}
	}

	private void onPressed()
	{
		regexListWidget.setSelected(this);
	}
}
