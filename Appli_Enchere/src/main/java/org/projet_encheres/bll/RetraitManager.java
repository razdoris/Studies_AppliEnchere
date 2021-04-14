package org.projet_encheres.bll;

import org.projet_encheres.bo.Articles;
import org.projet_encheres.bo.Retrait;
import org.projet_encheres.dal.DAOFactory;
import org.projet_encheres.dal.RetraitDAO;

public class RetraitManager {

	private RetraitDAO retraitDAO;

	public RetraitManager() {
		this.retraitDAO = DAOFactory.getRetraitDao();
	}
	
	public Retrait ajouterUnRetrait(Retrait retrait) {
		try {
			this.retraitDAO.insert(retrait);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retrait;
		
	}
	
	public Retrait recupererUnRetrait(int idRetrait){
		Retrait retrait = new Retrait();
		System.out.println("id dans article manager ok" + idRetrait);
		try
		{
			retrait = this.retraitDAO.selectById(idRetrait);
			System.out.println("retrait dans manager : " + retrait);
		}
		 catch (Exception e) 
		{
			 // TODO modifier la type d'erreur pour message utilisateur
			e.printStackTrace();
		}
		
		return retrait;
		
	}
}
