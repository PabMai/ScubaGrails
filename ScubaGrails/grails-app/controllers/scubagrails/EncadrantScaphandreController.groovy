package scubagrails

import org.springframework.dao.DataIntegrityViolationException

import scubagrails.utils.PaginateableList;

class EncadrantScaphandreController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [encadrantScaphandreInstanceList: EncadrantScaphandre.list(params), encadrantScaphandreInstanceTotal: EncadrantScaphandre.count()]
    }

    def create() {
        [encadrantScaphandreInstance: new EncadrantScaphandre(params)]
    }

    def save() {
        def encadrantScaphandreInstance = new EncadrantScaphandre(params)
        if (!encadrantScaphandreInstance.save(flush: true)) {
            render(view: "create", model: [encadrantScaphandreInstance: encadrantScaphandreInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'encadrantScaphandre.label', default: 'EncadrantScaphandre'), encadrantScaphandreInstance.id])
        redirect(action: "show", id: encadrantScaphandreInstance.id)
    }

    def show(Long id) {
        def encadrantScaphandreInstance = EncadrantScaphandre.get(id)
        if (!encadrantScaphandreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'encadrantScaphandre.label', default: 'EncadrantScaphandre'), id])
            redirect(action: "list")
            return
        }
		
		List<Abonne> listeOrigine = (List) encadrantScaphandreInstance.abonnes.toArray()
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
		

        [encadrantScaphandreInstance: encadrantScaphandreInstance, listeAbonnes:listePaginee, encadrantScaphandreInstanceTotal:listeOrigine.size()]
    }

    def edit(Long id) {
        def encadrantScaphandreInstance = EncadrantScaphandre.get(id)
        if (!encadrantScaphandreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'encadrantScaphandre.label', default: 'EncadrantScaphandre'), id])
            redirect(action: "list")
            return
        }

        [encadrantScaphandreInstance: encadrantScaphandreInstance]
    }

    def update(Long id, Long version) {
        def encadrantScaphandreInstance = EncadrantScaphandre.get(id)
        if (!encadrantScaphandreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'encadrantScaphandre.label', default: 'EncadrantScaphandre'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (encadrantScaphandreInstance.version > version) {
                encadrantScaphandreInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'encadrantScaphandre.label', default: 'EncadrantScaphandre')] as Object[],
                          "Another user has updated this EncadrantScaphandre while you were editing")
                render(view: "edit", model: [encadrantScaphandreInstance: encadrantScaphandreInstance])
                return
            }
        }

        encadrantScaphandreInstance.properties = params

        if (!encadrantScaphandreInstance.save(flush: true)) {
            render(view: "edit", model: [encadrantScaphandreInstance: encadrantScaphandreInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'encadrantScaphandre.label', default: 'EncadrantScaphandre'), encadrantScaphandreInstance.id])
        redirect(action: "show", id: encadrantScaphandreInstance.id)
    }

    def delete(Long id) {
        def encadrantScaphandreInstance = EncadrantScaphandre.get(id)
        if (!encadrantScaphandreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'encadrantScaphandre.label', default: 'EncadrantScaphandre'), id])
            redirect(action: "list")
            return
        }

        try {
            encadrantScaphandreInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'encadrantScaphandre.label', default: 'EncadrantScaphandre'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'encadrantScaphandre.label', default: 'EncadrantScaphandre'), id])
            redirect(action: "show", id: id)
        }
    }
}
