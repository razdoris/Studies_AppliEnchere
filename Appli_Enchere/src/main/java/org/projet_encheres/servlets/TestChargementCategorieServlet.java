package org.projet_encheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.projet_encheres.bll.ArticlesManager;
import org.projet_encheres.bll.CategoriesManager;
import org.projet_encheres.bo.Articles;
import org.projet_encheres.bo.Categories;

/**
 * Servlet implementation class ChargementCategorieServlet
 */
@WebServlet("/TestChargementCategorieServlet")
public class TestChargementCategorieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		List<Categories> listDeToutesLesCategories = new ArrayList<>();
		try
		{
			
			CategoriesManager categoriesManager = new CategoriesManager();
			listDeToutesLesCategories = categoriesManager.recupererListeDeToutesLesCategories();
			
			System.out.println("liste articles dans servlet : " + listDeToutesLesCategories);
			
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
			
		} // TODO Auto-generated method stub
		
		this.getServletContext().setAttribute("categorie", listDeToutesLesCategories);
		
		response.getWriter().append("categorie: ").append(listDeToutesLesCategories.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
