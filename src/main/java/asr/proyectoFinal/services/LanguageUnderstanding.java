package asr.proyectoFinal.services;

import java.awt.List;
import java.util.ArrayList;

import com.ibm.cloud.sdk.core.service.security.IamOptions;
import com.ibm.watson.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.natural_language_understanding.v1.model.CategoriesOptions;
import com.ibm.watson.natural_language_understanding.v1.model.EmotionOptions;
import com.ibm.watson.natural_language_understanding.v1.model.Features;

public class LanguageUnderstanding {
	
	private AnalysisResults analysisResults;
	private String text;

	public LanguageUnderstanding(String text, ArrayList<String> targets) {
		
		NaturalLanguageUnderstanding naturalLanguageUnderstanding = getAuthenticationOptions();
		
		EmotionOptions emotion= new EmotionOptions.Builder()
		  .targets(targets)
		  .build();

		CategoriesOptions categories= new CategoriesOptions.Builder()
		  .limit(3)
		  .build();

		Features features = new Features.Builder()
		  .categories(categories)
		  .emotion(emotion)
		  .build();

		AnalyzeOptions parameters = new AnalyzeOptions.Builder()
		  .html(text)
		  .features(features)
		  .build();

		AnalysisResults response = naturalLanguageUnderstanding
		  .analyze(parameters)
		  .execute()
		  .getResult();
		
		this.setAnalysisResults(response);
		this.setText(text);
	}
	
	public LanguageUnderstanding(String url, boolean clean) {

		NaturalLanguageUnderstanding naturalLanguageUnderstanding = getAuthenticationOptions();

		CategoriesOptions categories= new CategoriesOptions.Builder()
		  .limit(3)
		  .build();

		Features features = new Features.Builder()
		  .categories(categories)
		  .build();

		AnalyzeOptions parameters = new AnalyzeOptions.Builder()
		  .url(url)
		  .features(features)
		  .clean(clean)
		  .returnAnalyzedText(true)
		  .build();

		AnalysisResults analysisResponse = naturalLanguageUnderstanding
		  .analyze(parameters)
		  .execute()
		  .getResult();
		
		this.setAnalysisResults(analysisResponse);
		this.setText(analysisResponse.getAnalyzedText());
	}
	
	private NaturalLanguageUnderstanding getAuthenticationOptions() {
		IamOptions options = new IamOptions.Builder()
			    .apiKey("0KwV8-_wHfYadUk2eeOHJYfsShc3wHOpUqaTjl1xzOOl")
			    .build();

		NaturalLanguageUnderstanding naturalLanguageUnderstanding = new NaturalLanguageUnderstanding("2018-11-16", options);
		naturalLanguageUnderstanding.setEndPoint("https://gateway-lon.watsonplatform.net/natural-language-understanding/api");
		
		return naturalLanguageUnderstanding;
	}
	
	// GETTERS AND SETTERS

	public AnalysisResults getAnalysisResults() {
		return analysisResults;
	}

	public void setAnalysisResults(AnalysisResults analysisResults) {
		this.analysisResults = analysisResults;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	// END GETTERS AND SETTERS

}
