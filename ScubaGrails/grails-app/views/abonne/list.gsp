<%@ page import="scubagrails.Abonne" %>
<%@ page import="scubagrails.Niveau" %>
<%@ page import="scubagrails.Ecole" %>
<%@ page import="scubagrails.TypeMembre" %>
<r:require module="export"/>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'abonne.label', default: 'Abonne')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<filterpane:includes />
	</head>
	<body>
		<a href="#list-abonne" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
		<g:render template="/layouts/abonneSearch" />
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link controller="admin" action="index" class="adminHome"><g:message code="scubaGrails.nav.homeAdmin.label"/></g:link></li>
				<li><g:link class="create" action="create"><g:message code="abonne.new.label" args="[entityName]" /></g:link></li>		
			</ul>
		</div>
		<div id="list-abonne" class="content scaffold-list" role="main">
			<filterpane:isFiltered><span class="indicationFiltre">Cette liste est filtrée !</span></filterpane:isFiltered>
			<h1>Liste des abonnés</h1>			
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:if test="${request.messageRequete}">
 				<div class="message" role="status">${request.messageRequete}</div> 
 			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn params="${filterParams + ['q': params?.q]}" property="numeroLicence" title="${message(code: 'abonne.numeroLicenceListe.label', default: 'N° licence')}" />						
					
						<g:sortableColumn params="${filterParams + ['q': params?.q]}" property="nom" title="${message(code: 'abonne.nom.label', default: 'Nom')}" />
						
						<g:sortableColumn params="${filterParams + ['q': params?.q]}" property="prenom" title="${message(code: 'abonne.prenom.label', default: 'Prenom')}" />
					
						<g:sortableColumn params="${filterParams + ['q': params?.q]}" property="dateNaissance" title="${message(code: 'abonne.dateNaissance.label', default: 'Age')}" />
										
						<g:sortableColumn params="${filterParams + ['q': params?.q]}" property="ville" title="${message(code: 'abonne.ville.label', default: 'Ville')}" />
					
						<g:sortableColumn params="${filterParams + ['q': params?.q]}" property="niveau" title="${message(code: 'abonne.niveau.label', default: 'Niveau')}" />
						
						<g:sortableColumn params="${filterParams + ['q': params?.q]}" property="ecole" title="${message(code: 'abonne.ecole.label', default: 'Ecole')}" />
						
						<g:sortableColumn params="${filterParams + ['q': params?.q]}" property="typeMembre" title="${message(code: 'abonne.typeMembre.label', default: 'Type')}" />
						
						<g:sortableColumn params="${filterParams + ['q': params?.q]}" property="prixAbonnement" title="${message(code: 'abonne.prixAbonnement.label', default: 'Abonnement')}" />
					
<%--						<th> Action </th>--%>
					</tr>
				</thead>
				<tbody>
				<g:each in="${abonneInstanceList}" status="i" var="abonneInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${abonneInstance.id}">${fieldValue(bean: abonneInstance, field: "numeroLicence")} </g:link></td>
					
						<td>${fieldValue(bean: abonneInstance, field: "nom")}</td>	
						
						<td>${fieldValue(bean: abonneInstance, field: "prenom")}</td>											
					
						<td>${abonneInstance?.getAge()}</td>
					
						<td>${fieldValue(bean: abonneInstance, field: "ville")}</td>
					
						<td>${fieldValue(bean: abonneInstance, field: "niveau")}</td>
						
						<td>${fieldValue(bean: abonneInstance, field: "ecole")}</td>
						
						<td>${fieldValue(bean: abonneInstance, field: "typeMembre")}</td>
						
						<td>${fieldValue(bean: abonneInstance, field: "prixAbonnement")} &euro;</td>
						
<%--						<td> <g:link action="show" id="${abonneInstance.id}"> Visualiser </g:link> </td>--%>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${abonneInstanceTotal}" params="${filterParams}"/>
				<filterpane:filterButton text="Filtrer" />						
				<filterpane:isFiltered><g:link action="list">Effacer le filtre</g:link></filterpane:isFiltered>
				<select name="filtreRecherche" ONCHANGE="location = this.options[this.selectedIndex].value;">
				 <option value="">-Choisir un filtre-</option> 
				 <option value="filter?sort=nom&order=&filter.op.ville=ILike&filter.ville=%25Saint+Barth%25&filter.op.codePostal=Equal&filter.codePostal=49124">Résidant St Barth</option>
				 <option value="filter?sort=nom&order=asc&filter.op.niveau.niveau=Equal&filter.niveau.niveau=N1">Niveau N1</option>
				 <option value="filter?sort=nom&order=asc&filter.op.niveau.niveau=Equal&filter.niveau.niveau=N2">Niveau N2</option>
				 <option value="filter?sort=nom&order=asc&filter.op.niveau.niveau=Equal&filter.niveau.niveau=N3">Niveau N3</option>
				 <option value="filter?sort=nom&order=asc&filter.op.ecole.nom=Equal&filter.ecole.nom=Initiation">Ecole Initiation</option>
				 <option value="filter?sort=nom&order=asc&filter.op.ecole.nom=Equal&filter.ecole.nom=N1">Ecole N1</option>
				 <option value="filter?sort=nom&order=asc&filter.op.ecole.nom=Equal&filter.ecole.nom=N2">Ecole N2</option>
				 <option value="filter?sort=nom&order=asc&filter.op.ecole.nom=Equal&filter.ecole.nom=MF1">Ecole MF1</option>
				 <option value="">Autres...</option>
				</select>
			</div>
			<br />
			<export:formats formats="['csv', 'excel', 'pdf']" params="${filterParams + ['sort': params?.sort,'q' : params?.q]}"  />
				          		
									

<%--			<filterpane:filterLink values="${[ville:[op:'ILike', value:'%Saint Barth%'], codePostal:[op:'Equal',value:'49124']]}" controller="abonne" action="filter">--%>
<%--  					"Résidant St Barth"--%>
<%--			</filterpane:filterLink>	--%>


		</div>
		<filterpane:filterPane domain="Abonne" 
		dialog="true" 
		excludeProperties="mimeType,avatar,login,password,numeroRue"
		associatedProperties="niveau.niveau, ecole.nom, typeMembre.nom"
        filterPropertyValues="${[dateNaissance: [precision: 'day'], 
			dateCertificat: [precision: 'day'], 
			'niveau.niveau': [values:Niveau.list()],
			'ecole.nom': [values:Ecole.list()],
			'typeMembre.nom': [values:TypeMembre.list()]
			]}"
		/>
		
	</body>
</html>
