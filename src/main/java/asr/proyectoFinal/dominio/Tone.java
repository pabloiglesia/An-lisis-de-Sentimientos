/**
 * 
 */
package asr.proyectoFinal.dominio;

import java.util.List;

import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneCategory;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneScore;

/**
 * @author Pilar
 *
 */
public class Tone {

	/**
	 * @param args
	 */
	
	public Tone(ToneScore tone, ToneCategory category)
	{
		
	}
	public List<ToneScore> tones;
	public List<ToneCategory> categories;

	public List<ToneScore> getTones() {
		return tones;
	}

	public void setTones(List<ToneScore> tones) {
		this.tones = tones;
	}
	
	

}
