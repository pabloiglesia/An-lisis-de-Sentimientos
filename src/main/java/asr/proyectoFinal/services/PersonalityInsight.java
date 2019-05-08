package asr.proyectoFinal.services;

import com.ibm.watson.developer_cloud.personality_insights.v3.PersonalityInsights;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.Profile;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.ProfileOptions;
import com.ibm.watson.developer_cloud.service.security.IamOptions;

public class PersonalityInsight {
	
	private Profile profile;
	private String text;

	public PersonalityInsight(String text) {
		
		PersonalityInsights personalityInsights = getAuthenticationOptions();
		ProfileOptions profileOptions = new ProfileOptions.Builder()
				  .text(text)
				  .rawScores(true)
				  .build();
				
		Profile response = personalityInsights.profile(profileOptions).execute();
		this.setProfile(response);
		//this.setText(text);
	}

	private PersonalityInsights getAuthenticationOptions() {
		IamOptions options = new IamOptions.Builder()
			    .apiKey("8tZesvRsJ53A-s2u_3y6GonlGUgMELs46rgtGXtlhSUM")
			    .build();

		PersonalityInsights personalityInsights = new PersonalityInsights("2016-10-19", options);
		personalityInsights.setEndPoint("https://gateway-lon.watsonplatform.net/personality-insights/api");
		return personalityInsights;
	}
	
	// GETTERS AND SETTERS

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	// END GETTERS AND SETTERS

}

