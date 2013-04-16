package scubagrails

import java.text.DateFormat;
import java.util.List;

import javax.servlet.SessionCookieConfig;

class AbonneService {

	/**
	 * Méthode permettant de récupérer les abonnés
	 * ayant un certificat médical périmé
	 * @return {@link List{@link Abonne}
	 */
    List<Abonne> getAbonnesCertificatMedicalPerime() {
		
		def now = new Date() - 365				
		Abonne.findAllByDateCertificatLessThanEquals(now)
    }
	
	/**
	 * Méthode permettant de calculé le nombre de jour de péremption
	 * d'un certificat Médical par rapport à la date de péremption d'un an
	 * à partir de la date courante
	 * @param dateCertificat la date du certificat
	 * @return {@link int} nb de jour de péremption
	 */
	int getNbJourPeremptionCM(def dateCertificat) {
		use(groovy.time.TimeCategory) {
			def duration = new Date() - 365 - dateCertificat
			duration.days			
		 }
	}
}
