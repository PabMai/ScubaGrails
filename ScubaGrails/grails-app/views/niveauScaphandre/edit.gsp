<%@ page import="scubagrails.NiveauScaphandre" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'niveau.label', default: 'Niveau')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-niveau" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
		<g:if test="${session?.user?.admin}">
			<g:render template="/layouts/abonneSearch" />
		</g:if>
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link controller="admin" action="index" class="adminHome"><g:message code="scubaGrails.nav.homeAdmin.label"/></g:link></li>
				<li><g:link class="list" action="list"><g:message code="niveau.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="niveau.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="edit-niveau" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${niveauInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${niveauInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${niveauInstance?.id}" />
				<g:hiddenField name="version" value="${niveauInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
