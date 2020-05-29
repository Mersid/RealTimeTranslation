package net.mersid.realtimetranslate.kms;

public interface KeyManager {
	String getKey();
	void rotateKey();
	boolean hasValidKeysLeft();
}
