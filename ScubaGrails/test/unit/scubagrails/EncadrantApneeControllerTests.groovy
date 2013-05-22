package scubagrails



import org.junit.*
import grails.test.mixin.*

@TestFor(EncadrantApneeController)
@Mock(EncadrantApnee)
class EncadrantApneeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/encadrantApnee/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.encadrantApneeInstanceList.size() == 0
        assert model.encadrantApneeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.encadrantApneeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.encadrantApneeInstance != null
        assert view == '/encadrantApnee/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/encadrantApnee/show/1'
        assert controller.flash.message != null
        assert EncadrantApnee.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/encadrantApnee/list'

        populateValidParams(params)
        def encadrantApnee = new EncadrantApnee(params)

        assert encadrantApnee.save() != null

        params.id = encadrantApnee.id

        def model = controller.show()

        assert model.encadrantApneeInstance == encadrantApnee
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/encadrantApnee/list'

        populateValidParams(params)
        def encadrantApnee = new EncadrantApnee(params)

        assert encadrantApnee.save() != null

        params.id = encadrantApnee.id

        def model = controller.edit()

        assert model.encadrantApneeInstance == encadrantApnee
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/encadrantApnee/list'

        response.reset()

        populateValidParams(params)
        def encadrantApnee = new EncadrantApnee(params)

        assert encadrantApnee.save() != null

        // test invalid parameters in update
        params.id = encadrantApnee.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/encadrantApnee/edit"
        assert model.encadrantApneeInstance != null

        encadrantApnee.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/encadrantApnee/show/$encadrantApnee.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        encadrantApnee.clearErrors()

        populateValidParams(params)
        params.id = encadrantApnee.id
        params.version = -1
        controller.update()

        assert view == "/encadrantApnee/edit"
        assert model.encadrantApneeInstance != null
        assert model.encadrantApneeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/encadrantApnee/list'

        response.reset()

        populateValidParams(params)
        def encadrantApnee = new EncadrantApnee(params)

        assert encadrantApnee.save() != null
        assert EncadrantApnee.count() == 1

        params.id = encadrantApnee.id

        controller.delete()

        assert EncadrantApnee.count() == 0
        assert EncadrantApnee.get(encadrantApnee.id) == null
        assert response.redirectedUrl == '/encadrantApnee/list'
    }
}
