package scubagrails



import org.junit.*

import scubagrails.type.Sexe;
import grails.test.mixin.*

@TestFor(AbonneController)
@Mock([Abonne, Saison, Enregistrement])
class AbonneControllerTests {

    def populateValidParams(params) {
        assert params != null        
        params["nom"] = 'nomTest'
		params["prenom"] = 'prenomTest'
		params["dateNaissance"] = Date.parse("dd/MM/yyyy", "14/01/1972")
		params["sexe"] = Sexe.MASCULIN
		params["dateCertificat"] = Date.parse("dd/MM/yyyy", "13/09/2012")
		params["telephoneFixe"] = "0152658744"
		params["telephonePortable"] = "0652415263"
		params["mail"] ="j.dujardin@gmail.com"
		params["numeroLicence"] = "5548855"		
		params["prixAbonnement"] = 100.25
		params["prixAssurance"] = 1
		params["nomRue"] = "11 Rue des Tomates"
		params["codePostal"] = "37000"
		params["ville"] = "Tours"
		params["password"] = "test"
    }

    void testIndex() {
        controller.index()
        assert "/abonne/list" == response.redirectedUrl
    }

    void testList() {
		Saison saison = new Saison(libelle: "2012-2013",dateDebut: new Date() - 365,
			dateFin: new Date() - 1)
		saison.save()
        def model = controller.list()

        assert model.abonneInstanceList.size() == 0
        assert model.abonneInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.abonneInstance != null
    }

    void testSave() {
        controller.save()

        assert model.abonneInstance != null
        assert view == '/abonne/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/abonne/show/1'
        assert controller.flash.message != null
        assert Abonne.count() == 1
    }

    void testShow() {
        controller.show()
		
		// On ajout un enregistrement au moins
		
		
        assert flash.message != null
        assert response.redirectedUrl == '/abonne/list'

        populateValidParams(params)
        def abonne = new Abonne(params)

        assert abonne.save() != null

        params.id = abonne.id

        def model = controller.show()

        assert model.abonneInstance == abonne
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/abonne/list'

        populateValidParams(params)
        def abonne = new Abonne(params)

        assert abonne.save() != null

        params.id = abonne.id

        def model = controller.edit()

        assert model.abonneInstance == abonne
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/abonne/list'

        response.reset()

        populateValidParams(params)
        def abonne = new Abonne(params)

        assert abonne.save() != null

        // test invalid parameters in update
        params.id = abonne.id
        //add invalid values to params object
		params["ville"] = null

        controller.update()

        assert view == "/abonne/edit"
        assert model.abonneInstance != null

        abonne.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/abonne/show/$abonne.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        abonne.clearErrors()

        populateValidParams(params)
        params.id = abonne.id
        params.version = -1
        controller.update()

        assert view == "/abonne/edit"
        assert model.abonneInstance != null
        assert model.abonneInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/abonne/list'

        response.reset()

        populateValidParams(params)
        def abonne = new Abonne(params)

        assert abonne.save() != null
        assert Abonne.count() == 1

        params.id = abonne.id

        controller.delete()

        assert Abonne.count() == 0
        assert Abonne.get(abonne.id) == null
        assert response.redirectedUrl == '/abonne/list'
    }
}
