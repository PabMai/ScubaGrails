package scubagrails



import grails.test.GrailsUnitTestCase;
import grails.test.mixin.*
import grails.test.mixin.support.GrailsUnitTestMixin;

import org.junit.*

import scubagrails.type.Sexe;

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(AbonneService)
@Mock([Niveau, TypeMembre, Ecole, Abonne])
class AbonneServiceTests extends GroovyTestCase {
	
	def abonneService

    void testRecuperationListeAbonneCertifPerime() {			
		
	    def niveauN1 = new Niveau(niveau: "N1").save(failOnError : true)		
		def typeMembreLicence = new TypeMembre(nom: "Licence seule").save(failOnError : true)		
		def ecoleInit = new Ecole(nom: "Initiation").save(failOnError : true)		

		def abonne1 = new Abonne(nom:"Problo",
				prenom: "Guillaume",
				dateNaissance: Date.parse("dd/MM/yyyy", "14/12/1988"),
				departementNaissance: "49",
				lieuNaissance: "Angers",
				sexe: Sexe.MASCULIN,
				dateCertificat: new Date() - 500,
				telephoneFixe: "0241552233",
				telephonePortable: "0674885566",
				mail:"guillaume.problo@gmail.com",
				numeroLicence: "3556484",
				prixAbonnement: 450,
				prixAssurance: 54.24,
				secouriste: true,
				numeroRue: "10",
				nomRue: "Avenue du Général de Gaulle",
				codePostal: "75100",
				ville : "Paris",
				password: "test" )
		
		abonne1.setEcole(ecoleInit)
		abonne1.setTypeMembre(typeMembreLicence)
		abonne1.setNiveau(niveauN1)
		
		// on force la validate a false afin de pouvoir ajouter un certif périmé
		abonne1.save(validate : false)	
		
        List<Abonne> liste = abonneService.getAbonnesCertificatMedicalPerime()
		assertEquals(1, liste.size())
		assertEquals("Problo", ((Abonne) liste.get(0)).nom)
		
		abonne1.setDateCertificat(new Date())	
		abonne1.save(validate : false)
		
		liste = abonneService.getAbonnesCertificatMedicalPerime()
		assertEquals(0, liste.size())		
		
    }
	
	void testJourBetween() {
		// périmé de 5 jours (365 + 5)
		def dateCertif = new Date() - 370; 

		int nbJour = abonneService.getNbJourPeremptionCM(dateCertif)
		assertEquals(5, nbJour)
	}
	
}
