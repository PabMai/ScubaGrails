<%@ page import="scubagrails.Ecole" %>



<div class="fieldcontain ${hasErrors(bean: ecoleInstance, field: 'nom', 'error')} required">
	<label for="nom">
		<g:message code="ecole.nom.label" default="Nom" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nom" maxlength="30" required="" value="${ecoleInstance?.nom}"/>
</div>

<%--<div class="fieldcontain ${hasErrors(bean: ecoleInstance, field: 'abonnes', 'error')} ">--%>
<%--	<label for="abonnes">--%>
<%--		<g:message code="ecole.abonnes.label" default="Abonnes" />--%>
<%--		--%>
<%--	</label>--%>
<%--	--%>
<%--<ul class="one-to-many">--%>
<%--<g:each in="${ecoleInstance?.abonnes?}" var="a">--%>
<%--    <li><g:link controller="abonne" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>--%>
<%--</g:each>--%>
<%--<li class="add">--%>
<%--<g:link controller="abonne" action="create" params="['ecole.id': ecoleInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'abonne.label', default: 'Abonne')])}</g:link>--%>
<%--</li>--%>
<%--</ul>--%>
<%----%>
<%--</div>--%>

