package org.projet_encheres.bll;

import java.util.ArrayList;
import java.util.List;

import org.projet_encheres.bo.Categories;
import org.projet_encheres.dal.CategoriesDAO;
import org.projet_encheres.dal.DAOFactory;

public class CategoriesManager {
	
	private CategoriesDAO categoriesDAO;

	/**
	 * @param categoriesManager
	 */
	public CategoriesManager() {
		this.categoriesDAO = DAOFactory.getCategoriesDao();
	}
	
	public List<Categories> recupererListeDeToutesLesCategories(){
		List<Categories> listeDeToutesLesCategories = new ArrayList<>();
		try 
		{
			listeDeToutesLesCategories = this.categoriesDAO.selectAll();
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return listeDeToutesLesCategories;
	}

}
