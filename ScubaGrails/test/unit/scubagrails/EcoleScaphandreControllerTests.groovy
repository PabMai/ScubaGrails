package scubagrails



import org.junit.*

import scubagrails.type.Sexe;
import grails.test.mixin.*

@TestFor(EcoleScaphandreController)
@Mock([EcoleScaphandre, Abonne])
class EcoleScaphandreControllerTests {

    def populateValidParams(params) {
        assert params != null
        // Populate valid properties like...
        params["nom"] = 'ecoleTestScaphandre'
		
    }

    void testIndex() {
        controller.index()
        assert "/ecoleScaphandre/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.ecoleInstanceList.size() == 0
        assert model.ecoleInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.ecoleInstance != null
    }

    void testSave() {
        controller.save()

        assert model.ecoleInstance != null
        assert view == '/ecoleScaphandre/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/ecoleScaphandre/show/1'
        assert controller.flash.message != null
        assert EcoleScaphandre.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/ecoleScaphandre/list'

        populateValidParams(params)
        def ecole = new EcoleScaphandre(params)

        assert ecole.save() != null

        params.id = ecole.id

        def model = controller.show()

        assert model.ecoleInstance == ecole
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/ecoleScaphandre/list'

        populateValidParams(params)
        def ecole = new EcoleScaphandre(params)

        assert ecole.save() != null

        params.id = ecole.id

        def model = controller.edit()

        assert model.ecoleInstance == ecole
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/ecoleScaphandre/list'

        response.reset()

        populateValidParams(params)
        def ecole = new EcoleScaphandre(params)

        assert ecole.save() != null

        // test invalid parameters in update
        params.id = ecole.id
		params['nom'] = null
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/ecoleScaphandre/edit"
        assert model.ecoleInstance != null

        ecole.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/ecoleScaphandre/show/$ecole.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        ecole.clearErrors()

        populateValidParams(params)
        params.id = ecole.id
        params.version = -1
        controller.update()

        assert view == "/ecoleScaphandre/edit"
        assert model.ecoleInstance != null
        assert model.ecoleInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/ecoleScaphandre/list'

        response.reset()

        populateValidParams(params)
        def ecole = new EcoleScaphandre(params)

        assert ecole.save() != null
        assert EcoleScaphandre.count() == 1

        params.id = ecole.id

		// avec abonnés : pas de suppression
		createAbonne(ecole)
        controller.delete()		
		
        assert EcoleScaphandre.count() == 1 
        assert EcoleScaphandre.get(ecole.id) == ecole
		assert response.redirectedUrl == "/ecoleScaphandre/show/$ecole.id"		
		
		response.reset()
		
		// sans abonnés : suppression effective
		// CAS 1 : null
		ecole.abonnes = null
		controller.delete()
		
		assert EcoleScaphandre.count() == 0
		assert EcoleScaphandre.get(ecole.id) == null		
        assert response.redirectedUrl == '/ecoleScaphandre/list'
		
		response.reset()
		
		// CAS 2 : vide
		populateValidParams(params)
		ecole = new EcoleScaphandre(params)

		assert ecole.save(flush:true) != null
		assert EcoleScaphandre.count() == 1
		
		ecole.abonnes = new HashSet<Abonne>()
		controller.delete(ecole.id)
		
		assert EcoleScaphandre.count() == 0
		assert EcoleScaphandre.get(ecole.id) == null
		assert response.redirectedUrl == '/ecoleScaphandre/list'
				
    }
	
	void createAbonne(EcoleScaphandre ecoleS) {
		def abonne1 = new Abonne(nom:"Problo",
				prenom: "Guillaume",
				dateNaissance: Date.parse("dd/MM/yyyy", "14/12/1988"),
				sexe: Sexe.MASCULIN,
				dateCertificat: Date.parse("dd/MM/yyyy", "15/07/2012"),
				telephoneFixe: "0241552233",
				telephonePortable: "0674885566",
				mail:"guillaume.problo@gmail.com",
				numeroLicence: "3556484",
				prixAbonnement: 450,
				prixAssurance: 54.24,
				secouriste: true,				
				nomRue: "10, Avenue du Général de Gaulle",
				codePostal: "75100",
				ville : "Paris",				
				password: "test" )

				abonne1.setEcole(ecoleS)				
				abonne1.save()
				if(abonne1.hasErrors()){
					fail "Impossible d'inserer l'abonné"
				}
	}
}
