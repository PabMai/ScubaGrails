package scubagrails

import org.springframework.dao.DataIntegrityViolationException

class TypeMembreController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [typeMembreInstanceList: TypeMembre.list(params), typeMembreInstanceTotal: TypeMembre.count()]
    }

    def create() {
        [typeMembreInstance: new TypeMembre(params)]
    }

    def save() {
        def typeMembreInstance = new TypeMembre(params)
        if (!typeMembreInstance.save(flush: true)) {
            render(view: "create", model: [typeMembreInstance: typeMembreInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'typeMembre.label', default: 'TypeMembre'), typeMembreInstance.id])
        redirect(action: "show", id: typeMembreInstance.id)
    }

    def show(Long id) {
        def typeMembreInstance = TypeMembre.get(id)
        if (!typeMembreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'typeMembre.label', default: 'TypeMembre'), id])
            redirect(action: "list")
            return
        }

        [typeMembreInstance: typeMembreInstance]
    }

    def edit(Long id) {
        def typeMembreInstance = TypeMembre.get(id)
        if (!typeMembreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'typeMembre.label', default: 'TypeMembre'), id])
            redirect(action: "list")
            return
        }

        [typeMembreInstance: typeMembreInstance]
    }

    def update(Long id, Long version) {
        def typeMembreInstance = TypeMembre.get(id)
        if (!typeMembreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'typeMembre.label', default: 'TypeMembre'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (typeMembreInstance.version > version) {
                typeMembreInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'typeMembre.label', default: 'TypeMembre')] as Object[],
                          "Another user has updated this TypeMembre while you were editing")
                render(view: "edit", model: [typeMembreInstance: typeMembreInstance])
                return
            }
        }

        typeMembreInstance.properties = params

        if (!typeMembreInstance.save(flush: true)) {
            render(view: "edit", model: [typeMembreInstance: typeMembreInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'typeMembre.label', default: 'TypeMembre'), typeMembreInstance.id])
        redirect(action: "show", id: typeMembreInstance.id)
    }

    def delete(Long id) {
        def typeMembreInstance = TypeMembre.get(id)
        if (!typeMembreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'typeMembre.label', default: 'TypeMembre'), id])
            redirect(action: "list")
            return
        }

        try {
            typeMembreInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'typeMembre.label', default: 'TypeMembre'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'typeMembre.label', default: 'TypeMembre'), id])
            redirect(action: "show", id: id)
        }
    }
}
