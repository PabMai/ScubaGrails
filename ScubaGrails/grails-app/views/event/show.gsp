<%@ page import="scubagrails.Event" %>
<%@ page import="org.joda.time.Instant" %>


<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'event.label', default: 'Event')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>

    <r:require module="calendar" />

</head>

<body>
<a href="#show-event" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                            default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <g:if test="${session?.user?.admin}">
			<li><g:link controller="admin" action="index" class="adminHome"><g:message code="scubaGrails.nav.homeAdmin.label"/></g:link></li>
			<li><g:link class="calendrierProfil" controller="event" action="index"><g:message code="scubaGrails.abonne.calendrier.label"/></g:link></li>
			<li><g:link action="create" class="create">Ajouter un événement</g:link></li>
		</g:if>
		<g:if test="${session?.abonne}">
			<li><g:link class="abonneProfil" controller="abonne" action="show/${session?.abonne?.id}"><g:message code="scubaGrails.abonne.moncompte.label"/></g:link></li>
			<li><g:link class="calendrierProfil" controller="event" action="index"><g:message code="scubaGrails.abonne.calendrier.label"/></g:link></li>
		</g:if>
    </ul>
</div>

<div id="show-event" class="content scaffold-show" role="main">
    <h1>${eventInstance?.title}</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <ol class="property-list event">
        <li class="fieldcontain">
            <span id="when-label" class="property-label">Date de début</span>

            <span class="property-value" aria-labelledby="when-label">
                <g:formatDate date="${new Instant(occurrenceStart).toDate()}" format="E, dd/MM/yyyy à HH:mm"/>                
            </span>

        </li>
        
        <li class="fieldcontain">
            <span id="when-label" class="property-label">Date de fin</span>

            <span class="property-value" aria-labelledby="when-label">                
                <g:formatDate date="${new Instant(occurrenceEnd).toDate()}" format="E, dd/MM/yyyy à HH:mm"/>
            </span>

        </li>

        <g:if test="${eventInstance?.location}">
            <li class="fieldcontain">
                <span id="location-label" class="property-label"><g:message code="event.location.label"
                                                                            default="Lieu"/></span>

                <span class="property-value" aria-labelledby="location-label"><g:fieldValue bean="${eventInstance}"
                                                                                            field="location"/></span>

            </li>
        </g:if>

        <g:if test="${eventInstance?.description}">
            <li class="fieldcontain">
                <span id="description-label" class="property-label"><g:message code="event.description.label"
                                                                               default="Description"/></span>

                <span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${eventInstance}"
                                                                                               field="description"/></span>

            </li>
        </g:if>



    </ol>
    <g:if test="${session?.user?.admin}">
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${eventInstance?.id}"/>

            <g:hiddenField name="occurrenceStart" value="${occurrenceStart}" />
            <g:hiddenField name="occurrenceEnd" value="${occurrenceEnd}" />

            <g:actionSubmit class="edit" action="edit"
                            value="${message(code: 'default.button.edit.label', default: 'Edit')}"/>
            <g:actionSubmit class="delete ${eventInstance.isRecurring ? 'recurring' : ''}" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}" />
        </fieldset>
    </g:form>

    <g:if test="${eventInstance.isRecurring}">
        <g:render template="deletePopup" model="model" />
    </g:if>
    </g:if>

</div>
</body>
</html>
