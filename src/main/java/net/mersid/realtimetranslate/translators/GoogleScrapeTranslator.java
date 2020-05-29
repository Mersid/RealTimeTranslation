package net.mersid.realtimetranslate.translators;

import net.mersid.realtimetranslate.translations.GoogleScrapeTranslation;
import net.mersid.realtimetranslate.translations.SuccessfulGoogleScrapeTranslation;
import net.mersid.realtimetranslate.translations.UnsuccessfulGoogleScrapeTranslation;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GoogleScrapeTranslator {
	private final HttpClient httpClient;
	private final String endpoint;

	private Locale sourceLang; // Can be null for auto detect.
	private Locale destLang;

	public GoogleScrapeTranslator(Locale sourceLang, Locale destLang)
	{
		this.httpClient = HttpClients.createDefault();
		this.endpoint = "https://translate.google.com/m";

		this.sourceLang = sourceLang;
		this.destLang = destLang;
	}

	public GoogleScrapeTranslation getTranslation(String translate)
	{
		GoogleScrapeTranslation translation = getTranslationPageHtml(sourceLang, destLang, translate);
		SuccessfulGoogleScrapeTranslation successfulTranslation = convertTranslationText(translation);

		if (successfulTranslation == null)
			return translation;

		return successfulTranslation;
	}

	// Converts full HTML in a Google translation model into a translated string.
	// Returns null if the model contains a failed translation.
	private SuccessfulGoogleScrapeTranslation convertTranslationText(GoogleScrapeTranslation translation)
	{
		if (!translation.wasSuccessful())
			return null;

		SuccessfulGoogleScrapeTranslation successfulTranslation = translation.getSuccessfulTranslation();

		Matcher matcher = Pattern.compile("(?<=<div dir=\"ltr\" class=\"t0\">).*?(?=<\\/div>)").matcher(successfulTranslation.getText()); // Escaped HTML of translation as matcher
		matcher.find();
		successfulTranslation.setText(StringEscapeUtils.unescapeHtml4(matcher.group())); // matcher.group() returns escaped html.

		return successfulTranslation;
	}

	// Success translation model returned here has full html as well. Remove it in next stage.
	private GoogleScrapeTranslation getTranslationPageHtml(Locale sourceLang, Locale destLang, String translate)
	{
		try
		{
			RequestBuilder requestBuilder = RequestBuilder.get()
					.setUri(endpoint)
					.addParameter("hl", "en")
					.addParameter("tl", destLang.getLanguage())
					.addParameter("ie", "UTF-8")
					.addParameter("prev", "_m")
					.addParameter("q", translate);

			if (sourceLang != null)
				requestBuilder.addParameter("sl", sourceLang.getLanguage());

			HttpUriRequest httpUriRequest = requestBuilder.build();

			HttpResponse response = httpClient.execute(httpUriRequest);

			int errorCode = response.getStatusLine().getStatusCode();
			String responseString = EntityUtils.toString(response.getEntity()); // JSON encoded return value
			GoogleScrapeTranslation translation;
			if (errorCode == 200)
				translation = new SuccessfulGoogleScrapeTranslation(errorCode, responseString);
			else
				translation = new UnsuccessfulGoogleScrapeTranslation(errorCode, responseString);

			return translation;
		} catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public void setSourceLang(Locale locale)
	{
		this.sourceLang = locale;
	}

	public void setDestLang(Locale locale)
	{
		this.destLang = locale;
	}
}
