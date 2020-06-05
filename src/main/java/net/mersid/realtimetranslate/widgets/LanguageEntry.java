package net.mersid.realtimetranslate.widgets;

import net.mersid.realtimetranslate.language.Language;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.AlwaysSelectedEntryListWidget;

public class LanguageEntry extends AlwaysSelectedEntryListWidget.Entry<LanguageEntry> {

	private final LanguageSelectionListWidget languageSelectionListWidget;
	public final Language language;

	/**
	 * Represents a single entry in a {@link LanguageSelectionListWidget}.
	 * @param languageSelectionListWidget The caller, used to establish a two-way link.
	 * @param language See {@link Language}
	 */
	public LanguageEntry(LanguageSelectionListWidget languageSelectionListWidget, Language language)
	{
		this.languageSelectionListWidget = languageSelectionListWidget;
		this.language = language;
	}

	@Override
	public void render(int index, int y, int x, int width, int height, int mouseX, int mouseY, boolean hovering, float delta)
	{
		MinecraftClient mc = MinecraftClient.getInstance();
		assert mc.currentScreen != null; // Because if the current screen is null, how can the language selector and entries exist?

		languageSelectionListWidget.drawCenteredString(mc.textRenderer, language.getName() + " (" + language.getYandexCode() + " : " + language.getGoogleCode() + ")", mc.currentScreen.width / 2, y + 1, 0xFFFFFF);
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
