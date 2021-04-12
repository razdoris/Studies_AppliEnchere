package fr.eni.javaee.appliencheres.bll;

import java.util.HashMap;
import java.util.Map;

import fr.eni.javaee.appliencheres.bo.Utilisateurs;
import fr.eni.javaee.appliencheres.dal.DALException;
import fr.eni.javaee.appliencheres.dal.DAOFactory;
import fr.eni.javaee.appliencheres.dal.UtilisateurDAO;

public class UtilisateurManager {

	private  UtilisateurDAO utilisateurDao;
	
	public UtilisateurManager(){
		this.utilisateurDao = DAOFactory.getUtilisateurDao();
	}
	

	public Utilisateurs ajouterUtilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String code_postal, String ville, String mot_de_passe) throws BLLException {
		
		if(this.validerUtilisateur(pseudo, email).isEmpty());
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
			this.utilisateurDao.insert(utilisateur);
		
		}catch(Exception ex){
		 throw new BLLException( ex);
			}
			return utilisateur;
		}
		
	
	public Map<String,String> validerUtilisateur(String pseudo, String email) {
		
		Map<String,String> erreur= new HashMap<String, String>();
		try {
			if(this.utilisateurDao.selectByPseudo(pseudo).isEmpty()) {
			}
		} catch (DALException ex) {
			erreur.put(pseudo, ex.getMessage());
		}
		
		try {
			if(this.utilisateurDao.selectByEmail(email).isEmpty()) {
			}
		} catch (DALException ex) {
			erreur.put(email, ex.getMessage());
		}
		return erreur;
	}	
	
	public Utilisateurs selectionnerUnUtilisateur(Integer no_Utilisateur) throws DALException {
		return this.utilisateurDao.selectById(no_Utilisateur);
		
	}
	
}
