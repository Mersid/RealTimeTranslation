package net.mersid.realtimetranslate.language;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

public class Language {
	public static final transient Language AUTO = new Language();

	private final String name;
	private @Nullable @SerializedName("nameUnicode") final transient String unicodeName;
	private @Nullable final transient String googleCode;
	private @Nullable final transient String yandexCode;

	// Used only for creating the auto language definition - all others should be created using the lang.json file provided.
	private Language()
	{
		name = "Auto";
		unicodeName = null;
		googleCode = null;
		yandexCode = null;
	}

	public String getName()
	{
		return name;
	}

	public @Nullable String getUnicodeName()
	{
		return unicodeName;
	}

	public @Nullable String getGoogleCode()
	{
		return googleCode;
	}

	public @Nullable String getYandexCode()
	{
		return yandexCode;
	}
}
