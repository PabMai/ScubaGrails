package scubagrails



import org.junit.*
import grails.test.mixin.*

@TestFor(AbonneController)
@Mock(Abonne)
class AbonneControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/abonne/list" == response.redirectedUrl
    }

    void testList() {

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
        //TODO: add invalid values to params object

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
