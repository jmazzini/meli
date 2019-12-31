package com.juan.pablo.mazzini.morsedecoder.service;

public interface MorseTraslatorService {
	
	public String decodeBits2Morse(String message);
	public String translate2Human(String message);
	public String translate2Morse(String message);

}
