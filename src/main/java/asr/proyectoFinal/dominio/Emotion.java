package asr.proyectoFinal.dominio;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EmotionScores;

/**
 * Represents a Visitor document stored in Cloudant.
 */

public class Emotion {
	private Double anger;
	private Double disgust;
	private Double fear;
	private Double joy;
	private Double sadness;

	public Emotion(EmotionScores emotion) {
		this.anger = emotion.getAnger();
		this.disgust = emotion.getDisgust();
		this.fear = emotion.getFear();
		this.joy = emotion.getJoy();
		this.sadness = emotion.getSadness();
	}

	public Double getAnger() {
		return anger;
	}

	public void setAnger(Double anger) {
		this.anger = anger;
	}

	public Double getDisgust() {
		return disgust;
	}

	public void setDisgust(Double disgust) {
		this.disgust = disgust;
	}

	public Double getFear() {
		return fear;
	}

	public void setFear(Double fear) {
		this.fear = fear;
	}

	public Double getJoy() {
		return joy;
	}

	public void setJoy(Double joy) {
		this.joy = joy;
	}

	public Double getSadness() {
		return sadness;
	}

	public void setSadness(Double sadness) {
		this.sadness = sadness;
	}

	@Override
	public String toString() {
		return "Emotion [anger=" + anger + ", disgust=" + disgust + ", fear=" + fear + ", joy=" + joy + ", sadness="
				+ sadness + "]";
	}

}