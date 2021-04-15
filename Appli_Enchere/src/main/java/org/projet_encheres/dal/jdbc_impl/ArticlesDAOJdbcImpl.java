package org.projet_encheres.dal.jdbc_impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.projet_encheres.bo.Articles;
import org.projet_encheres.bo.Encheres;
import org.projet_encheres.dal.ArticleDAO;
import org.projet_encheres.dal.ConnectionProvider;

public class ArticlesDAOJdbcImpl implements ArticleDAO{

	private static final String INSERT="INSERT INTO articles "
			+ "(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_vendeur, no_categorie, vente_annule) "
			+ "Values (?, ?, ?, ?, ?, ?, ?, 0)";
	private static final String SELECT_ALL="SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial,"
			+ " prix_vente, no_vendeur, no_categorie, vente_annule FROM articles WHERE vente_annule = 0";
			
	private static final String SELECT_BY_ID="SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, "
			+ "prix_initial, prix_vente, no_vendeur, no_categorie, vente_annule FROM articles a WHERE no_article = ?";
	private static final String DELETE="DELETE FROM articles WHERE no_article = ?";
	private static final String UPDATE="UPDATE articles SET nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?"
										+ " prix_initial = ?, no_categorie = ?"
										+ "	WHERE no_article = ?";
	private static final String ABORDE_BID="UPDATE articles SET vente_annule = 1 WHERE no_article = ?";	
	
	@Override
	public void insert(Articles article) throws Exception {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			cnx.setAutoCommit(false);
			String nomArticle  = article.getNomArticle();
			String descriptionArticle  = article.getDescriptionArticle();
			Date dateDebutVenteArticle = Date.valueOf(article.getDateDebutEnchere());
			Date dateFinVenteArticle = Date.valueOf(article.getDateFinEnchere());
			int prixVenteArticle = article.getPrixInitial();
			int noVendeurArticle = article.getNoVendeur();
			int noCategorieArticle = article.getNoCategorie();
			try(PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)){
				pstmt.setString(1, nomArticle);
				pstmt.setString(2, descriptionArticle);
				pstmt.setDate(3, dateDebutVenteArticle);
				pstmt.setDate(4, dateFinVenteArticle);
				pstmt.setInt(5, prixVenteArticle);
				pstmt.setInt(6, noVendeurArticle);
				pstmt.setInt(7, noCategorieArticle);
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
							
				if(rs.next())
				{
					int identifiant = rs.getInt(1);
					article.setNoArticle(identifiant);
				}
				cnx.commit();
			}catch (Exception e) {
				// TODO modifier le type d'erreur pour message utilisateur
				cnx.rollback();
				throw e;
			}
			
		}
		catch(Exception e)
		{
			// TODO modifier le type d'erreur pour message utilisateur
			e.printStackTrace();
			
			throw e;
		}
		
	}

	@Override
	public List<Articles> selectAll() throws Exception {
		List<Articles> listArticles = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			
			try(PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL)){
				ResultSet rs =  pstmt.executeQuery();
				
				while(rs.next())
				{
					
					Articles article = new Articles();
					article.setNoArticle(rs.getInt("no_article"));
					article.setNomArticle(rs.getString("nom_article"));
					article.setDescriptionArticle(rs.getString("description"));
					
					LocalDate dateDebutEnchere = rs.getDate("date_debut_encheres").toLocalDate();
					LocalDate dateFinEnchere = rs.getDate("date_fin_encheres").toLocalDate();
					
					article.setDateDebutEnchere(dateDebutEnchere);
					article.setDateFinEnchere(dateFinEnchere);
					article.setPrixInitial(rs.getInt("prix_initial"));
					article.setPrixVente(rs.getInt("prix_vente"));
					article.setNoVendeur(rs.getInt("no_vendeur"));
					article.setNoCategorie(rs.getInt("no_categorie"));
					boolean annule = rs.getByte("vente_annule")==0?false:true;
					article.setAnnule(annule);
					
					listArticles.add(article);
				}
			}
			catch(Exception e)
			{
				// TODO modifier le type d'erreur pour message utilisateur
				e.printStackTrace();;
				throw e;
			}
		}
		catch(Exception e)
		{
			// TODO modifier le type d'erreur pour message utilisateur
			e.printStackTrace();;
			throw e;
		}
		//System.out.println("liste articles dans jdbcImpl :" + listArticles);
		return listArticles;
		
	}

	@Override
	public Articles selectById(int id) throws Exception {
		Articles article = new Articles();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try(PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID)){
				pstmt.setInt(1, id);
				ResultSet rs =  pstmt.executeQuery();
				while(rs.next())
				{
					article.setNoArticle(rs.getInt("no_article"));
					article.setNomArticle(rs.getString("nom_article"));
					article.setDescriptionArticle(rs.getString("description"));
					
					LocalDate dateDebutEnchere = rs.getDate("date_debut_encheres").toLocalDate();
					LocalDate dateFinEnchere = rs.getDate("date_fin_encheres").toLocalDate();
					
					article.setDateDebutEnchere(dateDebutEnchere);
					article.setDateFinEnchere(dateFinEnchere);
					article.setPrixInitial(rs.getInt("prix_initial"));
					article.setPrixVente(rs.getInt("prix_vente"));
					article.setNoVendeur(rs.getInt("no_vendeur"));
					article.setNoCategorie(rs.getInt("no_categorie"));
					boolean annule = rs.getByte("vente_annule")==0?false:true;
					article.setAnnule(annule);
				}
			}
			catch(Exception e)
			{
				// TODO modifier le type d'erreur pour message utilisateur
				e.printStackTrace();;
				throw e;
			}
		}
		catch(Exception e)
		{
			// TODO modifier le type d'erreur pour message utilisateur
			e.printStackTrace();;
			throw e;
		}
		
		return article;
	}

	@Override
	public void delete(int id) throws Exception {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try(PreparedStatement pstmt = cnx.prepareStatement(DELETE)){
				pstmt.setInt(1, id);
				pstmt.executeQuery();
			}
			catch(Exception e)
			{
				// TODO modifier le type d'erreur pour message utilisateur
				e.printStackTrace();;
				throw e;
			}
			
		}
		catch(Exception e)
		{
			// TODO modifier le type d'erreur pour message utilisateur
			e.printStackTrace();;
			throw e;
		}
		
	}
	

	@Override
	public void abordBid(int id) throws Exception {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try(PreparedStatement pstmt = cnx.prepareStatement(ABORDE_BID)){
				pstmt.setInt(1, id);
				pstmt.executeUpdate();
			}
			catch(Exception e)
			{
				// TODO modifier le type d'erreur pour message utilisateur
				e.printStackTrace();;
				throw e;
			}
			
		}
		catch(Exception e)
		{
			// TODO modifier le type d'erreur pour message utilisateur
			e.printStackTrace();;
			throw e;
		}
		
	}

	/*
	UPDATE="UPDATE articles SET nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?"
			+ " prix_initial = ?, no_categorie = ?"
			+ "	WHERE no_article = ?";
			*/
	@Override
	public void update(Articles article) throws Exception {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			cnx.setAutoCommit(false);
			String nomArticle  = article.getNomArticle();
			String descriptionArticle  = article.getDescriptionArticle();
			Date dateDebutVenteArticle = Date.valueOf(article.getDateDebutEnchere());
			Date dateFinVenteArticle = Date.valueOf(article.getDateFinEnchere());
			int prixVenteArticle = article.getPrixInitial();
			int noArticle = article.getNoArticle();
			int noCategorieArticle = article.getNoCategorie();
			try(PreparedStatement pstmt = cnx.prepareStatement(UPDATE)){
				pstmt.setString(1, nomArticle);
				pstmt.setString(2, descriptionArticle);
				pstmt.setDate(3, dateDebutVenteArticle);
				pstmt.setDate(4, dateFinVenteArticle);
				pstmt.setInt(5, prixVenteArticle);
				pstmt.setInt(6, noCategorieArticle);
				pstmt.setInt(7, noArticle);
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				cnx.commit();
			}catch (Exception e) {
				// TODO modifier le type d'erreur pour message utilisateur
				cnx.rollback();
				throw e;
			}
			
		}
		catch(Exception e)
		{
			// TODO modifier le type d'erreur pour message utilisateur
			e.printStackTrace();
			
			throw e;
		}
		
	}




}
