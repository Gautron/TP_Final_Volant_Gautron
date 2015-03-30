<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Enregistrer un livre</title>
<%@include file="head.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/formulaire-elegant.css">
</head>
<body>
<div class="container">
        <div class="row centered-form">
        <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
        	<div class="panel panel-default">
        		<div class="panel-heading">
			    		<h3 class="panel-title">Création d'un nouveau livre</h3>
			 			</div>
			 			<div class="panel-body">
			    		<form role="form" method="post" action="${pageContext.request.contextPath}/saveLivre">
	    					<div class="form-group ${form.errors.isbn.kindOfMessage}">
	               				<input id="isbn" name="isbn" type="text" placeholder="Numero ISBN du livre" class="form-control input-sm" value="${form.bean.isbn}">
	               				<c:if test="${form.errors.isbn.error}">
									<span class="help-block"> ${form.errors.isbn.errorMessage}</span>
								</c:if>
	  	            		</div>
	    		
			    			<div class="form-group control-group ${form.errors.titre.kindOfMessage}">
								<textarea id="titre" name="titre" class="form-control" placeholder="Titre du livre">${form.bean.titre}</textarea>
								<c:if test="${form.errors.titre.error}">
									 <span class="help-block"> ${form.errors.titre.errorMessage}</span>
								</c:if>
			    			</div>
			    			
			    			<div class="form-group control-group ${form.errors.etat.kindOfMessage}">
								<div  class="form-group control-group">
			    				<select class="form-control" id="etat" name="etat" value="${form.bean.etat}">
						    			<option value="Emprunte" selected="selected">Emprunte</option>		
						    			<option value="Rendu">Rendu</option>				
	   							</select>
								</div>		
								<c:if test="${form.errors.etat.error}">
									 <span class="help-block"> ${form.errors.etat.errorMessage}</span>
								</c:if>
			    			</div>
			    			
			    			<div class="form-group control-group ${form.errors.titre.kindOfMessage}">
								<textarea id="dernierEmprunteur" name="dernierEmprunteur" class="form-control" placeholder="Dernier Emprunteur(laisser vide si le livre n'a jamais été emprunté)">${form.bean.dernierEmprunteur}</textarea>
								<c:if test="${form.errors.dernierEmprunteur.error}">
									 <span class="help-block"> ${form.errors.dernierEmprunteur.errorMessage}</span>
								</c:if>
			    			</div>
			    			
			    			<div class="form-group control-group">
			    				<select class="form-control" name="genre">
				    				<c:forEach items="${form.bean.allGenres}" var="genre">
				    					<c:choose>
				    						<c:when test="${genre.selected}">
						    					<option value="${genre.id}" selected="selected">${genre.libelle}</option>
				    						</c:when>
				    						<c:otherwise>
						    					<option value="${genre.id}">${genre.libelle}</option>					
				    						</c:otherwise>
				    					</c:choose>
				    				</c:forEach>
	   							</select>
							</div>				
			    			<input type="submit" value="Enregistrer le livre" class="btn btn-info btn-block">
			    			<input type="hidden" name="id" value="${form.bean.id}"/>
			    		</form>
			    	</div>
	    		</div>
    		</div>
    	</div>
    </div>
</body>
</html>
