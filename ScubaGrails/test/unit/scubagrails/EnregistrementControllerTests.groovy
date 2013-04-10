package scubagrails



import org.junit.*
import grails.test.mixin.*

@TestFor(EnregistrementController)
@Mock(Enregistrement)
class EnregistrementControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/enregistrement/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.enregistrementInstanceList.size() == 0
        assert model.enregistrementInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.enregistrementInstance != null
    }

    void testSave() {
        controller.save()

        assert model.enregistrementInstance != null
        assert view == '/enregistrement/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/enregistrement/show/1'
        assert controller.flash.message != null
        assert Enregistrement.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/enregistrement/list'

        populateValidParams(params)
        def enregistrement = new Enregistrement(params)

        assert enregistrement.save() != null

        params.id = enregistrement.id

        def model = controller.show()

        assert model.enregistrementInstance == enregistrement
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/enregistrement/list'

        populateValidParams(params)
        def enregistrement = new Enregistrement(params)

        assert enregistrement.save() != null

        params.id = enregistrement.id

        def model = controller.edit()

        assert model.enregistrementInstance == enregistrement
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/enregistrement/list'

        response.reset()

        populateValidParams(params)
        def enregistrement = new Enregistrement(params)

        assert enregistrement.save() != null

        // test invalid parameters in update
        params.id = enregistrement.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/enregistrement/edit"
        assert model.enregistrementInstance != null

        enregistrement.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/enregistrement/show/$enregistrement.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        enregistrement.clearErrors()

        populateValidParams(params)
        params.id = enregistrement.id
        params.version = -1
        controller.update()

        assert view == "/enregistrement/edit"
        assert model.enregistrementInstance != null
        assert model.enregistrementInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/enregistrement/list'

        response.reset()

        populateValidParams(params)
        def enregistrement = new Enregistrement(params)

        assert enregistrement.save() != null
        assert Enregistrement.count() == 1

        params.id = enregistrement.id

        controller.delete()

        assert Enregistrement.count() == 0
        assert Enregistrement.get(enregistrement.id) == null
        assert response.redirectedUrl == '/enregistrement/list'
    }
}
