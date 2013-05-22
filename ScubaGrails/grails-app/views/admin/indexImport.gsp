<%@ page import="scubagrails.NiveauApnee" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">		
		<title>Gestion des niveaux</title>
	</head>
	<body>
		<a href="#list-niveau" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
		<g:if test="${session?.user?.admin}">
			<g:render template="/layouts/abonneSearch" />
		</g:if>
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link controller="admin" action="index" class="adminHome"><g:message code="scubaGrails.nav.homeAdmin.label"/></g:link></li>
			</ul>
		</div>
		<div id="importAbonne" class="content scaffold-list" role="main">
			
			
			
			<h1>Veuillez choisir un fichier à importer : </h1>
			
			<br />
			
			<g:form controller="admin" action="importAbonne" enctype="multipart/form-data">
				
				
				
				
				<div class="fieldcontain">
					<label for="nomFeuille">
						Nom de la feuille Excel
						<span class="required-indicator">*</span>
					</label>
					<g:textField name="nomFeuille" maxlength="50" required=""/>
				</div>
				
				<div class="fieldcontain">
					<label for="nomFeuille">
						Fichier
						<span class="required-indicator">*</span>
					</label>
					<input type="file" name="file" id="file" required="" />
				</div>		
								
				<br />
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="Importer" />
				</fieldset>
			</g:form>
			
			

		</div>
	</body>
</html>
