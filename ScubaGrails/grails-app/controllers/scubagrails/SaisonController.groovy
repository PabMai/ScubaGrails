package scubagrails

import org.springframework.dao.DataIntegrityViolationException

class SaisonController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [saisonInstanceList: Saison.list(params), saisonInstanceTotal: Saison.count()]
    }

    def create() {
        [saisonInstance: new Saison(params)]
    }

    def save() {
        def saisonInstance = new Saison(params)
        if (!saisonInstance.save(flush: true)) {
            render(view: "create", model: [saisonInstance: saisonInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'saison.label', default: 'Saison'), saisonInstance.id])
        redirect(action: "show", id: saisonInstance.id)
    }

    def show(Long id) {
        def saisonInstance = Saison.get(id)
        if (!saisonInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'saison.label', default: 'Saison'), id])
            redirect(action: "list")
            return
        }

        [saisonInstance: saisonInstance]
    }

    def edit(Long id) {
        def saisonInstance = Saison.get(id)
        if (!saisonInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'saison.label', default: 'Saison'), id])
            redirect(action: "list")
            return
        }

        [saisonInstance: saisonInstance]
    }

    def update(Long id, Long version) {
        def saisonInstance = Saison.get(id)
        if (!saisonInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'saison.label', default: 'Saison'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (saisonInstance.version > version) {
                saisonInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'saison.label', default: 'Saison')] as Object[],
                          "Another user has updated this Saison while you were editing")
                render(view: "edit", model: [saisonInstance: saisonInstance])
                return
            }
        }

        saisonInstance.properties = params

        if (!saisonInstance.save(flush: true)) {
            render(view: "edit", model: [saisonInstance: saisonInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'saison.label', default: 'Saison'), saisonInstance.id])
        redirect(action: "show", id: saisonInstance.id)
    }

    def delete(Long id) {
        def saisonInstance = Saison.get(id)
        if (!saisonInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'saison.label', default: 'Saison'), id])
            redirect(action: "list")
            return
        }

        try {
            saisonInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'saison.label', default: 'Saison'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'saison.label', default: 'Saison'), id])
            redirect(action: "show", id: id)
        }
    }
}
