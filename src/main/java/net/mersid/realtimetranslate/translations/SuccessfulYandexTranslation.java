package net.mersid.realtimetranslate.translations;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SuccessfulYandexTranslation extends YandexTranslation {

	private final String lang;
	private String[] text; // Translation result. Only field 0 should be populated.

	public SuccessfulYandexTranslation(int errorCode, String lang, String text)
	{
		super(errorCode);
		this.lang = lang;
	}

	public String getText()
	{
		return text[0];
	}

	public Locale getSourceLang()
	{
		Matcher matcher = Pattern.compile("^.*(?=-)").matcher(lang);
		matcher.find();
		return new Locale(matcher.group());

	}

	public Locale getDestinationLang()
	{
		Matcher matcher = Pattern.compile("(?<=-).*$").matcher(lang);
		matcher.find();
		return new Locale(matcher.group());
	}

	@Override
	public String toString()
	{
		return lang + " (" + getSourceLang().getDisplayLanguage() + " to " + getDestinationLang().getDisplayLanguage() + "): " + getText();
	}
}
