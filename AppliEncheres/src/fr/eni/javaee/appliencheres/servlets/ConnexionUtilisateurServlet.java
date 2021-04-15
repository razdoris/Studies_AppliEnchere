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
 * Servlet implementation class page2Servlet
 */
@WebServlet("/connexionUtilisateur")
public class ConnexionUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static final String inscription_resultat = "resultat";
	
    public ConnexionUtilisateurServlet() {
        super();
         }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		rd=request.getRequestDispatcher("/WEB-INF/jsp/connexionUtilisateur.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resultat;
		String pseudo = request.getParameter("pseudo");
		String password = request.getParameter("password");
		
		request.setAttribute("pseudo", pseudo);
		request.setAttribute("mdp", password);
		
		UtilisateurManager utilisateurManager= new UtilisateurManager();
		
		try {
			System.out.println("etape1");
			Utilisateurs user = utilisateurManager.verifierLoginUtilisateur(pseudo, password);
			if(user==null) {
				System.out.println("etape2");
				resultat="le mdp ne correspond pas au pseudo";
				request.setAttribute("resultat",resultat);
				request.getRequestDispatcher("/WEB-INF/jsp/connexionUtilisateur.jsp").forward(request, response);
			}else {
				System.out.println("etape3");
				HttpSession session = request.getSession(true);
				session.setAttribute("user", user);
				request.getRequestDispatcher("/WEB-INF/jsp/inscriptionUtilisateur.jsp").forward(request, response);
			}
		}catch(Exception ex){
			System.out.println("etape4");
				ex.printStackTrace();
				resultat=ex.getMessage();
				request.setAttribute("resultat", resultat);
				RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/inscriptionUtilisateur.jsp");
				rd.forward(request, response);
			}
				
		
	}

}
