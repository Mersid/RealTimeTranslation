package net.mersid.realtimetranslate.screens;

import net.mersid.realtimetranslate.RealTimeTranslate;
import net.mersid.realtimetranslate.utils.ChatUtils;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.LiteralText;
import org.lwjgl.glfw.GLFW;

public class MainScreen extends Screen {

	private final int TEXT_GREY_COLOR_CODE = 0xFFA0A0A0;


	private ButtonWidget cancelButtonWidget;
	private ButtonWidget translateButtonWidget;

	private TextFieldWidget translationFieldWidget;

	private String previewText;
	private boolean textChangedSinceLastPreviewTranslate;
	private long lastKeyboardActivityTime;

	public MainScreen()
	{
		super(new LiteralText("Translate"));
	}

	@Override
	protected void init() {
		this.initWidgets();

		assert this.minecraft != null;
		this.minecraft.keyboard.enableRepeatEvents(true); // Holding backspace to mass delete

		this.setFocused(this.translationFieldWidget);
		this.translationFieldWidget.setSelected(true);

		lastKeyboardActivityTime = System.currentTimeMillis();

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
		drawCenteredString(font, previewText, width / 2, height / 2 + 10, TEXT_GREY_COLOR_CODE);
		drawCenteredString(font, RealTimeTranslate.INSTANCE.yandexKeyManager.getKey(), width / 2, height / 4 + font.fontHeight + 4, TEXT_GREY_COLOR_CODE);

	}

	@Override
	public void tick()
	{
		translationFieldWidget.tick();

		String translateText = translationFieldWidget.getText();
		if (System.currentTimeMillis() - lastKeyboardActivityTime > RealTimeTranslate.INSTANCE.configuration.previewUpdateMillis
				&& textChangedSinceLastPreviewTranslate)
		{
			if (translateText.length() > 0)
			{
				RealTimeTranslate.INSTANCE.yandexTranslator.translateWithFunctionAsync(translateText, translation -> {
					if (translation.wasSuccessful())
					{
						previewText = translation.getSuccessfulTranslation().getText();
					}
					else
					{
						ChatUtils.putChatMessage(translation.getUnsuccessfulTranslation().toString());
						RealTimeTranslate.INSTANCE.yandexKeyManager.rotateKey();
					}
				});
			}
			else
			{
				previewText = "";
			}

			textChangedSinceLastPreviewTranslate = false; // Reset flag
		}
	}

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers)
	{
		if (keyCode == GLFW.GLFW_KEY_ENTER || keyCode == GLFW.GLFW_KEY_KP_ENTER)
		{
			sendTranslation(translationFieldWidget.getText());
			close();
			return true;
		}

		if (keyCode == GLFW.GLFW_KEY_ESCAPE)
		{
			close();
			return true;
		}

		return super.keyPressed(keyCode, scanCode, modifiers);
	}



	private void initWidgets()
	{
		cancelButtonWidget = new ButtonWidget(width / 2 - 100, height / 2 + 70, 98, 20, "Cancel", this::cancelButtonWidgetPress);
		children.add(cancelButtonWidget);

		translateButtonWidget = new ButtonWidget(width / 2 + 5, height / 2 + 70, 98, 20, "Translate", this::translateButtonWidgetPress);
		children.add(translateButtonWidget);

		translationFieldWidget = new TextFieldWidget(font, width / 2 - 99, height / 2 + 30, 200, 15, "Hi!");
		translationFieldWidget.setMaxLength(1024);
		translationFieldWidget.setChangedListener(this::translationFieldWidgetTextChanged);
		children.add(translationFieldWidget);
	}

	private void cancelButtonWidgetPress(ButtonWidget buttonWidget)
	{
		close();
	}

	private void translateButtonWidgetPress(ButtonWidget widget)
	{
		sendTranslation(translationFieldWidget.getText());
		close();
	}

	private void sendTranslation(String message)
	{
		if (message.length() == 0)
			return;

		RealTimeTranslate.INSTANCE.yandexTranslator.translateWithFunctionAsync(message, translation -> {
			if (translation.wasSuccessful())
				ChatUtils.sendChatMessagePacket(translation.getSuccessfulTranslation().getText());
			else
			{
				ChatUtils.putChatMessage(translation.getUnsuccessfulTranslation().toString());
				RealTimeTranslate.INSTANCE.yandexKeyManager.rotateKey();
			}

		});
	}

	private void translationFieldWidgetTextChanged(String newText)
	{
		textChangedSinceLastPreviewTranslate = true;
		lastKeyboardActivityTime = System.currentTimeMillis();
	}


	private void close()
	{
		assert minecraft != null;
		minecraft.openScreen(null);
	}

}
