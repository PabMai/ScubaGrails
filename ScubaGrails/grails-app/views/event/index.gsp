<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <meta name="layout" content="main">

  <title>Calendar</title>
  <r:require module="calendar" />
</head>
<body>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <!--  SI ADMIN -->
		<g:if test="${session?.user?.admin}">
			<li><g:link controller="admin" action="index" class="adminHome"><g:message code="scubaGrails.nav.homeAdmin.label"/></g:link></li>
			<li><g:link class="calendrierProfil" controller="event" action="index"><g:message code="scubaGrails.abonne.calendrier.label"/></g:link></li>
			<li><g:link action="create" class="create">Ajouter un évènement</g:link></li>
		</g:if>
		<!--  SI ABONNE -->
		<g:if test="${session?.abonne}">
			<li><g:link class="abonneProfil" controller="abonne" action="show/${session?.abonne?.id}"><g:message code="scubaGrails.abonne.moncompte.label"/></g:link></li>
			<li><g:link class="calendrierProfil" controller="event" action="index"><g:message code="scubaGrails.abonne.calendrier.label"/></g:link></li>
		</g:if>
        
       
    </ul>
</div>
	<div id="spinner" class="spinnerAgenda" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
    <div id="calendar"></div>

</body>
</html>