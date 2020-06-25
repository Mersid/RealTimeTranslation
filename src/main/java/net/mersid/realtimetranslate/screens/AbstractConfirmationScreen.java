package net.mersid.realtimetranslate.screens;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public abstract class AbstractConfirmationScreen extends Screen {

	protected final Screen parent;

	public AbstractConfirmationScreen(Screen parent, Text title)
	{
		super(title);

		this.parent = parent;
	}

	@Override
	public void init()
	{
		ButtonWidget yesButton = new ButtonWidget(this.width / 2 - 155, this.height / 6 + 96, 150, 20, "Yes", button -> onYes());
		children.add(yesButton);

		ButtonWidget noButton = new ButtonWidget(this.width / 2 - 155 + 160, this.height / 6 + 96, 150, 20, "No", button -> onClose());
		children.add(noButton);
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

	protected abstract void onYes();
}
