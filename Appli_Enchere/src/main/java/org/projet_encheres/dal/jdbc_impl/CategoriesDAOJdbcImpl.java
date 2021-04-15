package org.projet_encheres.dal.jdbc_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.projet_encheres.bo.Categories;
import org.projet_encheres.bo.Retrait;
import org.projet_encheres.dal.CategoriesDAO;
import org.projet_encheres.dal.ConnectionProvider;

public class CategoriesDAOJdbcImpl implements CategoriesDAO{

	private static final String INSERT="INSERT INTO categories Values (?, ?)";
	private static final String SELECT_ALL="SELECT no_categorie, libelle FROM categories";			
	private static final String SELECT_BY_ID="SELECT no_categorie, libelle FROM categories WHERE no_categorie = ?";
	private static final String DELETE="DELETE FROM categories WHERE no_categorie = ?";
	
	
	@Override
	public void insert(Categories categorie) throws Exception {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			cnx.setAutoCommit(false);
			int noCategorie = categorie.getNoCategorie();
			String nomCategorie  = categorie.getNomCategorie();
			
			try(PreparedStatement pstmt = cnx.prepareStatement(INSERT)){
				pstmt.setInt(1, noCategorie);
				pstmt.setString(2, nomCategorie);
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
	public List<Categories> selectAll() throws Exception {
		List<Categories> listCategorie = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try(PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL)){
				ResultSet rs =  pstmt.executeQuery();
				while(rs.next())
				{
					Categories categorie = new Categories();
					categorie.setNoCategorie(rs.getInt("no_categorie"));
					categorie.setNomCategorie(rs.getString("libelle"));
					listCategorie.add(categorie);
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
		return listCategorie;
	}

	@Override
	public Categories selectById(int id) throws Exception {
		Categories categorie = new Categories();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try(PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID)){
				pstmt.setInt(1, id);
				ResultSet rs =  pstmt.executeQuery();
				while(rs.next())
				{
					categorie.setNoCategorie(rs.getInt("no_categorie"));
					categorie.setNomCategorie(rs.getString("libelle"));
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
		return categorie;
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
	public void update(Categories objet) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
