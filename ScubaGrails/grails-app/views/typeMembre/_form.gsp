<%@ page import="scubagrails.TypeMembre" %>



<div class="fieldcontain ${hasErrors(bean: typeMembreInstance, field: 'nom', 'error')} required">
	<label for="nom">
		<g:message code="typeMembre.nom.label" default="Nom" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nom" maxlength="50" required="" value="${typeMembreInstance?.nom}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: typeMembreInstance, field: 'abonnes', 'error')} ">
	<label for="abonnes">
		<g:message code="typeMembre.abonnes.label" default="Abonnes" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${typeMembreInstance?.abonnes?}" var="a">
    <li><g:link controller="abonne" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="abonne" action="create" params="['typeMembre.id': typeMembreInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'abonne.label', default: 'Abonne')])}</g:link>
</li>
</ul>

</div>

