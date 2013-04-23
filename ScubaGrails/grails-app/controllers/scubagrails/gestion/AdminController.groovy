package scubagrails.gestion

import scubagrails.Abonne;
import scubagrails.AbonneService;
import scubagrails.Ecole;
import scubagrails.Niveau;
import scubagrails.Saison;
import scubagrails.SaisonService;
import scubagrails.User;

class AdminController {	
	
	AbonneService abonneService
	SaisonService saisonService
	
	def index = {
		
		// Pour améliorer les performances, on ne récupère qu'une seule fois
		// les données que l'on met en session
				
		// Récupération des abonnés ayant un certificat périmé
		if (!session?.isRecupereCM || session?.isRecupereCM == "0") {
			List<Abonne> listeAbonneCMPerimeUnAn = abonneService.getAbonnesCertificatMedicalPerime(365)
			session.listeAbonneCMPerime = listeAbonneCMPerimeUnAn
			//flag
			session.isRecupereCM = "1"
		}
		
		if(!session.isRecupereCMMois || session.isRecupereCMMois == "0") {
			List<Abonne> listeAbonneCMPerimeDansLeMois = abonneService.getAbonnesCertificatMedicalPerimeDansLeMois()
			session.listeAbonneCMPerimeDansLeMois = listeAbonneCMPerimeDansLeMois
			//flag
			session.isRecupereCMMois = "1"
		}
		
		// Récupération de la saison en cours
//		if (!session.isRecupereSaison || session.isRecupereSaison == "0") {			
//			Saison saisonEnCours = saisonService.getSaisonEnCours()
//			session.saisonEnCours = saisonEnCours
//			//flag
//			session.isRecupereSaison = "1"
//		}			
		
		Saison saisonEnCours = saisonService.getSaisonEnCours()
		
		// Récupération des statistiques  :
		[nbAbonne : Abonne.count, nbEcole : Ecole.count, nbSaison : Saison.count
			, nbNiveau : Niveau.count, nbUtilisateur : User.count, saisonEnCours : saisonEnCours]
		
	}
	
	def refreshAdminData = {
		
		List<Abonne> listeAbonneCMPerimeUnAn = abonneService.getAbonnesCertificatMedicalPerime(365)
		session.listeAbonneCMPerime = listeAbonneCMPerimeUnAn
		
		List<Abonne> listeAbonneCMPerimeDansLeMois = abonneService.getAbonnesCertificatMedicalPerimeDansLeMois()
		session.listeAbonneCMPerimeDansLeMois = listeAbonneCMPerimeDansLeMois
		
		redirect(view: "index")
	}
}
