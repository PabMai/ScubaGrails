<%@ page import="scubagrails.NiveauApnee" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">		
		<title>Gestion des niveaux</title>
		<script type="text/javascript">
		function afficheDiv(divsId){  
			  var div = document.getElementById(divsId);
			  if(div.style.display == ""){  
				  div.style.display = "none";  
			  } else {  
				  div.style.display = "";  
			  }  
			}  


		</script>
	</head>
	<body>
		<a href="#list-niveau" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
		<g:if test="${session?.user?.admin}">
			<g:render template="/layouts/abonneSearch" />
		</g:if>
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link controller="admin" action="index" class="adminHome"><g:message code="scubaGrails.nav.homeAdmin.label"/></g:link></li>
			</ul>
		</div>
		<div id="importAbonne" class="content scaffold-list" role="main">
						
			
			<h1>Le traitement a été effectué</h1>
			
			<br />
			
			<g:if test="${session.gestLogs}">	
				<div style="margin-left:28px">	
					<p> Nombre d'ajout : ${session.gestLogs?.nbSucces}</p>
					<p> Nombre d'erreurs : ${session.gestLogs?.nbErrors}</p>
				</div>	
				<br />
				<g:submitButton style="margin-left:28px" name="afficheLog" value="Afficher les logs" onclick="afficheDiv('logs');" />
			
				<div id="logs" style="display: none;"> 
				<br />			
				<g:each in="${session.gestLogs?.logs}">
						${it}<br />			
				</g:each>
			</div>
			
			</g:if>			
			<g:else>
				<p>Une erreur est survenue</p>
			</g:else>	
			

		</div>
	</body>
</html>
