package org.projet_encheres.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.projet_encheres.bll.BLLException;
import org.projet_encheres.bll.UtilisateurManager;

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
	public static final String inscription_erreur = "erreur";
	public static final String inscription_resultat = "resultat";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		rd=request.getRequestDispatcher("/WEB-INF/jsp/inscriptionUtilisateur.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Mise en place des paramètres pour récupérer les erreurs
		String resultat;
		Map<String,String> erreur= new HashMap<String, String>();
		
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
		
		//Validation présence des données
		try {
			validerParam(pseudo);
		}catch(Exception ex){
			erreur.put(champ_pseudo, ex.getMessage());
		}
		
		try {
			validerParam(nom);
		}catch(Exception ex){
			erreur.put(champ_nom, ex.getMessage());
		}
			
		try {	
			validerParam(prenom);
		}catch(Exception ex){
			erreur.put(champ_prenom, ex.getMessage());
		}
		
		try {	
			validerParam(email);
		}catch(Exception ex){
			erreur.put(champ_email, ex.getMessage());
		}
		
		try {	
			validerParam(telephone);
		}catch(Exception ex){
			erreur.put(champ_telephone, ex.getMessage());
		}
		
		try {	
			validerParam(rue);
		}catch(Exception ex){
			erreur.put(champ_rue, ex.getMessage());
		}
		
		try {	
			validerParam(cp);
		}catch(Exception ex){
			erreur.put(champ_cp, ex.getMessage());
		}
		try {	
			validerParam(ville);
		}catch(Exception ex){
			erreur.put(champ_ville, ex.getMessage());
		}
		
		//Validation du mot de passe
		try {	
			validerMotDePasse(password, confirmation);
		}catch(Exception ex){
			erreur.put(champ_password, ex.getMessage());
		}
		
		if(erreur.isEmpty()) {
			
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			try {
				utilisateurManager.ajouterUtilisateur(pseudo, nom, prenom, email, telephone, rue, cp, ville, password);
				RequestDispatcher rd = request.getRequestDispatcher("/accueil.jsp");
				rd.forward(request, response);
			}catch(Exception ex){
				resultat = "Echec de l'inscription";
				request.setAttribute(inscription_erreur, erreur);
				request.setAttribute(inscription_resultat, resultat);
				
				RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/inscriptionUtilisateur.jsp");
				rd.forward(request, response);
			}
		}
	}
	
	//Méthodes de validation des informations
	private void validerParam(String donnee) throws Exception {
		if(donnee == null || donnee.trim().length()==0 ) {
			throw new Exception();
		}
	}
	
	private void validerMotDePasse(String password, String confirmation) throws Exception {
		if (password != null && password.trim().length() != 0) {
			if (!password.equals(confirmation)) {
				throw new Exception("Mots de passe non identiques, veuillez rééssayer");
			}
		}
	}
}



