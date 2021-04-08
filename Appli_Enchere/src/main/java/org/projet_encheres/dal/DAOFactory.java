package org.projet_encheres.dal;

import org.projet_encheres.dal.jdbc_impl.ArticlesDAOJdbcImpl;
import org.projet_encheres.dal.jdbc_impl.RetraitDAOJdbcImpl;

public class DAOFactory {
	
	public static ArticleDAO getArticleDao() {
		return new ArticlesDAOJdbcImpl();
	}
	

	public static RetraitDAO getRetraitDao() {
		return new RetraitDAOJdbcImpl();
	}
	

}
