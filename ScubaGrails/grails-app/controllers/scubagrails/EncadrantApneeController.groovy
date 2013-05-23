package scubagrails

import org.springframework.dao.DataIntegrityViolationException

import scubagrails.utils.PaginateableList;

class EncadrantApneeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [encadrantApneeInstanceList: EncadrantApnee.list(params), encadrantApneeInstanceTotal: EncadrantApnee.count()]
    }

    def create() {
        [encadrantApneeInstance: new EncadrantApnee(params)]
    }

    def save() {
        def encadrantApneeInstance = new EncadrantApnee(params)
        if (!encadrantApneeInstance.save(flush: true)) {
            render(view: "create", model: [encadrantApneeInstance: encadrantApneeInstance])
            return
        }

        flash.message = message(code: 'encadrant.created.message', args: [encadrantApneeInstance.nom])
        redirect(action: "show", id: encadrantApneeInstance.id)
    }

    def show(Long id) {
        def encadrantApneeInstance = EncadrantApnee.get(id)
        if (!encadrantApneeInstance) {
            flash.message = message(code: 'encadrant.not.found.message')
            redirect(action: "list")
            return
        }
		
		List<Abonne> listeOrigine = (List) encadrantApneeInstance.abonnes.toArray()
		if (params.sort) {
				if (params?.order == "asc") {
					listeOrigine.sort{it.(params.sort)}
				} else if (params?.order == "desc") {
					listeOrigine.sort{a,b -> b.(params.sort) <=> a.(params.sort)}
				}
		} else {
				listeOrigine.sort{it.nom}
		}
		List<Abonne> listePaginee = []
		use(PaginateableList) {
			listePaginee = listeOrigine.paginate(10,(params.offset ?: 0))
		}

        [encadrantApneeInstance: encadrantApneeInstance, listeAbonnes:listePaginee, encadrantApneeInstanceTotal:listeOrigine.size()]
    }

    def edit(Long id) {
        def encadrantApneeInstance = EncadrantApnee.get(id)
        if (!encadrantApneeInstance) {
            flash.message = message(code: 'encadrant.not.found.message')
            redirect(action: "list")
            return
        }

        [encadrantApneeInstance: encadrantApneeInstance]
    }

    def update(Long id, Long version) {
        def encadrantApneeInstance = EncadrantApnee.get(id)
        if (!encadrantApneeInstance) {
            flash.message = message(code: 'encadrant.not.found.message')
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (encadrantApneeInstance.version > version) {
                encadrantApneeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'encadrantApnee.label', default: 'EncadrantApnee')] as Object[],
                          "Another user has updated this EncadrantApnee while you were editing")
                render(view: "edit", model: [encadrantApneeInstance: encadrantApneeInstance])
                return
            }
        }

        encadrantApneeInstance.properties = params

        if (!encadrantApneeInstance.save(flush: true)) {
            render(view: "edit", model: [encadrantApneeInstance: encadrantApneeInstance])
            return
        }

        flash.message = message(code: 'encadrant.updated.message', args: [encadrantApneeInstance.nom])
        redirect(action: "show", id: encadrantApneeInstance.id)
    }

    def delete(Long id) {
        def encadrantApneeInstance = EncadrantApnee.get(id)
        if (!encadrantApneeInstance) {
            flash.message = message(code: 'encadrant.not.found.message')
            redirect(action: "list")
            return
        }

        try {
            encadrantApneeInstance.delete(flush: true)
            flash.message = message(code: 'encadrant.deleted.message', args: [encadrantApneeInstance.nom])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'encadrant.not.deleted.message', args: [encadrantApneeInstance.nom])
            redirect(action: "show", id: id)
        }
    }
}
