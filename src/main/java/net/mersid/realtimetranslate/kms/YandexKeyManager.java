package net.mersid.realtimetranslate.kms;

import net.mersid.realtimetranslate.RealTimeTranslate;
import net.mersid.realtimetranslate.language.LanguageManager;
import net.mersid.realtimetranslate.translations.YandexTranslation;

import java.util.List;

public class YandexKeyManager implements KeyManager {

	public final String testString = "Of course it is happening inside your head, Harry, but why on Earth should that mean it’s not real?";
	private final List<String> keyList;
	private final LanguageManager languageManager;
	private String currentKey = ""; // Will be empty ONLY if no keys have been specified. Should not happen unless user manually deletes all keys.
	private boolean hasValidKeysLeft;

	public YandexKeyManager(LanguageManager languageManager, List<String> keyList)
	{
		this.keyList = keyList;
		this.languageManager = languageManager;
		rotateKey();
	}

	/**
	 * Returns the current key. use rotateKey() to get a new working key, if one is available.
	 * If no valid keys are available, rotateKey() does nothing, and the result of this method
	 * remains unchanged.
	 */
	@Override
	public String getKey()
	{
		return currentKey;
	}

	/**
	 * Swaps the current key for a valid one, unless no keys are valid. This method is thread-blocking.
	 */
	@Override
	public void rotateKey()
	{
		hasValidKeysLeft = true;
		for (String key : keyList)
		{
			if (isKeyValid(key))
			{
				currentKey = key;
				break;
			}
		}
		hasValidKeysLeft = false;
	}

	@Override
	public boolean hasValidKeysLeft()
	{
		return hasValidKeysLeft;
	}

	private boolean isKeyValid(String key)
	{
		YandexTranslation yt = RealTimeTranslate.INSTANCE.yandexTranslator.getTranslation(
				key,
				languageManager.getLanguageByName("English"),
				languageManager.getLanguageByName("French"),
				testString);
		return yt != null && yt.wasSuccessful();
	}
}
