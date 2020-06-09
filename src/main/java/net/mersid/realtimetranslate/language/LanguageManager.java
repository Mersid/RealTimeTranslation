package net.mersid.realtimetranslate.language;

import net.mersid.realtimetranslate.utils.JsonUtils;
import net.mersid.realtimetranslate.widgets.LanguageEntry;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LanguageManager {

	public final List<Language> languages;

	public LanguageManager()
	{
		InputStream stream = LanguageManager.class.getResourceAsStream("/assets/lang.json");
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		Language[] languages = JsonUtils.gson.fromJson(reader, Language[].class); // Normally prefer lists, but then we'd need to use TypeToken<> in the serialization process.

		this.languages = new ArrayList<>();
		this.languages.add(Language.AUTO);
		this.languages.addAll(Arrays.asList(languages));
	}

	/**
	 * Returns a {@link Language} by its name.
	 * @param name Full name of the language to return
	 * @return {@link Language} if the language exists, or null if it doesn't. It shouldn't, unless something's gone way south
	 */
	public Language getLanguageByName(String name)
	{
		for (Language language : languages)
		{
			if (language.getName().equals(name))
			{
				return  language;
			}
		}
		return null;
	}
}
