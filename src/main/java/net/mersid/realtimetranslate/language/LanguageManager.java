package net.mersid.realtimetranslate.language;

import net.mersid.realtimetranslate.utils.JsonUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LanguageManager {

	public final Language[] languages; // Normally prefer lists, but then we'd need to use TypeToken<> in the serialization process.

	public LanguageManager()
	{
		InputStream stream = LanguageManager.class.getResourceAsStream("/assets/lang.json");
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		languages = JsonUtils.gson.fromJson(reader, Language[].class);
	}
}
