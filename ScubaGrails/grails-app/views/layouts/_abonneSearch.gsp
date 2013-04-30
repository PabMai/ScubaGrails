<div id="search"> 
	<g:form url='[controller: "abonne", action: "search"]' 
		id="abonneSearchForm" 
		name="abonneSearchForm" 
		method="get"> 
<%--			<g:textField name="q" value="${params.q}" size="10" /> --%>
			<richui:autoComplete name="q" value="${params.q}" 
			action="${createLinkTo('dir': 'abonne/searchAjaxAutoComplete')}" 
			minQueryLength="2"			
			style="float:left;width:116px"	/>
			<input type="submit" value="Chercher" style="float:right" /> 
		
	</g:form> 
</div> 