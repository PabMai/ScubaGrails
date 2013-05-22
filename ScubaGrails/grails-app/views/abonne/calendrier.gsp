
<%@ page import="scubagrails.EcoleScaphandre" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ecole.label', default: 'Ecole')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-ecole" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
		<g:if test="${session?.user?.admin}">
			<g:render template="/layouts/abonneSearch" />
		</g:if>
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<!--  admin -->
				<g:if test="${session?.user?.admin}">
				<li><g:link controller="admin" action="index" class="adminHome"><g:message code="scubaGrails.nav.homeAdmin.label"/></g:link></li>
				</g:if>
				<!--  Abonne -->
				<g:if test="${session?.abonne}">
				<li><g:link class="abonneProfil" action="show/${session?.abonne?.id}">
					<g:message code="scubaGrails.abonne.moncompte.label"/>
				</g:link></li>
				</g:if>
			</ul>
		</div>
		<div style="margin-top:30px;padding-left:70px">
		<iframe src="https://www.google.com/calendar/embed?title=Agenda%20de%20l'association&amp;showTitle=0&amp;showCalendars=0&amp;showTz=0&amp;height=600&amp;wkst=2&amp;hl=fr&amp;bgcolor=%23ffffff&amp;src=vaosaonk09533qu7hmvmsddco0%40group.calendar.google.com&amp;color=%235229A3&amp;ctz=Europe%2FParis" 
		style=" border-width:0 " width="800" height="600" frameborder="0" scrolling="no"></iframe>
		</div>
	</body>
</html>
