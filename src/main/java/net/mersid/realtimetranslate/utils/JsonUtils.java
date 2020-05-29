package net.mersid.realtimetranslate.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public enum JsonUtils {
	;

	public static final Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
}
