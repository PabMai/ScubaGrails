package scubagrails

import org.springframework.dao.DataIntegrityViolationException

import scubagrails.utils.PaginateableList;

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

        flash.message = message(code: 'typeMembre.created.message', args: [typeMembreInstance.nom])
        redirect(action: "show", id: typeMembreInstance.id)
    }

    def show(Long id) {
        def typeMembreInstance = TypeMembre.get(id)
		params.max = Math.min(params.max ? params.int('max') : 5,100)
        if (!typeMembreInstance) {
            flash.message = message(code: 'typeMembre.not.found.message')
            redirect(action: "list")
            return
        }
		
		List<Abonne> listeOrigine = (List) typeMembreInstance.abonnes.toArray()
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
		
		[typeMembreInstance: typeMembreInstance, listeAbonnes:listePaginee , abonneInstanceTotal:listeOrigine.size()]
    }

    def edit(Long id) {
        def typeMembreInstance = TypeMembre.get(id)
        if (!typeMembreInstance) {
            flash.message = message(code: 'typeMembre.not.found.message')
            redirect(action: "list")
            return
        }

        [typeMembreInstance: typeMembreInstance]
    }

    def update(Long id, Long version) {
        def typeMembreInstance = TypeMembre.get(id)
        if (!typeMembreInstance) {
            flash.message = message(code: 'typeMembre.not.found.message')
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (typeMembreInstance.version > version) {
                typeMembreInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'typeMembre.label', default: 'TypeMembre')] as Object[],
                          "Un autre utilisateur à mis à jour ce type de membre pendant que vous l'éditiez")
                render(view: "edit", model: [typeMembreInstance: typeMembreInstance])
                return
            }
        }

        typeMembreInstance.properties = params

        if (!typeMembreInstance.save(flush: true)) {
            render(view: "edit", model: [typeMembreInstance: typeMembreInstance])
            return
        }

        flash.message = message(code: 'typeMembre.updated.message', args: [typeMembreInstance.nom])
        redirect(action: "show", id: typeMembreInstance.id)
    }

    def delete(Long id) {
        def typeMembreInstance = TypeMembre.get(id)
        if (!typeMembreInstance) {
            flash.message = message(code: 'typeMembre.not.found.message')
            redirect(action: "list")
            return
        }

        try {
            typeMembreInstance.delete(flush: true)
            flash.message = message(code: 'typeMembre.deleted.message', args: [typeMembreInstance.nom])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'typeMembre.not.deleted.message', args: [typeMembreInstance.nom])
            redirect(action: "show", id: id)
        }
    }
}
