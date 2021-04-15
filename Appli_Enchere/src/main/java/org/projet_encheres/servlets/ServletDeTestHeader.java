package org.projet_encheres.servlets;

import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.projet_encheres.bll.UtilisateurManager;
import org.projet_encheres.bo.Utilisateurs;
import org.projet_encheres.dal.DALException;

/**
 * Servlet implementation class ServletDeTestHeader
 */
@WebServlet("/ServletDeTestHeader")
public class ServletDeTestHeader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDeTestHeader() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();  // appel de la methode getSession qui créer un session si elle n'existe pas sionon retourn la session existante
		Utilisateurs user = new Utilisateurs();
		UtilisateurManager uM = new UtilisateurManager();
		try {
			user = uM.selectionnerUnUtilisateur(1);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(session.getAttribute("user")!=null) {
			// si l'attribut de session "user" existe je le supprime
			session.removeAttribute("user");
			System.out.println("destuction de la session");
		}else {
			// si l'attribut de session "user" n'existe pas, j'en créer un utilisateur
			//Random rand =new Random();
			

			System.out.println("creation de la session");
			session.setAttribute("user", user);  // je lie mon attribut "user" a ma session ainsi il est lier a l'utilisateur
			System.out.println(session.getAttribute("user"));
		}
		request.getRequestDispatcher("/WEB-INF/test.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
