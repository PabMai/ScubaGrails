<%@ page import="scubagrails.Abonne"%>

<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'abonne.label', default: 'Abonne')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
	<a href="#show-abonne" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div class="nav" role="navigation">
		<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link controller="admin" action="index" class="adminHome"><g:message code="scubaGrails.nav.homeAdmin.label"/></g:link></li>
		</ul>
	</div>
	<div id="show-abonne" class="content scaffold-show" role="main">
	<h1>Abonnés avec certificat médical périmé </h1>
	<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
	</g:if>

		<ol class="property-list ecole">

			<g:if test="${listePagineAbonneCMPerime}">
				<div id="list-abonne" class="content scaffold-list" role="main"
					style="width: 400px; margin-left: 260px">
					<h1 style="margin-left: 0px">
						
					</h1>
					<table>
						<thead>
							<tr>
								<g:sortableColumn property="prenom"
									title="${message(code: 'abonne.prenom.label', default: 'Prenom')}" />

								<g:sortableColumn property="nom"
									title="${message(code: 'abonne.nom.label', default: 'Nom')}" />
									
								<th> Certificat du </th>							
								
								<th> Périmé de </th>									
							</tr>
						</thead>
						<tbody>
							<g:each in="${listePagineAbonneCMPerime}" status="i" var="abonneInstance">
								<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">


									<td>
										<g:link action="show" id="${abonneInstance?.id}">${fieldValue(bean: abonneInstance, field: "prenom")}</g:link>
									</td>

									<td>
										${fieldValue(bean: abonneInstance, field: "nom")}
									</td>
									
									<td>
										<g:formatDate date="${abonneInstance?.dateCertificat}"
											format="dd/MM/yyyy" />										
									</td>
																			
									<td>										
										${abonneInstance?.nbJourPerimeCM} jours
									</td>
								</tr>
							</g:each>
						</tbody>
					</table>
					<div class="pagination">
						<g:paginate total="${abonneInstanceTotal}" />
					</div>
				</div>
			</g:if>
			<g:else>
				<g:message code="abonnePerime.not.found.abonne" />
			</g:else>

			<!-- FIN AJOUT -->

		</ol>

	</div>
</body>
</html>