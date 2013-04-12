package scubagrails

import org.springframework.dao.DataIntegrityViolationException

class AbonneController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [abonneInstanceList: Abonne.list(params), abonneInstanceTotal: Abonne.count()]
    }

    def create() {
        [abonneInstance: new Abonne(params)]
    }

    def save() {
        def abonneInstance = new Abonne(params)
        if (!abonneInstance.save(flush: true)) {
            render(view: "create", model: [abonneInstance: abonneInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'abonne.label', default: 'Abonne'), abonneInstance.id])
        redirect(action: "show", id: abonneInstance.id)
    }

    def show(Long id) {
        def abonneInstance = Abonne.get(id)
        if (!abonneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'abonne.label', default: 'Abonne'), id])
            redirect(action: "list")
            return
        }

        [abonneInstance: abonneInstance]
    }

    def edit(Long id) {
        def abonneInstance = Abonne.get(id)
        if (!abonneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'abonne.label', default: 'Abonne'), id])
            redirect(action: "list")
            return
        }

        [abonneInstance: abonneInstance]
    }

    def update(Long id, Long version) {
        def abonneInstance = Abonne.get(id)
        if (!abonneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'abonne.label', default: 'Abonne'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (abonneInstance.version > version) {
                abonneInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'abonne.label', default: 'Abonne')] as Object[],
                          "Another user has updated this Abonne while you were editing")
                render(view: "edit", model: [abonneInstance: abonneInstance])
                return
            }
        }
        abonneInstance.properties = params

        if (!abonneInstance.save(flush: true)) {
            render(view: "edit", model: [abonneInstance: abonneInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'abonne.label', default: 'Abonne'), abonneInstance.id])
        redirect(action: "show", id: abonneInstance.id)
    }

    def delete(Long id) {
        def abonneInstance = Abonne.get(id)
        if (!abonneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'abonne.label', default: 'Abonne'), id])
            redirect(action: "list")
            return
        }

        try {
            abonneInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'abonne.label', default: 'Abonne'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'abonne.label', default: 'Abonne'), id])
            redirect(action: "show", id: id)
        }
    }
	
	def ajouterAvatarAbonne = {
		def abonneInstance = Abonne.get(params.idAbonne)
		if (!abonneInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'abonne.label', default: 'Abonne'), id])
			redirect(action: "list")
			return
		}		
		
		/** File Upload **/
		def file = request.getFile('avatar')
		if (file.size != 0) {
		if (file.size <= 16384) {
		
		def okcontents = ['image/png', 'image/jpeg', 'image/gif']
		if (! okcontents.contains(file.getContentType())) {
		  flash.message = "Avatar must be one of: ${okcontents}"
		  render(view:'show', model:[abonneInstance: abonneInstance])
		  return;
		}
		abonneInstance.avatar = file.getBytes()
		abonneInstance.mimeType = file.getContentType()
		log.info("File uploaded: " + abonneInstance.mimeType)
		} else {
			// on ne fait rien, trop gros
			flash.message = "Image trop volumineuse (maxi : 16Ko) !"
			render(view:'show', model:[abonneInstance: abonneInstance])
		return
		}
		} else {
			// on ne fait rien
			flash.message = "Vous n'avez pas choisi de photo !"
			render(view:'show', model:[abonneInstance: abonneInstance])
			return
		}
		/** File upload **/
				
		if (!abonneInstance.save(flush: true)) {
			render(view: "show", model: [abonneInstance: abonneInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'abonne.label', default: 'Abonne'), abonneInstance.id])
		redirect(action: "show", id: abonneInstance.id)
	}
	
	def getAvatarAbonne = {
		def abonneInstance = Abonne.get(params.id)
		if (!abonneInstance || !abonneInstance.avatar || !abonneInstance.mimeType) {
		  response.sendError(404)
		  return;
		}
		response.setContentType(abonneInstance.mimeType)
		response.setContentLength(abonneInstance.avatar.size())
		OutputStream out = response.getOutputStream();
		out.write(abonneInstance.avatar);
		out.close();
	  }
}
