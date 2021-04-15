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
import org.projet_encheres.dal.ConnectionProvider;
import org.projet_encheres.dal.EncheresDAO;

public class EncheresDAOJdbcImpl implements EncheresDAO {
	
	private static final String INSERT="INSERT INTO Encheres Values (?, ?, ?, ?)";
	private static final String SELECT_ALL="SELECT no_encheres, no_utilisateur, no_article, date_enchere, montant_enchere "
											+ "FROM encheres";			
	private static final String SELECT_BY_ID="SELECT no_encheres, no_utilisateur, no_article, date_enchere, montant_enchere FROM encheres WHERE no_encheres = ?";
	private static final String SELECT_MAX_BY_ARTICLE_ID="SELECT TOP 1 e.no_encheres, e.montant_enchere, e.no_utilisateur, u.pseudo "
														+ "FROM encheres e "
														+ "LEFT JOIN utilisateurs u ON u.no_utilisateur = e.no_utilisateur "
														+ "WHERE no_article = ? ORDER BY montant_enchere DESC";
	private static final String DELETE="DELETE FROM encheres WHERE no_encheres = ?";
	

	@Override
	public void insert(Encheres enchere) throws Exception {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			cnx.setAutoCommit(false);
			int noUtilisateur = enchere.getNoUtilisateur();
			int noArticle = enchere.getNoArticle();
			Date dateEnchere = Date.valueOf(enchere.getDateEnchere());
			int montantEnchere = enchere.getMontantEnchere();
			
			
			try(PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)){
				pstmt.setInt(1, noUtilisateur);
				pstmt.setInt(2, noArticle);
				pstmt.setDate(3, dateEnchere);
				pstmt.setInt(4, montantEnchere);
				
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				
				if(rs.next())
				{
					int identifiant = rs.getInt(1);
					enchere.setNoEnchere(identifiant);
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
	public List<Encheres> selectAll() throws Exception {
		 List<Encheres> listeEncheres = new ArrayList<Encheres>();
		 try(Connection cnx = ConnectionProvider.getConnection())
			{
				
				try(PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL)){
					ResultSet rs =  pstmt.executeQuery();
					
					while(rs.next())
					{
						
						Encheres enchere = new Encheres();
						enchere.setNoEnchere(rs.getInt("no_encheres"));
						enchere.setNoUtilisateur(rs.getInt("no_utilisateur"));
						enchere.setNoArticle(rs.getInt("no_article"));
						
						LocalDate dateEnchere = rs.getDate("date_enchere").toLocalDate();
						
						enchere.setDateEnchere(dateEnchere);
						enchere.setMontantEnchere(rs.getInt("montant_enchere"));
						
						listeEncheres.add(enchere);
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
			System.out.println("liste encheres dans jdbcImpl :" + listeEncheres);
			return listeEncheres;
	}

	@Override
	public Encheres selectById(int id) throws Exception {
		Encheres enchere = new Encheres();
		 try(Connection cnx = ConnectionProvider.getConnection()){				
			try(PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID)){
				pstmt.setInt(1, id);
				ResultSet rs =  pstmt.executeQuery();					
				while(rs.next())
				{						
					
					enchere.setNoEnchere(rs.getInt("no_encheres"));
					enchere.setNoUtilisateur(rs.getInt("no_utilisateur"));
					enchere.setNoArticle(rs.getInt("no_article"));
					
					LocalDate dateEnchere = rs.getDate("date_enchere").toLocalDate();
					
					enchere.setDateEnchere(dateEnchere);
					enchere.setMontantEnchere(rs.getInt("montant_enchere"));
					
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
		System.out.println("enchere dans jdbcImpl :" + enchere);
		return enchere;
	}

	@Override
	public Encheres selectMaxByArticleId(int id) throws Exception {
		Encheres enchere = new Encheres();
		 try(Connection cnx = ConnectionProvider.getConnection())
			{
				
				try(PreparedStatement pstmt = cnx.prepareStatement(SELECT_MAX_BY_ARTICLE_ID)){
					pstmt.setInt(1, id);
					ResultSet rs =  pstmt.executeQuery();
					
					while(rs.next())
					{
						
						
						enchere.setNoEnchere(rs.getInt("no_encheres"));						
						enchere.setMontantEnchere(rs.getInt("montant_enchere"));
						enchere.setNoUtilisateur(rs.getInt("no_utilisateur"));
						enchere.setNomUtilisateurMaxEnchere(rs.getString("pseudo"));

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
			System.out.println("max encheres dans jdbcImpl :" + enchere);
			return enchere;
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
	public void update(Encheres objet) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
