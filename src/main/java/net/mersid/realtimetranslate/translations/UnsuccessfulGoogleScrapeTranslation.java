package net.mersid.realtimetranslate.translations;

public class UnsuccessfulGoogleScrapeTranslation extends GoogleScrapeTranslation {

	private final String html; // On error, send entire html, as errors are not documented on Google's page.

	public UnsuccessfulGoogleScrapeTranslation(int errorCode, String html)
	{
		super(errorCode);
		this.html = html;
	}

	public String getErrorHtml()
	{
		return html;
	}

	@Override
	public String toString()
	{
		return errorCode + ". Get the error html for more details.";
	}
}
