<%@ page import="scubagrails.NiveauApnee" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">		
		<title>Gestion des type d'encadrant</title>
	</head>
	<body>
		<a href="#list-niveau" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
		<g:if test="${session?.user?.admin}">
			<g:render template="/layouts/abonneSearch" />
		</g:if>
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link controller="admin" action="index" class="adminHome"><g:message code="scubaGrails.nav.homeAdmin.label"/></g:link></li>
			</ul>
		</div>
		<div id="list-niveau" class="content scaffold-list" role="main">
			<h1>Veuillez choisir un type d'encadrant à gérer</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<br />
			
			<table id="interfaceAdmin">
			<tr id="trInterfaceAdmin">
				<td>
					<g:link controller="encadrantScaphandre">
						<g:img dir="images/scuba/admin" file="scaphandre.png"  alt="Scaphandre" />
					</g:link>
				</td>
				<td>
					<g:link controller="encadrantApnee">
						<g:img dir="images/scuba/admin" file="apnee.png" alt="Apnee" />
					</g:link>
				</td>			
			</tr>
			<tr id="trInterfaceAdmin">
				<td>&nbsp;&nbsp;&nbsp;&nbsp;Scaphandre</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;Apnée</td>				
			</tr>
			</table>
			
			

		</div>
	</body>
</html>
