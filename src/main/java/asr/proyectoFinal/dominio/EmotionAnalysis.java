package asr.proyectoFinal.dominio;

import java.util.ArrayList;
import java.util.List;

import com.ibm.watson.natural_language_understanding.v1.model.CategoriesResult;

import asr.proyectoFinal.services.LanguageUnderstanding;
import asr.proyectoFinal.services.Translator;

public class EmotionAnalysis {
	
	// Cloudant
	private String _id;
	private String _rev;
	// End Cloudant

	private String text;
	private String language;
	private List<CategoriesResult> categories;
	private Emotion generalResults;
	private ArrayList<EmotionTarget> targetResults;
	
	
	public EmotionAnalysis(LanguageUnderstanding analysis) {
		this.setCategories(analysis.getAnalysisResults().getCategories());
		this.setText(analysis.getText());
		this.setLanguage(analysis.getAnalysisResults().getLanguage());
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
	
	public String getTextAsHTML() {
		return escape(text);
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
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
	
	public void translate(String language) {
		this.setText(Translator.translate(this.getText(), this.getLanguage(), language));
		this.setLanguage(language);	
	}
}
