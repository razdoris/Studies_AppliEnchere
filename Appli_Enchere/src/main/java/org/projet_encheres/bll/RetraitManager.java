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
		
		try
		{
			retrait = this.retraitDAO.selectById(idRetrait);
			
		}
		 catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		
		return retrait;
		
	}

	public void suprimerRetrait(int noArticleRetirerIci) {
		try
		{
			this.retraitDAO.delete(noArticleRetirerIci);
			
		}
		 catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		
	}

	public void modifierRetrait(Retrait retrait) {
		try
		{
			this.retraitDAO.update(retrait);
			
		}
		 catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		
	}
}
