package org.projet_encheres.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
/**
 * Servlet implementation class testPoolConnection
 */
@WebServlet("/testPoolConnection")
public class testPoolConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public testPoolConnection() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out = response.getWriter();
		
		try {
			Context context = new InitialContext();
			//recherche de la DataSource
			DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc/pool_cnx");
			
			//demande de conection. la methode getConnection met la demande en attente tant qu'il n'y as pas de connexion dispo dans le pool 
			Connection cnx = dataSource.getConnection();
			out.print("la connexion est " + (cnx.isClosed()?"fermée":"ouverte") + ".");
			
			//liberation de la connexion lorsqu'on en as plus besoin
			cnx.close(); //la connection n'est pas vraiment fermé mais remise dans le pool
			
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			out.println("une erreur est survenu lors de l'utilisation de la base de données: " + e.getMessage());
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
