package scubagrails

import org.springframework.dao.DataIntegrityViolationException

class EcoleController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [ecoleInstanceList: Ecole.list(params), ecoleInstanceTotal: Ecole.count()]
    }

    def create() {
        [ecoleInstance: new Ecole(params)]
    }

    def save() {
        def ecoleInstance = new Ecole(params)
        if (!ecoleInstance.save(flush: true)) {
            render(view: "create", model: [ecoleInstance: ecoleInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'ecole.label', default: 'Ecole'), ecoleInstance.id])
        redirect(action: "show", id: ecoleInstance.id)
    }

    def show(Long id) {
        def ecoleInstance = Ecole.get(id)
        if (!ecoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ecole.label', default: 'Ecole'), id])
            redirect(action: "list")
            return
        }

        [ecoleInstance: ecoleInstance]
    }

    def edit(Long id) {
        def ecoleInstance = Ecole.get(id)
        if (!ecoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ecole.label', default: 'Ecole'), id])
            redirect(action: "list")
            return
        }

        [ecoleInstance: ecoleInstance]
    }

    def update(Long id, Long version) {
        def ecoleInstance = Ecole.get(id)
        if (!ecoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ecole.label', default: 'Ecole'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (ecoleInstance.version > version) {
                ecoleInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'ecole.label', default: 'Ecole')] as Object[],
                          "Another user has updated this Ecole while you were editing")
                render(view: "edit", model: [ecoleInstance: ecoleInstance])
                return
            }
        }

        ecoleInstance.properties = params

        if (!ecoleInstance.save(flush: true)) {
            render(view: "edit", model: [ecoleInstance: ecoleInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'ecole.label', default: 'Ecole'), ecoleInstance.id])
        redirect(action: "show", id: ecoleInstance.id)
    }

    def delete(Long id) {
        def ecoleInstance = Ecole.get(id)
        if (!ecoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ecole.label', default: 'Ecole'), id])
            redirect(action: "list")
            return
        }

        try {
            ecoleInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'ecole.label', default: 'Ecole'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ecole.label', default: 'Ecole'), id])
            redirect(action: "show", id: id)
        }
    }
}
