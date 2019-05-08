package asr.proyectoFinal.servlets;


import java.io.IOException;
import java.util.ArrayList;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import asr.proyectoFinal.dao.CloudantEmotionAnalysisStore;
import asr.proyectoFinal.dominio.EmotionAnalysis;
import asr.proyectoFinal.services.LanguageUnderstanding;
import asr.proyectoFinal.services.PersonalityInsight;
import asr.proyectoFinal.services.TonePerception;
import asr.proyectoFinal.services.Translator;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = {"/list", "/insert", "/translate"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		CloudantEmotionAnalysisStore store = new CloudantEmotionAnalysisStore();
		System.out.println(request.getServletPath());
		
		switch(request.getServletPath())
		{
			case "/list":
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
			case "/insert":
				text = request.getParameter("text");
				String company = request.getParameter("companyName");
				language = request.getParameter("language");
				String candidate = request.getParameter("candidate");
				String[] keywords = request.getParameter("keywords").split(",");

				ArrayList<String> targets = new ArrayList<String>();
				targets.add(company);
				for (int i=0; i<keywords.length; i++)
					targets.add(Translator.translate(keywords[i], language, "en"));
				
				text = Translator.translate(text, language, "en");
				LanguageUnderstanding lu = new LanguageUnderstanding(text, targets, candidate);
				PersonalityInsight pi = new PersonalityInsight(text);
				TonePerception tp= new TonePerception(text);
				
				analysis = store.persist(new EmotionAnalysis(lu,pi,tp));
				
				response.sendRedirect("/asrProyectoFinal/list?id="+analysis.get_id());
				break;
		
			case "/translate":
				
				String id = request.getParameter("id");
				language = request.getParameter("language");
				analysis = store.get(id);
				analysis.translate(language);
								
				store.update(id, analysis);
				
				response.sendRedirect("/asrProyectoFinal/list?id="+analysis.get_id());
				break;
		}	

	}

}
