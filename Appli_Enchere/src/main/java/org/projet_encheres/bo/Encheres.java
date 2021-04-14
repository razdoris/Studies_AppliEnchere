package org.projet_encheres.bo;

import java.time.LocalDate;

public class Encheres {
	
	private int noEnchere;
	private int noUtilisateur;
	private int noArticle;
	private LocalDate dateEnchere;
	private int montantEnchere;
	private String nomUtilisateurMaxEnchere;
	/**
	 * 
	 */
	public Encheres() {
	}
	
	/**
	 * @param noEnchere
	 * @param noUtilisateur
	 * @param noArticle
	 * @param dateEnchere
	 * @param montantEnchere
	 */
	public Encheres(int noEnchere, int noUtilisateur, int noArticle, LocalDate dateEnchere, int montantEnchere) {
		this.noEnchere = noEnchere;
		this.noUtilisateur = noUtilisateur;
		this.noArticle = noArticle;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}
	
	/**
	 * @param noEnchere
	 * @param noUtilisateur
	 * @param noArticle
	 * @param dateEnchere
	 * @param montantEnchere
	 * @param nomUtilisateurMaxEnchere
	 */
	public Encheres(int noEnchere, int noUtilisateur, int noArticle, LocalDate dateEnchere, int montantEnchere,
			String nomUtilisateurMaxEnchere) {
		super();
		this.noEnchere = noEnchere;
		this.noUtilisateur = noUtilisateur;
		this.noArticle = noArticle;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.nomUtilisateurMaxEnchere = nomUtilisateurMaxEnchere;
	}

	/**
	 * @return the noEnchere
	 */
	public int getNoEnchere() {
		return noEnchere;
	}

	/**
	 * @param noEnchere the noEnchere to set
	 */
	public void setNoEnchere(int noEnchere) {
		this.noEnchere = noEnchere;
	}

	/**
	 * @return the noUtilisateur
	 */
	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	/**
	 * @param noUtilisateur the noUtilisateur to set
	 */
	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
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
	 * @return the dateEnchere
	 */
	public LocalDate getDateEnchere() {
		return dateEnchere;
	}

	/**
	 * @param dateEnchere the dateEnchere to set
	 */
	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	/**
	 * @return the montantEnchere
	 */
	public int getMontantEnchere() {
		return montantEnchere;
	}

	/**
	 * @param montantEnchere the montantEnchere to set
	 */
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	/**
	 * @return the nomUtilisateurMaxEnchère
	 */
	public String getNomUtilisateurMaxEnchere() {
		return nomUtilisateurMaxEnchere;
	}

	/**
	 * @param nomUtilisateurMaxEnchère the nomUtilisateurMaxEnchère to set
	 */
	public void setNomUtilisateurMaxEnchere(String nomUtilisateurMaxEnchere) {
		this.nomUtilisateurMaxEnchere = nomUtilisateurMaxEnchere;
	}

	@Override
	public String toString() {
		return "Encheres [noEnchere=" + noEnchere + ", noUtilisateur=" + noUtilisateur + ", noArticle=" + noArticle
				+ ", dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere + ", nomUtilisateurMaxEnchère="
				+ nomUtilisateurMaxEnchere + "]";
	}

	
	
	

}
