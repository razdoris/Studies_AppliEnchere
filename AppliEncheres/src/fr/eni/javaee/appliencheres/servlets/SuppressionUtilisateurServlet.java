package fr.eni.javaee.appliencheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.appliencheres.bll.UtilisateurManager;
import fr.eni.javaee.appliencheres.bo.Utilisateurs;

/**
 * Servlet implementation class SuppressionUtilisateurServlet
 */
@WebServlet("/suppressionUtilisateur")
public class SuppressionUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SuppressionUtilisateurServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		rd=request.getRequestDispatcher("/WEB-INF/jsp/modificationUtilisateur.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("test sup1");
		HttpSession session = request.getSession();
		Utilisateurs user = (Utilisateurs) session.getAttribute("user");
		System.out.println(user);
		UtilisateurManager utilisateurManager= new UtilisateurManager();
		Integer noUtilisateur = user.getNo_utilisateur();
		
		try {
			System.out.println("test sup2");
			utilisateurManager.supprimerUtilisateur(noUtilisateur);
			request.getRequestDispatcher("/WEB-INF/jsp/connexionUtilisateur.jsp").forward(request, response);
		}catch(Exception ex){
			System.out.println("test sup X");
				ex.printStackTrace();
				String resultat=ex.getMessage();
				request.setAttribute("resultat", resultat);
				RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/inscriptionUtilisateur.jsp");
				rd.forward(request, response);
			}
	}

}
