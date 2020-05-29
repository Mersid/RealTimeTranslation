package net.mersid.realtimetranslate.translations;

public abstract class GoogleScrapeTranslation extends Translation {

	public GoogleScrapeTranslation(int errorCode)
	{
		super(errorCode);
	}

	public SuccessfulGoogleScrapeTranslation getSuccessfulTranslation()
	{
		return (SuccessfulGoogleScrapeTranslation)this;
	}

	public UnsuccessfulGoogleScrapeTranslation getUnsuccessfulTranslation()
	{
		return (UnsuccessfulGoogleScrapeTranslation)this;
	}
}
