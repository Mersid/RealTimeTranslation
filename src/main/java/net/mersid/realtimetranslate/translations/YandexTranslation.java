package net.mersid.realtimetranslate.translations;

public abstract class YandexTranslation extends Translation {


	public YandexTranslation(int errorCode)
	{
		super(errorCode);
	}

	public SuccessfulYandexTranslation getSuccessfulTranslation()
	{
		return (SuccessfulYandexTranslation)this;
	}

	public UnsuccessfulYandexTranslation getUnsuccessfulTranslation()
	{
		return (UnsuccessfulYandexTranslation)this;
	}
}
