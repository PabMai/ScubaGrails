
<%@ page import="scubagrails.Abonne" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'abonne.label', default: 'Abonne')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-abonne" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-abonne" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list abonne">
			
				<g:if test="${abonneInstance?.nom}">
				<li class="fieldcontain">
					<span id="nom-label" class="property-label"><g:message code="abonne.nom.label" default="Nom" /></span>
					
						<span class="property-value" aria-labelledby="nom-label"><g:fieldValue bean="${abonneInstance}" field="nom"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${abonneInstance?.prenom}">
				<li class="fieldcontain">
					<span id="prenom-label" class="property-label"><g:message code="abonne.prenom.label" default="Prenom" /></span>
					
						<span class="property-value" aria-labelledby="prenom-label"><g:fieldValue bean="${abonneInstance}" field="prenom"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${abonneInstance?.dateNaissance}">
				<li class="fieldcontain">
					<span id="dateNaissance-label" class="property-label"><g:message code="abonne.dateNaissance.label" default="Date Naissance" /></span>
					
						<span class="property-value" aria-labelledby="dateNaissance-label"><g:formatDate date="${abonneInstance?.dateNaissance}" format="dd/MM/yyyy" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${abonneInstance?.departementNaissance}">
				<li class="fieldcontain">
					<span id="departementNaissance-label" class="property-label"><g:message code="abonne.departementNaissance.label" default="Departement Naissance" /></span>
					
						<span class="property-value" aria-labelledby="departementNaissance-label"><g:fieldValue bean="${abonneInstance}" field="departementNaissance"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${abonneInstance?.lieuNaissance}">
				<li class="fieldcontain">
					<span id="lieuNaissance-label" class="property-label"><g:message code="abonne.lieuNaissance.label" default="Lieu Naissance" /></span>
					
						<span class="property-value" aria-labelledby="lieuNaissance-label"><g:fieldValue bean="${abonneInstance}" field="lieuNaissance"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${abonneInstance?.sexe}">
				<li class="fieldcontain">
					<span id="sexe-label" class="property-label"><g:message code="abonne.sexe.label" default="Sexe" /></span>
					
						<span class="property-value" aria-labelledby="sexe-label"><g:fieldValue bean="${abonneInstance}" field="sexe"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${abonneInstance?.numeroRue}">
				<li class="fieldcontain">
					<span id="numeroRue-label" class="property-label"><g:message code="abonne.numeroRue.label" default="Numero Rue" /></span>
					
						<span class="property-value" aria-labelledby="numeroRue-label"><g:fieldValue bean="${abonneInstance}" field="numeroRue"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${abonneInstance?.nomRue}">
				<li class="fieldcontain">
					<span id="nomRue-label" class="property-label"><g:message code="abonne.nomRue.label" default="Nom Rue" /></span>
					
						<span class="property-value" aria-labelledby="nomRue-label"><g:fieldValue bean="${abonneInstance}" field="nomRue"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${abonneInstance?.codePostal}">
				<li class="fieldcontain">
					<span id="codePostal-label" class="property-label"><g:message code="abonne.codePostal.label" default="Code Postal" /></span>
					
						<span class="property-value" aria-labelledby="codePostal-label"><g:fieldValue bean="${abonneInstance}" field="codePostal"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${abonneInstance?.ville}">
				<li class="fieldcontain">
					<span id="ville-label" class="property-label"><g:message code="abonne.ville.label" default="Ville" /></span>
					
						<span class="property-value" aria-labelledby="ville-label"><g:fieldValue bean="${abonneInstance}" field="ville"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${abonneInstance?.telephoneFixe}">
				<li class="fieldcontain">
					<span id="telephoneFixe-label" class="property-label"><g:message code="abonne.telephoneFixe.label" default="Telephone Fixe" /></span>
					
						<span class="property-value" aria-labelledby="telephoneFixe-label"><g:fieldValue bean="${abonneInstance}" field="telephoneFixe"/></span>
					
				</li>
				</g:if>
				<%-- Si pas de fixe indiqué	--%>
				<g:else>
					<li class="fieldcontain">
					<span id="telephoneFixe-label" class="property-label"><g:message code="abonne.telephoneFixe.label" default="Telephone Fixe" /></span>
					
						<span class="property-value" aria-labelledby="telephoneFixe-label">N.C.</span>
					
				</li>
				</g:else>
			
				<g:if test="${abonneInstance?.telephonePortable}">
				<li class="fieldcontain">
					<span id="telephonePortable-label" class="property-label"><g:message code="abonne.telephonePortable.label" default="Telephone Portable" /></span>
					
						<span class="property-value" aria-labelledby="telephonePortable-label"><g:fieldValue bean="${abonneInstance}" field="telephonePortable"/></span>
					
				</li>
				</g:if>
				<%-- Si pas de portable indiqué	--%>
				<g:else>
					<li class="fieldcontain">
					<span id="telephonePortable-label" class="property-label"><g:message code="abonne.telephonePortable.label" default="Telephone Portable" /></span>
					
						<span class="property-value" aria-labelledby="telephonePortable-label">N.C.</span>
					
				</li>
				</g:else>
			
				<g:if test="${abonneInstance?.mail}">
				<li class="fieldcontain">
					<span id="mail-label" class="property-label"><g:message code="abonne.mail.label" default="Mail" /></span>
					
						<span class="property-value" aria-labelledby="mail-label"><g:fieldValue bean="${abonneInstance}" field="mail"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${abonneInstance?.numeroLicence}">
				<li class="fieldcontain">
					<span id="numeroLicence-label" class="property-label"><g:message code="abonne.numeroLicence.label" default="Numero Licence" /></span>
					
						<span class="property-value" aria-labelledby="numeroLicence-label"><g:fieldValue bean="${abonneInstance}" field="numeroLicence"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${abonneInstance?.dateCertificat}">
				<li class="fieldcontain">
					<span id="dateCertificat-label" class="property-label"><g:message code="abonne.dateCertificat.label" default="Date Certificat" /></span>
					
						<span class="property-value" aria-labelledby="dateCertificat-label"><g:formatDate date="${abonneInstance?.dateCertificat}" format="dd/MM/yyyy"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${abonneInstance?.encadrant}">
				<li class="fieldcontain">
					<span id="encadrant-label" class="property-label"><g:message code="abonne.encadrant.label" default="Encadrant" /></span>
					
						<span class="property-value" aria-labelledby="encadrant-label"><g:formatBoolean boolean="${abonneInstance?.encadrant}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${abonneInstance?.technicien}">
				<li class="fieldcontain">
					<span id="technicien-label" class="property-label"><g:message code="abonne.technicien.label" default="Technicien" /></span>
					
						<span class="property-value" aria-labelledby="technicien-label"><g:formatBoolean boolean="${abonneInstance?.technicien}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${abonneInstance?.secouriste}">
				<li class="fieldcontain">
					<span id="secouriste-label" class="property-label"><g:message code="abonne.secouriste.label" default="Secouriste" /></span>
					
						<span class="property-value" aria-labelledby="secouriste-label"><g:formatBoolean boolean="${abonneInstance?.secouriste}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${abonneInstance?.allergique}">
				<li class="fieldcontain">
					<span id="allergique-label" class="property-label"><g:message code="abonne.allergique.label" default="Allergique" /></span>
					
						<span class="property-value" aria-labelledby="allergique-label"><g:formatBoolean boolean="${abonneInstance?.allergique}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${abonneInstance?.comiteDirecteur}">
				<li class="fieldcontain">
					<span id="comiteDirecteur-label" class="property-label"><g:message code="abonne.comiteDirecteur.label" default="Comite Directeur" /></span>
					
						<span class="property-value" aria-labelledby="comiteDirecteur-label"><g:formatBoolean boolean="${abonneInstance?.comiteDirecteur}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${abonneInstance?.aideFamille}">
				<li class="fieldcontain">
					<span id="aideFamille-label" class="property-label"><g:message code="abonne.aideFamille.label" default="Aide Famille" /></span>
					
						<span class="property-value" aria-labelledby="aideFamille-label"><g:formatBoolean boolean="${abonneInstance?.aideFamille}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${abonneInstance?.autorisationParentale}">
				<li class="fieldcontain">
					<span id="autorisationParentale-label" class="property-label"><g:message code="abonne.autorisationParentale.label" default="Autorisation Parentale" /></span>
					
						<span class="property-value" aria-labelledby="autorisationParentale-label"><g:formatBoolean boolean="${abonneInstance?.autorisationParentale}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${abonneInstance?.prixAbonnement}">
				<li class="fieldcontain">
					<span id="prixAbonnement-label" class="property-label"><g:message code="abonne.prixAbonnement.label" default="Prix Abonnement" /></span>
					
						<span class="property-value" aria-labelledby="prixAbonnement-label"><g:fieldValue bean="${abonneInstance}" field="prixAbonnement"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${abonneInstance?.prixAssurance}">
				<li class="fieldcontain">
					<span id="prixAssurance-label" class="property-label"><g:message code="abonne.prixAssurance.label" default="Prix Assurance" /></span>
					
						<span class="property-value" aria-labelledby="prixAssurance-label"><g:fieldValue bean="${abonneInstance}" field="prixAssurance"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${abonneInstance?.ecole}">
				<li class="fieldcontain">
					<span id="ecole-label" class="property-label"><g:message code="abonne.ecole.label" default="Ecole" /></span>
					
						<span class="property-value" aria-labelledby="ecole-label"><g:link controller="ecole" action="show" id="${abonneInstance?.ecole?.id}">${abonneInstance?.ecole?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${abonneInstance?.enregistrements}">
				<li class="fieldcontain">
					<span id="enregistrements-label" class="property-label"><g:message code="abonne.enregistrements.label" default="Inscrit pour les saisons" /></span>
					
						<g:each in="${abonneInstance.enregistrements}" var="e">
						<span class="property-value" aria-labelledby="enregistrements-label"><g:link controller="enregistrement" action="show" id="${e.id}">${e?.saison?.libelle?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
				<g:else>
				<li class="fieldcontain">
					<span id="enregistrements-label" class="property-label"><g:message code="abonne.enregistrements.label" default="Inscrit pour les saisons" /></span>
					
						
						<span class="property-value" aria-labelledby="enregistrements-label" style="color:red">Pas d'inscriptions</span>
						
					
				</li>
				</g:else>
			
				<g:if test="${abonneInstance?.niveau}">
				<li class="fieldcontain">
					<span id="niveau-label" class="property-label"><g:message code="abonne.niveau.label" default="Niveau" /></span>
					
						<span class="property-value" aria-labelledby="niveau-label"><g:link controller="niveau" action="show" id="${abonneInstance?.niveau?.id}">${abonneInstance?.niveau?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${abonneInstance?.typeMembre}">
				<li class="fieldcontain">
					<span id="typeMembre-label" class="property-label"><g:message code="abonne.typeMembre.label" default="Type Membre" /></span>
					
						<span class="property-value" aria-labelledby="typeMembre-label"><g:link controller="typeMembre" action="show" id="${abonneInstance?.typeMembre?.id}">${abonneInstance?.typeMembre?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>		
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${abonneInstance?.id}" />
					<g:link class="edit" action="edit" id="${abonneInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
