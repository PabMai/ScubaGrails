<%@ page import="scubagrails.Abonne" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'abonne.label', default: 'Abonne')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-abonne" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
		<g:if test="${session?.user?.admin}">
			<g:render template="/layouts/abonneSearch" />
		</g:if>
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<!--  SI ADMIN -->
				<g:if test="${session?.user?.admin}">
				<li><g:link controller="admin" action="index" class="adminHome"><g:message code="scubaGrails.nav.homeAdmin.label"/></g:link></li>
				<li><g:link class="list" action="list"><g:message code="abonne.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="abonne.new.label" args="[entityName]" /></g:link></li>
				</g:if>
				<!--  SI ABONNE -->
				<g:if test="${session?.abonne}">
				<li>
					<g:link class="abonneProfil" action="show/${session?.abonne?.id}">
						<g:message code="scubaGrails.abonne.moncompte.label"/>
					</g:link>
				</li>
				</g:if>
			</ul>
		</div>
		<div id="edit-abonne" class="content scaffold-edit" role="main">			
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${abonneInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${abonneInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post">
				<g:hiddenField name="id" value="${abonneInstance?.id}" />
				<g:hiddenField name="version" value="${abonneInstance?.version}" />
				<fieldset class="form">
				<h1>Informations personnelles</h1>
					<g:render template="form"/>
			</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
					<g:if test="${session?.user?.admin}">
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</g:if>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
