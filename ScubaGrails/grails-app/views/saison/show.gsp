
<%@ page import="scubagrails.Saison" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'saison.label', default: 'Saison')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-saison" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link controller="admin" action="index" class="adminHome"><g:message code="scubaGrails.nav.homeAdmin.label"/></g:link></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-saison" class="content scaffold-show" role="main">
			<h1>Saison ${saisonInstance?.libelle}</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list saison">
										
				<g:if test="${saisonInstance?.dateDebut}">
				<li class="fieldcontain">
					<span id="dateDebut-label" class="property-label"><g:message code="saison.dateDebut.label" default="Date Debut" /></span>
					
						<span class="property-value" aria-labelledby="dateDebut-label"><g:formatDate date="${saisonInstance?.dateDebut}" format="dd/MM/yyyy" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${saisonInstance?.dateFin}">
				<li class="fieldcontain">
					<span id="dateFin-label" class="property-label"><g:message code="saison.dateFin.label" default="Date Fin" /></span>
					
						<span class="property-value" aria-labelledby="dateFin-label"><g:formatDate date="${saisonInstance?.dateFin}" format="dd/MM/yyyy" /></span>
					
				</li>
				</g:if>
			
<%--				<g:if test="${saisonInstance?.enregistrements}">--%>
<%--				<li class="fieldcontain">--%>
<%--					<span id="enregistrements-label" class="property-label"><g:message code="saison.enregistrements.label" default="Enregistrements" /></span>--%>
<%--					--%>
<%--						<g:each in="${saisonInstance.enregistrements}" var="e">--%>
<%--						<span class="property-value" aria-labelledby="enregistrements-label"><g:link controller="enregistrement" action="show" id="${e.id}">${e.abonne?.nom?.encodeAsHTML()}</g:link></span>--%>
<%--						</g:each>--%>
<%--					--%>
<%--				</li>--%>
<%--				</g:if>--%>
		<g:if test="${listeEnregistrement}">
			<div id="list-abonne" class="content scaffold-list" role="main" style="width:400px;margin: auto">
			<h1 style="margin-left:0px"><g:message code="saison.label.liste.abonne"/></h1>			
			<table>
				<thead>
					<tr>						
						<g:sortableColumn property="prenom" title="${message(code: 'abonne.prenom.label', default: 'Prenom')}" />
						
						<g:sortableColumn property="nom" title="${message(code: 'abonne.nom.label', default: 'Nom')}" />

					</tr>
				</thead>
				<tbody>
				<g:each in="${listeEnregistrement}" status="i" var="enregistrementInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td>${fieldValue(bean: enregistrementInstance.abonne, field: "prenom")}</td>
					
						<td>${fieldValue(bean: enregistrementInstance.abonne, field: "nom")}</td>
						
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${abonneInstanceTotal}" id="${saisonInstance?.id}" />
			</div>
		</div>
		</g:if>
		<g:else>
			<g:message code="saison.not.found.abonne" />
		</g:else>

			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${saisonInstance?.id}" />
					<g:link class="edit" action="edit" id="${saisonInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
