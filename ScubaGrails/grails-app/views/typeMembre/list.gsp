
<%@ page import="scubagrails.TypeMembre" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'typeMembre.label', default: 'TypeMembre')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-typeMembre" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
		<g:if test="${session?.user?.admin}">
			<g:render template="/layouts/abonneSearch" />
		</g:if>
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link controller="admin" action="index" class="adminHome"><g:message code="scubaGrails.nav.homeAdmin.label"/></g:link></li>
				<li><g:link class="create" action="create"><g:message code="typeMembre.new.label"/></g:link></li>
			</ul>
		</div>
		<div id="list-typeMembre" class="content scaffold-list" role="main">
			<h1><g:message code="typeMembre.list.label"/></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nom" title="${message(code: 'typeMembre.nom.label', default: 'Nom')}" />
						<th>${message(code: 'typeMembre.nbAbonne.label', default: 'Nombre d\'abonnés')}</th> 
					</tr>
				</thead>
				<tbody>
				<g:each in="${typeMembreInstanceList}" status="i" var="typeMembreInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${typeMembreInstance.id}">${fieldValue(bean: typeMembreInstance, field: "nom")}</g:link></td>
						<td>${typeMembreInstance.abonnes?.count?.size}</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${typeMembreInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
