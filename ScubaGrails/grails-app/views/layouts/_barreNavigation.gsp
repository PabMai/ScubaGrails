<div class="nav" role="navigation">
<g:if test="${session?.user?.admin}">
<g:render template="/layouts/abonneSearch" />
</g:if>
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<!--  SI ADMIN -->
				<g:if test="${session?.user?.admin}">
				<li><g:link controller="admin" action="index" class="adminHome"><g:message code="scubaGrails.nav.homeAdmin.label"/></g:link></li>
				<li><g:link class="calendrierProfil" controller="abonne" action="calendrier"><g:message code="scubaGrails.abonne.calendrier.label"/></g:link></li>
				</g:if>
				<!--  SI ABONNE -->
				<g:if test="${session?.abonne}">
				<li><g:link class="abonneProfil" controller="abonne" action="show/${session?.abonne?.id}"><g:message code="scubaGrails.abonne.moncompte.label"/></g:link></li>
				<li><g:link class="calendrierProfil" controller="abonne" action="calendrier"><g:message code="scubaGrails.abonne.calendrier.label"/></g:link></li>
				</g:if>
			</ul>
			
</div>