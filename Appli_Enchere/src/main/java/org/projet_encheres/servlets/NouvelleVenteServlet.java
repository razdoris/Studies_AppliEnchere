package org.projet_encheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.projet_encheres.bll.ArticlesManager;
import org.projet_encheres.bll.CategoriesManager;
import org.projet_encheres.bll.EncheresManager;
import org.projet_encheres.bll.RetraitManager;
import org.projet_encheres.bll.UtilisateurManager;
import org.projet_encheres.bo.Articles;
import org.projet_encheres.bo.Categories;
import org.projet_encheres.bo.Encheres;
import org.projet_encheres.bo.Retrait;
import org.projet_encheres.bo.Utilisateurs;
import org.projet_encheres.dal.UtilisateurDAO;

/**
 * Servlet implementation class ServletNouvelleVente
 */
@WebServlet(urlPatterns = {"/NouvelleVente", "/ModifierVente", "/VisualiserVente"})
public class NouvelleVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recuperation de parametres de requete
		HttpSession session = request.getSession();	
		String url = request.getServletPath();
		
		//appel de ma methode de recupération des catégories
		List<Categories> listCategories = recupererListCategories();	
		
		// création des variables
		String servlet = "nouvelleVente";
		LocalDate jour = LocalDate.now();
		Utilisateurs user = new Utilisateurs();
		int id = 0;
		
		Articles article =  new Articles();
		ArticlesManager articlesManager = new ArticlesManager();
		
		Retrait retrait = new Retrait();
		RetraitManager retraitManageur = new RetraitManager();
		
		Utilisateurs vendeur = new Utilisateurs();
		UtilisateurManager utilisateurManageur = new UtilisateurManager();
		
		Encheres maxEnchere = new Encheres();
		
		try {
			//test de l'url d'accès a la page
			if(!url.equals("/NouvelleVente")) {
				
				// recuperation des variables
				if(session.getAttribute("user")!=null && session.getAttribute("user")!="") {
					user = (Utilisateurs)session.getAttribute("user");
				}else {
					user.setNo_utilisateur(0);
				}
				id = Integer.parseInt(request.getParameter("id"));
				
				//pour test, a supprimer
				System.out.println("l'url n'est pas nouvelle vente et l'id objet est " +id);
				
				//recuperation des données de l'article
				article = articlesManager.recupererUnArticles(id);
				
				System.out.println("on recuper un objet : " + article);
				
				
				//POUR TEST
				//LocalDate yesterday = jour.minusDays(1);
				//article.setDateDebutEnchere(yesterday);
				//article.setDateFinEnchere(yesterday);
				
				//récupération des données eventuelles d'un retrait s'il n'y a pas de données, on rempli l'objet retrait avec les données utilisateur
				//TODO , A REVOIR
				retrait = retraitManageur.recupererUnRetrait(id);
				System.out.println("on recuper un retrait : " + retrait);
				
				//récupération des l'enchere la plus élevée si elle existe
				maxEnchere = recupererMaxEncheresDeLArticle(article.getNoArticle());
								
				
				if(article.getNoVendeur()==user.getNo_utilisateur()) {
					System.out.println("l'utilisateur est le vendeur");
					if(retrait==null) {
						retrait.setRue(user.getRue());
						retrait.setCodePostal(user.getCode_postal());
						retrait.setVille(user.getVille());
					}
					if(article.getDateDebutEnchere().isAfter(jour) && article.getDateFinEnchere().isAfter(jour)) {
						servlet = "nouvelleVente_modifier";
					}else if(article.getDateDebutEnchere().isBefore(jour) && article.getDateFinEnchere().isAfter(jour)) {
						servlet = "detail_vendeur";
					}else if(article.getDateDebutEnchere().isBefore(jour) && article.getDateFinEnchere().isBefore(jour)){
						servlet = "resultat_vendeur";
					}
				}else {
					System.out.println("l'utilisateur n'est pas le vendeur");
					if(retrait==null) {
						vendeur = utilisateurManageur.selectionnerUnUtilisateur(article.getNoVendeur());
						retrait.setRue(vendeur.getRue());
						retrait.setCodePostal(vendeur.getCode_postal());
						retrait.setVille(vendeur.getVille());
					}
					if(article.getDateDebutEnchere().isBefore(jour)) {
						servlet = "detail_acheteur";
					}else if(article.getDateFinEnchere().isBefore(jour)) {
						if(user.getNo_utilisateur() == maxEnchere.getNoUtilisateur()) {
							servlet = "resultat_acheteur_gagnant";
						}else{
							servlet = "resultat_acheteur_autre";
						}						
					}else {
						throw new Exception(" vous ne pouvez pas acceder a cette vente");
					}
				}
			
			} 
			
			System.out.println(url);
			request.setAttribute("articleAAfficher", article);
			request.setAttribute("retraitAAfficher", retrait);
			request.setAttribute("enchereAAfficher", maxEnchere);
			request.setAttribute("vendeur", vendeur);
			request.setAttribute("servlet", servlet);
			request.setAttribute("categories", listCategories);
			request.getRequestDispatcher("/WEB-INF/vendeur/Vente.jsp").forward(request, response);
			
		}catch (Exception e) {
			e.getStackTrace();
			response.getWriter().append("il y a une erreur" + e.getMessage());
		}
			
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Articles article =  new Articles();
		Retrait retrait = new Retrait();
		HttpSession session = request.getSession();	
		Utilisateurs user = new Utilisateurs();
		try {
			user = (Utilisateurs)session.getAttribute("user");
			article.setNoVendeur(user.getNo_utilisateur());
			article.setNomArticle(request.getParameter("nomArticle"));
			article.setDescriptionArticle(request.getParameter("descriptionArticle"));
			article.setNoCategorie(Integer.parseInt(request.getParameter("categorieArticle")));
			article.setPrixInitial(Integer.parseInt(request.getParameter("prixInitialArticle")));
			
			String dateDebut = request.getParameter("dateDebutArticle");
			LocalDate dateDebutEnchere = LocalDate.parse(dateDebut);
			String dateFin = request.getParameter("dateFinArticle");
			LocalDate dateFinEnchere = LocalDate.parse(dateFin);
			
			
			article.setDateDebutEnchere(dateDebutEnchere);
			article.setDateFinEnchere(dateFinEnchere);
			
			ArticlesManager articlesManager = new ArticlesManager();
			article = articlesManager.ajouterUnArticle(article);
			System.out.println("article ajouter : " + article);
			
			
			if(request.getParameter("nomRue")!=null && request.getParameter("nomRue").trim()!="") {
				retrait.setRue(request.getParameter("nomRue"));
				retrait.setCodePostal(request.getParameter("codePostal"));
				retrait.setVille(request.getParameter("nomVille"));
				article.setAdresseRetrait(retrait);
				RetraitManager retraitManager = new RetraitManager();
				retrait = retraitManager.ajouterUnRetrait(retrait);				
			}
					
		
			List<Categories> listCategories = recupererListCategories();
			System.out.println(article);
			request.setAttribute("articleAAfficher", article);
			request.setAttribute("retraitAAfficher", retrait);
			request.setAttribute("categories", listCategories);
			request.getRequestDispatcher("/WEB-INF/vendeur/nouvelleVente.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.getStackTrace();
			response.getWriter().append("il y a une erreur" + e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * @return the list of the category
	 */
	protected List<Categories> recupererListCategories(){
		List<Categories> listDeToutesLesCategories = new ArrayList<>();
		try
		{
			
			CategoriesManager categoriesManager = new CategoriesManager();
			listDeToutesLesCategories = categoriesManager.recupererListeDeToutesLesCategories();
			
			System.out.println("liste categories dans servlet : " + listDeToutesLesCategories);
			
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
			
		}
		return listDeToutesLesCategories;
	}
	
	
	protected Encheres recupererMaxEncheresDeLArticle(int idArticle){
		Encheres maxEnchereDeLArticle = new Encheres();
		try {
			EncheresManager enchereManager = new EncheresManager();
			maxEnchereDeLArticle = enchereManager.recupererListeDesEncheresDeLArticle(idArticle);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return maxEnchereDeLArticle;
		
				
	}

}
