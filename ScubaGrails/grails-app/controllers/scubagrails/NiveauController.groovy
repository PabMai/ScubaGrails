package scubagrails

import org.springframework.dao.DataIntegrityViolationException

class NiveauController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [niveauInstanceList: Niveau.list(params), niveauInstanceTotal: Niveau.count()]
    }

    def create() {
        [niveauInstance: new Niveau(params)]
    }

    def save() {
        def niveauInstance = new Niveau(params)
        if (!niveauInstance.save(flush: true)) {
            render(view: "create", model: [niveauInstance: niveauInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'niveau.label', default: 'Niveau'), niveauInstance.id])
        redirect(action: "show", id: niveauInstance.id)
    }

    def show(Long id) {
        def niveauInstance = Niveau.get(id)
        if (!niveauInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'niveau.label', default: 'Niveau'), id])
            redirect(action: "list")
            return
        }

        [niveauInstance: niveauInstance]
    }

    def edit(Long id) {
        def niveauInstance = Niveau.get(id)
        if (!niveauInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'niveau.label', default: 'Niveau'), id])
            redirect(action: "list")
            return
        }

        [niveauInstance: niveauInstance]
    }

    def update(Long id, Long version) {
        def niveauInstance = Niveau.get(id)
        if (!niveauInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'niveau.label', default: 'Niveau'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (niveauInstance.version > version) {
                niveauInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'niveau.label', default: 'Niveau')] as Object[],
                          "Another user has updated this Niveau while you were editing")
                render(view: "edit", model: [niveauInstance: niveauInstance])
                return
            }
        }

        niveauInstance.properties = params

        if (!niveauInstance.save(flush: true)) {
            render(view: "edit", model: [niveauInstance: niveauInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'niveau.label', default: 'Niveau'), niveauInstance.id])
        redirect(action: "show", id: niveauInstance.id)
    }

    def delete(Long id) {
        def niveauInstance = Niveau.get(id)
        if (!niveauInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'niveau.label', default: 'Niveau'), id])
            redirect(action: "list")
            return
        }

        try {
            niveauInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'niveau.label', default: 'Niveau'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'niveau.label', default: 'Niveau'), id])
            redirect(action: "show", id: id)
        }
    }
}
