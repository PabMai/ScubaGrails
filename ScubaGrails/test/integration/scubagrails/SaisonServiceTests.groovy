package scubagrails



import grails.test.mixin.*

import org.bouncycastle.bcpg.S2K;
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@Mock([Saison])
class SaisonServiceTests extends GroovyTestCase {

	SaisonService saisonService
	
    void testGetSaisonEnCours() {
		Saison saisonEnCours = new Saison(enCours: true, 
			libelle: "enCours",
			dateDebut: new Date(),
			dateFin: new Date() + 1).save()
			
		Saison saisonPasEnCours = new Saison(enCours : false,
			 libelle : "pasEnCours",
			 dateDebut: new Date(),
			 dateFin: new Date() + 1).save()

		Saison laSaisonEnCoursRecupere = saisonService.getSaisonEnCours()
		assertNotNull(laSaisonEnCoursRecupere)
		assertEquals("enCours", laSaisonEnCoursRecupere.libelle)
		
		
		// Aucune saison ne doit remonter
		saisonEnCours.setEnCours(false)
		saisonEnCours.save()
		
		laSaisonEnCoursRecupere = saisonService.getSaisonEnCours()
		assertNull(laSaisonEnCoursRecupere)
    }
}
