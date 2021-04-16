package fr.eni.javaee.appliencheres.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
 * Servlet implementation class ModificationUtilisateurServlet
 */
@WebServlet("/modificationUtilisateur")
public class ModificationUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
		public static final String champ_pseudo = "pseudo";
		public static final String champ_nom = "nom";
		public static final String champ_prenom = "prenom";
		public static final String champ_email = "email";
		public static final String champ_telephone = "telephone";
		public static final String champ_rue = "rue";
		public static final String champ_cp = "cp";
		public static final String champ_ville = "ville";
		public static final String champ_password = "passwordNew";
		public static final String champ_confirmation = "confirmation";
		public static final String inscription_erreur = "erreur";
		public static final String inscription_resultat = "resultat";
		
    public ModificationUtilisateurServlet() {
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
		
		//Mise en place des paramètres pour récupérer les erreurs
		HttpSession session = request.getSession();
		Utilisateurs user = (Utilisateurs) session.getAttribute("user");
		String resultat;
		Map<String,String> erreur= new HashMap<String, String>();
		
		//Récupération des données du formulaire 
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
			presenceParam(pseudo);
			conformiteParam(pseudo);
		}catch(Exception ex){
			erreur.put(champ_pseudo, ex.getMessage());
		}
		
		
		try {
			presenceParam(nom);
		}catch(Exception ex){
			erreur.put(champ_nom, ex.getMessage());
		}
			
		try {	
			presenceParam(prenom);
		}catch(Exception ex){
			erreur.put(champ_prenom, ex.getMessage());
		}
		
		try {	
			presenceParam(email);
		}catch(Exception ex){
			erreur.put(champ_email, ex.getMessage());
		}
		
		try {	
			presenceParam(telephone);
		}catch(Exception ex){
			erreur.put(champ_telephone, ex.getMessage());
		}
		
		try {	
			presenceParam(rue);
		}catch(Exception ex){
			erreur.put(champ_rue, ex.getMessage());
		}
		
		try {	
			presenceParam(cp);
		}catch(Exception ex){
			erreur.put(champ_cp, ex.getMessage());
		}
		try {	
			presenceParam(ville);
		}catch(Exception ex){
			erreur.put(champ_ville, ex.getMessage());
		}
		
		//Validation du mot de passe
		try {
			presenceMdp(password);
			validerMotDePasse(password, confirmation);
		}catch(Exception ex){
			erreur.put(champ_password, ex.getMessage());
		}
		
		if(erreur.isEmpty()) {
			//Affecter les donnees des champs au user de la session
			user.setPseudo(pseudo);
			user.setNom(nom);
			user.setPrenom(prenom);
			user.setEmail(email);
			user.setTelephone(telephone);
			user.setRue(rue);
			user.setCode_postal(cp);
			user.setVille(ville);
			user.setMot_de_passe(password);
			
			//envoyer au manager
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			try {
				utilisateurManager.modifierUnUtilisateur(user);
				//si traitement bll dal ok renvoi vers page affichage(pour les tests)
				request.setAttribute("modification_utilisateur", user);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/afficherUtilisateur.jsp");
				rd.forward(request, response);
			}catch(Exception ex){
				//si pb manager ou rqt
				ex.printStackTrace();
				resultat = ex.getMessage();
				request.setAttribute(inscription_resultat, resultat);
				RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/connexionUtilisateur.jsp");
				rd.forward(request, response);
			}
			//si erreur not empty, renvoi des erreurs de conformité
			}else {
				//renvoie les erreurs de la servlet
				resultat = "Echec de l'inscription";
				request.setAttribute(inscription_erreur, erreur);
				request.setAttribute(inscription_resultat, resultat);
				
				RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/modificationUtilisateur.jsp");
				rd.forward(request, response);
			}
		}
	
	
	//Méthodes de validation des informations
	private void presenceParam(String donnee) throws Exception {
		if(donnee == null || donnee.trim().length()==0 ) {
			throw new Exception("Le champ est vide, veuillez le compléter");
		}
	}
	
	private void conformiteParam(String donnee) throws Exception {
	String regExpression="[a-zA-Z_0-9]*";
	if(!donnee.matches(regExpression)) {
		throw new Exception("Le champ ne doit contenir que des caractères alphanumériques");
		}
	}
	private void presenceMdp(String donnee) throws Exception {
		if(donnee.trim().length()<8 ) {
			throw new Exception("Le champ doit comprendre 8 caractères");
		}
	}
	private void validerMotDePasse(String password, String confirmation) throws Exception {
			if (!password.equals(confirmation)) {
				throw new Exception("Mots de passe non identiques");
		}
	}
}
