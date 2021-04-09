<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="org.projet_encheres.bo.Articles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>testArticles</title>
</head>
<body>
	<% 	List<Articles> listeDeTousLesArticles = (List<Articles>)request.getAttribute("listeArticles");  %>
	<h1>visualiser article</h1>
	<% for (Articles article:listeDeTousLesArticles)
			{
			%>	<div class="article">
					<span>
						no article : <%=article.getNoArticle()%> <br>
						nom : <%=article.getNomArticle()%> <br>
						description : <%=article.getDescriptionArticle()%> <br>
						date debut :<%=article.getDateDebutEnchere()%> <br>
						date fin :<%=article.getDateFinEnchere()%> <br>
						prix initial : <%=article.getPrixInitial()%> <br>
						prix vente : <%=article.getPrixVente()%> <br> 
						no Vendeur : <%=article.getNoVendeur()%> <br>
						categorie : <%=article.getNoCategorie()%> <br>
						annulée ? :<%=article.isAnnule()%> <br>
						<hr /><hr />
					</span>
				</div>
				
			<%}; %>
		
</body>
</html>