package scubagrails.gestion

import java.text.SimpleDateFormat
import org.apache.commons.lang.mutable.MutableInt;

import scubagrails.Abonne;
import scubagrails.AbonneService;
import scubagrails.AdminService;
import scubagrails.Ecole;
import scubagrails.Enregistrement;
import scubagrails.Niveau;
import scubagrails.Saison;
import scubagrails.SaisonService;
import scubagrails.TypeMembre
import scubagrails.User;
import scubagrails.type.Sexe;
import scubagrails.utils.AbonneExcelImporter

class AdminController {	
	
	AbonneService abonneService
	SaisonService saisonService
	AdminService adminService
	
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

		// Récupération des données de la saison en cours
		if (!session.isRecupereDonneesSaisonEnCours || session.isRecupereDonneesSaisonEnCours == "0") {
			Saison saisonEnCours = saisonService.getSaisonEnCours()
			MutableInt nbFemme = new MutableInt(0)
			MutableInt nbHommme = new MutableInt(0)
			Map<String, Integer> statEcole = new TreeMap<String, Integer>()
			Map<String, Integer> statTypeM = new TreeMap<String, Integer>()
			saisonService.getDonneesSaisonEnCours(saisonEnCours, nbHommme, nbFemme, statEcole, statTypeM)
			
			// Mise en session
			session.saisonEnCours = saisonEnCours
			session.nbFemmeSaisonEnCours = nbFemme.intValue()
			session.nbHommeSaisonEnCours = nbHommme.intValue()
			session.statEcole = statEcole
			session.statTypeM = statTypeM
			
			//flag
			session.isRecupereDonneesSaisonEnCours = "1"
		}
		
		// Récupération des statistiques  :
		[nbAbonne : Abonne.count, nbEcole : Ecole.count, nbSaison : Saison.count
			, nbNiveau : Niveau.count, nbUtilisateur : User.count]
		
	}
	
	def refreshAdminData = {
		
		// 1 : Refresh CM Périmé
		List<Abonne> listeAbonneCMPerimeUnAn = abonneService.getAbonnesCertificatMedicalPerime(365)
		session.listeAbonneCMPerime = listeAbonneCMPerimeUnAn
		
		// 2 : Refresh CM Périmé dans le mois
		List<Abonne> listeAbonneCMPerimeDansLeMois = abonneService.getAbonnesCertificatMedicalPerimeDansLeMois()
		session.listeAbonneCMPerimeDansLeMois = listeAbonneCMPerimeDansLeMois
		
		// 3 : Refresh données saison en cours
		Saison saisonEnCours = saisonService.getSaisonEnCours()
		MutableInt nbFemme = new MutableInt(0)
		MutableInt nbHommme = new MutableInt(0)
		Map<String, Integer> statEcole = new TreeMap<String, Integer>()
		Map<String, Integer> statTypeM = new TreeMap<String, Integer>()
		saisonService.getDonneesSaisonEnCours(saisonEnCours, nbHommme, nbFemme, statEcole, statTypeM)
		session.saisonEnCours = saisonEnCours
		session.nbFemmeSaisonEnCours = nbFemme.intValue()
		session.nbHommeSaisonEnCours = nbHommme.intValue()
		session.statEcole = statEcole
		session.statTypeM = statTypeM
		
		// Redirection
		redirect(view: "index")
	}
	
	def importAbonne = {		
		//String fileName = /.\data_import\importAbonne.xls/
		String fileName = /.\data_import\importAbonneMultiple.xls/
		AbonneExcelImporter importer = new AbonneExcelImporter(fileName);
		def abonnesMapList = importer.getAbonnes();
		
		adminService.traiterImportAbonne(abonnesMapList);   
		
	}
}
