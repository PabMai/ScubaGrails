
<%@ page import="scubagrails.Enregistrement" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'enregistrement.label', default: 'Enregistrement')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-enregistrement" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-enregistrement" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="enregistrement.abonne.label" default="Abonne" /></th>
					
						<g:sortableColumn property="lastUpdated" title="${message(code: 'enregistrement.lastUpdated.label', default: 'Last Updated')}" />
					
						<th><g:message code="enregistrement.saison.label" default="Saison" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${enregistrementInstanceList}" status="i" var="enregistrementInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${enregistrementInstance.id}">${fieldValue(bean: enregistrementInstance, field: "abonne")}</g:link></td>
					
						<td><g:formatDate date="${enregistrementInstance.lastUpdated}" /></td>
					
						<td>${fieldValue(bean: enregistrementInstance, field: "saison")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${enregistrementInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
