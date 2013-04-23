
<%@ page import="scubagrails.Saison" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'saison.label', default: 'Saison')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-saison" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link controller="admin" action="index" class="adminHome"><g:message code="scubaGrails.nav.homeAdmin.label"/></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-saison" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="libelle" title="${message(code: 'saison.libelle.label', default: 'Libelle')}" />
					
						<g:sortableColumn property="dateDebut" title="${message(code: 'saison.dateDebut.label', default: 'Date de début')}" />
					
						<g:sortableColumn property="dateFin" title="${message(code: 'saison.dateFin.label', default: 'Date de fin')}" />
						
						<th>${message(code: 'saison.nbAbonne.label', default: 'Nombre d\'abonnés')}</th> 
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${saisonInstanceList}" status="i" var="saisonInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>
							<g:link action="show" id="${saisonInstance.id}">${fieldValue(bean: saisonInstance, field: "libelle")}</g:link>
							<g:if test="${saisonInstance?.enCours}">
								(en cours)
							</g:if>
						</td>
					
						<td><g:formatDate date="${saisonInstance.dateDebut}" format="dd/MM/yyyy" /></td>
					
						<td><g:formatDate date="${saisonInstance.dateFin}" format="dd/MM/yyyy" /></td>
						
						<td>${saisonInstance.enregistrements?.count?.size}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${saisonInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
