package fr.eni.javaee.appliencheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class page3Servlet
 */
@WebServlet("/inscriptionUtilisateur")
public class InscriptionUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public static final String champ_pseudo = "pseudo";
	public static final String champ_nom = "nom";
	public static final String champ_prenom = "prenom";
	public static final String champ_email = "email";
	public static final String champ_telephone = "telephone";
	public static final String champ_rue = "rue";
	public static final String champ_cp = "cp";
	public static final String champ_ville = "ville";
	public static final String champ_password = "password";
	public static final String champ_confirmation = "confirmation";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		rd=request.getRequestDispatcher("/WEB-INF/jsp/inscriptionUtilisateur.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Récupération des données 
		String pseudo = request.getParameter(champ_pseudo);
		String nom = request.getParameter(champ_nom);
		String prenom = request.getParameter(champ_prenom);
		String email = request.getParameter(champ_email);
		String telephone = request.getParameter(champ_telephone);
		String rue = request.getParameter(champ_rue);
		String cp = request.getParameter(champ_cp);
		String ville = request.getParameter(champ_ville);
		String password = request.getParameter(champ_password);
		String confirmation = request.getParameter(champ_confirmation);
		
		
		
	}
	
	
	private void validerPseudo(String pseudo) {
		//Verifier dans la BDD si adresse mail est présente
				//Renvoyer msg dans JSP si Nok
	}
	
	private void validerEmail (String email) {
		//Verifier dans la BDD si adresse mail est présente
		//Renvoyer msg dans JSP si Nok
	}
	
	//private void validerTelephone (String telephone) {}
	
	private void validerCp (String codePostal) {
		//
	}
	
	private void validerMotDePasse(String password, String confirmation) throws Exception {
		if (password != null && password.trim().length() != 0) {
			if (!password.equals(confirmation)) {
			
				throw new Exception("Mots de passe non identiques, veuillez rééssayer");
			
			}
		}
	}
}



