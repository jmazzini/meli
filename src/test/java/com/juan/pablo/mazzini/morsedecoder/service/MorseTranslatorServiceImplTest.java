package com.juan.pablo.mazzini.morsedecoder.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import static org.assertj.core.api.Assertions.assertThat;
import com.juan.pablo.mazzini.morsedecoder.service.impl.MorseTraslatorServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class MorseTranslatorServiceImplTest {
	
	@InjectMocks
	private MorseTraslatorServiceImpl service;
	
	@Test
	public void testHolaMeliBinaryCodeShouldGiveAHolaMeliMorseCode() {
		String messageRequest ="0000000011011011001110000011111100011111100111111000000011101111111101110111000000011000111111000000000000000000000000000000000000000000111111001111110000000110000110111111110111011100000011011100000000000";
		String response =this.service.decodeBits2Morse(messageRequest);
		String humanResponse = this.service.translate2Human(response);
		assertThat("HOLA MELI").isEqualTo(humanResponse);
		assertThat(".... --- .-.. .-  -- . .-.. ..").isEqualTo(response);
	}
	
	@Test
	public void testHolaBinaryCodeShouldGiveAHolaMorseCode() {
		String messageRequest ="000000000000110011011001100000011111110111111111110111111111000000000010111111110110011000000010111111100000000";
		String response =this.service.decodeBits2Morse(messageRequest);		
		String humanResponse = this.service.translate2Human(response);
		assertThat("HOLA").isEqualTo(humanResponse);
		assertThat(".... --- .-.. .-").isEqualTo(response);
	}
	
	@Test
	public void testAnyValidCodeStartinginOnes() {
		String messageRequest ="11111111110101010101010101011111110";
		
		assertThat("-........-").isEqualTo(this.service.decodeBits2Morse(messageRequest));
	}
	
	@Test
	public void testAnyValidCodeWithLongPauseStartEnd() {
		String messageRequest ="00000000000000000000000000000000000000000000000000000000000000000111111111111010101010101010101111111111000000000000000000000000000000000000000000000";
		
		assertThat("-........-").isEqualTo(this.service.decodeBits2Morse(messageRequest));
	}
	
	@Test
	public void testAnyValidCode() {
		String messageRequest ="101010101001110000010000";
		
		assertThat("..... - .").isEqualTo(this.service.decodeBits2Morse(messageRequest));
	}

	@Test
	public void testHolaMeliMorseToHumanOK() {
		String messageRequest =".... --- .-.. .- -- . .-.. ..";
		
		assertThat("HOLAMELI").isEqualTo(this.service.translate2Human(messageRequest));
	}
	
	@Test
	public void holaMeliWithSpaceMorseToHumanOK() {
		String messageRequest =".... --- .-.. .-  -- . .-.. ..";
		
		assertThat("HOLA MELI").isEqualTo(this.service.translate2Human(messageRequest));
	}
	
	@Test
	public void testHolaMeliHolaWithSpacesOK() {
		String messageRequest =".... --- .-.. .-    -- . .-.. ..    .... --- .-.. .-";
		
		assertThat("HOLA   MELI   HOLA").isEqualTo(this.service.translate2Human(messageRequest));
	}
	
	@Test
	public void testAllLettersMorseToHuman() {
		String messageRequest =".- -... -.-. -.. . ..-. --. .... .. .--- -.- .-.. -- -. --- .--. --.- .-. ... - ..- ...- .-- -..- -.-- --..";
		
		assertThat("ABCDEFGHIJKLMNOPQRSTUVWXYZ").isEqualTo(this.service.translate2Human(messageRequest));
	}
	
	@Test
	public void testAllNumbersMorseToHuman() {
		String messageRequest ="----- .---- ..--- ...-- ....- ..... -.... --... ---.. ----.";
		
		assertThat("0123456789").isEqualTo(this.service.translate2Human(messageRequest));
	}
	
	@Test
	public void testMorseToHumanInvalid() {
		String messageRequest ="-.....----";
		
		assertThat("[INVALID]").isEqualTo(this.service.translate2Human(messageRequest));
	}
	
	
	@Test
	public void testHolaMeliAlphatoMorseOK() {
		String messageRequest ="HOLAMELI";
		
		assertThat(".... --- .-.. .- -- . .-.. ..").isEqualTo(this.service.translate2Morse(messageRequest));
	}
	
	@Test
	public void testHolaMeliAlphatoMorseCaseInsensitiveOK() {
		String messageRequest ="holameli";
		
		assertThat(".... --- .-.. .- -- . .-.. ..").isEqualTo(this.service.translate2Morse(messageRequest));
	}
	
	@Test
	public void testHolaMeliWithSpaceAlphatoMorseOK() {
		String messageRequest ="HOLA MELI";
		
		assertThat(".... --- .-.. .-  -- . .-.. ..").isEqualTo(this.service.translate2Morse(messageRequest));
	}
	
	@Test
	public void testHolaMeliWithTripeSpaceAlphatoMorseOk() {
		String messageRequest ="HOLA   MELI";
		
		assertThat(".... --- .-.. .-    -- . .-.. ..").isEqualTo(this.service.translate2Morse(messageRequest));
	}
	
	@Test
	public void testHolaMeliAlphatoMorseInvalid() {
		
		String messageRequest ="HOLA %&#";
		assertThat("").isEqualTo(this.service.translate2Morse(messageRequest));
	}
	
	@Test
	public void testAllLettersAlphaToMorse() {
		String messageRequest ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		assertThat(".- -... -.-. -.. . ..-. --. .... .. .--- -.- .-.. -- -. --- .--. --.- .-. ... - ..- ...- .-- -..- -.-- --..").isEqualTo(this.service.translate2Morse(messageRequest));
	}
	
	@Test
	public void testAllNumbersAlphaToMorse() {
		String messageRequest ="0123456789";
		
		assertThat("----- .---- ..--- ...-- ....- ..... -.... --... ---.. ----.").isEqualTo(this.service.translate2Morse(messageRequest));
	}
	
}