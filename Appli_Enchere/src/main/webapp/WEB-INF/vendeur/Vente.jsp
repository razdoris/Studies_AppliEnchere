<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="org.projet_encheres.bo.Categories" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<link rel="stylesheet" href="css/main.css" media="screen"/>
		<title>Appli-Encheres nouvelle-vente</title>
	</head>
	<body>
	<%@ include file="../header.jspf" %> 
	
	<!-- affichage des info ventes selon la vision du vendeur tant que la vente n'est pas fini -->
	<c:choose>
		<c:when test="${fn:contains(servlet,'nouvelleVente')}">
		<!-- cas d'un nouvelle vente ou de la modification d'une ventes pour un vendeur -->
			<div class="container-fluid main">
				<div class="row">
					<div class="col-12 pb-5">
						<p class="h1 text-center">
							<c:choose>
					    		<c:when test="${fn:contains(servlet,'modifier')}">
					    			Modifier vente
					    		</c:when>
					    		<c:otherwise>
					    			Nouvelle vente
					    		</c:otherwise>
					    	</c:choose>
						</p>
					</div>
				</div>
				<div class="row">
					<div class="col-3 offset-1 ">
						<div class="pt-5">
							<img src="source/picture/img.png" class="img-thumbnail mx-auto" style="width: 200px;" alt="200x200" >
						</div>
					</div>				
					<div class="col-8">
					<p class="explaination col-7">champ obligatoire</p>
						<form method="post" action="">
							<div class="form-group required row">
							    <label for="nomArticle" class="col-sm-2 col-form-label">article</label>
							    <div class="col-sm-5">
							    	<input type="text" class="form-control" id="nomArticle" name="nomArticle" required value="${articleAAfficher.nomArticle}">
							    </div>
							</div>
							<div class="form-group required row">
							    <label for="descriptionArticle" class="col-sm-2 col-form-label">description</label>
							    <div class="col-sm-5">
									<textArea class="form-control" id="descriptionArticle" name="descriptionArticle" required>${articleAAfficher.descriptionArticle}</textArea>
							    </div>
							</div>
							<div class="form-group required row">
							    <label for="categorieArticle" class="col-sm-2 col-form-label ">catégorie</label>
							    <div class="col-sm-5">
									<select class="form-control" id="categorieArticle" name="categorieArticle" required>
							    		<c:forEach var="categorie" items="${categories}">
							    			<c:choose>
							    				<c:when test="${categorie.noCategorie==articleAAfficher.noCategorie}">
							    					<option value="${categorie.noCategorie}" selected>${categorie.nomCategorie}</option>
							    				</c:when>
							    				<c:otherwise>
							    					<option value="${categorie.noCategorie}">${categorie.nomCategorie}</option>
							    				</c:otherwise>
							    			</c:choose>
							    		</c:forEach>
							    	</select>
							    </div>
							</div>
							<div class="form-group row">
							    <label for="uploadImage" class="col-sm-2 col-form-label">photo de l'article</label>
							    <div class="col-sm-5">
							    	<input type="file" id="uploadImage"  class="form-control-file" name="uploadImage">
							    </div>
							</div>					
							<div class="form-group required row">
							    <label for="prixInitialArticle" class="col-sm-2 col-form-label" >Mise à prix</label>
							    <div class="col-sm-3">
							    	<input type="number" class="form-control" id="prixInitialArticle" name="prixInitialArticle" required value="${articleAAfficher.prixInitial}">
							    </div>
							</div>
							<div class="form-group required row">
							    <label for="dateDebutArticle" class="col-sm-2 col-form-label">Début de l'enchère</label>
							    <div class="col-sm-3">
							    	<input type="date" class="form-control" id="dateDebutArticle" name="dateDebutArticle" required value="${articleAAfficher.dateDebutEnchere}">
							    </div>
							</div>
							<div class="form-group required row">
							    <label for="dateFinArticle" class="col-sm-2 col-form-label">Fin de l'enchère</label>
							    <div class="col-sm-3">
							    	<input type="date" class="form-control" id="dateFinArticle" name="dateFinArticle" required value="${articleAAfficher.dateFinEnchere}">
							    </div>
							</div>
							<div class="row">
								<div class="col-8">
									<div class="container border">
										<div class="encard-title">
										Retrait
										</div>
										<div class="form-group row">
										    <label for="nomRue" class="col-sm-4 col-form-label">Rue :</label>
										    <div class="col-sm-6">
										    	<input type="text" class="form-control" id="nomRue" name="nomRue" value="${retraitAAfficher.rue}">
										    </div>
										</div>
										<div class="form-group row">
										    <label for="codePostal" class="col-sm-4 col-form-label">Code postal :</label>
										    <div class="col-sm-6">
										    	<input type="text" class="form-control" id="codePostal" name="codePostal" value="${retraitAAfficher.codePostal}">
										    </div>
										</div>
										<div class="form-group row">
										    <label for="nomVille" class="col-sm-4 col-form-label">Ville :</label>
										    <div class="col-sm-6">
										    	<input type="text" class="form-control" id="nomVille" name="nomVille" value="${retraitAAfficher.ville}">
										    </div>
										</div>
									</div>	
								</div>					
							</div>
							<div class="row">
								<div class="col-8">
									<div class="container d-flex justify-content-between p-5">
										<input type="submit" class="btn btn-light" name="action" value="Enregister">
										<input type="submit" class="btn btn-light" name="action" value="Annuler">
										<c:if test="${fn:contains(servlet,'modifier')}"><!-- modifier -->
											<input type="submit" class="btn btn-light"name="action" value="Annuler la vente">
										</c:if>									
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div class="container-fluid main">
				<div class="row">
					<div class="col-12 pb-5">
						<p class="h2 text-center">
							<c:choose>
					    		<c:when test="${fn:contains(servlet,'detail')}">
					    			${articleAAfficher.nomArticle}
					    		</c:when>
					    		<c:when test="${fn:contains(servlet,'resultat_acheteur_gagnant')}">
					    			Vous avez remporté la vente
					    		</c:when>
					    		<c:otherwise>
					    			${enchereAAfficher.nomUtilisateurMaxEnchere} à remporté la vente
					    		</c:otherwise>
					    	</c:choose>
						</p>
					</div>
				</div>
				<div class="row">
					<div class="col-3 offset-1 ">
						<div class="pt-5">
							<img src="source/picture/img.png" class="img-thumbnail mx-auto" style="width: 200px;" alt="200x200" >
						</div>
					</div>				
					<div class="col-8">
						<form method="post" action="">
							<div class="form-group row">
							    <div class="col-sm-7">
							    	<p>${articleAAfficher.nomArticle}"</p>
							    </div>
							</div>
							<div class="form-group row">
							    <label for="descriptionArticle" class="col-sm-2 col-form-label">description</label>
							    <div class="col-sm-5">
							    	<textArea readonly class="form-control-plaintext" id="descriptionArticle" name="descriptionArticle" >${articleAAfficher.descriptionArticle}</textArea>
							    </div>
							</div>
							<div class="form-group row">
							    <label for="categorieArticle" class="col-sm-2 col-form-label">catégorie</label>
							    <div class="col-sm-5">
						    		<c:forEach var="categorie" items="${categories}">
						    			<c:if test="${categorie.noCategorie==articleAAfficher.noCategorie}">
					    					<input type="text" readonly class="form-control-plaintext" id="categorieArticle" name="categorieArticle" value=${categorie.nomCategorie}>
					    				</c:if>
						    		</c:forEach>
							    </div>
							</div>
							<div class="form-group row">
							    <label for="meilleurOffre" class="col-sm-2 col-form-label">Meilleurs Offre</label>
							    <div class="col-sm-5">
							    	${enchereAAfficher.montantEnchere}
							    	<c:if test="${!fn:contains(servlet,'gagnant')}">
							    		par ${enchereAAfficher.nomUtilisateurMaxEnchere}
							    	</c:if> 							    	
							    </div>
							</div>
							<div class="form-group row">
							    <label for="prixInitialArticle" class="col-sm-2 col-form-label" >Mise à prix</label>
							    <div class="col-sm-3">
							    	<input type="number" readonly class="form-control-plaintext" id="prixInitialArticle" name="prixInitialArticle" value="${articleAAfficher.prixInitial}">
							    </div>
							</div>
							<div class="form-group row">
							    <label for="dateFinArticle" class="col-sm-2 col-form-label">Fin de l'enchère</label>
							    <div class="col-sm-3">
							    	<input type="date"readonly class="form-control-plaintext" id="dateFinArticle" name="dateFinArticle" value="${articleAAfficher.dateFinEnchere}">
							    </div>
							</div>
							<div class="form-group row">
							    <label for="retrait" class="col-sm-2 col-form-label">Retrait</label>
							    <div class="col-sm-3">
							    	<input type="text" readonly class="form-control-plaintext" id="retrait-rue" name="retrait-rue" value="${retraitAAfficher.rue}">
							    	<input type="text" readonly class="form-control-plaintext" id="retrait-cp_ville" name="retrait-cp_ville" value="${retraitAAfficher.codePostal} ${retraitAAfficher.ville}">
							    </div>
							</div>
							<c:choose>
								<c:when test=""> <!-- si détail acheteur-->
									<div class="form-group row">
									    <label for="vendeur" class="col-sm-2 col-form-label">Vendeur</label>
									    <div class="col-sm-3">
									    	<input type="text" readonly class="form-control-plaintext" id="retrait-rue" name="retrait-rue" value="${vendeur.pseudo}">
									    </div>
									</div>
									<div class="form-group row">
									    <label for="proposition" class="col-sm-2 col-form-label">Ma proposition</label>
									    <div class="col-sm-3">
									    	<input type="number" class="form-control" id="proposition" name="proposition" value="${enchereAAfficher.montantEnchere}">
									    </div>
									</div>
									<input type="submit" class="btn btn-light" name="action" value="Encherir">
								</c:when>
								<c:when test=""> <!-- si resultat vendeur acheteur-->
									<input type="submit" class="btn btn-light" name="action" value="Retrait effectué">
								</c:when>
								<c:otherwise>
									<button type="button" onclick = "history.back()">Back</button>
								</c:otherwise>
							</c:choose>
						</form>
					</div>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
		
		
	
	<%@ include file="../footer.jspf" %> <!-- code d'inclusion de la jspf footer /!\ attention au chemin du fichier, il peut etre different -->
	
		
	
	</body>
</html>