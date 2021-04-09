package org.projet_encheres.dal.jdbc_impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.projet_encheres.bo.Articles;
import org.projet_encheres.bo.Retrait;
import org.projet_encheres.dal.ConnectionProvider;
import org.projet_encheres.dal.RetraitDAO;

public class RetraitDAOJdbcImpl implements RetraitDAO{

	private static final String INSERT="INSERT INTO retraits "
			+ "Values (?, ?, ?, ?)";
	private static final String SELECT_ALL="SELECT no_article, rue, code_postal, ville FROM retraits";
	private static final String SELECT_BY_ID="SELECT no_article, rue, code_postal, ville FROM retraits WHERE no_article";
	private static final String DELETE="DELETE FROM retraits WHERE no_article";
	
	@Override
	public void insert(Retrait retrait) throws Exception {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			cnx.setAutoCommit(false);
			int noArticle = retrait.getNoArticleRetirerIci();
			String rueRetrait  = retrait.getRue();
			String cpRetrait  = retrait.getCodePostal();
			String villeRetrait = retrait.getVille();
			
			try(PreparedStatement pstmt = cnx.prepareStatement(INSERT)){
				pstmt.setInt(1, noArticle);
				pstmt.setString(2, rueRetrait);
				pstmt.setString(3, cpRetrait);
				pstmt.setString(4, villeRetrait);
				pstmt.executeUpdate();
				
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
	public List<Retrait> selectAll() throws Exception {
		List<Retrait> listRetrait = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try(PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL)){
				ResultSet rs =  pstmt.executeQuery();
				while(rs.next())
				{
					Retrait retrait = new Retrait();
					retrait.setNoArticleRetirerIci(rs.getInt("no_article"));
					retrait.setRue(rs.getString("rue"));
					retrait.setCodePostal(rs.getString("code_postal"));
					retrait.setVille(rs.getString("ville"));
					listRetrait.add(retrait);
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
		System.out.println("liste dans jdbcImpl :" + listRetrait);
		return listRetrait;
		
	}

	@Override
	public Retrait selectById(int id) throws Exception {
		Retrait retrait = new Retrait();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try(PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID)){
				pstmt.setInt(1, id);
				ResultSet rs =  pstmt.executeQuery();
				while(rs.next())
				{
					retrait.setNoArticleRetirerIci(rs.getInt("no_article"));
					retrait.setRue(rs.getString("rue"));
					retrait.setCodePostal(rs.getString("code_postal"));
					retrait.setVille(rs.getString("ville"));
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
		
		return retrait;
	}

	@Override
	public void delete(Retrait retrait) throws Exception {
		int id = retrait.getNoArticleRetirerIci();
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

}
