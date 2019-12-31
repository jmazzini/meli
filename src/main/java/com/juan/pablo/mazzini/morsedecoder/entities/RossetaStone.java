package com.juan.pablo.mazzini.morsedecoder.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RossetaStone {
	public static final Map<String, String> morseAlpha = new HashMap<String, String>();
	public static Map<String, String> alphaMorse = new HashMap<String, String>();
	static {
		morseAlpha.put(".-", "A");
		morseAlpha.put("-...", "B");
		morseAlpha.put("-.-.", "C");
		morseAlpha.put("-..", "D");
		morseAlpha.put(".", "E");
		morseAlpha.put("..-.", "F");
		morseAlpha.put("--.", "G");
		morseAlpha.put("....", "H");
		morseAlpha.put("..", "I");
		morseAlpha.put(".---", "J");
		morseAlpha.put("-.-", "K");
		morseAlpha.put(".-..", "L");
		morseAlpha.put("--", "M");
		morseAlpha.put("-.", "N");
		morseAlpha.put("---", "O");
		morseAlpha.put(".--.", "P");
		morseAlpha.put("--.-", "Q");
		morseAlpha.put(".-.", "R");
		morseAlpha.put("...", "S");
		morseAlpha.put("-", "T");
		morseAlpha.put("..-", "U");
		morseAlpha.put("...-", "V");
		morseAlpha.put(".--", "W");
		morseAlpha.put("-..-", "X");
		morseAlpha.put("-.--", "Y");
		morseAlpha.put("--..", "Z");
		morseAlpha.put("-----", "0");
		morseAlpha.put(".----", "1");
		morseAlpha.put("..---", "2");
		morseAlpha.put("...--", "3");
		morseAlpha.put("....-", "4");
		morseAlpha.put(".....", "5");
		morseAlpha.put("-....", "6");
		morseAlpha.put("--...", "7");
		morseAlpha.put("---..", "8");
		morseAlpha.put("----.", "9");
		alphaMorse = morseAlpha.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
	}

}
