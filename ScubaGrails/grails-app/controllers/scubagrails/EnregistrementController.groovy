package scubagrails

import org.springframework.dao.DataIntegrityViolationException

class EnregistrementController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [enregistrementInstanceList: Enregistrement.list(params), enregistrementInstanceTotal: Enregistrement.count()]
    }

    def create() {
        [enregistrementInstance: new Enregistrement(params)]
    }

    def save() {
        def enregistrementInstance = new Enregistrement(params)
        if (!enregistrementInstance.save(flush: true)) {
            render(view: "create", model: [enregistrementInstance: enregistrementInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'enregistrement.label', default: 'Enregistrement'), enregistrementInstance.id])
        redirect(action: "show", id: enregistrementInstance.id)
    }

    def show(Long id) {
        def enregistrementInstance = Enregistrement.get(id)
        if (!enregistrementInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'enregistrement.label', default: 'Enregistrement'), id])
            redirect(action: "list")
            return
        }

        [enregistrementInstance: enregistrementInstance]
    }

    def edit(Long id) {
        def enregistrementInstance = Enregistrement.get(id)
        if (!enregistrementInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'enregistrement.label', default: 'Enregistrement'), id])
            redirect(action: "list")
            return
        }

        [enregistrementInstance: enregistrementInstance]
    }

    def update(Long id, Long version) {
        def enregistrementInstance = Enregistrement.get(id)
        if (!enregistrementInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'enregistrement.label', default: 'Enregistrement'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (enregistrementInstance.version > version) {
                enregistrementInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'enregistrement.label', default: 'Enregistrement')] as Object[],
                          "Another user has updated this Enregistrement while you were editing")
                render(view: "edit", model: [enregistrementInstance: enregistrementInstance])
                return
            }
        }

        enregistrementInstance.properties = params

        if (!enregistrementInstance.save(flush: true)) {
            render(view: "edit", model: [enregistrementInstance: enregistrementInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'enregistrement.label', default: 'Enregistrement'), enregistrementInstance.id])
        redirect(action: "show", id: enregistrementInstance.id)
    }

    def delete(Long id) {
        def enregistrementInstance = Enregistrement.get(id)
        if (!enregistrementInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'enregistrement.label', default: 'Enregistrement'), id])
            redirect(action: "list")
            return
        }

        try {
            enregistrementInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'enregistrement.label', default: 'Enregistrement'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'enregistrement.label', default: 'Enregistrement'), id])
            redirect(action: "show", id: id)
        }
    }
}
