package scubagrails

import java.text.DateFormat;
import java.util.List;

import javax.servlet.SessionCookieConfig;

class AbonneService {

	/**
	 * Méthode permettant de récupérer les abonnés
	 * ayant un certificat médical périmé
	 * @param indicateurPeremption le nombre de jour indiquant la péremption
	 * (si 365 : péremption en un an)
	 * @return {@link List{@link Abonne}
	 */
    List<Abonne> getAbonnesCertificatMedicalPerime(int indicateurPeremption) {
		
		def now = new Date() - indicateurPeremption				
		Abonne.findAllByDateCertificatLessThanEquals(now)
    }
	
	List<Abonne> getAbonnesCertificatMedicalPerimeDansLeMois() {
				
		def firstDate = new Date() - 365
		def secondDate = new Date() - 335
		Abonne.findAllByDateCertificatBetween(firstDate,secondDate)
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
	
	/**
	 * Méthode permettant de calculé le nombre de jour avant péremption
	 * d'un certificat Médical par rapport à la date de péremption d'un an
	 * à partir de la date courante
	 * @param dateCertificat la date du certificat
	 * @return {@link int} nb de jour avant péremption
	 */
	int getNbJourAvantPeremptionCM(def dateCertificat) {
		use(groovy.time.TimeCategory) {
			Date datePeremption = dateCertificat + 365
			def duration = datePeremption - (new Date())
			duration.days + 1
		 }
	}
	
	/**
	 * TODO supprimer
	 * @param abonne
	 */
	void ajouterUnMois(Abonne abonne) {
		abonne.setDateCertificat(abonne.getDateCertificat() + 30)
	}
	
	/**
	 * TODO supprimer
	 * @param abonne
	 */
	void enleverUnMois(Abonne abonne) {
		abonne.setDateCertificat(abonne.getDateCertificat() - 30)
	}
}
