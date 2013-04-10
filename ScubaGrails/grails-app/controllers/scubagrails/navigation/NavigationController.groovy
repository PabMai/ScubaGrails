package scubagrails.navigation

class NavigationController {

	def index() {
		if(!session.user) {
			redirect(controller:"user", action:"login")
			return false
		} else {
			redirect(controller:"admin", action:"index")
		}
	}
}

