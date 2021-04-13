package fr.eni.javaee.appliencheres.dal;


import java.util.List;

import fr.eni.javaee.appliencheres.bo.Utilisateurs;



public interface UtilisateurDAO {

	public abstract void insert(Utilisateurs utilisateur)  throws DALException;

	public abstract void update(Utilisateurs utilisateur) throws DALException;
	
	public abstract Utilisateurs selectById(int id) throws DALException;
	
	public abstract void delete(int id)  throws DALException;

	public List<Utilisateurs> selectByPseudo(String pseudo) throws DALException;
	
	public List<Utilisateurs> selectByEmail(String email) throws DALException;
}
