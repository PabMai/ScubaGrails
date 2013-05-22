
<%@ page import="scubagrails.EncadrantScaphandre" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'encadrantScaphandre.label', default: 'EncadrantScaphandre')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-encadrantScaphandre" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link controller="admin" action="index" class="adminHome"><g:message code="scubaGrails.nav.homeAdmin.label"/></g:link></li>
				<li><g:link class="create" action="create">Ajouter un type encadrant Scaphandre</g:link></li>
			</ul>
		</div>
		<div id="list-encadrantScaphandre" class="content scaffold-list" role="main">
			<h1>Liste des types d'encadrant Scaphandre</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>					
						<g:sortableColumn property="nom" title="${message(code: 'encadrantScaphandre.nom.label', default: 'Nom')}" />
						<th>${message(code: 'ecole.nbAbonne.label', default: 'Nombre d\'abonnés')}</th> 
					</tr>
				</thead>
				<tbody>
				<g:each in="${encadrantScaphandreInstanceList}" status="i" var="encadrantScaphandreInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${encadrantScaphandreInstance.id}">${fieldValue(bean: encadrantScaphandreInstance, field: "nom")}</g:link></td>
						<td>${encadrantScaphandreInstance.abonnes?.count?.size}</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${encadrantScaphandreInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
