<%@ page import="scubagrails.Saison" %>



<div class="fieldcontain ${hasErrors(bean: saisonInstance, field: 'libelle', 'error')} required">
	<label for="libelle">
		<g:message code="saison.libelle.label" default="Libelle" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="libelle" maxlength="60" required="" value="${saisonInstance?.libelle}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: saisonInstance, field: 'dateDebut', 'error')} required">
	<label for="dateDebut">
		<g:message code="saison.dateDebut.label" default="Date Debut" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateDebut" precision="day"  value="${saisonInstance?.dateDebut}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: saisonInstance, field: 'dateFin', 'error')} required">
	<label for="dateFin">
		<g:message code="saison.dateFin.label" default="Date Fin" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateFin" precision="day"  value="${saisonInstance?.dateFin}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: saisonInstance, field: 'enregistrements', 'error')} ">
	<label for="enregistrements">
		<g:message code="saison.enregistrements.label" default="Enregistrements" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${saisonInstance?.enregistrements?}" var="e">
    <li><g:link controller="enregistrement" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="enregistrement" action="create" params="['saison.id': saisonInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'enregistrement.label', default: 'Enregistrement')])}</g:link>
</li>
</ul>

</div>

