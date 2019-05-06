package asr.proyectoFinal.dominio;

import java.util.ArrayList;
import java.util.List;

import com.ibm.watson.natural_language_understanding.v1.model.CategoriesResult;
import com.ibm.watson.personality_insights.v3.model.Trait;
import com.ibm.watson.tone_analyzer.v3.model.ToneAnalysis;

import asr.proyectoFinal.services.LanguageUnderstanding;
import asr.proyectoFinal.services.PersonalityInsight;
import asr.proyectoFinal.services.TonePerception;

public class EmotionAnalysis {
	
	// Cloudant
	private String _id;
	private String _rev;
	// End Cloudant

	private String text;
	private List<CategoriesResult> categories;
	private Emotion generalResults;
	private ArrayList<EmotionTarget> targetResults;
	private List<Trait> personalities;
	private ToneAnalysis toneAnalysis;
	
	public EmotionAnalysis(LanguageUnderstanding analysis, PersonalityInsight personalityInsight,TonePerception tonePerception) {
		this.setCategories(analysis.getAnalysisResults().getCategories());
		this.text = analysis.getText();
		this.setGeneralResults(analysis);
		this.setTargetResults(analysis);
		this.setPersonalities(personalityInsight.getProfile().getPersonality());
		
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
	
	public String getTextAsHTML() {
		return escape(text);
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
	
	

	public List<Trait> getPersonalities() {
		return personalities;
	}

	public void setPersonalities(List<Trait> personalities) {
		this.personalities = personalities;
	}

	@Override
	public String toString() {
		return "EmotionAnalysis [_id=" + _id + ", _rev=" + _rev + ", categories=" + categories
				+ ", generalResults=" + generalResults + ", targetResults=" + targetResults + "]";
	}
	
	private static String escape(String s) {
	    StringBuilder builder = new StringBuilder();
	    boolean previousWasASpace = false;
	    for( char c : s.toCharArray() ) {
	        if( c == ' ' ) {
	            if( previousWasASpace ) {
	                builder.append("&nbsp;");
	                previousWasASpace = false;
	                continue;
	            }
	            previousWasASpace = true;
	        } else {
	            previousWasASpace = false;
	        }
	        switch(c) {
	            case '<': builder.append("&lt;"); break;
	            case '>': builder.append("&gt;"); break;
	            case '&': builder.append("&amp;"); break;
	            case '"': builder.append("&quot;"); break;
	            case '\n': builder.append("<br>"); break;
	            // We need Tab support here, because we print StackTraces as HTML
	            case '\t': builder.append("&nbsp; &nbsp; &nbsp;"); break;  
	            default:
	                if( c < 128 ) {
	                    builder.append(c);
	                } else {
	                    builder.append("&#").append((int)c).append(";");
	                }    
	        }
	    }
	    return builder.toString();
	}
}
