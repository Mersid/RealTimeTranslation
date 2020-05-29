package net.mersid.realtimetranslate.utils;

import com.google.gson.JsonSyntaxException;
import net.mersid.realtimetranslate.configuration.Configuration;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

public enum ConfigurationUtils {
	;

	public static void saveConfiguration(Configuration configuration, Path path)
	{
		try
		{
			Files.write(path, JsonUtils.gson.toJson(configuration).getBytes());
		} catch (IOException e)
		{
			System.out.println("Error saving configuration to " + path.toString());
			e.printStackTrace();
		}
	}

	public static Configuration loadConfiguration(Path path)
	{
		try
		{
			Reader reader = Files.newBufferedReader(path);
			Configuration configuration = JsonUtils.gson.fromJson(reader, Configuration.class);

			// Saving configuration ensures ALL values are written to the json file.
			// Turns out that missing entries are ok by the json reader (implicit default)
			saveConfiguration(configuration, path);

			return configuration;
		} catch (IOException | JsonSyntaxException ioException)
		{
			System.out.println("Error loading configuration file from " + path.toString() + ". A new one will be created.");
			Configuration newConfiguration = new Configuration();
			saveConfiguration(new Configuration(), path);
			return newConfiguration;
		}
	}
}
