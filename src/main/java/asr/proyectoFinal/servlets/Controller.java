package asr.proyectoFinal.servlets;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.cloud.sdk.core.service.security.IamOptions;
import com.ibm.watson.natural_language_understanding.v1.model.CategoriesResult;
import com.ibm.watson.natural_language_understanding.v1.model.EmotionResult;
import com.ibm.watson.natural_language_understanding.v1.model.TargetedEmotionResults;
import com.ibm.watson.personality_insights.v3.PersonalityInsights;
import com.ibm.watson.personality_insights.v3.model.Profile;
import com.ibm.watson.personality_insights.v3.model.ProfileOptions;

import asr.proyectoFinal.dao.CloudantEmotionAnalysisStore;
import asr.proyectoFinal.dominio.EmotionAnalysis;
import asr.proyectoFinal.dominio.Palabra;
import asr.proyectoFinal.services.LanguageUnderstanding;
import asr.proyectoFinal.services.PersonalityInsight;
import asr.proyectoFinal.services.TonePerception;
import asr.proyectoFinal.services.Translator;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = {"/list/", "/insert/", "/translate/"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		CloudantEmotionAnalysisStore store = new CloudantEmotionAnalysisStore();
		System.out.println(request.getServletPath());
		
		switch(request.getServletPath())
		{
			case "/list/":
				String id = request.getParameter("id");
				if(id == null) {
					request.getSession().setAttribute("record", store.getAll().iterator());
					RequestDispatcher rd = request.getRequestDispatcher("/list.jsp");
					rd.forward(request, response);
				} else {
					EmotionAnalysis analysis = store.get(id);
					System.out.println("The id is: " + id);
					request.getSession().setAttribute("analysis", analysis);
					RequestDispatcher rd = request.getRequestDispatcher("/results.jsp");
					rd.forward(request, response);
				}
			break;
			case "/insert/":
				System.out.println("Entrando en insert");
				String text = request.getParameter("text");
				String language = request.getParameter("language");
				String[] keywords = request.getParameter("keywords").split(",");

				ArrayList<String> targets = new ArrayList<String>();
				for (int i=0; i<keywords.length; i++)
					targets.add(Translator.translate(keywords[i], language, "en"));
				
				text = Translator.translate(text, language, "en");
				LanguageUnderstanding lu = new LanguageUnderstanding(text, targets);
				TonePerception tp= new TonePerception(text);
				PersonalityInsight pi = new PersonalityInsight(text);
				EmotionAnalysis analysis = store.persist(new EmotionAnalysis(lu, pi, tp));
			break;	
				
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		CloudantEmotionAnalysisStore store = new CloudantEmotionAnalysisStore();
		System.out.println(request.getServletPath());
		
		String text;
		String language;
		EmotionAnalysis analysis;
		switch(request.getServletPath())
		{
			case "/insert/":
				System.out.println("Entrando en insert");
				text = request.getParameter("text");
				language = request.getParameter("language");
				String[] keywords = request.getParameter("keywords").split(",");

				ArrayList<String> targets = new ArrayList<String>();
				for (int i=0; i<keywords.length; i++)
					targets.add(Translator.translate(keywords[i], language, "en"));
				
				text = Translator.translate(text, language, "en");
				LanguageUnderstanding lu = new LanguageUnderstanding(text, targets);
				PersonalityInsight pi = new PersonalityInsight(text);
				TonePerception tp= new TonePerception(text);
				
				analysis = store.persist(new EmotionAnalysis(lu,pi,tp));
				
				response.sendRedirect("/asrProyectoFinal/list/?id="+analysis.get_id());
				break;
		
			case "/translate/":
				
				String id = request.getParameter("id");
				language = request.getParameter("language");
				analysis = store.get(id);
				analysis.translate(language);
								
				store.update(id, analysis);
				
				response.sendRedirect("/asrProyectoFinal/list/?id="+analysis.get_id());
				break;
		}	

	}

}
