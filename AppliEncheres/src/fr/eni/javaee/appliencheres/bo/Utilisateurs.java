package fr.eni.javaee.appliencheres.bo;

public class Utilisateurs {

	private Integer no_utilisateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String rue;
	private String code_postal;
	private String ville;
	private String mot_de_passe;
	private Integer credit;
	private Boolean administrateur;
	
	
	public Utilisateurs() {
		super();
	}


	public Utilisateurs(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String code_postal, String ville, String mot_de_passe) {
		super();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
		this.mot_de_passe = mot_de_passe;
	}


	public Utilisateurs(Integer no_utilisateur, String pseudo, String nom, String prenom, String email,
			String telephone, String rue, String code_postal, String ville, String mot_de_passe, Integer credit,
			Boolean administrateur) {
		super();
		this.no_utilisateur = no_utilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
		this.mot_de_passe = mot_de_passe;
		this.credit = credit;
		this.administrateur = administrateur;
	}


	public Utilisateurs(String pseudo, String nom, String prenom, String email,
			String telephone, String rue, String code_postal, String ville) {
		super();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
	}


	public Utilisateurs(Integer no_utilisateur) {
		super();
		this.no_utilisateur = no_utilisateur;
	}


	public Integer getNo_utilisateur() {
		return no_utilisateur;
	}


	public String getPseudo() {
		return pseudo;
	}


	public String getNom() {
		return nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public String getEmail() {
		return email;
	}


	public String getTelephone() {
		return telephone;
	}


	public String getRue() {
		return rue;
	}


	public String getCode_postal() {
		return code_postal;
	}


	public String getVille() {
		return ville;
	}


	public String getMot_de_passe() {
		return mot_de_passe;
	}


	public Integer getCredit() {
		return credit;
	}


	public Boolean getAdministrateur() {
		return administrateur;
	}


	public void setNo_utilisateur(Integer no_utilisateur) {
		this.no_utilisateur = no_utilisateur;
	}


	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public void setRue(String rue) {
		this.rue = rue;
	}


	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}


	public void setVille(String ville) {
		this.ville = ville;
	}


	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}


	public void setCredit(Integer credit) {
		this.credit = credit;
	}


	public void setAdministrateur(Boolean administrateur) {
		this.administrateur = administrateur;
	}


	@Override
	public String toString() {
		return "Utilisateurs [" + (no_utilisateur != null ? "no_utilisateur=" + no_utilisateur + ", " : "")
				+ (pseudo != null ? "pseudo=" + pseudo + ", " : "") + (nom != null ? "nom=" + nom + ", " : "")
				+ (prenom != null ? "prenom=" + prenom + ", " : "") + (email != null ? "email=" + email + ", " : "")
				+ (telephone != null ? "telephone=" + telephone + ", " : "") + (rue != null ? "rue=" + rue + ", " : "")
				+ (code_postal != null ? "code_postal=" + code_postal + ", " : "")
				+ (ville != null ? "ville=" + ville + ", " : "")
				+ (mot_de_passe != null ? "mot_de_passe=" + mot_de_passe + ", " : "")
				+ (credit != null ? "credit=" + credit + ", " : "")
				+ (administrateur != null ? "administrateur=" + administrateur : "") + "]";
	}
}
