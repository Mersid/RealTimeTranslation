package net.mersid.realtimetranslate;

import net.fabricmc.api.ModInitializer;
import net.mersid.realtimetranslate.translators.YandexTranslator;

public class RealTimeTranslateInitializer implements ModInitializer {
	private static boolean initialized;

	@Override
	public void onInitialize() {
		if(initialized)
			throw new RuntimeException(
					"Attempted to initialize the mod more than once!");

		RealTimeTranslate.INSTANCE.initialize();
		initialized = true;
	}
}
