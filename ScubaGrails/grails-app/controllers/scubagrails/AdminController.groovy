package scubagrails

class AdminController {

    def beforeInterceptor = [action:this.&auth]
	
	def auth() {
		if(!session.user) {
			redirect(controller:"user", action:"login")
			return false
		}
	}
	
	def index = {}
}
