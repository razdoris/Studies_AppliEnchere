package org.projet_encheres.bo;

import java.util.ArrayList;
import java.util.List;

import org.projet_encheres.bll.CategoriesManager;

public class Categories {
	
	private int noCategorie;
	private String nomCategorie;
	/**
	 * 
	 */
	public Categories() {
		super();
	}
	/**
	 * @param noCategorie
	 * @param nomCategorie
	 */
	public Categories(int noCategorie, String nomCategorie) {
		super();
		this.noCategorie = noCategorie;
		this.nomCategorie = nomCategorie;
	}
	/**
	 * @return the noCategorie
	 */
	public int getNoCategorie() {
		return noCategorie;
	}
	/**
	 * @param noCategorie the noCategorie to set
	 */
	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}
	/**
	 * @return the nomCategorie
	 */
	public String getNomCategorie() {
		return nomCategorie;
	}
	/**
	 * @param nomCategorie the nomCategorie to set
	 */
	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}
	@Override
	public String toString() {
		return "Categories [noCategorie=" + noCategorie + ", nomCategorie=" + nomCategorie + "]";
	}
	
	

}
