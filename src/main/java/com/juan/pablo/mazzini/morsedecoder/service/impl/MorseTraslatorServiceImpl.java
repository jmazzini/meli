package com.juan.pablo.mazzini.morsedecoder.service.impl;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.juan.pablo.mazzini.morsedecoder.entities.RossetaStone;
import com.juan.pablo.mazzini.morsedecoder.service.MorseTraslatorService;

@Service
public class MorseTraslatorServiceImpl implements MorseTraslatorService {

//	There are rules to help people distinguish dots from dashes in Morse code.
//	The length of a dot is 1 time unit.
//	A dash is 3 time units.
//	The space between symbols (dots and dashes) of the same letter is 1 time unit.
//	The space between letters is 3 time units.
//	The space between words is 7 time units.

	private static final String DOT = ".";
	private static final String DASH = "-";
	private static final String SPACE = " ";
	private static final String DOBLE_SPACE = SPACE + SPACE;
	private static final String UNKNOWN_CHARACTER = "[INVALID]";
	private static final String EMPTY = "";

	@Override
	public String decodeBits2Morse(String message) {

		List<Map.Entry<Integer, Integer>> messageCompressed = compress(message);
		Integer maxDashSize = messageCompressed.stream().filter(f -> f.getKey() == 1).max(Map.Entry.comparingByValue())
				.get().getValue();
		Integer limitDashDot = (maxDashSize / 3) + (maxDashSize / 6); // Limit intelegible from dash to dot
		Integer limitSpaceBetweenLetters = limitDashDot; // same than dash dot
		Integer limitSpaceBetweenWords = limitDashDot * 7;

		String messageAsMorseLanguage = "";

		for (Entry<Integer, Integer> entry : messageCompressed) {
			if (entry.getKey().equals(1)) {
				if (entry.getValue() <= limitDashDot)
					messageAsMorseLanguage = messageAsMorseLanguage + DOT;
				else
					messageAsMorseLanguage = messageAsMorseLanguage + DASH;
			} else {
				if (entry.getValue() > limitSpaceBetweenWords) {// between words
					messageAsMorseLanguage = messageAsMorseLanguage + DOBLE_SPACE;
				} else if (entry.getValue() > limitSpaceBetweenLetters)// space between letters
					messageAsMorseLanguage = messageAsMorseLanguage + SPACE;
			}
		}

		return messageAsMorseLanguage.trim();
	}

	@Override
	public String translate2Human(String message) {
		List<String> characterArr = Arrays.asList(message.split(SPACE));
		String messageAsHumanLanguage = "";
		for (String character : characterArr) {
			if (!character.isEmpty()) {
				String alfaCharacter = RossetaStone.morseAlpha.get(character);
				messageAsHumanLanguage = messageAsHumanLanguage
						.concat(alfaCharacter == null ? UNKNOWN_CHARACTER : alfaCharacter);
			} else {
				messageAsHumanLanguage = concatSpace(messageAsHumanLanguage);
			}
		}
		return messageAsHumanLanguage;

	}

	private String concatSpace(String messageAsHumanLanguage) {
		return messageAsHumanLanguage.concat(SPACE);
	}

	@Override
	public String translate2Morse(String message) {
		List<Character> charArr = message.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
		String messageAsMorseLanguage = "";
		for (Character character : charArr) {
			if (!character.toString().equals(SPACE)) {
				String morseCharacter = RossetaStone.alphaMorse.get(character.toString().toUpperCase());
				if (morseCharacter == null)
					return EMPTY;
				messageAsMorseLanguage = messageAsMorseLanguage.concat(morseCharacter);
			}
			messageAsMorseLanguage = concatSpace(messageAsMorseLanguage);
		}
		return messageAsMorseLanguage.trim();
	}

	private List<Map.Entry<Integer, Integer>> compress(String message) {
		List<Map.Entry<Integer, Integer>> messageCompressed = new ArrayList<>();
		List<Character> bitArr = message.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
		int counter = 0;
		int zeros = 0;
		Character lastCaracter = 0;
		for (Character bit : bitArr) {
			if (bit.equals('1')) {
				counter++;
			} else {
				if (lastCaracter.equals('1')) {
					Map.Entry<Integer, Integer> pair1 = new AbstractMap.SimpleEntry<>(1, counter);
					messageCompressed.add(pair1);
					counter = 0;
				}
			}

			if (bit.equals('0')) {
				zeros++;
			} else {
				if (lastCaracter.equals('0')) {
					Map.Entry<Integer, Integer> pair2 = new AbstractMap.SimpleEntry<>(0, zeros);
					messageCompressed.add(pair2);
					zeros = 0;
				}
			}
			lastCaracter = bit;
		}
		return messageCompressed;

	}

}
