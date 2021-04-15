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

	public Encheres recupererMaxEncheresDeLArticle(int idArticle) {
		Encheres maxEncheresDunArticle = new Encheres();
		try {
			maxEncheresDunArticle = this.enchereDao.selectMaxByArticleId(idArticle);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return maxEncheresDunArticle;
	}

	public void ajouterEnchere(Encheres enchere) {
		Encheres Enchere = new Encheres();
		try {
			this.enchereDao.insert(enchere);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	
	
}
