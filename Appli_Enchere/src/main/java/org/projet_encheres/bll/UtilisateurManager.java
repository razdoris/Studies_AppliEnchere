package org.projet_encheres.bll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.projet_encheres.bo.Utilisateurs;
import org.projet_encheres.dal.DALException;
import org.projet_encheres.dal.DAOFactory;
import org.projet_encheres.dal.UtilisateurDAO;


public class UtilisateurManager {

	private  UtilisateurDAO utilisateurDao;
	
	public UtilisateurManager(){
		this.utilisateurDao = DAOFactory.getUtilisateurDao();
	}
	

	public Utilisateurs ajouterUtilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse) throws BLLException {
		
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
			utilisateur.setCodePostal(codePostal);
			utilisateur.setVille(ville);
			utilisateur.setMotDPasse(motDePasse);
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
	
	public Utilisateurs selectionnerUnUtilisateur(Integer noUtilisateur) throws DALException {
		Utilisateurs utilisateur = new Utilisateurs(); 
		try {
			utilisateur = this.utilisateurDao.selectById(noUtilisateur);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return utilisateur;
	}
	
	public List<Utilisateurs> recupererListeDeTousLesUtilisateur() throws DALException {
		List<Utilisateurs> ListeDeTousLesUtilisateur = new ArrayList<>(); 
		try {
			ListeDeTousLesUtilisateur = this.utilisateurDao.selectAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ListeDeTousLesUtilisateur;
	}


	public void majCredit(int noUtilisateur, int modification) {
		try {
			this.utilisateurDao.majCredit(noUtilisateur, modification);;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
