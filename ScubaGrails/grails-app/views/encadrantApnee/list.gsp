
<%@ page import="scubagrails.EncadrantApnee" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'encadrantApnee.label', default: 'EncadrantApnee')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-encadrantApnee" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link controller="admin" action="index" class="adminHome"><g:message code="scubaGrails.nav.homeAdmin.label"/></g:link></li>
				<li><g:link class="create" action="create">Ajouter un type encadrant Apnée</g:link></li>
			</ul>
		</div>
		<div id="list-encadrantApnee" class="content scaffold-list" role="main">
			<h1>Liste des type d'encadrant Apnée</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nom" title="${message(code: 'encadrantApnee.nom.label', default: 'Nom')}" />
						<th>${message(code: 'ecole.nbAbonne.label', default: 'Nombre d\'abonnés')}</th> 
					</tr>
				</thead>
				<tbody>
				<g:each in="${encadrantApneeInstanceList}" status="i" var="encadrantApneeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${encadrantApneeInstance.id}">${fieldValue(bean: encadrantApneeInstance, field: "nom")}</g:link></td>
						<td>${encadrantApneeInstance.abonnes?.count?.size}</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${encadrantApneeInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
