package org.projet_encheres.bll;

import org.projet_encheres.dal.DAOFactory;
import org.projet_encheres.dal.RetraitDAO;

public class RetraitManager {

	private RetraitDAO retraitDAO;

	public RetraitManager() {
		this.retraitDAO = DAOFactory.getRetraitDao();
	}
	
	
}
