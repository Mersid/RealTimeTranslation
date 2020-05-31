package net.mersid.realtimetranslate.screens;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.LiteralText;

public class ConfigurationHubScreen extends Screen {

	private final Screen parent;

	public ConfigurationHubScreen(Screen parent)
	{
		super(new LiteralText("Configuration Hub"));
		this.parent = parent;
	}

	@Override
	public void render(int mouseX, int mouseY, float delta)
	{
		super.render(mouseX, mouseY, delta);
	}

	@Override
	public void onClose()
	{
		if (minecraft == null)
			return;

		minecraft.openScreen(parent);
	}

	@Override
	public void init(MinecraftClient client, int width, int height)
	{
		super.init(client, width, height);
	}

	@Override
	public void tick()
	{
		super.tick();
	}
}
