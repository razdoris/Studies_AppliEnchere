<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Inscription</title>


<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<!-- CSS -->
<link rel="stylesheet" href="css/creationCompte.css">


</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<h3>ENI-Enchères</h3>
				<h4 class="text-center">Mon profil</h4><br>
			</div>
		</div>
		<form method="post" action="inscriptionUtilisateur" role="form">
			<div class="form-group">
			
				<div class="form-group row">
						<label for="pseudo" class="col-lg-1 col-sm-5 col-form-label">Pseudo</label>
						<div class="col-lg-5 col-sm-8">
						<input type="text" class="form-control" name="pseudo" id="pseudo" />
					</div>
						<label for="nom" class="col-lg-1 col-sm-5 col-form-label">Nom</label>
						<div class="col-lg-5 col-sm-8">
						<input type="text" class="form-control" name="nom"  id="nom" />
					</div>
				</div>
				
				<div class="form-group row">
						<label for="prenom" class="col-lg-1 col-sm-5 col-form-label">Prénom</label>
						<div class="col-lg-5 col-sm-8">
						<input type="text" class="form-control" name="prenom" id="prenom" />
					</div>

						<label for="email" class="col-lg-1 col-sm-5 col-form-label">Email</label>
						<div class="col-lg-5 col-sm-8">
						<input type="email" class="form-control" name="email" id="email" />
					</div>
				</div>
				
				<div class="form-group row">	

						<label for="telephone" class="col-lg-1 col-sm-5 col-form-label">Téléphone</label>
						<div class="col-lg-5 col-sm-8">
						<input type="tel" class="form-control" name="telephone" id="telephone" />
					</div>

						<label for="rue" class="col-lg-1 col-sm-5 col-form-label">Rue</label>
						<div class="col-lg-5 col-sm-8">
						<input type="text" class="form-control" name="rue" id="rue" />
					</div>
				</div>
				
				<div class="form-group row">
						<label for="cp" class="col-lg-1 col-sm-5 col-form-label">Code Postal</label>
						<div class="col-lg-5 col-sm-8">
						<input type="number" class="form-control" name="cp" id="cp" />
					</div>

						<label for="ville" class="col-lg-1 col-sm-5 col-form-label">Ville</label>
						<div class="col-lg-5 col-sm-8">
						<input type="text" class="form-control" name="ville" id="ville" />
					</div>
				</div>
				
				<div class="form-group row">	
						<label for="password" class="col-lg-1 col-sm-5 col-form-label">Mot de passe</label>
						<div class="col-lg-5 col-sm-8">
						<input type="password" class="form-control" name="password" id="password" />
					</div>

						<label for="confirmation" class="col-lg-1 col-sm-5 col-form-label">Confirmation</label>
						<div class="col-lg-5 col-sm-8">
						<input type="password" class="form-control" name="confirmation"  id="confirmation" />
					</div>
					</div>
					
					<div class="row">
		<div class="col-md-6">
			 
			<button type="submit" class=" btn btn-outline-dark btn-lg float-right class1">Créer</button>
		</div>
		<div class="col-md-6">
			 
			<button type="reset" class="btn btn-outline-dark btn-lg class1">Annuler</button>
		</div>
		</div>
	</div>



	</form>
</div>


</body>
</html>