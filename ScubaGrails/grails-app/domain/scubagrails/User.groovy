package scubagrails

class User {

    String login
	String password
	String email
	String nom
	String prenom
	String role = "administrateur"
	

	static constraints = {
		nom(blank:false, maxSize: 40, minSize: 2)
		prenom(blank:false, maxSize: 40, minSize: 2)
		login(blank:false, nullable:false, unique:true)
		password(blank:false, password:true, minSize:4)
		email(blank:false, email:true)
		role(blank:false, inList:["administrateur", "autre..."])
	}

	String toString(){
		login
	}

	def beforeInsert = {
		password = password.encodeAsMD5()
	}
	
	// pour éviter de persister admin (isAdmin)
	static transients = ['admin']

	boolean isAdmin(){
		return role == "administrateur"
	}
}
