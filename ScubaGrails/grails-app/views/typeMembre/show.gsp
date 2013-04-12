
<%@ page import="scubagrails.TypeMembre"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'typeMembre.label', default: 'TypeMembre')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
	<a href="#show-typeMembre" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message
						code="default.home.label" /></a></li>
			<li><g:link class="list" action="list">
					<g:message code="default.list.label" args="[entityName]" />
				</g:link></li>
			<li><g:link class="create" action="create">
					<g:message code="default.new.label" args="[entityName]" />
				</g:link></li>
		</ul>
	</div>
	<div id="show-typeMembre" class="content scaffold-show" role="main">
		<h1>
			<g:message code="default.show.label" args="[entityName]" />
		</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		<ol class="property-list typeMembre">

			<g:if test="${listeAbonnes}">
				<div id="list-abonne" class="content scaffold-list" role="main"
					style="width: 400px; margin-left: 260px">
					<h1 style="margin-left: 0px">
						<g:message code="typeMembre.label.liste.abonne" />
					</h1>
					<table>
						<thead>
							<tr>
								<g:sortableColumn property="prenom"
									title="${message(code: 'abonne.prenom.label', default: 'Prenom')}" />

								<g:sortableColumn property="nom"
									title="${message(code: 'abonne.nom.label', default: 'Nom')}" />

							</tr>
						</thead>
						<tbody>
							<g:each in="${listeAbonnes}" status="i" var="abonneInstance">
								<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">


									<td>
										${fieldValue(bean: abonneInstance, field: "prenom")}
									</td>

									<td>
										${fieldValue(bean: abonneInstance, field: "nom")}
									</td>

								</tr>
							</g:each>
						</tbody>
					</table>
					<div class="pagination">
						<g:paginate total="${abonneInstanceTotal}"
							id="${typeMembreInstance?.id}" />
					</div>
				</div>
			</g:if>
			<g:else>
				<g:message code="typeMembre.not.found.abonne" />
			</g:else>

		</ol>
		<g:form>
			<fieldset class="buttons">
				<g:hiddenField name="id" value="${typeMembreInstance?.id}" />
				<g:link class="edit" action="edit" id="${typeMembreInstance?.id}">
					<g:message code="default.button.edit.label" default="Edit" />
				</g:link>
				<g:actionSubmit class="delete" action="delete"
					value="${message(code: 'default.button.delete.label', default: 'Delete')}"
					onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
			</fieldset>
		</g:form>
	</div>
</body>
</html>
