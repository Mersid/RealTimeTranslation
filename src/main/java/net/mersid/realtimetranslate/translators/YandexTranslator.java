package net.mersid.realtimetranslate.translators;

import com.google.gson.JsonObject;
import net.mersid.realtimetranslate.RealTimeTranslate;
import net.mersid.realtimetranslate.translations.SuccessfulYandexTranslation;
import net.mersid.realtimetranslate.translations.UnsuccessfulYandexTranslation;
import net.mersid.realtimetranslate.translations.YandexTranslation;
import net.mersid.realtimetranslate.utils.JsonUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Locale;
import java.util.function.Consumer;

public class YandexTranslator {
	private final HttpClient httpClient;
	private final String endpoint;

	public YandexTranslator()
	{
		httpClient = HttpClients.createDefault();
		endpoint = "https://translate.yandex.net/api/v1.5/tr.json/translate";
	}

	public YandexTranslation getTranslation(String apiKey, @Nullable /* Null for auto detect. */ Locale sourceLang, Locale destLang, String translate)
	{
		try
		{
			HttpUriRequest httpRequest = RequestBuilder.get()
					.setUri(endpoint)
					.setHeader("Content-Type", ContentType.APPLICATION_JSON.getMimeType())
					.addParameter("key", apiKey)
					.addParameter("text", translate)
					.addParameter("lang", getLangPair(sourceLang, destLang))
					.build();

			HttpResponse response = httpClient.execute(httpRequest);
			String responseString = EntityUtils.toString(response.getEntity()); // JSON encoded return value

			JsonObject jsonObject = JsonUtils.gson.fromJson(responseString, JsonObject.class);

			if (jsonObject.get("code").getAsInt() == 200)
				return JsonUtils.gson.fromJson(jsonObject, SuccessfulYandexTranslation.class);
			return JsonUtils.gson.fromJson(jsonObject, UnsuccessfulYandexTranslation.class);

		} catch (IOException e) // Probably couldn't connect to server because you got blacklisted for spam
		{
			return null;
		}
	}

	/**
	 * Named terribly... but feed it a string to translate, and a lambda function
	 * to make it the translator perform an action as defined by the function.
	 *
	 * Also, in the callback, it's best to call a key rotate if something goes wrong.
	 **/
	public void translateWithFunctionAsync(String translateText, Consumer<YandexTranslation> callback)
	{
		RealTimeTranslate.INSTANCE.threadPool.execute(() -> {
			YandexTranslation translation = RealTimeTranslate.INSTANCE.yandexTranslator.getTranslation(
					RealTimeTranslate.INSTANCE.yandexKeyManager.getKey(),
					RealTimeTranslate.INSTANCE.configuration.sourceLanguage,
					RealTimeTranslate.INSTANCE.configuration.destinationLanguage,
					translateText);

			callback.accept(translation);
		});
	}

	private String getLangPair(Locale source, Locale dest)
	{
		if (source == null)
			return dest.getLanguage();
		return source.getLanguage() + "-" + dest.getLanguage();
	}
}
