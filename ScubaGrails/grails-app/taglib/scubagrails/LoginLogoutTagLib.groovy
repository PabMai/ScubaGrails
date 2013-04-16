package scubagrails

class LoginLogoutTagLib {
	
	static namespace = 'loginLogout'

	def loginControl = {
		if(request.getSession(false) && session.user){
			out << "Bonjour ${session.user.login} "
			out << """[${link(action:"logout",
					controller:"user"){"Logout"}}]"""
		} else if (request.getSession(false) && session.abonne) {
			out << "Bonjour ${session.abonne.login} "
			out << """[${link(action:"logout",
						controller:"user"){"Logout"}}]"""		
		} else {
			out << """[${link(action:"login",
					controller:"user"){'Login'}}]"""
		}
	}
}
