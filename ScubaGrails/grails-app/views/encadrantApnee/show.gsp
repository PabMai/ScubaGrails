
<%@ page import="scubagrails.EncadrantApnee" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'encadrantApnee.label', default: 'EncadrantApnee')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-encadrantApnee" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link controller="admin" action="index" class="adminHome"><g:message code="scubaGrails.nav.homeAdmin.label"/></g:link></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create">Ajouter un type encadrant Apnée</g:link></li>
			</ul>
		</div>
		<div id="show-encadrantApnee" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list encadrantApnee">
			
				<g:if test="${encadrantApneeInstance?.nom}">
				<li class="fieldcontain">
					<span id="nom-label" class="property-label"><g:message code="encadrantApnee.nom.label" default="Nom" /></span>
					
						<span class="property-value" aria-labelledby="nom-label"><g:fieldValue bean="${encadrantApneeInstance}" field="nom"/></span>
					
				</li>
				</g:if>	
				
				<g:if test="${listeAbonnes}">
			<div id="list-abonne" class="content scaffold-list" role="main" style="width:400px;margin: auto">
			<h1 style="margin-left:0px"><g:message code="encadrantApnee.label.liste.abonne"/></h1>			
			<table>
				<thead>
					<tr>						
						<g:sortableColumn property="prenom" title="${message(code: 'abonne.prenom.label', default: 'Prénom')}" />
						
						<g:sortableColumn property="nom" title="${message(code: 'abonne.nom.label', default: 'Nom')}" />

					</tr>
				</thead>
				<tbody>
				<g:each in="${listeAbonnes}" status="i" var="abonneInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td>${fieldValue(bean: abonneInstance, field: "prenom")}</td>
					
						<td>${fieldValue(bean: abonneInstance, field: "nom")}</td>
						
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${encadrantApneeInstanceTotal}" id="${encadrantApneeInstance?.id}" />
			</div>
		</div>
		</g:if>
		<g:else>
			<g:message code="encadrant.not.found.abonne" />
		</g:else>			
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${encadrantApneeInstance?.id}" />
					<g:link class="edit" action="edit" id="${encadrantApneeInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
