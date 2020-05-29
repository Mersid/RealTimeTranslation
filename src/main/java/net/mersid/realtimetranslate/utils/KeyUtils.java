package net.mersid.realtimetranslate.utils;

import com.sun.istack.internal.Nullable;
import net.mersid.realtimetranslate.RealTimeTranslate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public enum KeyUtils {
	;

	// Returns null if no valid keys
	public static @Nullable String getValidYandexKey()
	{
		List<String> keys = new ArrayList<>(RealTimeTranslate.INSTANCE.configuration.yandexApiKeys);
		List<String> invalidKeys = RealTimeTranslate.INSTANCE.configuration.invalidYandexApiKeys;
		keys.removeAll(invalidKeys);

		return keys.get(new Random().nextInt(keys.size()));
	}
}
