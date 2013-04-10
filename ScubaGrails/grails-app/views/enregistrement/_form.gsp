<%@ page import="scubagrails.Enregistrement" %>



<div class="fieldcontain ${hasErrors(bean: enregistrementInstance, field: 'abonne', 'error')} required">
	<label for="abonne">
		<g:message code="enregistrement.abonne.label" default="Abonne" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="abonne" name="abonne.id" from="${scubagrails.Abonne.list()}" optionKey="id" required="" value="${enregistrementInstance?.abonne?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: enregistrementInstance, field: 'saison', 'error')} required">
	<label for="saison">
		<g:message code="enregistrement.saison.label" default="Saison" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="saison" name="saison.id" from="${scubagrails.Saison.list()}" optionKey="id" required="" value="${enregistrementInstance?.saison?.id}" class="many-to-one"/>
</div>

