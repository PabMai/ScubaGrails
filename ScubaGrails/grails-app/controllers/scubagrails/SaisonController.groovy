package scubagrails

import org.springframework.dao.DataIntegrityViolationException

import scubagrails.utils.PaginateableList;

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

        flash.message = message(code: 'saison.created.message', args: [saisonInstance.libelle])
        redirect(action: "show", id: saisonInstance.id)
    }

    def show(Long id) {
        def saisonInstance = Saison.get(id)
		params.max = Math.min(params.max ? params.int('max') : 5,100)
        if (!saisonInstance) {
            flash.message = message(code: 'saison.not.found.message')
            redirect(action: "list")
            return
        }
		
		List<Abonne> listeOrigine = (List) saisonInstance.enregistrements.toArray()
		if (params.sort) {
				if (params?.order == "asc") {
					listeOrigine.sort{it?.abonne?.(params.sort)}
				} else if (params?.order == "desc") {
					listeOrigine.sort{a,b -> b?.abonne?.(params.sort) <=> a?.abonne?.(params.sort)}
				}
		} else {
				listeOrigine.sort{it?.abonne?.nom}
		}
		List<Abonne> listePaginee = []
		use(PaginateableList) {
			listePaginee = listeOrigine.paginate(5,(params.offset ?: 0))
		}
		
		[saisonInstance: saisonInstance, listeEnregistrement:listePaginee , abonneInstanceTotal:listeOrigine.size()]
    }

    def edit(Long id) {
        def saisonInstance = Saison.get(id)
        if (!saisonInstance) {
            flash.message = message(code: 'saison.not.found.message')
            redirect(action: "list")
            return
        }

        [saisonInstance: saisonInstance]
    }

    def update(Long id, Long version) {
        def saisonInstance = Saison.get(id)
        if (!saisonInstance) {
            flash.message = message(code: 'saison.not.found.message')
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (saisonInstance.version > version) {
                saisonInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'saison.label', default: 'Saison')] as Object[],
                          "Un autre utilisateur à mise à jour cette saison pendant que vous l'éditiez")
                render(view: "edit", model: [saisonInstance: saisonInstance])
                return
            }
        }

        saisonInstance.properties = params

        if (!saisonInstance.save(flush: true)) {
            render(view: "edit", model: [saisonInstance: saisonInstance])
            return
        }

        flash.message = message(code: 'saison.updated.message', args: [saisonInstance.libelle])
        redirect(action: "show", id: saisonInstance.id)
    }

    def delete(Long id) {
        def saisonInstance = Saison.get(id)
        if (!saisonInstance) {
            flash.message = message(code: 'saison.not.found.message')
            redirect(action: "list")
            return
        }

        try {
            saisonInstance.delete(flush: true)
            flash.message = message(code: 'saison.deleted.message', args: [saisonInstance.libelle])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'saison.not.deleted.message', args: [saisonInstance.libelle])
            redirect(action: "show", id: id)
        }
    }
}
