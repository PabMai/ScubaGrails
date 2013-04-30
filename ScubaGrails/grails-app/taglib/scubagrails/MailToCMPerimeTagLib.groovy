package scubagrails

class MailToCMPerimeTagLib {	
	
	def mailTo = {	attrs, body ->
		def mail = attrs.mail
		def prenom = attrs.prenom
		def nbJourPerime = attrs.nbJourPerime	
		// Boolean afin de savoir si on est dans le cas d'un
		// CM périmé ou bientot périmé (dans le mois)
		boolean avantMois = attrs.avantMois
		
		StringBuilder linkMailto = new StringBuilder()		
		// Début du lien 
		linkMailto.append("<a class='alertMail' onclick=\"adclick('mailto:$mail?subject=")		
		
		// Sujet
		String subject = java.net.URLEncoder.encode("Alerte ASCSB - Certificat médical", "UTF-8")
		linkMailto.append(subject)
		
		// Body
		linkMailto.append("&body=")
		String bodyS = ""
		if (avantMois) {
			bodyS = java.net.URLEncoder.encode("Bonjour $prenom,\n\nTon certificat médical va expirer dans $nbJourPerime jours. Pourrais-tu m'en fournir un nouveau ?", "UTF-8")
		} else {
			bodyS = java.net.URLEncoder.encode("Bonjour $prenom,\n\nTon certificat médical est expiré depuis $nbJourPerime jours.", "UTF-8")
		}
		linkMailto.append(bodyS)
		
		// Fin du lien
		linkMailto.append("')\">Envoyer mail</a>")
		
		out << linkMailto.toString()
				
	}
		
	def mailToAll = { attrs, body ->	
		
		// Boolean afin de savoir si on est dans le cas d'un
		// CM périmé ou bientot périmé (dans le mois)
		boolean avantMois = attrs.avantMois
		
		StringBuilder linkMailto = new StringBuilder()
		
		// Début du lien
		linkMailto.append("<a class='alertMail' onclick=\"adclick('mailto:")	
		linkMailto.append(body())
		linkMailto.append("?subject=")
		
		//Sujet
		String subject = java.net.URLEncoder.encode("Alerte ASCSB - Certificat médical", "UTF-8")
		linkMailto.append(subject)
		
		//Body
		linkMailto.append("&body=")
		String bodyS = "" 
		if (avantMois) {
			bodyS = java.net.URLEncoder.encode("Bonjour,\n\nTon certificat médical va expirer. Pourrais-tu m'en fournir un autre ?", "UTF-8")
		} else {
			bodyS = java.net.URLEncoder.encode("Bonjour,\n\nTon certificat médical est expiré.", "UTF-8")
		}
		
		linkMailto.append(bodyS)
		
		// Fin du lien
		linkMailto.append("')\">Envoi groupé</a>")		
		
		out << linkMailto.toString()
		

	}
}
