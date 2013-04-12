<%@ page import="scubagrails.TypeMembre" %>



<div class="fieldcontain ${hasErrors(bean: typeMembreInstance, field: 'nom', 'error')} required">
	<label for="nom">
		<g:message code="typeMembre.nom.label" default="Nom" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nom" maxlength="50" required="" value="${typeMembreInstance?.nom}"/>
</div>





