package scubagrails



import org.junit.*
import grails.test.mixin.*

@TestFor(TypeMembreController)
@Mock(TypeMembre)
class TypeMembreControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/typeMembre/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.typeMembreInstanceList.size() == 0
        assert model.typeMembreInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.typeMembreInstance != null
    }

    void testSave() {
        controller.save()

        assert model.typeMembreInstance != null
        assert view == '/typeMembre/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/typeMembre/show/1'
        assert controller.flash.message != null
        assert TypeMembre.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/typeMembre/list'

        populateValidParams(params)
        def typeMembre = new TypeMembre(params)

        assert typeMembre.save() != null

        params.id = typeMembre.id

        def model = controller.show()

        assert model.typeMembreInstance == typeMembre
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/typeMembre/list'

        populateValidParams(params)
        def typeMembre = new TypeMembre(params)

        assert typeMembre.save() != null

        params.id = typeMembre.id

        def model = controller.edit()

        assert model.typeMembreInstance == typeMembre
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/typeMembre/list'

        response.reset()

        populateValidParams(params)
        def typeMembre = new TypeMembre(params)

        assert typeMembre.save() != null

        // test invalid parameters in update
        params.id = typeMembre.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/typeMembre/edit"
        assert model.typeMembreInstance != null

        typeMembre.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/typeMembre/show/$typeMembre.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        typeMembre.clearErrors()

        populateValidParams(params)
        params.id = typeMembre.id
        params.version = -1
        controller.update()

        assert view == "/typeMembre/edit"
        assert model.typeMembreInstance != null
        assert model.typeMembreInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/typeMembre/list'

        response.reset()

        populateValidParams(params)
        def typeMembre = new TypeMembre(params)

        assert typeMembre.save() != null
        assert TypeMembre.count() == 1

        params.id = typeMembre.id

        controller.delete()

        assert TypeMembre.count() == 0
        assert TypeMembre.get(typeMembre.id) == null
        assert response.redirectedUrl == '/typeMembre/list'
    }
}
