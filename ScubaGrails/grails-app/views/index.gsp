<!DOCTYPE html>
<html>
<head>
<title>Scuba Grails</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta name="description" content="ASCSB Plongée" />
<meta name="keywords" content="ASCSB, plongée" />
<meta name="author" content="MeYoGui" />
<%--<link href="style.css" rel="stylesheet" type="text/css" />--%>
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
								<!--<h1><a>ScubaGrails</a> <span>ASCSB Plong&eacute;e</span></h1> -->
								<ul class="top-links">
									<li><a><img alt="home" src="images/scuba/design/icon-home.gif" /></a></li>
									<li><a href=""><img alt="mail" src="images/scuba/design/icon-mail.gif" /></a></li>
									<li><a href=""><img alt="map" src="images/scuba/design/icon-map.gif" /></a></li>
								</ul>
							</div>
							<div class="extra-img"><img alt="extra-img" src="images/scuba/design/extra-img.png" class="png"/></div>
							<div class="wrapper">
<!-- nav -->
								<div id="nav">
									<ul>
										<li><a>Accueil</a></li>
										<li><g:link controller="user" action="login">Se connecter</g:link></li>
<%--										<li><a href="articles.html">Articles</a></li>--%>
<%--										<li><a href="contact.html">Nous contacter</a></li>--%>
										<li><a href="#">Autres ...</a></li>
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
														<h2>Bienvenue sur ScubaGrails</h2>
														<p>Amis plongeurs, bonjour ! </p>
														<p>Ce site vous permet de vous tenir au courant des évènements 
														de votre association préférée, de modifier vos informations personnelles si celles-ci venaient à changer et bien d'autres choses.</p> 
														<p>Connectez-vous à votre compte pour profiter de toutes ces fonctionnalités.</p> 
														
													</div>
												</div>
											</div>
											<div class="left-bot-corner png"><div class="right-bot-corner png"><div class="border-bot png"></div></div></div>
										</div>
<!-- box end -->
									</div>
									<h2>Le club</h2>
									<ul class="items-list">
										<li>
											<img alt="empty-img" src="images/scuba/photos/photo_acceuil_1.jpg" width="150px"/>
											<h3><a href="">A propos de la photo</a></h3>
												Petit texte d'introduction Petit texte d'introduction Petit texte d'introduction Petit texte d'introduction Petit texte d'introduction
										</li>
										<li>
											<img alt="empty-img" src="images/scuba/photos/photo_acceuil_13.jpg" width="150px"/>
											<h3>A propos de la photo</h3>
											Petit texte d'introduction Petit texte d'introduction Petit texte d'introduction Petit texte d'introduction Petit texte d'introduction
										</li>
										<li>
											<img alt="empty-img" src="images/scuba/photos/photo_acceuil_2.jpg" width="150px"/>
											<h3>A propos de la photo</h3>
											Petit texte d'introduction Petit texte d'introduction Petit texte d'introduction Petit texte d'introduction Petit texte d'introduction
										</li>
									</ul>
								</div>
							</div>
						</div>
<!-- footer -->
						<div id="footer">
							<div class="indent">
								<div class="fright" style="margin-top:60px">Copyrights - <a href="http://www.guillaume-monjal.com">MeYoGui</a></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>