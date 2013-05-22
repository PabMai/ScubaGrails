<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Console d'Administration - Scuba Grails</title>
		<style type="text/css" media="screen">
			#status {
				background-color: #eee;
				border: .2em solid #fff;
				margin: 2em 2em 1em;
				padding: 1em;
				width: 12em;
				float: left;
				-moz-box-shadow: 0px 0px 1.25em #ccc;
				-webkit-box-shadow: 0px 0px 1.25em #ccc;
				box-shadow: 0px 0px 1.25em #ccc;
				-moz-border-radius: 0.6em;
				-webkit-border-radius: 0.6em;
				border-radius: 0.6em;
			}

			.ie6 #status {
				display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
			}

			#status ul {
				font-size: 0.9em;
				list-style-type: none;
				margin-bottom: 0.6em;
				padding: 0;
			}

			#status li {
				line-height: 1.3;
			}

			#status h1 {
				text-transform: uppercase;
				font-size: 1.1em;
				margin: 0 0 0.3em;
			}

			#page-body {
				margin: 2em 1em 1.25em 18em;
			}

			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

<%--			p {--%>
<%--				line-height: 1.5;--%>
<%--				margin: 0.25em 0;--%>
<%--			}--%>

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				line-height: 1.3;
				list-style-position: inside;
				margin: 0.25em 0;
			}

			@media screen and (max-width: 480px) {
				#status {
					display: none;
				}

				#page-body {
					margin: 0 1em 1em;
				}

				#page-body h1 {
					margin-top: 0;
				}
			}
		</style>
	</head>
	<body>		
		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="status" role="complementary">
		<g:link controller="admin" action="refreshAdminData">
			<g:img dir="images/scuba" file="refresh.png" class="refreshDonneesAdmin" title="Rafraichir les données"/>
		</g:link>
			<h1>Indicateurs</h1>			
			<ul>				
				<li>Nombre d'abonnés : ${nbAbonne}</li>
				<li>Nombre d'administrateurs : ${nbUtilisateur}</li>
				<li>Nombre d'écoles : ${nbEcole}</li>
				<li>Nombre de saisons : ${nbSaison}</li>
				<li>Nombre de niveaux : ${nbNiveau}</li>				
			</ul>
			<br />
			<h1>Certificats</h1>
			<ul>
				<g:if test="${session?.listeAbonneCMPerime?.size() > 0}">
					<li><span style="color: red">Périmés : </span>
					<g:link controller="abonne" action="showAbonneCMPerime"> ${session?.listeAbonneCMPerime?.size()}</g:link>
					</li>
				</g:if>
				<g:else>
					<li>Périmés : 0</li>
				</g:else>
				<g:if test="${session?.listeAbonneCMPerimeDansLeMois?.size() > 0}">
					<li>Périmés dans le mois :
					<g:link controller="abonne" action="showAbonneCMPerimeMois"> ${session?.listeAbonneCMPerimeDansLeMois?.size()}</g:link>
					</li>
				</g:if>
				<g:else>
					<li>Périmés dans le mois : 0</li>
				</g:else>				
			</ul>
			<br />
			<!--  Saison en cours -->
			<g:if test="${session?.saisonEnCours}">
			<h1>Saison ${session?.saisonEnCours?.libelle}</h1>
			<ul>
				<li>Effectif : ${session?.saisonEnCours?.enregistrements?.count?.size}</li>			
				<li>Nombre d'hommes : ${session?.nbHommeSaisonEnCours} </li>	
				<li>Nombre de femmes : ${session?.nbFemmeSaisonEnCours} </li>
				<%-- Stats sur les écoles ?? --%>	
<%--				<g:each in="${session?.statEcole}" var="ecole">--%>
<%--				<li>Ecole ${ecole.key?.encodeAsHTML()} : ${ecole.value?.encodeAsHTML()}</li>--%>
<%--				</g:each>	--%>
<%--				<br />--%>
				<g:each in="${session?.statTypeM}" var="typeM">
				<li>${typeM.key?.encodeAsHTML()} : ${typeM.value?.encodeAsHTML()}</li>
				</g:each>							
			</ul>
			</g:if>
			<g:else>
				<h1>Pas de saison en cours</h1>
			</g:else>
			
			
			<br />

			<ul>
			<li>Version actuelle : <g:meta name="app.version"/></li>
			</ul>				
		</div>
		<g:render template="/layouts/barreNavigation" />
		<div id="page-body" role="main">
			<g:if test="${flash.message}">
				<div class="message" role="status" style="margin:0">
					${flash.message}
				</div>
			</g:if>
			<h1>Bienvenue sur l'interface de gestion du club</h1>
			<p>Afin de gérer les différentes fonctionnalit&eacute;s du club, veuillez cliquer sur les actions suivantes : </p>
			<br /><br />

			<!--  Interface admin -->
			<table id="interfaceAdmin">
			<tr id="trInterfaceAdmin">
				<td>
					<g:link controller="abonne">
						<g:img dir="images/scuba/admin" file="user.png"  alt="Gérer les abonnés" />
					</g:link>
				</td>
				<td>
					<g:link controller="user">
							<g:img dir="images/scuba/admin" file="admin.png"  alt="Gérer les administrateurs" />
					</g:link>			
				</td>
				<td>
					<g:link controller="ecole">
							<g:img dir="images/scuba/admin" file="ecole.png"  alt="Gérer les écoles" />
					</g:link>
				</td>
			</tr>
			<tr id="trInterfaceAdmin">
				<td>Gérer les abonnés</td>
				<td>Gérer les administrateurs</td>
				<td>Gérer les écoles</td>
			</tr>
			</table>
			
			<br />
			
			<table id="interfaceAdmin">
			<tr id="trInterfaceAdmin">
				<td>
					<g:link controller="niveau">
						<g:img dir="images/scuba/admin" file="niveau.png"  alt="Gérer les niveaux" />
					</g:link>
				</td>
				<td>
					<g:link controller="typeMembre">
						<g:img dir="images/scuba/admin" file="typeMembre.png" alt="Gérer les types de membre" />
					</g:link>
				</td>
				<td>
					<g:link controller="saison">
						<g:img dir="images/scuba/admin" file="saison.png" alt="Gérer les saisons" />
					</g:link>
				</td>
			</tr>
			<tr id="trInterfaceAdmin">
				<td>Gérer les niveaux</td>
				<td>Gérer les types de membre</td>
				<td>Gérer les saisons</td>
			</tr>			
			</table>
			
			<br />
			
			<table id="interfaceAdmin">
			<tr id="trInterfaceAdmin">
				<td>
					<g:link controller="admin" action="indexImport">
						<g:img dir="images/scuba/admin" file="importExcel.png" alt="Importer un fichier excel" />
					</g:link>
				</td>
				<td>
					<g:link controller="encadrant">
						<g:img dir="images/scuba/admin" file="encadrant.png" alt="Gérer les types d'encadrant" />
					</g:link>
				</td>
				<td>
					<g:link controller="admin">
						<g:img dir="images/scuba/admin" file="questionMark.png" alt="?" />
					</g:link>
				</td>
			</tr>
			<tr id="trInterfaceAdmin">
				<td>Importer un fichier excel</td>
				<td>Gérer les types d'encadrant</td>
				<td>?</td>
			</tr>			
			</table>

						
<%--			<div id="controller-list" role="navigation">--%>
<%--				<h2>Choix :</h2>--%>
<%--				<ul>--%>
<%--					<g:if test="${session?.user?.admin}">--%>
<%--						<li class="controller"> <g:link controller="abonne"> Gérer les abonnés </g:link> </li>--%>
<%--						<li class="controller"> <g:link controller="user"> Gérer les utilisateurs </g:link> </li>--%>
<%--						<li class="controller"> <g:link controller="ecole"> Gérer les écoles </g:link> </li>--%>
<%--						<li class="controller"> <g:link controller="niveau"> Gérer les niveaux </g:link> </li>--%>
<%--						<li class="controller"> <g:link controller="typeMembre"> Gérer les types de membre </g:link> </li>--%>
<%--						<li class="controller"> <g:link controller="saison"> Gérer les saisons </g:link> </li>--%>
<%--					</g:if>--%>
<%--					--%>
<%--				</ul>--%>
<%--			</div>--%>
		</div>
	</body>
</html>
