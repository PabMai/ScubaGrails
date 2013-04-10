package scubagrails



import org.junit.*
import grails.test.mixin.*

@TestFor(EcoleController)
@Mock(Ecole)
class EcoleControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/ecole/list" == response.redirectedUrl
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
        assert view == '/ecole/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/ecole/show/1'
        assert controller.flash.message != null
        assert Ecole.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/ecole/list'

        populateValidParams(params)
        def ecole = new Ecole(params)

        assert ecole.save() != null

        params.id = ecole.id

        def model = controller.show()

        assert model.ecoleInstance == ecole
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/ecole/list'

        populateValidParams(params)
        def ecole = new Ecole(params)

        assert ecole.save() != null

        params.id = ecole.id

        def model = controller.edit()

        assert model.ecoleInstance == ecole
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/ecole/list'

        response.reset()

        populateValidParams(params)
        def ecole = new Ecole(params)

        assert ecole.save() != null

        // test invalid parameters in update
        params.id = ecole.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/ecole/edit"
        assert model.ecoleInstance != null

        ecole.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/ecole/show/$ecole.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        ecole.clearErrors()

        populateValidParams(params)
        params.id = ecole.id
        params.version = -1
        controller.update()

        assert view == "/ecole/edit"
        assert model.ecoleInstance != null
        assert model.ecoleInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/ecole/list'

        response.reset()

        populateValidParams(params)
        def ecole = new Ecole(params)

        assert ecole.save() != null
        assert Ecole.count() == 1

        params.id = ecole.id

        controller.delete()

        assert Ecole.count() == 0
        assert Ecole.get(ecole.id) == null
        assert response.redirectedUrl == '/ecole/list'
    }
}
