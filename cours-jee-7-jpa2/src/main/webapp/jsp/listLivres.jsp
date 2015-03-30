<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste des livres</title>
<%@include file="head.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/formulaire-elegant.css">
</head>
<body>



<div class="container">
	 			<div class="row centered-form">
	       	        <div class="col-xs-12 col-sm-8 col-md-8 col-sm-offset-2 col-md-offset-2">
			        	<div class="panel panel-default">
								Livre recherché : <textarea id="titre_rec" name="titre_rech" class="form-control" placeholder="Titre du livre recherché"></textarea>
			    			<input type="button" id="BoutonRechercherLivre" value="Rechercher" class="btn btn-info btn-block">
	    				</div>
	    			</div>
	    		</div>	    
	    		<div align="center" ><span id="livre_recherche"> </span></div>
	    		
	    
	   
	 			<div class="row centered-form">
	       	        <div class="col-xs-12 col-sm-8 col-md-8 col-sm-offset-2 col-md-offset-2">
			        	<div class="panel panel-default">
								Genre recherché : 
								<div class="form-group control-group">
			    				<select id="genre_rec"  class="form-control" name="genre">
				    				<c:forEach items="${genres}" var="livre">
				    				
						    			<option value="${livre.genre.libelle}">${livre.genre.libelle}</option>
						    				
						    			
						    								
				    			</c:forEach>
				    			
	   							</select>
								</div>	
								
			    			<input type="button"  id="BoutonRechercherLivreParGenre" value="Rechercher" class="btn btn-info btn-block">
	    				</div>
	    			</div>
	    		</div>
	    	<div align="center"><span id="livres_recherche"> </span></div>
	    
        <div class="row centered-form">
	       	
	        <div class="col-xs-12 col-sm-8 col-md-8 col-sm-offset-2 col-md-offset-2">
	        	<div class="panel panel-default">
	    
	        		<div class="panel-heading">
			    		<h3 class="panel-title">Liste des livres enregistrés&nbsp;<a href="${pageContext.request.contextPath}/addNewLivre" style="float:right">Créer un livre</a></h3>
		 			</div>
		 			<div id="ListeLivres" class="panel-body">
		 				<table class="table table-striped">
		 					<thead>
			 					<tr>
			 						<th>#</th>
			 						<th>ISBN</th>
			 						<th>Titre</th>
			 						<th>Genre</th>
			 						<th>Etat</th>
			 						<th>Dernier Emprunteur</th>
			 						<th>Actions</th>
			 					</tr>
		 					</thead>
		 					<tbody>
		 						<c:choose >
		 							<c:when test="${empty param['titre_rech']}">
					 					<c:forEach items="${livres}" var="livre">
							    			<tr>
							    				<td>${livre.id}</td>
							    				<td>${livre.isbn}</td>
							    				<td>${livre.titre}</td>
							    				<td>${livre.genre.libelle}</td>
							    				<td>${livre.etat}</td>
							    				<td>${livre.dernierEmprunteur}</td>
							    				
							    				<td><a href="${pageContext.request.contextPath}/editLivre?id=${livre.id}">modifier</a>&nbsp;<a href="${pageContext.request.contextPath}/deleteLivre?id=${livre.id}">supprimer</a></td>
							    			</tr>
							    		</c:forEach>
							    	</c:when>
							    	<c:otherwise >
							    		<tr>
							    				<td>${livreRecherche.id}</td>
							    				<td>${livreRecherche.isbn}</td>
							    				<td>${livreRecherche.titre}</td>
							    				<td>${livreRecherche.genre.libelle}</td>
							    				<td>${livreRecherche.etat}</td>
							    				<td>${livreRecherche.dernierEmprunteur}</td>
							    				
							    				<td><a href="${pageContext.request.contextPath}/editLivre?id=${livreRecherche.id}">modifier</a>&nbsp;<a href="${pageContext.request.contextPath}/deleteLivre?id=${livreRecherche.id}">supprimer</a></td>
							    		</tr>
							    	</c:otherwise>
							    </c:choose>
				    		</tbody>
				    	</table>
			    	</div>
			    	
				
			    	
	    		</div>
	   		</div>
	   		
	   		
	   		
	   		
	   			   		
	   		
    	</div>
    	
    	
    	
    	
    	<c:if test="${empty param['titre_rech'] }">
    	 	<div class="row centered-form">
	        <div class="col-xs-12 col-sm-8 col-md-8 col-sm-offset-2 col-md-offset-2">
	        	<div class="panel panel-default">
    	
	   			<span >Nombre d'ouvrage total : 
	   				<c:forEach items="${livres}" varStatus="vs">
	   					<c:if test="${vs.last}" >
	   						<c:out value="${vs.count}"/> 
	   					</c:if>
	   				</c:forEach>
	   			
	   			</span><br>
	   			<span >Nombre d'ouvrage emprunté : 
	   				<c:set var="count" value="0" scope="page" />
	   				<c:forEach items="${livres}" var="livre" varStatus="vs">
	   					<c:if test="${ 'Emprunte' eq livre.etat }" >
	   						<c:set var="count" value="${count + 1}" scope="page"/>
	   					</c:if>
	   					<c:if test="${vs.last}" >
	   						<c:out value="${count}"/> 
	   					</c:if>
	   				</c:forEach>
	   			
	   			
	   		    </span>
	   			</div>
	   		</div>
	   		</div>
	   	</c:if>
	   	
	   	
	   	
    </div>
    
    
    
	   		
    <script type="text/javascript">
			    	
		$("#BoutonRechercherLivre").click(function (){
			$.ajax({
				url : '${pageContext.request.contextPath}/searchLivre?titre_rech=' + $("#titre_rec").val()+'',
				success : function (data, textStatus, jqXHR) {
					$('#livre_recherche').text(data);
				}
			});
		});
		
		$("#BoutonRechercherLivreParGenre").click(function (){	
			$.ajax({
				url : '${pageContext.request.contextPath}/searchGenre?genre_rech=' + $("#genre_rec").val()+'',
				success : function (data, textStatus, jqXHR) {
					$('#livres_recherche').html(data);
				}
			});
		});
   	
	</script>
    
    
</body>
</html>
