package net.mersid.realtimetranslate.configuration;

import java.util.Arrays;
import java.util.List;

public class Configuration {
	public String incomingSourceLanguage = "Auto";
	public String incomingDestinationLanguage = "English";

	public String outgoingSourceLanguage = "Auto";
	public String outgoingDestinationLanguage = "French";

	public int previewUpdateMillis = 250;

	public List<String> yandexApiKeys = Arrays.asList(
			"trnsl.1.1.20200425T233751Z.fe761e987c4f7c49.5afb3acb0771386cbea720f2fda57c007abba9a5",
			"trnsl.1.1.20200510T211027Z.8261b7143f1792b8.36149b146350a9face6cea101bc6bba74597db66",
			"trnsl.1.1.20200510T224716Z.e03500112e353138.f890398b519fb24ffbdbf97acb89d2c3b868a298",
			"trnsl.1.1.20200510T224923Z.a7495263b82ed147.db1c0e64630961e39417c158005835e9aa980a05",
			"trnsl.1.1.20200510T225044Z.51bbb94daf22147f.3572d3daecdefaa0b43713b40981d99d2b41d426");

	public List<String> regexList = Arrays.asList(
			"<(\\w+)> ",
			"\\(From (\\w+)\\):( )?",
			"(\\w+) whispers ",
			"(\\[\\S+\\]( )?){0,2}(\\w+)( )?Â»( )?",
			"(\\[\\S+\\]( )?){0,2}(\\w+)( )?:( )?(Eye\\[\\d\\] )?",
			"\\d{1,3} (\\w+ )?(\\w+) ",
			"Dead (\\d+ )?(\\w+ )?(\\w+) ",
			"(\\w+) > \\w+ ",
			"(\\w+) whispers to you: ",
			"(\\(Team\\) )?(\\[\\w+\\] ){1,2}(\\w+): ",
			"(\\w+ )?\\w+: (\\w+) > ",
			"\\[Lvl \\d+\\] â–¶ (\\w+: )?(\\w+) > ",
			"â–¶ \\[\\d+\\] (\\w+: )?(\\w+) > ",
			"â–¶ (\\w+: )?(\\w+) > ",
			"(\\[\\w+\\])?\\[Level \\d+\\] \\[(\\w+)\\] ",
			"(\\w+) tells you: ",
			"\\[(\\w+) -> \\w+\\] ",
			"(\\w+ )?(\\w+-)?(\\w+)(\\*)?(\\+){0,2}:"
	);
}
