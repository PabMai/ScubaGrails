package scubagrails



import org.junit.*
import grails.test.mixin.*

@TestFor(NiveauScaphandreController)
@Mock(NiveauScaphandre)
class NiveauScaphandreControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/niveau/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.niveauInstanceList.size() == 0
        assert model.niveauInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.niveauInstance != null
    }

    void testSave() {
        controller.save()

        assert model.niveauInstance != null
        assert view == '/niveau/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/niveau/show/1'
        assert controller.flash.message != null
        assert NiveauScaphandre.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/niveau/list'

        populateValidParams(params)
        def niveau = new NiveauScaphandre(params)

        assert niveau.save() != null

        params.id = niveau.id

        def model = controller.show()

        assert model.niveauInstance == niveau
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/niveau/list'

        populateValidParams(params)
        def niveau = new NiveauScaphandre(params)

        assert niveau.save() != null

        params.id = niveau.id

        def model = controller.edit()

        assert model.niveauInstance == niveau
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/niveau/list'

        response.reset()

        populateValidParams(params)
        def niveau = new NiveauScaphandre(params)

        assert niveau.save() != null

        // test invalid parameters in update
        params.id = niveau.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/niveau/edit"
        assert model.niveauInstance != null

        niveau.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/niveau/show/$niveau.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        niveau.clearErrors()

        populateValidParams(params)
        params.id = niveau.id
        params.version = -1
        controller.update()

        assert view == "/niveau/edit"
        assert model.niveauInstance != null
        assert model.niveauInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/niveau/list'

        response.reset()

        populateValidParams(params)
        def niveau = new NiveauScaphandre(params)

        assert niveau.save() != null
        assert NiveauScaphandre.count() == 1

        params.id = niveau.id

        controller.delete()

        assert NiveauScaphandre.count() == 0
        assert NiveauScaphandre.get(niveau.id) == null
        assert response.redirectedUrl == '/niveau/list'
    }
}
