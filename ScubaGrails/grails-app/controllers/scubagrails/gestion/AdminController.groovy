package scubagrails.gestion

import java.text.SimpleDateFormat
import org.apache.commons.lang.mutable.MutableInt;
import org.springframework.web.multipart.MultipartFile;

import scubagrails.Abonne;
import scubagrails.AbonneService;
import scubagrails.AdminService;
import scubagrails.EcoleScaphandre;
import scubagrails.Enregistrement;
import scubagrails.NiveauScaphandre;
import scubagrails.Saison;
import scubagrails.SaisonService;
import scubagrails.TypeMembre
import scubagrails.UploadService
import scubagrails.User;
import scubagrails.gestionLogs.GestionLogs;
import scubagrails.type.Sexe;
import scubagrails.utils.AbonneExcelImporter

class AdminController {	
	
	AbonneService abonneService
	SaisonService saisonService
	AdminService adminService
	UploadService uploadService
	
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
		[nbAbonne : Abonne.count, nbEcole : EcoleScaphandre.count, nbSaison : Saison.count
			, nbNiveau : NiveauScaphandre.count, nbUtilisateur : User.count]
		
	}
	
	def refreshAdminData = {
		println ("Start Refresing Admin Data : " + new Date().format("HH:mm:ss"))
		
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
		
		println ("Stop Refresing Admin Data : " + new Date().format("HH:mm:ss"))
	}
	
	def importAbonne = {	
		if (params.file){
			MultipartFile downloadedFile = request.getFile("file")
			def chemin = ""
			if (!downloadedFile.isEmpty() && (downloadedFile.getContentType() == "application/vnd.ms-excel" 
				|| downloadedFile.getContentType() == "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
				chemin = uploadService.uploadFile(downloadedFile, "excelImport.xls", "data_import")

				String nomFeuille = params.nomFeuille
				//String nomFeuille = /.\data_import\importAbonne_OK.xls/
				//String nomFeuille = /.\data_import\importAbonneMultiple_OK.xls/
				//String nomFeuille = /.\data_import\importFull.xls/
				AbonneExcelImporter importer = new AbonneExcelImporter(chemin, nomFeuille);
				def abonnesMapList = importer.getAbonnes();

				GestionLogs gestLogs = adminService.traiterImportAbonne(abonnesMapList);
				session.gestLogs = gestLogs
			} else {
				session.gestLogs = null
			}
		} else {
			redirect(view: "index")
		}
		
	}
	
	def indexImport = {		
	}
}
