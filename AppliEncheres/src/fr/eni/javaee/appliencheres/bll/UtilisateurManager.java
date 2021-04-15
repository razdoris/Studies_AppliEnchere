package fr.eni.javaee.appliencheres.bll;

import java.util.List;

import fr.eni.javaee.appliencheres.bo.Utilisateurs;
import fr.eni.javaee.appliencheres.dal.DALException;
import fr.eni.javaee.appliencheres.dal.DAOFactory;
import fr.eni.javaee.appliencheres.dal.UtilisateurDAO;


public class UtilisateurManager {

	private  UtilisateurDAO utilisateurDao;
	public UtilisateurManager(){
		this.utilisateurDao = DAOFactory.getUtilisateurDao();
	}
	
	public Utilisateurs  verifierLoginUtilisateur(String pseudo, String password) throws BLLException {
		System.out.println("manager1");
		Utilisateurs user = null;
		try {
			user = utilisateurDao.selectByLogin(pseudo, password);
		}catch(DALException ex) {
			throw new BLLException("erreur bll");
			}
		return user;
	}
	
	public Utilisateurs ajouterUtilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String code_postal, String ville, String mot_de_passe) throws BLLException {
		System.out.println("manager1");
		
			if(!verifierUnicitePseudo(pseudo).isEmpty()) {
				throw new BLLException("Le pseudo n'est pas disponible");
				}
			
			if(!verifierUniciteEmail(email).isEmpty()) {
				throw new BLLException("Il existe déjà un compte lié à cet email");
				
		}
		Utilisateurs utilisateur =null;
		try {
			utilisateur = new Utilisateurs();
			utilisateur.setPseudo(pseudo);
			utilisateur.setNom(nom);
			utilisateur.setPrenom(prenom);
			utilisateur.setEmail(email);
			utilisateur.setTelephone(telephone);
			utilisateur.setRue(rue);
			utilisateur.setCode_postal(code_postal);
			utilisateur.setVille(ville);
			utilisateur.setMot_de_passe(mot_de_passe);
			utilisateur.setCredit(10);
			utilisateur.setAdministrateur(false);
			this.utilisateurDao.insert(utilisateur);
		
		}catch(DALException ex){
		 throw new BLLException( "Erreur d'intégration");
			}
		
			return utilisateur;
		}
		

	
	public void modifierUnUtilisateur(Utilisateurs utilisateur) throws BLLException {
		try {
			utilisateurDao.update(utilisateur);
		}catch(DALException ex) {
			throw new BLLException("erreur de modification des données");
			}
	}
	
	public Utilisateurs selectionnerUnUtilisateur(Integer no_Utilisateur) throws BLLException {
		Utilisateurs user = null;
		try{
			user=utilisateurDao.selectById(no_Utilisateur);
		}catch(DALException ex) {
			throw new BLLException("erreur de modification des données");
			}
		return user;
	}
	
	public void supprimerUtilisateur(Integer no_Utilisateur) throws DALException {
		this.utilisateurDao.delete(no_Utilisateur);
	}
	
	
	
	
	
	public List<Utilisateurs> verifierUnicitePseudo(String pseudo) throws BLLException {
		List<Utilisateurs> listUtilisateurPseudo = null;
			try {
				listUtilisateurPseudo = utilisateurDao.selectByPseudo(pseudo); 
				} catch (DALException ex) {
					throw new BLLException();
				}
			return listUtilisateurPseudo;	
	}	
	
	public List<Utilisateurs> verifierUniciteEmail(String email) throws BLLException {
		List<Utilisateurs> listUtilisateurEmail = null;
			try {
				listUtilisateurEmail = utilisateurDao.selectByEmail(email); 
				} catch (DALException ex) {
					throw new BLLException();
				}
			return listUtilisateurEmail;	
	}	
	
	
}
