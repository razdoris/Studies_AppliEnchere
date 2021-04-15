package org.projet_encheres.servlets;

import java.io.IOException;
import java.net.URI;
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
					user.setNoUtilisateur(0);
				}
				id = Integer.parseInt(request.getParameter("id"));
				
				//pour test, a supprimer
				System.out.println("l'url n'est pas nouvelle vente et l'id objet est " +id);
				
				//recuperation des données de l'article
				article = articlesManager.recupererUnArticles(id);
				
				//System.out.println("on recuper un objet : " + article);
				
				
				//POUR TEST
				//LocalDate yesterday = jour.minusDays(1);
				//article.setDateDebutEnchere(yesterday);
				//article.setDateFinEnchere(yesterday);
				
				//récupération des données eventuelles d'un retrait s'il n'y a pas de données, on rempli l'objet retrait avec les données utilisateur
				//TODO , A REVOIR
				retrait = retraitManageur.recupererUnRetrait(article.getNoArticle());
				System.out.println("on recuper un retrait : " + retrait);
				
				//récupération des l'enchere la plus élevée si elle existe
				maxEnchere = recupererMaxEncheresDeLArticle(article.getNoArticle());
				
				vendeur = utilisateurManageur.selectionnerUnUtilisateur(article.getNoVendeur());
								
				
				if(article.getNoVendeur()==user.getNoUtilisateur()) {
					System.err.println("l'utilisateur est le vendeur");
					
					if(retrait.getRue()==null) {
						retrait.setRue(user.getRue());
						retrait.setCodePostal(user.getCodePostal());
						retrait.setVille(user.getVille());
					}
					System.err.println(retrait);
					if(article.getDateDebutEnchere().isAfter(jour) && article.getDateFinEnchere().isAfter(jour)) {
						servlet = "nouvelleVente_modifier";
					}else if(article.getDateDebutEnchere().isBefore(jour) && article.getDateFinEnchere().isAfter(jour)) {
						servlet = "detail_vendeur";
					}else if(article.getDateDebutEnchere().isBefore(jour) && article.getDateFinEnchere().isBefore(jour)){
						servlet = "resultat_vendeur";
					}
				}else {
					System.err.println("l'utilisateur n'est pas le vendeur");
					if(retrait.getRue()==null) {
						
						retrait.setRue(vendeur.getRue());
						retrait.setCodePostal(vendeur.getCodePostal());
						retrait.setVille(vendeur.getVille());
					}
					if(article.getDateDebutEnchere().isBefore(jour)) {
						servlet = "detail_acheteur";
						if(article.getDateFinEnchere().isBefore(jour)) {
							if(user.getNoUtilisateur() == maxEnchere.getNoUtilisateur()) {
								servlet = "resultat_acheteur_gagnant";
							}else{
								servlet = "resultat_acheteur_autre";
							}
						}
					}else {
						throw new Exception(" vous ne pouvez pas acceder a cette vente");
					}
				}
			
			} 
			
			System.out.println(servlet);
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
		Retrait retraitEnBdd = new Retrait();
		RetraitManager retraitManager = new RetraitManager();
		HttpSession session = request.getSession();	
		Utilisateurs user = new Utilisateurs();
		String servlet = "nouvelleVente_modifier";
		String url = request.getServletPath();
		try {
			user = (Utilisateurs)session.getAttribute("user");
			article.setNoArticle(Integer.parseInt(request.getParameter("noArticle")));			
			article.setNomArticle(request.getParameter("nomArticle"));
			article.setDescriptionArticle(request.getParameter("descriptionArticle"));
			article.setNoCategorie(Integer.parseInt(request.getParameter("noCategorieArticle")));
			article.setPrixInitial(Integer.parseInt(request.getParameter("prixInitialArticle")));
			
			String dateDebut = request.getParameter("dateDebutArticle");
			LocalDate dateDebutEnchere = LocalDate.parse(dateDebut);
			String dateFin = request.getParameter("dateFinArticle");
			LocalDate dateFinEnchere = LocalDate.parse(dateFin);
			
			
			article.setDateDebutEnchere(dateDebutEnchere);
			article.setDateFinEnchere(dateFinEnchere);
			
			if(request.getParameter("nomRue")!=null && request.getParameter("nomRue").trim()!="") {
				retrait.setRue(request.getParameter("nomRue"));
				retrait.setCodePostal(request.getParameter("codePostal"));
				retrait.setVille(request.getParameter("nomVille"));
							
			}
			
			ArticlesManager articlesManager = new ArticlesManager();
			
			System.out.println(request.getParameter("action"));
			
			if(request.getParameter("action") == "Enregister") {
				if(!url.contains("nouvelleVente_modifier")) {
					article.setNoVendeur(user.getNoUtilisateur());
					article = articlesManager.ajouterUnArticle(article);
					System.out.println("article ajouter : " + article);
					article.setAdresseRetrait(retrait);	
					if(retrait.getRue()!=null) {
						retrait = retraitManager.ajouterUnRetrait(retrait);
					}
					
					
				}else if(url.contains("nouvelleVente_modifier")) {
					article.setNoVendeur(user.getNoUtilisateur());
					articlesManager.modifierUnArticle(article);
					if(retrait.getRue()!=user.getRue() && retrait.getCodePostal()!=user.getCodePostal() && retrait.getVille()!=user.getVille()) {
						retraitEnBdd = retraitManager.recupererUnRetrait(article.getNoArticle());
						if(retraitEnBdd == null){
							retrait = retraitManager.ajouterUnRetrait(retrait);
						}
						if(retrait.getRue()!=retraitEnBdd.getRue() && retrait.getCodePostal()!=retraitEnBdd.getCodePostal() && retrait.getVille()!=retraitEnBdd.getVille()) {
							retraitManager.modifierRetrait(retrait);
						}
					}else {
						retraitManager.suprimerRetrait(retrait.getNoArticleRetirerIci());
					}
					System.out.println("modifier article : " + article);				
									
						
				}
				
			}else if(request.getParameter("action").contains("Annuler la vente")) {
				//article.setNoVendeur(0)
				article.setAnnule(true);
				String urlGo = request.getScheme() +"://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
				articlesManager.annulerVenteArticle(article.getNoArticle());

				System.out.println(urlGo);
				response.sendRedirect(urlGo);
			}else if(request.getParameter("action").contains("Encherir")) {
				int proposition = Integer.parseInt(request.getParameter("proposition"));
				LocalDate jour = LocalDate.now();
				Encheres enchere = new Encheres();
				enchere.setNoUtilisateur(user.getNoUtilisateur());
				enchere.setDateEnchere(jour);
				enchere.setNoArticle(article.getNoArticle());
				enchere.setMontantEnchere(proposition);
				EncheresManager enchereManageur = new EncheresManager();
				servlet = "detail_acheteur";
				UtilisateurManager utilisateurManager = new UtilisateurManager();
				int maxEnchere = Integer.parseInt(request.getParameter("maxEnchere"));
				int idAcheteurMaxEnchere = Integer.parseInt(request.getParameter("idAcheteurMaxEnchere"));
				if(proposition>maxEnchere && proposition<=user.getCredit()) {
					utilisateurManager.majCredit(user.getNoUtilisateur(), -proposition);
					utilisateurManager.majCredit(idAcheteurMaxEnchere, maxEnchere);
					enchereManageur.ajouterEnchere(enchere);
				}else {
					throw new Exception("le montant de l'enchère doit etre superieur a l'enchere maximal déjà proposé");
				}
			}
		
			List<Categories> listCategories = recupererListCategories();
			System.out.println(article);
			
			request.setAttribute("articleAAfficher", article);
			request.setAttribute("retraitAAfficher", retrait);
			request.setAttribute("categories", listCategories);
			request.setAttribute("servlet", servlet);
			request.getRequestDispatcher("/WEB-INF/vendeur/Vente.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.getStackTrace();
			if(e.getMessage().contains("user")) {
				response.getWriter().append("vous devez etre connecté");
			}
			response.getWriter().append("il y a une erreur " + e.getMessage());
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
			maxEnchereDeLArticle = enchereManager.recupererMaxEncheresDeLArticle(idArticle);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return maxEnchereDeLArticle;
		
				
	}

}
