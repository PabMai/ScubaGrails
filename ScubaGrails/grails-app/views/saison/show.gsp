
<%@ page import="scubagrails.Saison" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'saison.label', default: 'Saison')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-saison" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-saison" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list saison">
			
				<g:if test="${saisonInstance?.libelle}">
				<li class="fieldcontain">
					<span id="libelle-label" class="property-label"><g:message code="saison.libelle.label" default="Libelle" /></span>
					
						<span class="property-value" aria-labelledby="libelle-label"><g:fieldValue bean="${saisonInstance}" field="libelle"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${saisonInstance?.dateDebut}">
				<li class="fieldcontain">
					<span id="dateDebut-label" class="property-label"><g:message code="saison.dateDebut.label" default="Date Debut" /></span>
					
						<span class="property-value" aria-labelledby="dateDebut-label"><g:formatDate date="${saisonInstance?.dateDebut}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${saisonInstance?.dateFin}">
				<li class="fieldcontain">
					<span id="dateFin-label" class="property-label"><g:message code="saison.dateFin.label" default="Date Fin" /></span>
					
						<span class="property-value" aria-labelledby="dateFin-label"><g:formatDate date="${saisonInstance?.dateFin}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${saisonInstance?.enregistrements}">
				<li class="fieldcontain">
					<span id="enregistrements-label" class="property-label"><g:message code="saison.enregistrements.label" default="Enregistrements" /></span>
					
						<g:each in="${saisonInstance.enregistrements}" var="e">
						<span class="property-value" aria-labelledby="enregistrements-label"><g:link controller="enregistrement" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${saisonInstance?.id}" />
					<g:link class="edit" action="edit" id="${saisonInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
