package org.projet_encheres.dal.jdbc_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.projet_encheres.bo.Articles;
import org.projet_encheres.bo.Utilisateurs;
import org.projet_encheres.dal.ConnectionProvider;
import org.projet_encheres.dal.DALException;
import org.projet_encheres.dal.UtilisateurDAO;


public class UtilisateurDAOJDBCImpl implements UtilisateurDAO {


	private static final String SELECT_ALL="SELECT u.no_utilisateur, u.pseudo, u.nom, u.prenom, u.email, u.telephone, u.rue, u.code_postal, u.ville, u.email, u.credit, u.administrateur FROM utilisateurs u";
	private static final String INSERT_UTILISATEUR="INSERT INTO utilisateurs(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe) values(?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_UTILISATEUR="UPDATE utilisateurs SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=? WHERE no_utilisateur=?";
	private static final String SELECT_BY_ID_UTILISATEUR="SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM utilisateurs WHERE no_utilisateur=?";
	private static final String DELETE_UTILISATEUR="DELETE FROM utilisateurs WHERE no_utilisateur=?";
	private static final String SELECT_BY_PSEUDO="SELECT no_utilisateur FROM utilisateurs WHERE pseudo=?";
	private static final String SELECT_BY_EMAIL="SELECT no_utilisateur FROM utilisateurs WHERE email=?";
	private static final String MODIFICATION_CREDIT="UPDATE utilisateurs SET credit = ((SELECT u2.credit FROM utilisateurs u2 WHERE u2.no_utilisateur = ?) + (?)) WHERE no_utilisateur = ?";
	
	
	public void insert(Utilisateurs utilisateur) throws DALException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			cnx.setAutoCommit(false);
			try(PreparedStatement pstmt = cnx.prepareStatement(INSERT_UTILISATEUR, PreparedStatement.RETURN_GENERATED_KEYS)){
				pstmt.setString(1, utilisateur.getPseudo());
				pstmt.setString(2, utilisateur.getNom());
				pstmt.setString(3, utilisateur.getPrenom());
				pstmt.setString(4, utilisateur.getEmail());
				pstmt.setString(5, utilisateur.getTelephone());
				pstmt.setString(6, utilisateur.getRue());
				pstmt.setString(7, utilisateur.getCodePostal());
				pstmt.setString(8, utilisateur.getVille());
				pstmt.setString(9, utilisateur.getMotDePasse());
				pstmt.execute();
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs.next())
				{
					utilisateur.setNoUtilisateur(rs.getInt(1));
				}	
				cnx.commit();
			}catch (Exception ex) {
				cnx.rollback();
			}
		}catch (Exception ex) {
			throw new DALException(ex);
		}
	}


	public void update(Utilisateurs utilisateur) throws DALException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			cnx.setAutoCommit(false);
			try(PreparedStatement pstmt = cnx.prepareStatement(UPDATE_UTILISATEUR)){
				pstmt.setString(1, utilisateur.getPseudo());
				pstmt.setString(2, utilisateur.getNom());
				pstmt.setString(3, utilisateur.getPrenom());
				pstmt.setString(4, utilisateur.getEmail());
				pstmt.setString(5, utilisateur.getTelephone());
				pstmt.setString(6, utilisateur.getRue());
				pstmt.setString(7, utilisateur.getCodePostal());
				pstmt.setString(8, utilisateur.getVille());
				pstmt.setString(9, utilisateur.getMotDePasse());
				pstmt.executeUpdate();
				cnx.commit();

			}catch (Exception ex) {
				cnx.rollback();

			}
		}
		catch(Exception ex)
		{
			throw new DALException(ex);
		}

	}
	
	public Utilisateurs selectById(int id) throws DALException {
		Utilisateurs user= null;
		ResultSet rs = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
				try(PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID_UTILISATEUR)){
				pstmt.setInt(1, id);
				rs = pstmt.executeQuery();
				if(rs.next()){
					user= new Utilisateurs();
					user.setNoUtilisateur(rs.getInt("no_utilisateur"));
					user.setPseudo(rs.getString("pseudo"));
					user.setNom(rs.getString("nom"));
					user.setPrenom(rs.getString("prenom"));
					user.setEmail(rs.getString("email"));
					user.setTelephone(rs.getString("telephone"));
					user.setRue(rs.getString("rue"));
					user.setCodePostal(rs.getString("code_postal"));
					user.setVille(rs.getString("ville"));
					user.setMotDPasse(rs.getString("mot_de_passe"));
					user.setCredit(rs.getInt("credit"));
					user.setAdministrateur(rs.getByte("administrateur")==0?false:true);							
				}

			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		catch(Exception ex)
		{
			throw new DALException(ex);
		}
		return user;

	}
	
	
	public void delete(int id) throws DALException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
				try(PreparedStatement pstmt = cnx.prepareStatement(DELETE_UTILISATEUR)){
				pstmt.setInt(1, id);;
				pstmt.executeUpdate();

			}catch (Exception ex) {
				cnx.rollback();
			}
		}
		catch(Exception ex)
		{
			throw new DALException(ex);
		}

	}
	
	public List<Utilisateurs> selectByPseudo(String pseudo) throws DALException {
		List<Utilisateurs>listUtilisateurs = new ArrayList<>();
		ResultSet rs = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
				try(PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_PSEUDO)){
				pstmt.setString(1, pseudo);
				rs = pstmt.executeQuery();
				if(rs.next()){
					Utilisateurs user= new Utilisateurs(rs.getInt("no_utilisateur"));
				listUtilisateurs.add(user);
				
				}

			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		catch(Exception ex)
		{
			throw new DALException(ex);
		}
		return listUtilisateurs;

	}
	
	public List<Utilisateurs> selectByEmail(String email) throws DALException {
		List<Utilisateurs>listUtilisateurs = new ArrayList<>();
		ResultSet rs = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
				try(PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_EMAIL)){
				pstmt.setString(1, email);
				rs = pstmt.executeQuery();
				if(rs.next()){
					Utilisateurs user= new Utilisateurs(rs.getInt("no_utilisateur"));
				listUtilisateurs.add(user);
				
				}

			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		catch(Exception ex)
		{
			throw new DALException(ex);
		}
		return listUtilisateurs;

	}


	@Override
	public List<Utilisateurs> selectAll() throws Exception {
		// TODO create methode
		return null;
		
	}


	@Override
	public void majCredit(int idUtilisateur, int modificationDeCredit) throws DALException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			cnx.setAutoCommit(false);
			try(PreparedStatement pstmt = cnx.prepareStatement(MODIFICATION_CREDIT)){
				pstmt.setInt(1, idUtilisateur);
				pstmt.setInt(2, modificationDeCredit);
				pstmt.setInt(3, idUtilisateur);
				
				pstmt.executeUpdate();
				cnx.commit();

			}catch (Exception ex) {
				cnx.rollback();

			}
		}
		catch(Exception ex)
		{
			throw new DALException(ex);
		}
		
	}

	
}











