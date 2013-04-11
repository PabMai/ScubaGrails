package scubagrails

import org.springframework.dao.DataIntegrityViolationException

import scubagrails.utils.PaginateableList;

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

        flash.message = message(code: 'niveau.created.message', args: [niveauInstance.niveau])
        redirect(action: "show", id: niveauInstance.id)
    }

    def show(Long id) {
        def niveauInstance = Niveau.get(id)
		params.max = Math.min(params.max ? params.int('max') : 5,100)
        if (!niveauInstance) {
            flash.message = message(code: 'niveau.not.found.message', args: [message(code: 'niveau.label', default: 'Niveau'), id])
            redirect(action: "list")
            return
        }
		
		List<Abonne> listeOrigine = (List) niveauInstance.abonnes.toArray()
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
			listePaginee = listeOrigine.paginate(5,(params.offset ?: 0))
		}
		
		[niveauInstance: niveauInstance, listeAbonnes:listePaginee , abonneInstanceTotal:listeOrigine.size()]
    }

    def edit(Long id) {
        def niveauInstance = Niveau.get(id)
        if (!niveauInstance) {
            flash.message = message(code: 'niveau.not.found.message', args: [message(code: 'niveau.label', default: 'Niveau'), id])
            redirect(action: "list")
            return
        }

        [niveauInstance: niveauInstance]
    }

    def update(Long id, Long version) {
        def niveauInstance = Niveau.get(id)
        if (!niveauInstance) {
            flash.message = message(code: 'niveau.not.found.message', args: [message(code: 'niveau.label', default: 'Niveau'), id])
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

        flash.message = message(code: 'niveau.updated.message', args: [niveauInstance.niveau])
        redirect(action: "show", id: niveauInstance.id)
    }

    def delete(Long id) {
        def niveauInstance = Niveau.get(id)
        if (!niveauInstance) {
            flash.message = message(code: 'niveau.not.found.message')
            redirect(action: "list")
            return
        }

        try {
            niveauInstance.delete(flush: true)
            flash.message = message(code: 'niveau.deleted.message', args: [niveauInstance.niveau])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'niveau.not.deleted.message', args: [niveauInstance.niveau])
            redirect(action: "show", id: id)
        }
    }
}
