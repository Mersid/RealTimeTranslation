package net.mersid.realtimetranslate.translations;

import com.google.gson.annotations.SerializedName;

public abstract class Translation {

	@SerializedName("code")
	protected int errorCode = 0;

	public Translation(int errorCode)
	{
		this.errorCode = errorCode;
	}

	public int getErrorCode()
	{
		return errorCode;
	}

	public boolean wasSuccessful()
	{
		return errorCode == 200;
	}


}
