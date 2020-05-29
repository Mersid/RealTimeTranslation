package net.mersid.realtimetranslate;

import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.mersid.realtimetranslate.configuration.Configuration;
import net.mersid.realtimetranslate.translators.GoogleScrapeTranslator;
import net.mersid.realtimetranslate.translators.YandexTranslator;
import net.mersid.realtimetranslate.utils.ConfigurationUtils;
import net.mersid.realtimetranslate.validators.YandexKeyValidator;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

import java.nio.file.ClosedWatchServiceException;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public enum RealTimeTranslate {
	INSTANCE;

	public Configuration configuration;
	public NotEventHandler notEventHandler;
	public YandexTranslator yandexTranslator;
	public GoogleScrapeTranslator googleScrapeTranslator;
	public YandexKeyValidator yandexKeyValidator;

	public FabricKeyBinding keybind;

	public ExecutorService threadPool;

	public void initialize()
	{
		configuration = ConfigurationUtils.loadConfiguration(Paths.get("realtimetranslate.json"));
		notEventHandler = new NotEventHandler();

		yandexTranslator = new YandexTranslator();
		googleScrapeTranslator = new GoogleScrapeTranslator(configuration.sourceLanguage, configuration.destinationLanguage);

		keybind = FabricKeyBinding.Builder.create(new Identifier("realtimetranslate", "menu"), InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_Y, "Real Time Translate").build();
		KeyBindingRegistry.INSTANCE.addCategory("Real Time Translate");
		KeyBindingRegistry.INSTANCE.register(keybind);

		threadPool = Executors.newFixedThreadPool(4);

		yandexKeyValidator = new YandexKeyValidator();
		configuration.invalidYandexApiKeys = yandexKeyValidator.getInvalidKeys(configuration.yandexApiKeys);
	}
}
