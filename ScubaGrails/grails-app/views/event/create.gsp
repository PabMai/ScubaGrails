<%@ page import="scubagrails.Event" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'event.label', default: 'Event')}" />
    <title><g:message code="default.create.label" args="[entityName]" /></title>

    <r:require module="calendar" />
</head>
<body>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link controller="admin" action="index" class="adminHome"><g:message code="scubaGrails.nav.homeAdmin.label"/></g:link></li>
		<li><g:link class="calendrierProfil" controller="event" action="index"><g:message code="scubaGrails.abonne.calendrier.label"/></g:link></li>
    </ul>
</div>

<div id="create-event" class="content scaffold-create" role="main">

<h1>Créer un événement</h1>

<g:if test="${flash.message}">
    <div class="alert-message block-message info">${flash.message}</div>
</g:if>

<g:hasErrors bean="${eventInstance}">
    <ul class="errors" role="alert">
        <g:eachError bean="${eventInstance}" var="error">
            <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                    error="${error}"/></li>
        </g:eachError>
    </ul>
</g:hasErrors>

<g:form action="save" class="main" method="post" >

    <fieldset class="form">
        <g:render template="form" model="model" />
    </fieldset>

    <fieldset class="buttons">
        <g:submitButton name="Créer" class="save">Sauvegarder</g:submitButton>
    </fieldset>

</g:form>

</div>
</body>
</html>