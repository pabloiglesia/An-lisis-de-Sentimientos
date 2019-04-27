package asr.proyectoFinal.services;

import com.ibm.cloud.sdk.core.service.security.IamOptions;
import com.ibm.watson.language_translator.v3.LanguageTranslator;
import com.ibm.watson.language_translator.v3.model.TranslateOptions;
import com.ibm.watson.language_translator.v3.model.TranslationResult;
import com.ibm.watson.language_translator.v3.util.Language;

public class Translator
{
	public static String translate(String texto, String source, String target)
	{
		IamOptions iamOptions = new IamOptions.Builder()
				  .apiKey("y_SdizANmBhTex3ex5LKGIiCq1uNFv3J_9OaLpJLRQBR")
				  .build();
		
		LanguageTranslator service = new LanguageTranslator("2018-10-21");
		service.setEndPoint("https://gateway-lon.watsonplatform.net/language-translator/api");
		service.setIamCredentials(iamOptions);

		TranslateOptions translateOptions = new TranslateOptions.Builder()
		  .addText(texto)
		  .source(source)
		  .target(target)
		  .build();
		
		String translationResult = service.translate(translateOptions).execute().getResult().getTranslations().get(0).getTranslationOutput();

		return translationResult;
	}
}