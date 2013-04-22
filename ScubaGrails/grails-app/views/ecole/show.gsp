
<%@ page import="scubagrails.Ecole" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ecole.label', default: 'Ecole')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-ecole" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link controller="admin" action="index" class="adminHome"><g:message code="scubaGrails.nav.homeAdmin.label"/></g:link></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-ecole" class="content scaffold-show" role="main">
			<g:if test="${ecoleInstance?.nom}">
			<h1>Ecole ${ecoleInstance?.nom}</h1>
			</g:if>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list ecole">
				
				
			<!--  Ajout GMO -->
		
			<g:if test="${listeAbonnes}">
			<div id="list-abonne" class="content scaffold-list" role="main" style="width:400px;margin: auto">
			<h1 style="margin-left:0px"><g:message code="ecole.label.liste.abonne"/></h1>			
			<table>
				<thead>
					<tr>						
						<g:sortableColumn property="prenom" title="${message(code: 'abonne.prenom.label', default: 'Prenom')}" />
						
						<g:sortableColumn property="nom" title="${message(code: 'abonne.nom.label', default: 'Nom')}" />

					</tr>
				</thead>
				<tbody>
				<g:each in="${listeAbonnes}" status="i" var="abonneInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
					
						<td>${fieldValue(bean: abonneInstance, field: "prenom")}</td>
					
						<td>${fieldValue(bean: abonneInstance, field: "nom")}</td>
						
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${abonneInstanceTotal}" id="${ecoleInstance?.id}" />
			</div>
		</div>
		</g:if>
		<g:else>
			<g:message code="ecole.not.found.abonne" />
		</g:else>		
				
				<!-- FIN AJOUT -->
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${ecoleInstance?.id}" />
					<g:link class="edit" action="edit" id="${ecoleInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
