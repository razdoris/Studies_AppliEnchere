package org.projet_encheres.bll;

import java.util.ArrayList;
import java.util.List;

import org.projet_encheres.bo.Articles;
import org.projet_encheres.dal.ArticleDAO;
import org.projet_encheres.dal.DAOFactory;

public class ArticlesManager {

	private ArticleDAO articleDAO;
	
	public ArticlesManager() {
		this.articleDAO = DAOFactory.getArticleDao();
	}
	
	public List<Articles> recupereListeDeTousLesArticles(){
		List<Articles> listDeTousLesArticles = new ArrayList<>();
		try
		{
			listDeTousLesArticles = this.articleDAO.selectAll();
			System.out.println("liste articles dans manager : " + listDeTousLesArticles);
		}
		 catch (Exception e) 
		{
			 // TODO modifier la type d'erreur pour message utilisateur
			e.printStackTrace();
		}
		
		return listDeTousLesArticles;
		
	}
	
	
	
}
