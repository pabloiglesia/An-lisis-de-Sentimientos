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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.watson.natural_language_understanding.v1.model.EmotionResult;
import com.ibm.watson.natural_language_understanding.v1.model.TargetedEmotionResults;

import asr.proyectoFinal.dao.CloudantPalabraStore;
import asr.proyectoFinal.dominio.EmotionAnalysis;
import asr.proyectoFinal.dominio.Palabra;
import asr.proyectoFinal.services.LanguageUnderstanding;
import asr.proyectoFinal.services.Translator;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = {"/listar", "/insertar", "/hablar"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		PrintWriter out = response.getWriter();
		out.println("<html><head><meta charset=\"UTF-8\"></head><body>");
		
		CloudantPalabraStore store = new CloudantPalabraStore();
		System.out.println(request.getServletPath());
		switch(request.getServletPath())
		{
			case "/listar":
				if(store.getDB() == null)
					  out.println("No hay DB");
				else
					out.println("Palabras en la BD Cloudant:<br />" + store.getAll());
				break;
				
			case "/insertar":
				Palabra palabra = new Palabra();
				String parametro = request.getParameter("palabra");

				if(parametro==null)
				{
					out.println("usage: /insertar?palabra=palabra_a_traducir");
				}
				else
				{
					if(store.getDB() == null) 
					{
						out.println(String.format("Palabra: %s", palabra));
					}
					else
					{
						palabra.setName(parametro);
						store.persist(palabra);
					    out.println(String.format("Almacenada la palabra: %s", palabra.getName()));			    	  
					}
				}
				break;
		}
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LanguageUnderstanding languageUndestanding = new LanguageUnderstanding("www.pabloiglesia.com",true);
		String texto = languageUndestanding.getText();
		String traduccion = Translator.translate(texto, "es", "en");
		
		ArrayList<String> targets = new ArrayList<String>();
		targets.add("BBVA");
		targets.add("finance");
		LanguageUnderstanding lu = new LanguageUnderstanding(traduccion, targets);
				
		new EmotionAnalysis(lu);
		
		PrintWriter out = response.getWriter();
		out.println(lu.getAnalysisResults().getCategories());

	}

}
