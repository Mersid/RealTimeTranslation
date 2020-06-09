package net.mersid.realtimetranslate.translations;

import net.mersid.realtimetranslate.RealTimeTranslate;
import net.mersid.realtimetranslate.language.Language;

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

	public Language getSourceLang()
	{
		Matcher matcher = Pattern.compile("^.*(?=-)").matcher(lang);
		matcher.find();
		return RealTimeTranslate.INSTANCE.languageManager.getLanguageByYandexCode(matcher.group());

	}

	public Language getDestinationLang()
	{
		Matcher matcher = Pattern.compile("(?<=-).*$").matcher(lang);
		matcher.find();
		return RealTimeTranslate.INSTANCE.languageManager.getLanguageByYandexCode(matcher.group());
	}

	@Override
	public String toString()
	{
		return lang + " (" + getSourceLang().getName() + " to " + getDestinationLang().getName() + "): " + getText();
	}
}
