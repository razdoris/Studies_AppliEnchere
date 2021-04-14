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
		<title>Brocanchere</title>
	</head>
	<body>
	<%@ include file="header.jspf" %> 
	<div class="container-fluid main">
		<div class="row">
			<div class="col-12 pb-5">
				<p class="h1 text-center">Liste des enchères ${user.no_utilisateur}</p>
			</div>
		</div>
		<form class="row" method="post" action="">
			<div class="col-5 offset-1">
				<div class="form-group">
					<div class="row">
						<label for="filtreNom" class="col-2 col-form-label">Filtre</label>
					</div>						
					<div class="row">
						<input type="text" class="form-control col-10" id="filtreNom" name="filtreNom" value="${filtreNom}">
					</div>  				    
				</div>
				<div class="form-group row">
				    <label for="filtreCategorie" class="col-sm-2 col-form-label ">catégorie</label>
				    <div class="col-sm-5">
						<select class="form-control" id="filtreCategorie" name="filtreCategorie" required>
							<option value="0" selected></option>
							<c:forEach var="categorie" items="${categories}">
				    			<c:choose>
				    				<c:when test="${categorie.noCategorie==filtreCategorie}">
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
				<c:if test="${!empty user}">
					<div class="row">
						<div class="col-6">
							<input type="radio" name="persoOuiNon" id="persoOuiNon1" class="form-check-input">
							<label class="form-check-label" for="persoOuiNon1">
							    Achats
							</label>
							<div class="choixAcheteur">
								<div class="offset-1">
									<input type="checkbox" name="choixEnchereAcheteur" id="enchereOuverte" class="form-check-input">
									<label class="form-check-label" for="enchereOuverte">
									    enchères ouvertes
									</label>
								</div>
								<div class="offset-1">
									<input type="checkbox" name="choixEnchereAcheteur" id="mesEnchereEnCours" class="form-check-input">
									<label class="form-check-label" for="mesEnchereEnCours">
									    mes enchère en cours
									</label>
								</div>
								<div class="offset-1">
									<input type="checkbox" name="choixEnchereAcheteur" id="mesEnchereGagnee" class="form-check-input">
									<label class="form-check-label" for="enchereOuverte">
									    mes enchères gagnées
									</label>
								</div>
							</div>
						</div>
						<div class="col-6">
							<input type="radio" name="persoOuiNon" id="persoOuiNon2" class="form-check-input">
							<label class="form-check-label" for="persoOuiNon2">
							    Mes Ventes
							</label>
							<div class="choixVendeur">
								<div class="offset-1">
									<input type="checkbox" name="choixEnchereVendeur" id="venteEnCoure" class="form-check-input">
									<label class="form-check-label" for="venteEnCoure">
									    mes ventes en cours
									</label>
								</div>
								<div class="offset-1">
									<input type="checkbox" name="choixEnchereVendeur" id="venteNonDebute" class="form-check-input">
									<label class="form-check-label" for="venteNonDebute">
									    ventes non débutées
									</label>
								</div>
								<div class="offset-1">
									<input type="checkbox" name="choixEnchereVendeur" id="venteTermine" class="form-check-input">
									<label class="form-check-label" for="venteTermine">
									    ventes terminées
									</label>
								</div>
							</div>
						</div>
					</div>
				</c:if>
			</div>
			<div class="col-6">
				<input type="submit" value="Rechercher">
			</div>
		</form>
	</div>
	
	
	
	<%@ include file="footer.jspf" %> 
	</body>
</html>