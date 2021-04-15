package fr.eni.javaee.appliencheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.appliencheres.bo.Utilisateurs;

/**
 * Servlet implementation class AffichageUtilisateurServlet
 */
@WebServlet("/AffichageUtilisateur")
public class AffichageUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AffichageUtilisateurServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateurs user = (Utilisateurs) session.getAttribute("user");
		
		RequestDispatcher rd = null;
		rd=request.getRequestDispatcher("/WEB-INF/jsp/modificationUtilisateur.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
