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
import com.ibm.watson.natural_language_understanding.v1.model.KeywordsOptions;
import com.ibm.watson.natural_language_understanding.v1.model.SentimentOptions;

public class LanguageUnderstanding {
	
	private AnalysisResults analysisResults;
	private String text;
	private String company;
	private String candidate;

	public LanguageUnderstanding(String text, ArrayList<String> targets, String candidate) {
		
		NaturalLanguageUnderstanding naturalLanguageUnderstanding = getAuthenticationOptions();
		
		Features features = getFeatures(targets);

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
		this.setCompany(targets.get(0));
		this.setCandidate(candidate);
	}

	public LanguageUnderstanding(String url, boolean clean, ArrayList<String> targets, String candidate) {

		NaturalLanguageUnderstanding naturalLanguageUnderstanding = getAuthenticationOptions();

		Features features = getFeatures(targets);

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
		this.setCompany(targets.get(0));
		this.setCandidate(candidate);
	}
	
	private NaturalLanguageUnderstanding getAuthenticationOptions() {
		IamOptions options = new IamOptions.Builder()
			    .apiKey("0KwV8-_wHfYadUk2eeOHJYfsShc3wHOpUqaTjl1xzOOl")
			    .build();

		NaturalLanguageUnderstanding naturalLanguageUnderstanding = new NaturalLanguageUnderstanding("2018-11-16", options);
		naturalLanguageUnderstanding.setEndPoint("https://gateway-lon.watsonplatform.net/natural-language-understanding/api");
		
		return naturalLanguageUnderstanding;
	}
	
	private Features getFeatures(ArrayList<String> targets) {		
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
				
		return features;
	}
	
	// GETTERS AND SETTERS
	public String getCandidate() {
		return candidate;
	}

	public void setCandidate(String candidate) {
		this.candidate = candidate;
	}
	
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
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
