
<%@ page import="scubagrails.Niveau" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'niveau.label', default: 'Niveau')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-niveau" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link controller="admin" action="index" class="adminHome"><g:message code="scubaGrails.nav.homeAdmin.label"/></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-niveau" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="niveau" title="${message(code: 'niveau.niveau.label', default: 'Niveau')}" />
						<th>${message(code: 'niveau.nbAbonne.label', default: 'Nombre d\'abonnés')}</th> 
					</tr>
				</thead>
				<tbody>
				<g:each in="${niveauInstanceList}" status="i" var="niveauInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${niveauInstance.id}">${fieldValue(bean: niveauInstance, field: "niveau")}</g:link></td>
						<td>${niveauInstance.abonnes?.count?.size}</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${niveauInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
