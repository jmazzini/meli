package com.juan.pablo.mazzini.morsedecoder.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juan.pablo.mazzini.morsedecoder.dto.TranslatorRequest;
import com.juan.pablo.mazzini.morsedecoder.dto.TranslatorResponse;
import com.juan.pablo.mazzini.morsedecoder.entities.Language;
import com.juan.pablo.mazzini.morsedecoder.service.MorseTraslatorService;

@RestController
@RequestMapping("/translate")
public class MorseTranslatorController {
	
	@Autowired
	private MorseTraslatorService morseTraslatorService;
	
	@PostMapping("/2textfrombinary")
	public TranslatorResponse decodeBits2Morse(@RequestBody TranslatorRequest request) {
		return translate(request,Language.BINARY);
	}
	
	@PostMapping("/2text")
	 public TranslatorResponse translate2text(@RequestBody TranslatorRequest request){
		 return translate(request,Language.ALPHA);
	 }
	
	@PostMapping("/2morse")
	 public TranslatorResponse translate2morse(@RequestBody TranslatorRequest request) throws Exception{
	  return translate(request,Language.MORSE);
	 }
	
	private TranslatorResponse translate(TranslatorRequest request,Language lang) {
		String response ="";
		switch (lang) {
		case ALPHA:
			response =morseTraslatorService.translate2Human(request.getText());
			break;
		case MORSE:
			response =morseTraslatorService.translate2Morse(request.getText());
			break;
		case BINARY:
			response =morseTraslatorService.translate2Human(morseTraslatorService.decodeBits2Morse(request.getText()));
			break;
		default:
			return new TranslatorResponse(500,"Not implemented");
		}	
		
		return new TranslatorResponse(200,response);
	}
	

}
