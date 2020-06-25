package net.mersid.realtimetranslate.screens;

import net.mersid.realtimetranslate.widgets.LanguageSelectionListWidget;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.LiteralText;

public class ConfigurationHubScreen extends Screen {

	private final Screen parent;
	public ConfigurationHubScreen(Screen parent)
	{
		super(new LiteralText("Configuration Hub"));
		this.parent = parent;
	}

	@Override
	public void init()
	{
		ButtonWidget incomingSourceLanguageScreenButtonWidget = new ButtonWidget(width / 2 - 235, height / 2 - 46, 225, 20,
				"Change Incoming Source Language", button -> openScreen(new IncomingSourceLanguageSelectionScreen(this)));
		children.add(incomingSourceLanguageScreenButtonWidget);

		ButtonWidget incomingDestinationLanguageScreenButtonWidget = new ButtonWidget(width / 2 + 10, height / 2 - 46, 225, 20,
				"Change Incoming Destination Language", button -> openScreen(new IncomingDestinationLanguageSelectionScreen(this)));
		children.add(incomingDestinationLanguageScreenButtonWidget);

		ButtonWidget outgoingSourceLanguageScreenButtonWidget = new ButtonWidget(width / 2 - 235, height / 2 - 22, 225, 20,
				"Change Outgoing Source Language", button -> openScreen(new OutgoingSourceLanguageSelectionScreen(this)));
		children.add(outgoingSourceLanguageScreenButtonWidget);

		ButtonWidget outgoingDestinationLanguageScreenButtonWidget = new ButtonWidget(width / 2 + 10, height / 2 - 22, 225, 20,
				"Change Outgoing Destination Language", button -> openScreen(new OutgoingDestinationLanguageSelectionScreen(this)));
		children.add(outgoingDestinationLanguageScreenButtonWidget);

		ButtonWidget yandexApiKeyManagerScreenButtonWidget = new ButtonWidget(width / 2 - 235, height / 2 + 2, 225, 20,
				"Manage Yandex API Keys", button -> System.out.println("E"));
		children.add(yandexApiKeyManagerScreenButtonWidget);

		ButtonWidget regexManagerScreenButtonWidget = new ButtonWidget(width / 2 + 10, height / 2 + 2, 225, 20,
				"Manage Regular Expressions", button -> openScreen(new RegexScreen(this)));
		children.add(regexManagerScreenButtonWidget);

		ButtonWidget backButtonWidget = new ButtonWidget(width / 2 - 112, height / 2 + 26, 225, 20,
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

		drawCenteredString(font, title.asString(), width / 2, height / 4, 0xFFFFFFFF);
	}

	@Override
	public void onClose()
	{
		if (minecraft == null)
			return;

		minecraft.openScreen(parent);
	}

	private void openScreen(Screen screen)
	{
		MinecraftClient.getInstance().openScreen(screen);
	}

}
