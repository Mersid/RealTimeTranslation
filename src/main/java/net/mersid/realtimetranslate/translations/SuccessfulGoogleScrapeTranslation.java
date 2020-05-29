package net.mersid.realtimetranslate.translations;

public class SuccessfulGoogleScrapeTranslation extends GoogleScrapeTranslation {

	private String text;

	public SuccessfulGoogleScrapeTranslation(int errorCode, String text)
	{
		super(errorCode);
		this.text = text;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	@Override
	public String toString()
	{
		return text;
	}
}
