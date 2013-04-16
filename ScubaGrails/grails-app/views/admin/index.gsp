<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Welcome to Grails</title>
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

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}

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
			<h1>Indicateurs</h1>
			<ul>				
				<li>Nombre d'abonnés : ${nbAbonne}</li>
				<li>Nombre d'administrateurs : ${nbUtilisateur}</li>
				<li>Nombre d'écoles : ${nbEcole}</li>
				<li>Nombre de saisons : ${nbSaison}</li>
				<li>Nombre de niveaux : ${nbNiveau}</li>								
				
				<g:if test="${session?.listeAbonneCMPerime?.size() > 0}">
				<li><span style="color: red">Certificat périmé : </span>
				<g:link controller="abonne" action="showAbonneCMPerime"> ${session?.listeAbonneCMPerime?.size()}</g:link>
				</li>
				</g:if>
				<g:else>
				<li>Certificat périmé : 
				0
				</li>
				</g:else>
				
			</ul>
			<h1>Info</h1>
			<ul>
				<li>Version actuelle : <g:meta name="app.version"/></li>				
			</ul>
			<br />
			<br />
			<br />
			<br />
		</div>
		<g:render template="/layouts/barreNavigation" />
		<div id="page-body" role="main">
			<h1>Bienvenue Scuba Grails</h1>

<%--			<div id="controller-list" role="navigation">--%>
<%--				<h2>Available Controllers:</h2>--%>
<%--				<ul>--%>
<%--					<g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">--%>
<%--						<li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>--%>
<%--					</g:each>--%>
<%--				</ul>--%>
<%--			</div>--%>
			
			<div id="controller-list" role="navigation">
				<h2>Choix :</h2>
				<ul>
					<g:if test="${session?.user?.admin}">
						<li class="controller"> <g:link controller="abonne"> Gérer les abonnés </g:link> </li>
						<li class="controller"> <g:link controller="user"> Gérer les utilisateurs </g:link> </li>
						<li class="controller"> <g:link controller="ecole"> Gérer les écoles </g:link> </li>
						<li class="controller"> <g:link controller="niveau"> Gérer les niveaux </g:link> </li>
						<li class="controller"> <g:link controller="typeMembre"> Gérer les types de membre </g:link> </li>
						<li class="controller"> <g:link controller="saison"> Gérer les saisons </g:link> </li>
					</g:if>
					
				</ul>
			</div>
		</div>
	</body>
</html>
