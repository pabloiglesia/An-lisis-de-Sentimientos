package asr.proyectoFinal.dominio;

import java.util.ArrayList;
import java.util.List;

import com.ibm.watson.natural_language_understanding.v1.model.CategoriesResult;

import asr.proyectoFinal.services.LanguageUnderstanding;

public class EmotionAnalysis {
	
	// Cloudant
	private String _id;
	private String _rev;
	// End Cloudant

	private String text;
	private List<CategoriesResult> categories;
	private Emotion generalResults;
	private ArrayList<EmotionTarget> targetResults;
	
	public EmotionAnalysis(LanguageUnderstanding analysis) {
		this.setCategories(analysis.getAnalysisResults().getCategories());
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
	
	public List<CategoriesResult> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoriesResult> list) {
		this.categories = list;
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
		return "EmotionAnalysis [_id=" + _id + ", _rev=" + _rev + ", categories=" + categories
				+ ", generalResults=" + generalResults + ", targetResults=" + targetResults + "]";
	}

}
