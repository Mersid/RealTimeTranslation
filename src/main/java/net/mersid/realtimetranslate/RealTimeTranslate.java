package net.mersid.realtimetranslate;

import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.mersid.realtimetranslate.configuration.Configuration;
import net.mersid.realtimetranslate.kms.YandexKeyManager;
import net.mersid.realtimetranslate.language.LanguageManager;
import net.mersid.realtimetranslate.translators.GoogleScrapeTranslator;
import net.mersid.realtimetranslate.translators.YandexTranslator;
import net.mersid.realtimetranslate.utils.ConfigurationUtils;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.lwjgl.glfw.GLFW;

import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public enum RealTimeTranslate {
	INSTANCE;

	public Configuration configuration;
	public NotEventHandler notEventHandler;
	public YandexTranslator yandexTranslator;
	public GoogleScrapeTranslator googleScrapeTranslator;
	public YandexKeyManager yandexKeyManager;
	public LanguageManager languageManager;

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

		yandexKeyManager = new YandexKeyManager(configuration.yandexApiKeys);

		languageManager = new LanguageManager();
	}
}
