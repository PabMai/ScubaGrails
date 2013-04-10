package scubagrails



import org.junit.*
import grails.test.mixin.*

@TestFor(SaisonController)
@Mock(Saison)
class SaisonControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/saison/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.saisonInstanceList.size() == 0
        assert model.saisonInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.saisonInstance != null
    }

    void testSave() {
        controller.save()

        assert model.saisonInstance != null
        assert view == '/saison/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/saison/show/1'
        assert controller.flash.message != null
        assert Saison.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/saison/list'

        populateValidParams(params)
        def saison = new Saison(params)

        assert saison.save() != null

        params.id = saison.id

        def model = controller.show()

        assert model.saisonInstance == saison
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/saison/list'

        populateValidParams(params)
        def saison = new Saison(params)

        assert saison.save() != null

        params.id = saison.id

        def model = controller.edit()

        assert model.saisonInstance == saison
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/saison/list'

        response.reset()

        populateValidParams(params)
        def saison = new Saison(params)

        assert saison.save() != null

        // test invalid parameters in update
        params.id = saison.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/saison/edit"
        assert model.saisonInstance != null

        saison.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/saison/show/$saison.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        saison.clearErrors()

        populateValidParams(params)
        params.id = saison.id
        params.version = -1
        controller.update()

        assert view == "/saison/edit"
        assert model.saisonInstance != null
        assert model.saisonInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/saison/list'

        response.reset()

        populateValidParams(params)
        def saison = new Saison(params)

        assert saison.save() != null
        assert Saison.count() == 1

        params.id = saison.id

        controller.delete()

        assert Saison.count() == 0
        assert Saison.get(saison.id) == null
        assert response.redirectedUrl == '/saison/list'
    }
}
