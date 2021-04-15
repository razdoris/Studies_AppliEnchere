package fr.eni.javaee.appliencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.javaee.appliencheres.bo.Utilisateurs;

public class UtilisateurDAOJDBCImpl implements UtilisateurDAO {


	private static final String INSERT_UTILISATEUR="INSERT INTO utilisateurs(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe,credit, administrateur) values(?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_UTILISATEUR="UPDATE utilisateurs SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=? WHERE no_utilisateur=?";
	private static final String SELECT_ALL_UTILISATEURS="SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM utilisateurs";
	private static final String SELECT_BY_ID_UTILISATEUR="SELECT pseudo, nom, prenom, email, telephone, rue, code_postal, ville FROM utilisateurs WHERE no_utilisateur=?";
	private static final String SELECT_BY_LOGIN_UTILISATEUR="SELECT * FROM utilisateurs WHERE pseudo=? AND mot_de_passe=?";
	private static final String DELETE_UTILISATEUR="DELETE FROM utilisateurs WHERE no_utilisateur=?";
	private static final String SELECT_BY_PSEUDO="SELECT no_utilisateur FROM utilisateurs WHERE pseudo=?";
	private static final String SELECT_BY_EMAIL="SELECT no_utilisateur FROM utilisateurs WHERE email=?";
	
	@Override
	public void insert(Utilisateurs utilisateur) throws DALException {
		System.out.println("jdbc1");
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
				pstmt.setString(7, utilisateur.getCode_postal());
				pstmt.setString(8, utilisateur.getVille());
				pstmt.setString(9, utilisateur.getMot_de_passe());
				pstmt.setInt(10, utilisateur.getCredit());
				pstmt.setBoolean(11, utilisateur.getAdministrateur());
				pstmt.execute();
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs.next())
				{
					utilisateur.setNo_utilisateur(rs.getInt(1));
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
				pstmt.setString(7, utilisateur.getCode_postal());
				pstmt.setString(8, utilisateur.getVille());
				pstmt.setString(9, utilisateur.getMot_de_passe());
				pstmt.executeUpdate();
				cnx.commit();

			}catch (Exception ex) {
				cnx.rollback();

			}
		}catch(Exception ex)
		{
			throw new DALException(ex);
		}

	}
	
	public List<Utilisateurs> selectAll() throws DALException{
		List<Utilisateurs> listUtilisateurs = new ArrayList<>();
		Utilisateurs user= null;
		ResultSet rs = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
				try(PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_UTILISATEURS)){
					rs = pstmt.executeQuery();
					while(rs.next())
					{
						user= new Utilisateurs(rs.getInt("no_utilisateur"),rs.getString("pseudo"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),
								rs.getString("telephone"),rs.getString("rue"),rs.getString("code_postal"),rs.getString("ville"),rs.getString("mot_de_passe"),
								rs.getInt("credit"),rs.getBoolean("administrateur"));
						
						listUtilisateurs.add(user);
					}
				}catch (Exception ex) {
					ex.printStackTrace();
				}
			}catch(Exception ex)
			{
				throw new DALException(ex);
			}
			return listUtilisateurs;

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
					user= new Utilisateurs(rs.getString("pseudo"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),
							rs.getString("telephone"),rs.getString("rue"),rs.getString("code_postal"),rs.getString("ville"),rs.getString("mot_de_passe"),
							rs.getInt("credit"),rs.getBoolean("administrateur"));
				}

			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}catch(Exception ex)
		{
			throw new DALException(ex);
		}
		return user;

	}
	
	
	
	public Utilisateurs selectByLogin(String pseudo, String password) throws DALException {
		System.out.println("jdbc2");
		Utilisateurs user= null;
		ResultSet rs = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
				try(PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_LOGIN_UTILISATEUR)){
				pstmt.setString(1, pseudo);
				pstmt.setString(2, password);
				rs = pstmt.executeQuery();
				if(rs.next()){
					user= new Utilisateurs(rs.getInt("no_utilisateur"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),
							rs.getString("telephone"),rs.getString("rue"),rs.getString("code_postal"),rs.getString("ville"),
							rs.getInt("credit"),rs.getBoolean("administrateur"));
				}

			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}catch(Exception ex)
		{
			throw new DALException(ex);
		}
		return user;

	}
	
	
	public void delete(int id) throws DALException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
				try(PreparedStatement pstmt = cnx.prepareStatement(DELETE_UTILISATEUR)){
				pstmt.setInt(1, id);
				pstmt.executeUpdate();

			}catch (Exception ex) {
				cnx.rollback();
			}
		}catch(Exception ex)
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
		}catch(Exception ex)
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
		}catch(Exception ex)
		{
			throw new DALException(ex);
		}
		return listUtilisateurs;

	}
	
}











