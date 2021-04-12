<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Connexion utilisateur</title>

 <!-- Bootstrap core CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>

<body>


<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<h3 class="text-left">ENI-Enchères</h3>
			<form method="post" action="connexion" role="form">
				<div class="form-group">
					 
					<label for="identifiant">Identifiant</label>
					<input type="text" class="form-control" id="identifiant"  />
				</div>
				<div class="form-group">
					 
					<label for="password">Mot de Passe</label>
					<input type="password" class="form-control" id="password" />
				</div>
				
				<div class="checkbox">
					 
					<label>
						<input type="checkbox" /> Rester Connecté</label>
				</div> 
				<button type="submit" class="btn btn-primary" >Connexion</button>
				<br><br>
			</form> <a href="#" class="btn btn-lg btn-secondary btn-block active" type="button">Créer un compte</a>
		</div>
	</div>
</div>

</body>
</html>