package net.mersid.realtimetranslate.translations;

public class UnsuccessfulYandexTranslation extends YandexTranslation {

	private String message;

	public UnsuccessfulYandexTranslation(int errorCode, String message)
	{
		super(errorCode);
		this.message = message;
	}

	public String getMessage()
	{
		return message;
	}

	@Override
	public String toString()
	{
		return errorCode + ": " + message;
	}
}
