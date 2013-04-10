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
		<g:message code="abonne.prenom.label" default="Prenom" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="prenom" maxlength="50" required="" value="${abonneInstance?.prenom}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'dateNaissance', 'error')} required">
	<label for="dateNaissance">
		<g:message code="abonne.dateNaissance.label" default="Date Naissance" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateNaissance" precision="day"  value="${abonneInstance?.dateNaissance}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'departementNaissance', 'error')} required">
	<label for="departementNaissance">
		<g:message code="abonne.departementNaissance.label" default="Departement Naissance" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="departementNaissance" maxlength="30" required="" value="${abonneInstance?.departementNaissance}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'lieuNaissance', 'error')} required">
	<label for="lieuNaissance">
		<g:message code="abonne.lieuNaissance.label" default="Lieu Naissance" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lieuNaissance" maxlength="50" required="" value="${abonneInstance?.lieuNaissance}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'sexe', 'error')} required">
	<label for="sexe">
		<g:message code="abonne.sexe.label" default="Sexe" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="sexe" from="${scubagrails.type.Sexe?.values()}" keys="${scubagrails.type.Sexe.values()*.name()}" required="" value="${abonneInstance?.sexe?.name()}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'numeroRue', 'error')} required">
	<label for="numeroRue">
		<g:message code="abonne.numeroRue.label" default="Numero Rue" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="numeroRue" maxlength="8" required="" value="${abonneInstance?.numeroRue}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'nomRue', 'error')} required">
	<label for="nomRue">
		<g:message code="abonne.nomRue.label" default="Nom Rue" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nomRue" maxlength="100" required="" value="${abonneInstance?.nomRue}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'codePostal', 'error')} required">
	<label for="codePostal">
		<g:message code="abonne.codePostal.label" default="Code Postal" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="codePostal" maxlength="5" required="" value="${abonneInstance?.codePostal}"/>
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
		<g:message code="abonne.telephoneFixe.label" default="Telephone Fixe" />
		
	</label>
	<g:textField name="telephoneFixe" pattern="${abonneInstance.constraints.telephoneFixe.matches}" value="${abonneInstance?.telephoneFixe}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'telephonePortable', 'error')} ">
	<label for="telephonePortable">
		<g:message code="abonne.telephonePortable.label" default="Telephone Portable" />
		
	</label>
	<g:textField name="telephonePortable" pattern="${abonneInstance.constraints.telephonePortable.matches}" value="${abonneInstance?.telephonePortable}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'mail', 'error')} required">
	<label for="mail">
		<g:message code="abonne.mail.label" default="Mail" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="mail" required="" value="${abonneInstance?.mail}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'numeroLicence', 'error')} required">
	<label for="numeroLicence">
		<g:message code="abonne.numeroLicence.label" default="Numero Licence" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="numeroLicence" maxlength="20" required="" value="${abonneInstance?.numeroLicence}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'dateCertificat', 'error')} required">
	<label for="dateCertificat">
		<g:message code="abonne.dateCertificat.label" default="Date Certificat" />
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
		<g:message code="abonne.prixAbonnement.label" default="Prix Abonnement" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="prixAbonnement" value="${fieldValue(bean: abonneInstance, field: 'prixAbonnement')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'prixAssurance', 'error')} required">
	<label for="prixAssurance">
		<g:message code="abonne.prixAssurance.label" default="Prix Assurance" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="prixAssurance" value="${fieldValue(bean: abonneInstance, field: 'prixAssurance')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'ecole', 'error')} required">
	<label for="ecole">
		<g:message code="abonne.ecole.label" default="Ecole" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="ecole" name="ecole.id" from="${scubagrails.Ecole.list()}" optionKey="id" required="" value="${abonneInstance?.ecole?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'enregistrements', 'error')} ">
	<label for="enregistrements">
		<g:message code="abonne.enregistrements.label" default="Enregistrements" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${abonneInstance?.enregistrements?}" var="e">
    <li><g:link controller="enregistrement" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="enregistrement" action="create" params="['abonne.id': abonneInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'enregistrement.label', default: 'Enregistrement')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'niveau', 'error')} required">
	<label for="niveau">
		<g:message code="abonne.niveau.label" default="Niveau" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="niveau" name="niveau.id" from="${scubagrails.Niveau.list()}" optionKey="id" required="" value="${abonneInstance?.niveau?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: abonneInstance, field: 'typeMembre', 'error')} required">
	<label for="typeMembre">
		<g:message code="abonne.typeMembre.label" default="Type Membre" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="typeMembre" name="typeMembre.id" from="${scubagrails.TypeMembre.list()}" optionKey="id" required="" value="${abonneInstance?.typeMembre?.id}" class="many-to-one"/>
</div>

