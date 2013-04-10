
<%@ page import="scubagrails.Enregistrement" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'enregistrement.label', default: 'Enregistrement')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-enregistrement" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-enregistrement" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list enregistrement">
			
				<g:if test="${enregistrementInstance?.abonne}">
				<li class="fieldcontain">
					<span id="abonne-label" class="property-label"><g:message code="enregistrement.abonne.label" default="Abonne" /></span>
					
						<span class="property-value" aria-labelledby="abonne-label"><g:link controller="abonne" action="show" id="${enregistrementInstance?.abonne?.id}">${enregistrementInstance?.abonne?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${enregistrementInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="enregistrement.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${enregistrementInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${enregistrementInstance?.saison}">
				<li class="fieldcontain">
					<span id="saison-label" class="property-label"><g:message code="enregistrement.saison.label" default="Saison" /></span>
					
						<span class="property-value" aria-labelledby="saison-label"><g:link controller="saison" action="show" id="${enregistrementInstance?.saison?.id}">${enregistrementInstance?.saison?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${enregistrementInstance?.id}" />
					<g:link class="edit" action="edit" id="${enregistrementInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
