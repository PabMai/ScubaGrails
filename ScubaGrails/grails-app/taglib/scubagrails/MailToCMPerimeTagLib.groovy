package scubagrails

class MailToCMPerimeTagLib {	
	
	def mailTo = {	attrs, body ->
		def mail = attrs.mail
		def prenom = attrs.prenom
		def nbJourPerime = attrs.nbJourPerime
		
		out << "<a class='alertMail' href='mailto:$mail?subject=Alerte ASCSB - Certificat médical périmé"
        out << "?body="
		out << "Bonjour $prenom, %0D%0D"
		out << "Ton certificat médical est expiré depuis $nbJourPerime jours. %0D"
		out << "rajouter texte ..."		
		out << "'>Envoyer mail</a>"
				
	}
		
	def mailToAll = { attrs, body ->		
		out << "<a class='alertMail' href='mailto:"
		out << body()
		out << "?subject=Alerte ASCSB - Certificat médical périmé"
		out << "?body="
		out << "Bonjour, %0D%0D"
		out << "Ton certificat médical est expiré. %0D"
		out << "rajouter texte ..."
		out << "'>Envoi groupé</a>"		
	}
}
