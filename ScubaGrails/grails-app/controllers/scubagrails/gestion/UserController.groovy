package scubagrails.gestion

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
		flash.message = "Au revoir ${session.user.login} !"
		session.user = null
		redirect(action:"login")
	}

	def authenticate = {
		def user =
				User.findByLoginAndPassword(params.login,
				params.password.encodeAsMD5())
		if(user){
			session.user = user
			flash.message = "Bonjour ${user.login}!"			
			redirect(controller:"admin", action:"index")			
		}else{
			flash.message =
					"Mauvais login et/ou mot de passe."
			redirect(action:"login")
		}
	}
}
