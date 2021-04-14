package org.projet_encheres.dal;


import java.util.List;

import org.projet_encheres.bo.Utilisateurs;



public interface UtilisateurDAO extends DAO<Utilisateurs>{

	

	public List<Utilisateurs> selectByPseudo(String pseudo) throws DALException;
	
	public List<Utilisateurs> selectByEmail(String email) throws DALException;
}
