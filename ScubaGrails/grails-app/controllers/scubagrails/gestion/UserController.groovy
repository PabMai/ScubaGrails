package scubagrails.gestion

import scubagrails.Abonne;
import scubagrails.User;

class UserController {
	
	def scaffold = User
	
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
		String messageAuRevoir = "Au revoir " + login
		flash.message = "${messageAuRevoir}"
		session.user = null
		session.abonne = null
		redirect(action:"login")
	}

	def authenticate = {
		// On regarde si c'est un utilisateur
		def user =
				User.findByLoginAndPassword(params.login,
				params.password.encodeAsMD5())
		if(user){
			session.user = user
			flash.message = "Bonjour ${user.login} "
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
