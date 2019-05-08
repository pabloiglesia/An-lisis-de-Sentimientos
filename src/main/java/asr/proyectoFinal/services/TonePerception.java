package asr.proyectoFinal.services;

import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneOptions;

public class TonePerception {
	
	private ToneAnalysis toneAnalysis;
	private String text;

	public TonePerception(String text) {
		
		ToneAnalyzer toneAnalyzer = getAuthenticationOptions();
		ToneOptions toneOptions = new ToneOptions.Builder()
				  .text(text)
				  .sentences(true)
				  .build();
				
		ToneAnalysis response = toneAnalyzer.tone(toneOptions).execute();
		this.setToneAnalysis(response);
		//this.setText(text);
	}

	private ToneAnalyzer getAuthenticationOptions() {
		IamOptions options = new IamOptions.Builder()
				  .apiKey("mHumGjNfgNzIvf5KHMyVNobo0SqS9obmjvHL-LrXGHO4")
				  .build();

		ToneAnalyzer toneAnalyzer = new ToneAnalyzer("2016-10-19", options);
		toneAnalyzer.setEndPoint("https://gateway-lon.watsonplatform.net/tone-analyzer/api");
		return toneAnalyzer;
	}
	
	// GETTERS AND SETTERS

	public ToneAnalysis getToneAnalysis() {
		return toneAnalysis;
	}

	public void setToneAnalysis(ToneAnalysis toneAnalysis) {
		this.toneAnalysis = toneAnalysis;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	// END GETTERS AND SETTERS

}


