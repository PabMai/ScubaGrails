<%@ page import="scubagrails.Niveau" %>



<div class="fieldcontain ${hasErrors(bean: niveauInstance, field: 'niveau', 'error')} required">
	<label for="niveau">
		<g:message code="niveau.niveau.label" default="Niveau" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="niveau" maxlength="50" required="" value="${niveauInstance?.niveau}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: niveauInstance, field: 'abonnes', 'error')} ">
	<label for="abonnes">
		<g:message code="niveau.abonnes.label" default="Abonnes" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${niveauInstance?.abonnes?}" var="a">
    <li><g:link controller="abonne" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="abonne" action="create" params="['niveau.id': niveauInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'abonne.label', default: 'Abonne')])}</g:link>
</li>
</ul>

</div>

