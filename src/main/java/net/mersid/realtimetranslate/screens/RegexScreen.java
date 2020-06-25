package net.mersid.realtimetranslate.screens;

import net.mersid.realtimetranslate.RealTimeTranslate;
import net.mersid.realtimetranslate.utils.ConfigurationUtils;
import net.mersid.realtimetranslate.widgets.RegexListWidget;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.LiteralText;

public class RegexScreen extends Screen {

	private final Screen parent;
	private RegexListWidget regexListWidget;

	protected RegexScreen(Screen parent)
	{
		super(new LiteralText("Regex Configuration"));

		this.parent = parent;
	}

	@Override
	public void init()
	{
		this.regexListWidget = new RegexListWidget(MinecraftClient.getInstance(), width, height, 32, height - 61, 18);
		children.add(regexListWidget);

		ButtonWidget backButtonWidget = new ButtonWidget(width / 2 - 155, height - 38, 150, 20,
				"Back", button -> onClose());
		children.add(backButtonWidget);

		
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

		drawCenteredString(font, title.asString(), width / 2, 16 - font.fontHeight / 2, 0xFFFFFFFF);
		//drawCenteredString(font, hint, width / 2, height - 55, 0xFFA0A0A0);

	}

	@Override
	public void onClose()
	{
		if (minecraft == null)
			return;

		ConfigurationUtils.saveConfiguration(RealTimeTranslate.INSTANCE.configuration, RealTimeTranslate.INSTANCE.configPath);
		minecraft.openScreen(parent);
	}
}
