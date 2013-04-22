<%@ page import="scubagrails.User"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="main" />
<title>Authentification Scuba Grails</title>
</head>
<body>
	<g:render template="/layouts/barreNavigation" />
	<div class="body">
		<h1>&nbsp;&nbsp;&nbsp;Bienvenue, veuillez vous identifier :</h1>
		<g:if test="${flash.message}">
			<div class="message">
				${flash.message}
			</div>
		</g:if>
		<g:form action="authenticate" method="post">
			<div class="dialog">				
				<br />
				<table>
					<tbody>
						<tr class="prop">
							<td valign="top" class="name"><label for="login">Login :</label>
							</td>
							<td valign="top"><input type="text" id="login" name="login" />
							</td>
						</tr>
						<tr class="prop">
							<td valign="top" class="name"><label for="password">Mot de passe :</label>
							</td>
							<td valign="top"><input type="password" id="password"
								name="password" /></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="buttons" style="width:126px;margin:auto;">
				<span class="button"> <input type="submit" value="Se connecter" />
				</span>
			</div>
		</g:form>
	</div>
</body>
</html>
