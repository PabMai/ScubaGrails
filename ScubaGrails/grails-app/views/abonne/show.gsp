
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
			<li><a class="home" href="${createLink(uri: '/')}"><g:message
						code="default.home.label" /></a></li>
			<!--  SI ADMIN -->
			<g:if test="${session?.user?.admin}">
			<li><g:link controller="admin" action="index" class="adminHome">
						<g:message code="scubaGrails.nav.homeAdmin.label" />
					</g:link>
			</li>
			<li><g:link class="list" action="list">
					<g:message code="default.list.label" args="[entityName]" />
				</g:link>
			</li>				
				<li><g:link class="create" action="create">
					<g:message code="default.new.label" args="[entityName]" />
				</g:link>
			</li>
			</g:if>
			<!--  SI ABONNE -->
			<g:if test="${session?.abonne}">
			<li><g:link class="abonneProfil" action="show/${session?.abonne?.id}">
					<g:message code="scubaGrails.abonne.moncompte.label"/>
				</g:link></li>
			</g:if>
		</ul>
	</div>
	<div id="show-abonne" class="content scaffold-show" role="main">
		<h1>
			<g:fieldValue bean="${abonneInstance}" field="prenom" />
			<g:fieldValue bean="${abonneInstance}" field="nom" />
		</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>

		
			<g:if test="${abonneInstance?.avatar}">
			<div class="presentationAbonne">
	  			<img class="avatar" src="${createLink(controller:'abonne', action:'getAvatarAbonne', id:abonneInstance?.id)}" />
			</div>
			</g:if>
			<g:else>
			<div class="ajouterPhoto">
			<g:form controller="abonne" action="ajouterAvatarAbonne" method="post" enctype="multipart/form-data">				
					<input type="hidden" name="idAbonne" value="${abonneInstance?.id}" />
					<label for="avatar">Ajouter une photo : </label>
					<input type="file" name="avatar" id="avatar" />
					<input type="submit" class="buttons" value="Upload"/>
			</g:form>
			</div>
			</g:else>
		

		<ol class="property-list abonne" style="margin-left: 50px;width:300px;" >
			<li class="fieldcontain">
				${abonneInstance?.prenom} ${abonneInstance?.nom} (${abonneInstance?.numeroLicence})
			</li>
			<li class="fieldcontain">Né le <g:formatDate
					date="${abonneInstance?.dateNaissance}" format="dd/MM/yyyy" /> (${abonneInstance?.getAge()} ans), ${abonneInstance?.sexe.value}</li>
			<li class="fieldcontain">
				${abonneInstance?.numeroRue}, ${abonneInstance?.nomRue}
			</li>
			<li class="fieldcontain">
				${abonneInstance?.codePostal} - ${abonneInstance?.ville}
			</li>
			<li class="fieldcontain">Fixe : <g:if
					test="${abonneInstance?.telephoneFixe}">
					${abonneInstance?.telephoneFixe}
				</g:if> <g:else>N.C.</g:else>
			</li>
			<li class="fieldcontain">Portable : <g:if
					test="${abonneInstance?.telephonePortable}">
					${abonneInstance?.telephonePortable}
				</g:if> <g:else>N.C.</g:else>
			</li>
			<li class="fieldcontain">
				${abonneInstance?.mail}
			</li>
		</ol>

		<h1>Informations Association</h1>
		
		<!--  DIVISION boolean -->
			<div style="float:right;width:300px;margin-top:-10px;padding-right:40px;">
			<ol class="property-list abonne">
			<li class="fieldcontain"><span id="encadrant-label"
				class="property-label-abonne" style="text-align: left;"><g:message
						code="abonne.encadrant.label" default="Encadrant" /></span> <span
				class="property-value-abonne" aria-labelledby="encadrant-label">
					<g:if test="${abonneInstance?.encadrant}">
						<img src="${resource(dir:'images/scuba',file:'fleche_ok.png')}"
							alt="Oui" />
					</g:if> <g:else>
						<img src="${resource(dir:'images/scuba',file:'fleche_ko.png')}"
							alt="Non" />
					</g:else>
			</span></li>



			<li class="fieldcontain"><span id="technicien-label"
				class="property-label-abonne" style="text-align: left;"><g:message
						code="abonne.technicien.label" default="Technicien" /></span> <span
				class="property-value-abonne" aria-labelledby="technicien-label">
					<g:if test="${abonneInstance?.technicien}">
						<img src="${resource(dir:'images/scuba',file:'fleche_ok.png')}"
							alt="Oui" />
					</g:if> <g:else>
						<img src="${resource(dir:'images/scuba',file:'fleche_ko.png')}"
							alt="Non" />
					</g:else>
			</span></li>



			<li class="fieldcontain"><span id="secouriste-label"
				class="property-label-abonne" style="text-align: left;"><g:message
						code="abonne.secouriste.label" default="Secouriste" /></span> <span
				class="property-value-abonne" aria-labelledby="secouriste-label">
					<g:if test="${abonneInstance?.secouriste}">
						<img src="${resource(dir:'images/scuba',file:'fleche_ok.png')}"
							alt="Oui" />
					</g:if> <g:else>
						<img src="${resource(dir:'images/scuba',file:'fleche_ko.png')}"
							alt="Non" />
					</g:else>
			</span></li>



			<li class="fieldcontain"><span id="allergique-label"
				class="property-label-abonne"><g:message
						code="abonne.allergique.label" default="Allergique" /></span> <span
				class="property-value-abonne" aria-labelledby="allergique-label">
					<g:if test="${abonneInstance?.allergique}">
						<img src="${resource(dir:'images/scuba',file:'fleche_ok.png')}"
							alt="Oui" />
					</g:if> <g:else>
						<img src="${resource(dir:'images/scuba',file:'fleche_ko.png')}"
							alt="Non" />
					</g:else>
			</span></li>




			<li class="fieldcontain"><span id="comiteDirecteur-label"
				class="property-label-abonne"><g:message
						code="abonne.comiteDirecteur.label" default="Comite Directeur" /></span>

				<span class="property-value-abonne"
				aria-labelledby="comiteDirecteur-label"> <g:if
						test="${abonneInstance?.comiteDirecteur}">
						<img src="${resource(dir:'images/scuba',file:'fleche_ok.png')}"
							alt="Oui" />
					</g:if> <g:else>
						<img src="${resource(dir:'images/scuba',file:'fleche_ko.png')}"
							alt="Non" />
					</g:else>
			</span></li>
			
				<li class="fieldcontain"><span id="aideFamille-label"
					class="property-label-abonne"><g:message
							code="abonne.aideFamille.label" default="Aide Famille" /></span> <span
					class="property-value-abonne" aria-labelledby="aideFamille-label">
						<g:if
						test="${abonneInstance?.aideFamille}">
						<img src="${resource(dir:'images/scuba',file:'fleche_ok.png')}"
							alt="Oui" />
					</g:if> <g:else>
						<img src="${resource(dir:'images/scuba',file:'fleche_ko.png')}"
							alt="Non" />
					</g:else></span></li>


			
				<li class="fieldcontain"><span id="autorisationParentale-label"
					class="property-label-abonne"><g:message
							code="abonne.autorisationParentale.label"
							default="Autorisation Parentale" /></span> <span
					class="property-value-abonne"
					aria-labelledby="autorisationParentale-label">
					<g:if
						test="${abonneInstance?.autorisationParentale}">
						<img src="${resource(dir:'images/scuba',file:'fleche_ok.png')}"
							alt="Oui" />
					</g:if> <g:else>
						<img src="${resource(dir:'images/scuba',file:'fleche_ko.png')}"
							alt="Non" />
					</g:else>
					</span></li>
			</ol>
			</div>
		
		
		<ol class="property-list abonne" style="margin-left: 50px">

			<g:if test="${abonneInstance?.dateCertificat}">
				<li class="fieldcontain"><span id="dateCertificat-label"
					class="property-label-abonne" style="text-align: left;"><g:message
							code="abonne.dateCertificat.label" default="Date du certificat" /></span>

					<span class="property-value-abonne"
					aria-labelledby="dateCertificat-label"><g:formatDate
							date="${abonneInstance?.dateCertificat}" format="dd/MM/yyyy" /></span></li>
			</g:if>

			<g:if test="${abonneInstance?.prixAbonnement}">
				<li class="fieldcontain"><span id="prixAbonnement-label"
					class="property-label-abonne"><g:message
							code="abonne.prixAbonnement.label" default="Prix de l'abonnement" /></span>

					<span class="property-value-abonne"
					aria-labelledby="prixAbonnement-label"><g:fieldValue
							bean="${abonneInstance}" field="prixAbonnement" /> &euro;</span></li>
			</g:if>

			<g:if test="${abonneInstance?.prixAssurance}">
				<li class="fieldcontain"><span id="prixAssurance-label"
					class="property-label-abonne"><g:message
							code="abonne.prixAssurance.label" default="Prix de l'assurance" /></span>
					<span class="property-value-abonne"
					aria-labelledby="prixAssurance-label"><g:fieldValue
							bean="${abonneInstance}" field="prixAssurance" /> &euro;</span></li>
			</g:if>

			<g:if test="${abonneInstance?.ecole}">
				<li class="fieldcontain"><span id="ecole-label"
					class="property-label-abonne"><g:message
							code="abonne.ecole.label" default="Ecole" /></span> <span
					class="property-value-abonne" aria-labelledby="ecole-label"><g:link
							controller="ecole" action="show"
							id="${abonneInstance?.ecole?.id}">
							${abonneInstance?.ecole?.encodeAsHTML()}
						</g:link></span></li>
			</g:if>

<%--			<g:if test="${abonneInstance?.enregistrements}">--%>
<%--				<li class="fieldcontain"><span id="enregistrements-label"--%>
<%--					class="property-label-abonne"><g:message--%>
<%--							code="abonne.enregistrements.label"--%>
<%--							default="Inscrit pour les saisons" /></span> <g:each--%>
<%--						in="${abonneInstance.enregistrements}" var="e">--%>
<%--						<span class="property-value-abonne"--%>
<%--							aria-labelledby="enregistrements-label"><g:link--%>
<%--								controller="enregistrement" action="show" id="${e.id}">--%>
<%--								${e?.saison?.libelle?.encodeAsHTML()}--%>
<%--							</g:link></span>--%>
<%--					</g:each></li>--%>
<%--			</g:if>--%>
			<g:if test="${listeEnregistrement}">
				<li class="fieldcontain"><span id="enregistrements-label"
					class="property-label-abonne"><g:message
							code="abonne.enregistrements.label"
							default="Trois dernières saisons" /></span> <g:each
						in="${listeEnregistrement}" var="e">
						<span class="property-value-abonne"
							aria-labelledby="enregistrements-label"><g:link
								controller="enregistrement" action="show" id="${e.id}">
								${e?.saison?.libelle?.encodeAsHTML()}
							</g:link></span>
					</g:each></li>
			</g:if>

			<g:else>
				<li class="fieldcontain"><span id="enregistrements-label"
					class="property-label-abonne"><g:message
							code="abonne.enregistrements.label"
							default="Inscrit pour les saisons" /></span> <span
					class="property-value-abonne"
					aria-labelledby="enregistrements-label" style="color: red">Pas
						d'inscriptions</span></li>
			</g:else>

			<g:if test="${abonneInstance?.niveau}">
				<li class="fieldcontain"><span id="niveau-label"
					class="property-label-abonne"><g:message
							code="abonne.niveau.label" default="Niveau" /></span> <span
					class="property-value-abonne" aria-labelledby="niveau-label"><g:link
							controller="niveau" action="show"
							id="${abonneInstance?.niveau?.id}">
							${abonneInstance?.niveau?.encodeAsHTML()}
						</g:link></span></li>
			</g:if>

			<g:if test="${abonneInstance?.typeMembre}">
				<li class="fieldcontain"><span id="typeMembre-label"
					class="property-label-abonne"><g:message
							code="abonne.typeMembre.label" default="Type Membre" /></span> <span
					class="property-value-abonne" aria-labelledby="typeMembre-label"><g:link
							controller="typeMembre" action="show"
							id="${abonneInstance?.typeMembre?.id}">
							${abonneInstance?.typeMembre?.encodeAsHTML()}
						</g:link></span></li>
			</g:if>

		</ol>
		<g:form style="margin-top:20px">
			<fieldset class="buttons">
				<g:hiddenField name="id" value="${abonneInstance?.id}" />
				<g:link class="edit" action="edit" id="${abonneInstance?.id}">
					<g:message code="default.button.edit.label" default="Editer" />
				</g:link>
				<g:link class="editMDP" action="editMotDePasse" id="${abonneInstance?.id}">
					<g:message code="abonne.button.editMDP.label" default="Changer le mot de passe" />
				</g:link>
				<g:if test="${session?.user?.admin}">
				<g:actionSubmit class="delete" action="delete"
					value="${message(code: 'default.button.delete.label', default: 'Delete')}"
					onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</g:if>
			</fieldset>
		</g:form>
	</div>
</body>
</html>
