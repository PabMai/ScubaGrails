package scubagrails.gestion

import scubagrails.Abonne;
import scubagrails.AbonneService;
import scubagrails.Ecole;
import scubagrails.Niveau;
import scubagrails.Saison;
import scubagrails.User;

class AdminController {	
	
	AbonneService abonneService
	
	def index = {
		
		// Récupération des abonnés ayant un certificat périmé
		List<Abonne> listeAbonneCMPerime = abonneService.getAbonnesCertificatMedicalPerime()
		
		session.listeAbonneCMPerime = listeAbonneCMPerime
		// Récupération des statistiques  :
		[nbAbonne : Abonne.count, nbEcole : Ecole.count, nbSaison : Saison.count
			, nbNiveau : Niveau.count, nbUtilisateur : User.count]
		
	}
}
