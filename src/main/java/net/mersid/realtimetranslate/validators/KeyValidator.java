package net.mersid.realtimetranslate.validators;

import java.util.List;

@Deprecated
public interface KeyValidator {
	public List<String> getInvalidKeys(List<String> keys);
}
