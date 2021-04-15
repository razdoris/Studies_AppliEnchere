<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Connexion utilisateur</title>

 <!-- Bootstrap core CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>

<body>
<%@ include file="headerConnexion.jspf" %> <!-- code d'inclusion de la jspf header /!\ attention au chemin du fichier, il peut etre different -->
<br>
<br>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			
			<form method="post" action="connexionUtilisateur" role="form">
				<div class="form-group">
					 
					<label for="pseudo">Pseudo</label>
					<input type="text" class="form-control" name="pseudo" id="pseudo" value="<c:out value="${param.pseudo}"/>"/>
						<!--<p class="font-italic text-danger"><small>${erreur['pseudo']}</small></p>-->
				</div>
				<div class="form-group">
					 
					<label for="password">Mot de Passe</label>
					<input type="password" class="form-control" name="password" id="password" />
					<!--  <p class="font-italic text-danger"><small>${erreur['password']}</small></p>-->
				</div>
				
				<div class="checkbox">
					 
					<label>
						<input type="checkbox"/>Rester Connecté</label>
				</div> 
				<button type="submit" class="btn btn-primary" >Connexion</button>
				<br><br>
				<p class="font-italic text-danger"><small>${resultat}</small></p>
			</form> <a href="<%=request.getContextPath() %>/inscriptionUtilisateur" class="btn btn-lg btn-secondary btn-block active" type="button">Créer un compte</a>
		</div>
	</div>
</div>
<%@ include file="footer.jspf" %> <!-- code d'inclusion de la jspf footer /!\ attention au chemin du fichier, il peut etre different -->
</body>
</html>