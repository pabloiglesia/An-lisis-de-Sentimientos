package asr.proyectoFinal.dominio;

import java.util.ArrayList;

import asr.proyectoFinal.services.LanguageUnderstanding;

public class EmotionAnalysis {
	
	// Cloudant
	private String _id;
	private String _rev;
	// End Cloudant

	private String text;
	private Emotion generalResults;
	private ArrayList<EmotionTarget> targetResults;
	
	public EmotionAnalysis(LanguageUnderstanding analysis) {
		this.text = analysis.getText();
		this.setGeneralResults(analysis);
		this.setTargetResults(analysis);
	}
	
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String get_rev() {
		return _rev;
	}

	public void set_rev(String _rev) {
		this._rev = _rev;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Emotion getGeneralResults() {
		return generalResults;
	}

	public void setGeneralResults(Emotion generalResults) {
		this.generalResults = generalResults;
	}
	
	public void setGeneralResults(LanguageUnderstanding analysis) {
		this.generalResults = new Emotion(analysis.getAnalysisResults().getEmotion().getDocument().getEmotion());
	}

	public ArrayList<EmotionTarget> getTargetResults() {
		return targetResults;
	}

	public void setTargetResults(ArrayList<EmotionTarget> targetResults) {
		this.targetResults = targetResults;
	}
	
	public void setTargetResults(LanguageUnderstanding analysis) {
		this.targetResults = new ArrayList<EmotionTarget>();
		for(int i = 0; i < analysis.getAnalysisResults().getEmotion().getTargets().size(); i++) {
			this.targetResults.add(new EmotionTarget(analysis.getAnalysisResults().getEmotion().getTargets().get(i)));
		}
	}

	@Override
	public String toString() {
		return "EmotionAnalysis [text=" + text + ", generalResults=" + generalResults + ", targetResults="
				+ targetResults + "]";
	}

}
