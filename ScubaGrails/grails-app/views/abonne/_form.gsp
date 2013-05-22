<%@ page import="scubagrails.Abonne" %>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'nom', 'error')} required">
	<label for="nom">
		<g:message code="abonne.nom.label" default="Nom" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nom" maxlength="50" required="" value="${abonneInstance?.nom}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'prenom', 'error')} required">
	<label for="prenom">
		<g:message code="abonne.prenom.label" default="Prénom" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="prenom" maxlength="50" required="" value="${abonneInstance?.prenom}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'dateNaissance', 'error')} required">
	<label for="dateNaissance">
		<g:message code="abonne.dateNaissance.label" default="Date de naissance" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateNaissance" precision="day"  value="${abonneInstance?.dateNaissance}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'sexe', 'error')} required">
	<label for="sexe">
		<g:message code="abonne.sexe.label" default="Sexe" />
		<span class="required-indicator">*</span>
	</label>	
	<g:select name="sexe" from="${scubagrails.type.Sexe?.values()}" keys="${scubagrails.type.Sexe.values()*.name()}" required="" value="${abonneInstance?.sexe?.name()}"/>
</div>

<h1> Coordonnées </h1>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'nomRue', 'error')} required">
	<label for="nomRue">
		<g:message code="abonne.nomRue.label" default="Nom de la rue" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nomRue" size="50" maxlength="100" required="" value="${abonneInstance?.nomRue}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'codePostal', 'error')} required">
	<label for="codePostal">
		<g:message code="abonne.codePostal.label" default="Code postal" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="codePostal" maxlength="5" size="5" pattern="${abonneInstance.constraints.codePostal.matches}" required="" value="${abonneInstance?.codePostal}"/>
	(ex : 49000)
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'ville', 'error')} required">
	<label for="ville">
		<g:message code="abonne.ville.label" default="Ville" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="ville" maxlength="50" required="" value="${abonneInstance?.ville}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'telephoneFixe', 'error')} ">
	<label for="telephoneFixe">
		<g:message code="abonne.telephoneFixe.label" default="Telephone fixe" />
		
	</label>
	<g:textField name="telephoneFixe" size="10" pattern="${abonneInstance.constraints.telephoneFixe.matches}" value="${abonneInstance?.telephoneFixe}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'telephonePortable', 'error')} ">
	<label for="telephonePortable">
		<g:message code="abonne.telephonePortable.label" default="Telephone portable" />
		
	</label>
	<g:textField name="telephonePortable" size="10" pattern="${abonneInstance.constraints.telephonePortable.matches}" value="${abonneInstance?.telephonePortable}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'mail', 'error')} required">
	<label for="mail">
		<g:message code="abonne.mail.label" default="Mail" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="mail" size="30" required="" value="${abonneInstance?.mail}"/>
</div>


<%-- ================================  --%>
<%-- ========= SI ADMIN =============  --%>
<%-- ================================  --%>
<g:if test="${session?.user?.admin}">

<h1>Informations pour l'association</h1>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'numeroLicence', 'error')} required">
	<label for="numeroLicence">
		<g:message code="abonne.numeroLicence.label" default="Numero de licence" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="numeroLicence" maxlength="20" required="" value="${abonneInstance?.numeroLicence}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'dateCertificat', 'error')} required">
	<label for="dateCertificat">
		<g:message code="abonne.dateCertificat.label" default="Date du certificat médical" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateCertificat" precision="day"  value="${abonneInstance?.dateCertificat}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'encadrant', 'error')} ">
	<label for="encadrant">
		<g:message code="abonne.encadrant.label" default="Encadrant" />
		
	</label>
	<g:checkBox name="encadrant" value="${abonneInstance?.encadrant}" />
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'technicien', 'error')} ">
	<label for="technicien">
		<g:message code="abonne.technicien.label" default="Technicien" />
		
	</label>
	<g:checkBox name="technicien" value="${abonneInstance?.technicien}" />
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'secouriste', 'error')} ">
	<label for="secouriste">
		<g:message code="abonne.secouriste.label" default="Secouriste" />
		
	</label>
	<g:checkBox name="secouriste" value="${abonneInstance?.secouriste}" />
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'allergique', 'error')} ">
	<label for="allergique">
		<g:message code="abonne.allergique.label" default="Allergique" />
		
	</label>
	<g:checkBox name="allergique" value="${abonneInstance?.allergique}" />
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'comiteDirecteur', 'error')} ">
	<label for="comiteDirecteur">
		<g:message code="abonne.comiteDirecteur.label" default="Comite Directeur" />
		
	</label>
	<g:checkBox name="comiteDirecteur" value="${abonneInstance?.comiteDirecteur}" />
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'aideFamille', 'error')} ">
	<label for="aideFamille">
		<g:message code="abonne.aideFamille.label" default="Aide Famille" />
		
	</label>
	<g:checkBox name="aideFamille" value="${abonneInstance?.aideFamille}" />
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'autorisationParentale', 'error')} ">
	<label for="autorisationParentale">
		<g:message code="abonne.autorisationParentale.label" default="Autorisation Parentale" />
		
	</label>
	<g:checkBox name="autorisationParentale" value="${abonneInstance?.autorisationParentale}" />
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'prixAbonnement', 'error')} required">
	<label for="prixAbonnement">
		<g:message code="abonne.prixAbonnement.label" default="Prix de l'abonnement" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="prixAbonnement" size="5" value="${fieldValue(bean: abonneInstance, field: 'prixAbonnement')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'prixAssurance', 'error')} required">
	<label for="prixAssurance">
		<g:message code="abonne.prixAssurance.label" default="Prix de l'assurance" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="prixAssurance" size="5" value="${fieldValue(bean: abonneInstance, field: 'prixAssurance')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'ecole', 'error')} required">
	<label for="ecole">
		<g:message code="abonne.ecole.label" default="Ecole Scaphandre" />		
	</label>
	<g:select id="ecole" name="ecole.id" from="${scubagrails.EcoleScaphandre.list()}" optionKey="id" value="${abonneInstance?.ecole?.id}" class="many-to-one" noSelection="['null':'Aucune']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'ecoleApnee', 'error')} required">
	<label for="ecoleApnee">
		<g:message code="abonne.ecoleApnee.label" default="Ecole Apnée" />		
	</label>
	<g:select id="ecoleApnee" name="ecoleApnee.id" from="${scubagrails.EcoleApnee.list()}" optionKey="id" value="${abonneInstance?.ecoleApnee?.id}" class="many-to-one" noSelection="['null':'Aucune']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'lesSaisonsSelection', 'error')} ">
		<label for="lesSaisonsSelection"> <g:message
				code="abonne.enregistrements.label"
				default="Sélectionner la ou les saisons auxquels l'abonné est inscrit (utiliser la touche CTRL de votre clavier)" />

		</label>
		<g:select name="lesSaisonsSelection" optionKey="id"
			from="${listeDesSaisons}" size="5" multiple="yes"
			value="${abonneInstance?.enregistrements*.saison?.id}" />
</div>

	<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'niveau', 'error')} required">
		<label for="niveau">
			<g:message code="abonne.niveau.label" default="Niveau Scaphandre" />			
		</label>
		<g:select id="niveau" name="niveau.id" from="${scubagrails.NiveauScaphandre.list()}" optionKey="id" value="${abonneInstance?.niveau?.id}" class="many-to-one" noSelection="['null':'Aucun']"/>
	</div>
	
	<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'niveauApnee', 'error')} required">
		<label for="niveauApnee">
			<g:message code="abonne.niveauApnee.label" default="Niveau Apnée" />			
		</label>
		<g:select id="niveauApnee" name="niveauApnee.id" from="${scubagrails.NiveauApnee.list()}" optionKey="id" value="${abonneInstance?.niveauApnee?.id}" class="many-to-one" noSelection="['null':'Aucun']"/>
	</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'typeMembre', 'error')} required">
	<label for="typeMembre">
		<g:message code="abonne.typeMembre.label" default="Type de membre" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="typeMembre" name="typeMembre.id" from="${scubagrails.TypeMembre.list()}" optionKey="id" required="" value="${abonneInstance?.typeMembre?.id}" class="many-to-one"/>
</div>
</g:if>

