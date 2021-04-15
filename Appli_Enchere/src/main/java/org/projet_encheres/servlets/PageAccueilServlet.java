package org.projet_encheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.projet_encheres.bll.ArticlesManager;
import org.projet_encheres.bll.CategoriesManager;
import org.projet_encheres.bo.Articles;
import org.projet_encheres.bo.Categories;
import org.projet_encheres.bo.Utilisateurs;

/**
 * Servlet implementation class PageAccueilServlet
 */
//@WebServlet(urlPatterns = {"/accueil", "/index"})
public class PageAccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();	
		String url = request.getServletPath();
		LocalDate jour = LocalDate.now();
		
		String filtreNom = new String();
		int filtreCategorie = 0;
		
		if(session.getAttribute("user")!=null && session.getAttribute("user")!="") {
			Utilisateurs user = (Utilisateurs)session.getAttribute("user");
			request.setAttribute("user", user);
			System.out.println(user);
		}
		if(session.getAttribute("filtreNom")!=null && session.getAttribute("filtreNom")!="") {
			filtreNom = (String) request.getAttribute("filtreNom");
		}
		if(session.getAttribute("filtreCategorie")!=null && session.getAttribute("filtreCategorie")!="") {
			filtreCategorie = (Integer)request.getAttribute("filtreCategorie");
		}
		
		
		CategoriesManager categoriesManager = new CategoriesManager();
		List<Categories> listDeToutesLesCategories = categoriesManager.recupererListeDeToutesLesCategories();
		ArticlesManager articleManager = new ArticlesManager();
		List<Articles> listDeTousLesArticles = articleManager.recupererListeDeTousLesArticles();
		//System.out.println(listDeTousLesArticles);
		
		request.setAttribute("filtreNom", filtreNom);
		request.setAttribute("jour", jour);
		request.setAttribute("filtreCategorie", filtreCategorie);
		
		request.setAttribute("articles", listDeTousLesArticles);
		request.setAttribute("categories", listDeToutesLesCategories);
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
