package scubagrails



import org.junit.*
import grails.test.mixin.*

@TestFor(EncadrantScaphandreController)
@Mock(EncadrantScaphandre)
class EncadrantScaphandreControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/encadrantScaphandre/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.encadrantScaphandreInstanceList.size() == 0
        assert model.encadrantScaphandreInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.encadrantScaphandreInstance != null
    }

    void testSave() {
        controller.save()

        assert model.encadrantScaphandreInstance != null
        assert view == '/encadrantScaphandre/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/encadrantScaphandre/show/1'
        assert controller.flash.message != null
        assert EncadrantScaphandre.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/encadrantScaphandre/list'

        populateValidParams(params)
        def encadrantScaphandre = new EncadrantScaphandre(params)

        assert encadrantScaphandre.save() != null

        params.id = encadrantScaphandre.id

        def model = controller.show()

        assert model.encadrantScaphandreInstance == encadrantScaphandre
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/encadrantScaphandre/list'

        populateValidParams(params)
        def encadrantScaphandre = new EncadrantScaphandre(params)

        assert encadrantScaphandre.save() != null

        params.id = encadrantScaphandre.id

        def model = controller.edit()

        assert model.encadrantScaphandreInstance == encadrantScaphandre
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/encadrantScaphandre/list'

        response.reset()

        populateValidParams(params)
        def encadrantScaphandre = new EncadrantScaphandre(params)

        assert encadrantScaphandre.save() != null

        // test invalid parameters in update
        params.id = encadrantScaphandre.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/encadrantScaphandre/edit"
        assert model.encadrantScaphandreInstance != null

        encadrantScaphandre.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/encadrantScaphandre/show/$encadrantScaphandre.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        encadrantScaphandre.clearErrors()

        populateValidParams(params)
        params.id = encadrantScaphandre.id
        params.version = -1
        controller.update()

        assert view == "/encadrantScaphandre/edit"
        assert model.encadrantScaphandreInstance != null
        assert model.encadrantScaphandreInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/encadrantScaphandre/list'

        response.reset()

        populateValidParams(params)
        def encadrantScaphandre = new EncadrantScaphandre(params)

        assert encadrantScaphandre.save() != null
        assert EncadrantScaphandre.count() == 1

        params.id = encadrantScaphandre.id

        controller.delete()

        assert EncadrantScaphandre.count() == 0
        assert EncadrantScaphandre.get(encadrantScaphandre.id) == null
        assert response.redirectedUrl == '/encadrantScaphandre/list'
    }
}
