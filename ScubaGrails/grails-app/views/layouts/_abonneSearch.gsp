<div id="search"> 
	<g:form url='[controller: "abonne", action: "search"]' 
		id="abonneSearchForm" 
		name="abonneSearchForm" 
		method="get"> 
			<g:textField name="q" value="${params.q}" size="10" /> 
			<input type="submit" value="Chercher" /> 
	</g:form> 
</div> 