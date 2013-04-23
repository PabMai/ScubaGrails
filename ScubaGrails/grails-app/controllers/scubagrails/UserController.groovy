package scubagrails

import org.springframework.dao.DataIntegrityViolationException

class UserController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [userInstanceList: User.list(params), userInstanceTotal: User.count()]
    }

    def create() {
        [userInstance: new User(params)]
    }

    def save() {
        def userInstance = new User(params)
        if (!userInstance.save(flush: true)) {
            render(view: "create", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'user.created.message', args: [userInstance.login])
        redirect(action: "show", id: userInstance.id)
    }

    def show(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'user.not.found.message')
            redirect(action: "list")
            return
        }

        [userInstance: userInstance]
    }

    def edit(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'user.not.found.message')
            redirect(action: "list")
            return
        }

        [userInstance: userInstance]
    }

    def update(Long id, Long version) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'user.not.found.message')
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (userInstance.version > version) {
                userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'user.label', default: 'User')] as Object[],
                          "Another user has updated this User while you were editing")
                render(view: "edit", model: [userInstance: userInstance])
                return
            }
        }

        userInstance.properties = params

        if (!userInstance.save(flush: true)) {
            render(view: "edit", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'user.updated.message')
        redirect(action: "show", id: userInstance.id)
    }

    def delete(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'user.not.found.message')
            redirect(action: "list")
            return
        }

        try {
            userInstance.delete(flush: true)
            flash.message = message(code: 'user.deleted.message', args: [userInstance.login])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "show", id: id)
        }
    }
	
	// interceptor
	def beforeInterceptor = [action:this.&auth,
		except:[
			'login',
			'logout',
			'authenticate'
		]]

	def auth() {
		// si il n'y a pas de session (pas authentifié)
		if(!session.user) {
			redirect(controller:"user", action:"login")
			return false
		}
	}

	def login = {
		if (session.user) {
			redirect(controller:"admin", action:"index")
		}
	}

	def logout = {
		String login = session?.user?.login ?: session?.abonne?.login
		if (login == "agnes") {
			login = "Maman"
		}					
		session.user = null
		session.abonne = null
		session.invalidate()
		String messageAuRevoir = "Au revoir " + login
		flash.message = "${messageAuRevoir}"
		redirect(action:"login")
	}

	def authenticate = {
		// On regarde si c'est un utilisateur
		def user =
				User.findByLoginAndPassword(params.login,
				params.password.encodeAsMD5())
		if(user){
			session.user = user
			String login = user.login
			if (user.login == "agnes") {
				login = "Maman"
			}
			flash.message = "Bonjour $login"
			redirect(controller:"admin", action:"index")
		}else{
			// Alors c'est un abonné ?
			def abonne = Abonne.findByLoginAndPassword(params.login,
					params.password.encodeAsMD5())
			if (abonne) {
				session.abonne = abonne
				flash.message = "Bonjour ${abonne.login} "
				redirect(controller:"abonne", action:"show/${abonne.id}")
			} else {
				flash.message =
						"Mauvais login et/ou mot de passe."
				redirect(action:"login")
			}
		}
	}
}
