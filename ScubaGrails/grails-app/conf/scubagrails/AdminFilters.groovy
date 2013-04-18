package scubagrails

class AdminFilters {

    def filters = {
        // filtre pour les admins
        adminOnly(controller:'ecole|enregistrement|niveau|saison|typeMembre', action:'*') {
			before = {
				if (session?.abonne) {
					flash.message = "Accès non autorisé"
					redirect(controller:"abonne", action:"show/${session?.abonne?.id}")
					return false
				}
				// pas admin ?
				if(!session?.user?.admin){
					flash.message = "Accès non autorisé"
					redirect(controller:"user", action:"login")
					return false
				}
			}            
        }
		
		userAndAdmin(controller:'admin', action:'*') {
			before = {
				// pas admin ?
				if(!session?.user?.admin){
					flash.message = "Accès non autorisé"
					redirect(controller:"user", action:"login")
					return false
				}
			}
		}
		
		gestionAbonne(controller:'abonne', action:'list|create|delete|showAbonneCMPerime') {
			before = {
				// admin ? --> ok
				if(session?.user?.admin){
					return true
				} else if(session?.abonne){
					redirect(controller:"abonne", action:"show/${session?.abonne?.id}")
					return false
				} else {
					flash.message = "Accès non autorisé"
					redirect(controller:"user", action:"login")
					return false
				}
			}
		}
		
		gestionAbonneShow(controller:'abonne', action:'show|edit|edit|editMotDePasse') {
			before = {
				// admin ? --> ok
				if(session?.user?.admin){
					return true
				}				
				// pas abonné ?
				if(!session?.abonne){
					flash.message = "Accès non autorisé"
					redirect(controller:"user", action:"login")
					return false
				} else {			
					if (!(params.id == String.valueOf(session?.abonne?.id))) {
						flash.message = "Accès non autorisé"						
						redirect(controller:"abonne", action:"show/${session?.abonne?.id}")
						return false
					}
				}
			}
		}
    }
}
