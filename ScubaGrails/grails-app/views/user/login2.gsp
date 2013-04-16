<%@ page import="scubagrails.User"%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />--%>
<%--<meta name="layout" content="main" />--%>
<%--<title>Authentification Scuba Grails</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--	<g:render template="/layouts/barreNavigation" />--%>
<%--	<div class="body">--%>
<%--		<h1>Bienvenue, veuillez vous identifier :</h1>--%>
<%--		<g:if test="${flash.message}">--%>
<%--			<div class="message">--%>
<%--				${flash.message}--%>
<%--			</div>--%>
<%--		</g:if>--%>
<%--		<g:form action="authenticate" method="post">--%>
<%--			<div class="dialog">				--%>
<%--				<br />--%>
<%--				<table>--%>
<%--					<tbody>--%>
<%--						<tr class="prop">--%>
<%--							<td valign="top" class="name"><label for="login">Login :</label>--%>
<%--							</td>--%>
<%--							<td valign="top"><input type="text" id="login" name="login" />--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr class="prop">--%>
<%--							<td valign="top" class="name"><label for="password">Mot de passe :</label>--%>
<%--							</td>--%>
<%--							<td valign="top"><input type="password" id="password"--%>
<%--								name="password" /></td>--%>
<%--						</tr>--%>
<%--					</tbody>--%>
<%--				</table>--%>
<%--			</div>--%>
<%--			<div class="buttons">--%>
<%--				<span class="button"> <input type="submit" value="Login" />--%>
<%--				</span>--%>
<%--			</div>--%>
<%--		</g:form>--%>
<%--	</div>--%>
<%--</body>--%>
<%--</html>--%>

<!DOCTYPE html>
<html>
<head>
<title>Scuba Grails - Se connecter</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta name="description" content="Place your description here" />
<meta name="keywords" content="put, your, keyword, here" />
<meta name="author" content="MeYoGui" />
<%--<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">--%>
<link rel="stylesheet" href="${resource(dir: 'css', file: 'style.css')}" type="text/css">
<!--[if lt IE 7]><script type="text/javascript" src="ie_png.js"></script><script type="text/javascript">ie_png.fix('.png, #nav li a, .button span, .button a');</script><![endif]--> 
</head>

<body id="page1" class="designScuba">
	<div class="tail-right"></div>
	<div class="tail-right-top"></div>
	<div class="tail-top">
		<div class="tail-bottom">
			<div class="tail-bg">
				<div class="top-bg">
					<div class="tail-right-bot"></div>
					<div class="bot-bg">
						<div id="main">
<!-- header -->
							<div id="header">
								<h1><a>ScubaGrails</a><span>ASCSB Plong&eacute;e</span></h1>
								<ul class="top-links">
									<li><a><img alt="home" src="../images/scuba/design/icon-home.gif" /></a></li>
									<li><a href="contact.html"><img alt="mail" src="../images/scuba/design/icon-mail.gif" /></a></li>
									<li><a href="sitemap.html"><img alt="map" src="../images/scuba/design/icon-map.gif" /></a></li>
								</ul>
							</div>
							<div class="extra-img"><img alt="extra-img" src="../images/scuba/design/extra-img.png" class="png"/></div>
							<div class="wrapper">
<!-- nav -->
								<div id="nav">
									<ul>
										<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
										<li><g:link controller="user" action="login">Se connecter</g:link></li>
										<li><a href="articles.html">Articles</a></li>
										<li><a href="contact.html">Nous contacter</a></li>
										<li><a href="sitemap.html">Autres ...</a></li>
									</ul>
								</div>
<!-- content -->
								<div id="content">
									<div class="section">
<!-- box begin -->
										<div class="box">
											<div class="left-top-corner png"><div class="right-top-corner png"><div class="border-top png"></div></div></div>
											<div class="border-left png">
												<div class="border-right png">
													<div class="inside png">
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
																			<td valign="top" class="name"><label for="login">Login
																					:</label></td>
																			<td valign="top"><input type="text" id="login"
																				name="login" /></td>
																		</tr>
																		<tr class="prop">
																			<td valign="top" class="name"><label
																				for="password">Mot de passe :</label></td>
																			<td valign="top"><input type="password"
																				id="password" name="password" /></td>
																		</tr>
																	</tbody>
																</table>
															</div>
															<div class="buttons">
																<span class="button"> <input type="submit"
																	value="Login" />
																</span>
															</div>
														</g:form>
													</div>
												</div>
											</div>
											<div class="left-bot-corner png"><div class="right-bot-corner png"><div class="border-bot png"></div></div></div>
										</div><!-- box end -->										
									</div>
								</div>
							</div>
						</div>
<!-- footer -->
						<div id="footer">
							<div class="indent">
								<div class="fleft">Copyrights - MeYoGui</div>
<%--								<div class="fright">Designed by: <a href="http://www.templates.com/" title="templates.com - website templates provider"><img alt="website templates" src="images/scuba/design/templates-logo.gif" /></a>&nbsp; <a href="http://www.templates.com" title="templates.com - website templates provider">Website Templates</a> Provider</div>--%>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
