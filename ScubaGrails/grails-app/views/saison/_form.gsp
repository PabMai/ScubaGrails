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


