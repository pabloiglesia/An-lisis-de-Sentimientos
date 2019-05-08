/**
 * 
 */
package asr.proyectoFinal.dominio;

import com.ibm.watson.developer_cloud.personality_insights.v3.model.Profile;

/**
 * @author Pilar
 *
 */
public class Personality {

	/**
	 * @param args
	 */
	private Double extraversion;
	private Double openness;
	private Double conscientiousness;
	private Double agreeableness;
	private Double emotional_range;
	
	
	
	public Personality( Profile profile) {
		this.openness = profile.getPersonality().get(0).getRawScore();
		this.conscientiousness = profile.getPersonality().get(1).getRawScore();
		this.extraversion = profile.getPersonality().get(2).getRawScore();
		this.agreeableness = profile.getPersonality().get(3).getRawScore();
		this.emotional_range = profile.getPersonality().get(4).getRawScore();
	}

	public Double getExtraversion() {
		return extraversion;
	}

	public void setExtraversion(Double extraversion) {
		this.extraversion = extraversion;
	}

	public Double getOpenness() {
		return openness;
	}

	public void setOpenness(Double openness) {
		this.openness = openness;
	}

	public Double getConscientiousness() {
		return conscientiousness;
	}

	public void setConscientiousness(Double conscientiousness) {
		this.conscientiousness = conscientiousness;
	}

	public Double getAgreeableness() {
		return agreeableness;
	}

	public void setAgreeableness(Double agreeableness) {
		this.agreeableness = agreeableness;
	}

	public Double getEmotional_range() {
		return emotional_range;
	}

	public void setEmotional_range(Double emotional_range) {
		this.emotional_range = emotional_range;
	}

	@Override
	public String toString() {
		return "Personality [Openness=" + openness + ", Conscientiousness=" + conscientiousness + ", Extraversion=" + extraversion + ", Agreeableness=" + agreeableness + ", Emotional Range="
				+ emotional_range + "]";
	}
	
	

}