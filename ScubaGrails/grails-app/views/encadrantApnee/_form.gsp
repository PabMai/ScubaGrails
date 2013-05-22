<%@ page import="scubagrails.EncadrantApnee" %>



<div class="fieldcontain ${hasErrors(bean: encadrantApneeInstance, field: 'nom', 'error')} required">
	<label for="nom">
		<g:message code="encadrantApnee.nom.label" default="Nom" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nom" maxlength="30" required="" value="${encadrantApneeInstance?.nom}"/>
</div>

<%--<div class="fieldcontain ${hasErrors(bean: encadrantApneeInstance, field: 'abonnes', 'error')} ">--%>
<%--	<label for="abonnes">--%>
<%--		<g:message code="encadrantApnee.abonnes.label" default="Abonnes" />--%>
<%--		--%>
<%--	</label>--%>
	
<%--<ul class="one-to-many">--%>
<%--<g:each in="${encadrantApneeInstance?.abonnes?}" var="a">--%>
<%--    <li><g:link controller="abonne" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>--%>
<%--</g:each>--%>
<%--<li class="add">--%>
<%--<g:link controller="abonne" action="create" params="['encadrantApnee.id': encadrantApneeInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'abonne.label', default: 'Abonne')])}</g:link>--%>
<%--</li>--%>
<%--</ul>--%>

</div>

