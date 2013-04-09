import grails.util.Environment;
import scubagrails.User

class BootStrap {

    def init = { servletContext ->
		
		switch(Environment.getCurrent()){
			/* Environnement de d�veloppement */
			case Environment.DEVELOPMENT:
				// Cr�ation d'un Admin
				def agnes = new User(login:"agnes",
				password:"agnes", 
				email:"agnes@test.com",
				nom: "tri",
				prenom: "agnes",
				role: "administrateur")
				agnes.save()
				if(agnes.hasErrors()){
					println agnes.errors
				} else {
					println "Utilisateur 'Agnes' cree : OK !"
				}
				
				// Cr�ation d'un User
				def moi = new User(login:"moimoi",
					password:"moimoi",
					email:"moi@test.com",
					nom: "moiNom",
					prenom: "moiPrenom",
					role: "utilisateur")
					moi.save()
					if(moi.hasErrors()){
						println moi.errors
					} else {
						println "Utilisateur 'Moi' cree : OK !"
					}
			
			break
			/* Environnement de production */
			case Environment.PRODUCTION : 
			break
		}
		
		
    }
    def destroy = {
    }
}
