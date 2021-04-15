package org.projet_encheres.dal;

import java.util.List;

import org.projet_encheres.bo.Articles;
import org.projet_encheres.bo.Encheres;

public interface ArticleDAO extends DAO<Articles> {
	
	
	public void abordBid(int idArticle) throws Exception;
	

}
