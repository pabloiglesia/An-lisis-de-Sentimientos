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
import asr.proyectoFinal.services.Translator;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = {"/list/", "/insert/"})
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
					System.out.println(id);
					request.getSession().setAttribute("analysis", analysis);
					RequestDispatcher rd = request.getRequestDispatcher("/results.jsp");
					rd.forward(request, response);
				}
				break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String text = request.getParameter("text");
		String language = request.getParameter("language");
		ArrayList<String> targets = new ArrayList<String>();
		targets.add("BBVA");
		targets.add("finance");
		targets.add("Marketing");
		targets.add("University");

		String traduccion = Translator.translate(text, language, "en");
		LanguageUnderstanding lu = new LanguageUnderstanding(traduccion, targets);
		
		CloudantEmotionAnalysisStore store = new CloudantEmotionAnalysisStore();
		EmotionAnalysis analysis = store.persist(new EmotionAnalysis(lu));
		
		request.getSession().setAttribute("nombre_param", "valor_param");
		response.sendRedirect("/ejemplo.jsp");
	}

}
