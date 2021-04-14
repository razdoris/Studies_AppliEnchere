package org.projet_encheres.bll;

import java.util.ArrayList;
import java.util.List;

import org.projet_encheres.bo.Encheres;
import org.projet_encheres.dal.DAOFactory;
import org.projet_encheres.dal.EncheresDAO;

public class EncheresManager {
	
	private EncheresDAO enchereDao;
	
	public EncheresManager() {
		this.enchereDao =  DAOFactory.getEncheresDao();
	}

	public Encheres recupererListeDesEncheresDeLArticle(int idArticle) {
		Encheres maxEncheresDunArticle = new Encheres();
		try {
			maxEncheresDunArticle = this.enchereDao.selectByArticleId(idArticle);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return maxEncheresDunArticle;
	}

	
	
}
