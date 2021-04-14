package fr.eni.javaee.appliencheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class page2Servlet
 */
@WebServlet("/connexionUtilisateur")
public class ConnexionUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ConnexionUtilisateurServlet() {
        super();
         }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		rd=request.getRequestDispatcher("/WEB-INF/jsp/connexionUtilisateur.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("pseudo");
		String password = request.getParameter("password");
		
		request.setAttribute("pseudo", login);
		request.setAttribute("mdp", password);
		
	}

}
