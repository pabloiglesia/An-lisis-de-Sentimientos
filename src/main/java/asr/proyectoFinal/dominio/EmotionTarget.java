package asr.proyectoFinal.dominio;

import com.ibm.watson.natural_language_understanding.v1.model.TargetedEmotionResults;

public class EmotionTarget {
	
	private String targetName;
	private Emotion targetResults;
	
	public EmotionTarget(TargetedEmotionResults target) {
		this.targetName = target.getText();
		this.targetResults = new Emotion(target.getEmotion());
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public Emotion getTargetResults() {
		return targetResults;
	}

	public void setTargetResults(Emotion targetResults) {
		this.targetResults = targetResults;
	}

	@Override
	public String toString() {
		return "EmotionTarget [targetName=" + targetName + ", targetResults=" + targetResults + "]";
	}
}
