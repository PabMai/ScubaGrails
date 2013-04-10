
<%@ page import="scubagrails.Abonne" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'abonne.label', default: 'Abonne')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-abonne" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-abonne" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="numeroLicence" title="${message(code: 'abonne.numeroLicence.label', default: 'Numéro de licence')}" />						
					
						<g:sortableColumn property="prenom" title="${message(code: 'abonne.prenom.label', default: 'Prenom')}" />
						
						<g:sortableColumn property="nom" title="${message(code: 'abonne.nom.label', default: 'Nom')}" />
					
						<g:sortableColumn property="dateNaissance" title="${message(code: 'abonne.dateNaissance.label', default: 'Date Naissance')}" />
										
						<g:sortableColumn property="lieuNaissance" title="${message(code: 'abonne.lieuNaissance.label', default: 'Lieu Naissance')}" />
					
						<g:sortableColumn property="sexe" title="${message(code: 'abonne.sexe.label', default: 'Sexe')}" />
					
						<th> Action </th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${abonneInstanceList}" status="i" var="abonneInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>${fieldValue(bean: abonneInstance, field: "numeroLicence")}</td>
					
						<td>${fieldValue(bean: abonneInstance, field: "prenom")}</td>
					
						<td>${fieldValue(bean: abonneInstance, field: "nom")}</td>						
					
						<td><g:formatDate date="${abonneInstance.dateNaissance}" format="dd/MM/yyyy" /></td>
					
						<td>${fieldValue(bean: abonneInstance, field: "lieuNaissance")}</td>
					
						<td>${fieldValue(bean: abonneInstance, field: "sexe")}</td>
						
						<td> <g:link action="show" id="${abonneInstance.id}"> Modifier </g:link> </td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${abonneInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
