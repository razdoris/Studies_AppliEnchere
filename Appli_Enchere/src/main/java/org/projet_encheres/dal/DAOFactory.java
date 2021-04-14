package org.projet_encheres.dal;

import org.projet_encheres.dal.jdbc_impl.ArticlesDAOJdbcImpl;
import org.projet_encheres.dal.jdbc_impl.CategoriesDAOJdbcImpl;
import org.projet_encheres.dal.jdbc_impl.EncheresDAOJdbcImpl;
import org.projet_encheres.dal.jdbc_impl.RetraitDAOJdbcImpl;
import org.projet_encheres.dal.jdbc_impl.UtilisateurDAOJDBCImpl;

public class DAOFactory {
	
	
	public static ArticleDAO getArticleDao() {
		return new ArticlesDAOJdbcImpl();
	}
	

	public static RetraitDAO getRetraitDao() {
		return new RetraitDAOJdbcImpl();
	}
	

	
	public static UtilisateurDAO getUtilisateurDao() {
		return new UtilisateurDAOJDBCImpl();
	}


	public static CategoriesDAO getCategoriesDao() {
		return new CategoriesDAOJdbcImpl();
	}
	

	public static EncheresDAO getEncheresDao() {
		return new EncheresDAOJdbcImpl();
	}
	

	

}

