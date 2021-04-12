package fr.eni.javaee.appliencheres.dal;

public class DAOFactory {
	
	public static UtilisateurDAO getUtilisateurDao() {
		return new UtilisateurDAOJDBCImpl();
	}
	

	

}

