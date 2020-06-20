package net.mersid.realtimetranslate.screens;

import net.mersid.realtimetranslate.RealTimeTranslate;
import net.mersid.realtimetranslate.utils.ConfigurationUtils;
import net.mersid.realtimetranslate.widgets.LanguageEntry;
import net.mersid.realtimetranslate.widgets.LanguageSelectionListWidget;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import java.util.List;
import java.util.function.Consumer;


public abstract class AbstractLanguageSelectionScreen extends Screen {

	private final Screen parent;
	protected final boolean auto;
	public String hint;
	public final Consumer<LanguageEntry> callback;
	protected LanguageSelectionListWidget languageSelectionListWidget;

	public AbstractLanguageSelectionScreen(Screen parent, Text title, boolean auto, Consumer<LanguageEntry> callback)
	{
		super(title);
		this.parent = parent;
		this.auto = auto;
		this.callback = callback;
	}

	@Override
	public void init()
	{
		this.languageSelectionListWidget = new LanguageSelectionListWidget(MinecraftClient.getInstance(), width, height, 32, height - 61, 18, auto, callback);
		children.add(languageSelectionListWidget);

		ButtonWidget backButtonWidget = new ButtonWidget(width / 2 - 112, height - 30, 225, 20,
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
		drawCenteredString(font, hint, width / 2, height - 55, 0xFFA0A0A0);

	}

	@Override
	public void onClose()
	{
		if (minecraft == null)
			return;

		ConfigurationUtils.saveConfiguration(RealTimeTranslate.INSTANCE.configuration, RealTimeTranslate.INSTANCE.configPath);
		minecraft.openScreen(parent);
	}

	public List<LanguageEntry> getLanguageEntries()
	{
		return languageSelectionListWidget.children();
	}

	public LanguageEntry getLanguageEntryByName(String name)
	{
		for (LanguageEntry languageEntry : getLanguageEntries())
		{
			if (languageEntry.language.getName().equals(name))
				return  languageEntry;
		}
		return null;
	}
}
