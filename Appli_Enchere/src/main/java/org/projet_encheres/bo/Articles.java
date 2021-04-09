package org.projet_encheres.bo;

import java.time.LocalDate;

public class Articles {
	
	private int noArticle;
	private String nomArticle;
	private String descriptionArticle;
	private LocalDate dateDebutEnchere;
	private LocalDate dateFinEnchere;
	private int prixInitial;
	private int prixVente;
	private int noCategorie;
	private int noVendeur;
	private boolean annule;
	private Retrait adresseRetrait;
	
	
	/**
	 * constructor without element
	 */
	public Articles() {
		super();
	}


	/**
	 * <h1>constructor with parameters<h1>
	 * @param nomArticle : article's name
	 * @param descriptionArticle : a light description of the article
	 * @param dateDebutEnchere : start date of sale
	 * @param dateFinEnchere : end date of sale
	 * @param prixInitial : first price
	 * @param noCategorie :  the number of the article's category
	 * @param annule : true if the owner delete the sale
	 */
	public Articles(String nomArticle, String descriptionArticle, LocalDate dateDebutEnchere, LocalDate dateFinEnchere,
			int prixInitial, int noCategorie, boolean annule) {
		super();
		this.nomArticle = nomArticle;
		this.descriptionArticle = descriptionArticle;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEnchere = dateFinEnchere;
		this.prixInitial = prixInitial;
		this.noCategorie = noCategorie;
		this.annule = annule;
	}


	/**
	 * @return the noArticle
	 */
	public int getNoArticle() {
		return noArticle;
	}


	/**
	 * @param noArticle the noArticle to set
	 */
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}


	/**
	 * @return the nomArticle
	 */
	public String getNomArticle() {
		return nomArticle;
	}


	/**
	 * @param nomArticle the nomArticle to set
	 */
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}


	/**
	 * @return the descriptionArticle
	 */
	public String getDescriptionArticle() {
		return descriptionArticle;
	}


	/**
	 * @param descriptionArticle the descriptionArticle to set
	 */
	public void setDescriptionArticle(String descriptionArticle) {
		this.descriptionArticle = descriptionArticle;
	}


	/**
	 * @return the dateDebutEnchere
	 */
	public LocalDate getDateDebutEnchere() {
		return dateDebutEnchere;
	}


	/**
	 * @param dateDebutEnchere the dateDebutEnchere to set
	 */
	public void setDateDebutEnchere(LocalDate dateDebutEnchere) {
		this.dateDebutEnchere = dateDebutEnchere;
	}


	/**
	 * @return the dateFinEnchere
	 */
	public LocalDate getDateFinEnchere() {
		return dateFinEnchere;
	}


	/**
	 * @param dateFinEnchere the dateFinEnchere to set
	 */
	public void setDateFinEnchere(LocalDate dateFinEnchere) {
		this.dateFinEnchere = dateFinEnchere;
	}


	/**
	 * @return the prixInitial
	 */
	public int getPrixInitial() {
		return prixInitial;
	}


	/**
	 * @param prixInitial the prixInitial to set
	 */
	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}


	/**
	 * @return the prixVente
	 */
	public int getPrixVente() {
		return prixVente;
	}


	/**
	 * @param prixVente the prixVente to set
	 */
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
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
	 * @return the noVendeur
	 */
	public int getNoVendeur() {
		return noVendeur;
	}


	/**
	 * @param noVendeur the noVendeur to set
	 */
	public void setNoVendeur(int noVendeur) {
		this.noVendeur = noVendeur;
	}


	/**
	 * @return the annule
	 */
	public boolean isAnnule() {
		return annule;
	}


	/**
	 * @param annule the annule to set
	 */
	public void setAnnule(boolean annule) {
		this.annule = annule;
	}


	/**
	 * @return the adresseRetrait
	 */
	public Retrait getAdresseRetrait() {
		return adresseRetrait;
	}


	/**
	 * @param adresseRetrait the adresseRetrait to set
	 */
	public void setAdresseRetrait(Retrait adresseRetrait) {
		this.adresseRetrait = adresseRetrait;
		adresseRetrait.setArticleRetirerIci(this);
	}


	@Override
	public String toString() {
		return "Articles [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", descriptionArticle="
				+ descriptionArticle + ", dateDebutEnchere=" + dateDebutEnchere + ", dateFinEnchere=" + dateFinEnchere
				+ ", prixInitial=" + prixInitial + ", prixVente=" + prixVente + ", noCategorie=" + noCategorie
				+ ", noVendeur=" + noVendeur + ", annule=" + annule + ", adresseRetrait=" + adresseRetrait + "]";
	}


	
	
	
	
	
	

	
	
}
