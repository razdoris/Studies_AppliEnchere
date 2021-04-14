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
	
	public List<Articles> recupererListeDeTousLesArticles(){
		List<Articles> listeDeTousLesArticles = new ArrayList<>();
		try
		{
			listeDeTousLesArticles = this.articleDAO.selectAll();
			System.out.println("liste articles dans manager : " + listeDeTousLesArticles);
		}
		 catch (Exception e) 
		{
			 // TODO modifier la type d'erreur pour message utilisateur
			e.printStackTrace();
		}
		
		return listeDeTousLesArticles;
		
	}
	
	public Articles recupererUnArticles(int idArticle){
		Articles article = new Articles();
		System.out.println("id dans article manager ok" + idArticle);
		try
		{
			article = this.articleDAO.selectById(idArticle);
			System.out.println("articles dans manager : " + article);
		}
		 catch (Exception e) 
		{
			 // TODO modifier la type d'erreur pour message utilisateur
			e.printStackTrace();
		}
		
		return article;
		
	}
	
	public Articles ajouterUnArticle(Articles article) {
		try {
			this.articleDAO.insert(article);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return article;
	}
	
	
	
}
