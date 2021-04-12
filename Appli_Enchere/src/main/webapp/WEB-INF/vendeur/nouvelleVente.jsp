<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<link rel="stylesheet" href="css/main.css" media="screen"/>
		<title>Appli-Encheres nouvelle-vente</title>
	</head>
	<body>
	
	<%@ include file="../header.jspf" %> <!-- code d'inclusion de la jspf header /!\ attention au chemin du fichier, il peut etre different -->
	
	
		<div class="container-fluid main">
			<div class="row">
				<div class="col-12">
					<p class="h1 text-center">nouvelle vente</p>
				</div>
			</div>
			<div class="row">
				<div class="col-4">
					<img src="source/picture/img.png" class="img-thumbnail" alt="200x200" >
				</div>				
				<div class="col-8">
					<form>
						<div class="form-group row">
						    <label for="nomArticle" class="col-sm-2 col-form-label">Article</label>
						    <div class="col-sm-6">
						    	<input type="text" class="form-control" id="nomArticle" name="nomArticle">
						    </div>
						</div>
						<div class="form-group row">
						    <label for="descriptionArticle" class="col-sm-2 col-form-label">description</label>
						    <div class="col-sm-6">
						    	<textArea class="form-control" id="descriptionArticle" name="descriptionArticle"></textArea>
						    </div>
						</div>
						<div class="form-group row">
						    <label for="categorieArticle" class="col-sm-2 col-form-label">catégorie</label>
						    <div class="col-sm-6">
						    	<select class="form-control" id="categorieArticle" name="categorieArticle">
						    		<option value="1">cat1</option>
						    		<option value="2">cat2</option>
						    	</select>
						    </div>
						</div>
						<div class="form-group row">
						    <label for="uploadImage" class="col-sm-2 col-form-label">photo de l'article</label>
						    <div class="col-sm-6">
						    	<input type="file" id="uploadImage" name="uploadImage">
						    </div>
						</div>
						<div class="form-group row">
						    <label for="prixInitialArticle" class="col-sm-2 col-form-label">Mise à prix</label>
						    <div class="col-sm-6">
						    	<input type="number"  id="prixInitialArticle" name="prixInitialArticle">
						    </div>
						</div>
						<div class="form-group row">
						    <label for="dateDebutArticle" class="col-sm-2 col-form-label">Début de l'enchère</label>
						    <div class="col-sm-6">
						    	<input type="datetime-local"  id="dateDebutArticle" name="dateDebutArticle">
						    </div>
						</div>
						<div class="form-group row">
						    <label for="dateFinArticle" class="col-sm-2 col-form-label">Fin de l'enchère</label>
						    <div class="col-sm-6">
						    	<input type="datetime-local"  id="dateFinArticle" name="dateFinArticle">
						    </div>
						</div>
						<div class="row">
							<div class="col-8">
								<div class="container border">
									<div class="encard-title">
									Retrait
									</div>
									<div class="form-group row">
									    <label for="nomRue" class="col-sm-2 col-form-label">Rue :</label>
									    <div class="col-sm-6">
									    	<input type="text" class="form-control" id="nomRue" name="nomRue">
									    </div>
									</div>
									<div class="form-group row">
									    <label for="codePostal" class="col-sm-2 col-form-label">Code postal :</label>
									    <div class="col-sm-6">
									    	<input type="text" class="form-control" id="codePostal" name="codePostal">
									    </div>
									</div>
									<div class="form-group row">
									    <label for="nomVille" class="col-sm-2 col-form-label">Ville :</label>
									    <div class="col-sm-6">
									    	<input type="text" class="form-control" id="nomVille" name="nomVille">
									    </div>
									</div>
								</div>	
							</div>					
						</div>
						<div class="row">
							<div class="col-8">
								<div class="container d-flex justify-content-between p-5">
									<button type="button" class="btn btn-light">Enregister</button>
									<button type="button" class="btn btn-light">Annuler</button>
									<button type="button" class="btn btn-light">Annuler la vente</button>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		
		</div>
		
	
	<%@ include file="../footer.jspf" %> <!-- code d'inclusion de la jspf footer /!\ attention au chemin du fichier, il peut etre different -->
	
		
	
	</body>
</html>