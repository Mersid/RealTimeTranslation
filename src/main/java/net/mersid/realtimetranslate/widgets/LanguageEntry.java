package net.mersid.realtimetranslate.widgets;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.options.LanguageOptionsScreen;
import net.minecraft.client.gui.widget.AlwaysSelectedEntryListWidget;

public class LanguageEntry extends AlwaysSelectedEntryListWidget.Entry<LanguageEntry> {

	private final LanguageSelectionListWidget languageSelectionListWidget;

	public LanguageEntry(LanguageSelectionListWidget languageSelectionListWidget)
	{
		this.languageSelectionListWidget = languageSelectionListWidget;
	}

	@Override
	public void render(int index, int y, int x, int width, int height, int mouseX, int mouseY, boolean hovering, float delta)
	{
		MinecraftClient mc = MinecraftClient.getInstance();

		assert mc.currentScreen != null; // Because if the current screen is null, how can the language selector and entries exist?

		mc.textRenderer.setRightToLeft(true);
		languageSelectionListWidget.drawCenteredString(mc.textRenderer, "Hello world" , mc.currentScreen.width / 2, y + 1, 0xFFFFFF);
		mc.textRenderer.setRightToLeft(mc.getLanguageManager().isRightToLeft());
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
		languageSelectionListWidget.setSelected(this);
	}
}
