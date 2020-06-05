package net.mersid.realtimetranslate.screens;

import net.mersid.realtimetranslate.widgets.LanguageSelectionListWidget;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.LiteralText;

public class ConfigurationHubScreen extends Screen {

	private final Screen parent;
	private LanguageSelectionListWidget languageSelectionListWidget;

	public ConfigurationHubScreen(Screen parent)
	{
		super(new LiteralText("Configuration Hub"));
		this.parent = parent;
	}

	@Override
	public void render(int mouseX, int mouseY, float delta)
	{
		fill(0, 0, this.width, this.height, 0x7F000000); // ARGB

		// Render all elements added using this.children.add
		for (Element child : children)
		{
			if (child instanceof Drawable)
				((Drawable)child).render(mouseX, mouseY, delta);
		}

		//drawCenteredString(font, String.valueOf(width), width / 2, height / 4, 0xFFFFFFFF);
	}

	@Override
	public void onClose()
	{
		if (minecraft == null)
			return;

		minecraft.openScreen(parent);
	}

	@Override
	public void init()
	{
		this.languageSelectionListWidget = new LanguageSelectionListWidget(MinecraftClient.getInstance(), width, height, 32, height - 61, 18);
		children.add(languageSelectionListWidget);
	}


}
