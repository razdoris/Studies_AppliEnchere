package org.projet_encheres.bo;

public class Retrait {

	private String rue;
	private String codePostal;
	private String ville;
	private int noArticleRetirerIci;
	
	/**
	 * 
	 */
	public Retrait() {
		super();
	}

	/**
	 * @param rue : street to collect the article
	 * @param codePostal : zip code to collect the article
	 * @param ville : city to collect the article
	 */
	public Retrait(String rue, String codePostal, String ville) {
		super();
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	/**
	 * @return the rue
	 */
	public String getRue() {
		return rue;
	}

	/**
	 * @param rue the rue to set
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}

	/**
	 * @return the codePostal
	 */
	public String getCodePostal() {
		return codePostal;
	}

	/**
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}	

	/**
	 * @return the noArticleRetirerIci
	 */
	public int getNoArticleRetirerIci() {
		return noArticleRetirerIci;
	}
	

	/**
	 * @param noArticleRetirerIci the noArticleRetirerIci to set
	 */
	public void setNoArticleRetirerIci(int noArticleRetirerIci) {
		this.noArticleRetirerIci = noArticleRetirerIci;
	}

	/**
	 * @param noArticleRetirerIci the noArticleRetirerIci to set
	 */
	public void setArticleRetirerIci(Articles article) {
		if(article.getAdresseRetrait() == this) {
			this.noArticleRetirerIci = article.getNoArticle();
		}
		
	}

	@Override
	public String toString() {
		return "Retrait [rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville + ", noArticleRetirerIci="
				+ noArticleRetirerIci + "]";
	}

	
	
	
}
