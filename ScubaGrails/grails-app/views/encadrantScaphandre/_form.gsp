<%@ page import="scubagrails.EncadrantScaphandre" %>



<div class="fieldcontain ${hasErrors(bean: encadrantScaphandreInstance, field: 'nom', 'error')} required">
	<label for="nom">
		<g:message code="encadrantScaphandre.nom.label" default="Nom" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nom" maxlength="30" required="" value="${encadrantScaphandreInstance?.nom}"/>
</div>

<%--<div class="fieldcontain ${hasErrors(bean: encadrantScaphandreInstance, field: 'abonnes', 'error')} ">--%>
<%--	<label for="abonnes">--%>
<%--		<g:message code="encadrantScaphandre.abonnes.label" default="Abonnes" />--%>
<%--		--%>
<%--	</label>--%>
	
<%--<ul class="one-to-many">--%>
<%--<g:each in="${encadrantScaphandreInstance?.abonnes?}" var="a">--%>
<%--    <li><g:link controller="abonne" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>--%>
<%--</g:each>--%>
<%--<li class="add">--%>
<%--<g:link controller="abonne" action="create" params="['encadrantScaphandre.id': encadrantScaphandreInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'abonne.label', default: 'Abonne')])}</g:link>--%>
<%--</li>--%>
<%--</ul>--%>

</div>

