package org.projet_encheres.bll;

import org.projet_encheres.dal.ArticleDAO;
import org.projet_encheres.dal.DAOFactory;

public class ArticlesManager {

	private ArticleDAO articleDAO;
	
	public ArticlesManager() {
		this.articleDAO = DAOFactory.getArticleDao();
	}
}
