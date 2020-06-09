package net.mersid.realtimetranslate.translators;

import com.google.gson.JsonObject;
import net.mersid.realtimetranslate.RealTimeTranslate;
import net.mersid.realtimetranslate.language.Language;
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

import java.io.IOException;
import java.util.function.Consumer;

public class YandexTranslator {
	private final HttpClient httpClient;
	private final String endpoint;

	public YandexTranslator()
	{
		httpClient = HttpClients.createDefault();
		endpoint = "https://translate.yandex.net/api/v1.5/tr.json/translate";
	}

	/**
	 * Gets the appropriate translation. This method makes a REST API call, and may take some time.
	 * @param apiKey Yandex API key
	 * @param sourceLang The source {@link net.mersid.realtimetranslate.language.Language} to translate from, or null for auto
	 * @param destLang The destination {@link net.mersid.realtimetranslate.language.Language} to translate to
	 * @param translate The text to translate
	 * @return An {@link SuccessfulYandexTranslation} if the translation is successful, or an {@link UnsuccessfulYandexTranslation} if it is not.
	 */
	public YandexTranslation getTranslation(String apiKey, Language sourceLang, Language destLang, String translate)
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
	 * The translation key is automatically pulled from {@link RealTimeTranslate#yandexKeyManager}
	 * However, the languages must be supplied manually.
	 *
	 * @param translateText The text to translate
	 * @param source The language to translate from. Use {@link Language#AUTO} for automatic detection
	 * @param dest The language to translate to
	 * @param callback The method to run when the translation is finished. A {@link YandexTranslation}
	 *                 object is provided to obtain relevant values.
	 */
	public void translateWithFunctionAsync(String translateText, Language source, Language dest, Consumer<YandexTranslation> callback)
	{
		RealTimeTranslate.INSTANCE.threadPool.execute(() -> {
			YandexTranslation translation = RealTimeTranslate.INSTANCE.yandexTranslator.getTranslation(
					RealTimeTranslate.INSTANCE.yandexKeyManager.getKey(),
					source,
					dest,
					translateText);

			callback.accept(translation);
		});
	}

	private String getLangPair(Language source, Language dest)
	{
		if (source == null || source.getName().equals("Auto"))
			return dest.getYandexCode();
		return source.getYandexCode() + "-" + dest.getYandexCode();
	}
}
