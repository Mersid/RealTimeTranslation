package net.mersid.realtimetranslate.validators;

import net.mersid.realtimetranslate.RealTimeTranslate;
import net.mersid.realtimetranslate.translations.YandexTranslation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Deprecated
public class YandexKeyValidator implements KeyValidator {
	ExecutorService executorService;

	public YandexKeyValidator()
	{
		executorService = Executors.newFixedThreadPool(4);
	}

	@Override
	public List<String> getInvalidKeys(List<String> keys)
	{
		List<String> invalidKeys = new ArrayList<>();
		for (String key : keys)
		{
			if (!isKeyValid(key))
				invalidKeys.add(key);
		}
		return invalidKeys;
	}

	private boolean isKeyValid(String key)
	{
		YandexTranslation yt = RealTimeTranslate.INSTANCE.yandexTranslator.getTranslation(key, Locale.ENGLISH, Locale.FRENCH, "Deceive your other self. Deceive the world. That is what you must do to reach Steins Gate.");
		return yt != null && yt.wasSuccessful();
	}
}
