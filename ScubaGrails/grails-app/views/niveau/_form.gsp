<%@ page import="scubagrails.Niveau" %>



<div class="fieldcontain ${hasErrors(bean: niveauInstance, field: 'niveau', 'error')} required">
	<label for="niveau">
		<g:message code="niveau.niveau.label" default="Niveau" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="niveau" maxlength="50" required="" value="${niveauInstance?.niveau}"/>
</div>




</div>

