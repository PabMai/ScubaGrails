package scubagrails

import org.springframework.dao.DataIntegrityViolationException

import com.sun.net.ssl.internal.ssl.Alerts;

import scubagrails.utils.PaginateableList;

class EcoleScaphandreController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [ecoleInstanceList: EcoleScaphandre.list(params), ecoleInstanceTotal: EcoleScaphandre.count()]
    }

    def create() {
        [ecoleInstance: new EcoleScaphandre(params)]
    }

    def save() {
        def ecoleInstance = new EcoleScaphandre(params)
        if (!ecoleInstance.save(flush: true)) {
            render(view: "create", model: [ecoleInstance: ecoleInstance])
            return
        }
        flash.message = message(code: 'ecole.created.message', args: [ecoleInstance.nom.encodeAsHTML()])
        redirect(action: "show", id: ecoleInstance.id)
    }

    def show(Long id) {
        def ecoleInstance = EcoleScaphandre.get(id)
		params.max = Math.min(params.max ? params.int('max') : 5,100)		
		
		if (!ecoleInstance) {
			flash.message = message(code: 'ecole.not.found.message', args: [
				message(code: 'ecole.label', default: 'Ecole'),
				id
			])
			redirect(action: "list")
			return
		}

		List<Abonne> listeOrigine = (List) ecoleInstance.abonnes?.toArray()
		List<Abonne> listePaginee = []
		if (listeOrigine) {
			if (params.sort) {
				if (params?.order == "asc") {
					listeOrigine.sort{it.(params.sort)}
				} else if (params?.order == "desc") {
					listeOrigine.sort{a,b -> b.(params.sort) <=> a.(params.sort)}
				}
			} else {
				listeOrigine.sort{it.nom}
			}
			use(PaginateableList) {
				listePaginee = listeOrigine.paginate(5,(params.offset ?: 0))
			}
		}
		
        [ecoleInstance: ecoleInstance, listeAbonnes:listePaginee , abonneInstanceTotal:listeOrigine ? listeOrigine.size() : 0]
    }

    def edit(Long id) {
        def ecoleInstance = EcoleScaphandre.get(id)
        if (!ecoleInstance) {
            flash.message = message(code: 'ecole.not.found.message', args: [message(code: 'ecole.label', default: 'Ecole'), id])
            redirect(action: "list")
            return
        }

        [ecoleInstance: ecoleInstance]
    }

    def update(Long id, Long version) {
        def ecoleInstance = EcoleScaphandre.get(id)
        if (!ecoleInstance) {
            flash.message = message(code: 'ecole.not.found.message', args: [message(code: 'ecole.label', default: 'Ecole'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (ecoleInstance.version > version) {
                ecoleInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'ecole.label', default: 'Ecole')] as Object[],
                          "Un autre utilisateur a mise à jour cette école pendant que vous l'éditiez")
                render(view: "edit", model: [ecoleInstance: ecoleInstance])
                return
            }
        }

        ecoleInstance.properties = params

        if (!ecoleInstance.save(flush: true)) {
            render(view: "edit", model: [ecoleInstance: ecoleInstance])
            return
        }

        flash.message = message(code: 'ecole.updated.message')
        redirect(action: "show", id: ecoleInstance.id)
    }

    def delete(Long id) {
        def ecoleInstance = EcoleScaphandre.get(id)
        if (!ecoleInstance) {
            flash.message = message(code: 'ecole.not.found.message', args: [message(code: 'ecole.label', default: 'Ecole'), id])
            redirect(action: "list")
            return
        }
		
		if (ecoleInstance.abonnes != null && !ecoleInstance.abonnes.isEmpty()) {
			flash.message = message(code: 'ecole.not.deleted.message', args: [ecoleInstance.nom])
			redirect(action: "show", id: id)
			return
		}

        try {
            ecoleInstance.delete(flush: true)
            flash.message = message(code: 'ecole.deleted.message', args: [ecoleInstance.nom])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'ecole.not.deleted.message', args: [ecoleInstance.nom])
            redirect(action: "show", id: id)
        }
    }
}
