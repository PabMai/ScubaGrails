package scubagrails

class AdminFilters {

    def filters = {
        // filtre pour les admins
        adminOnly(controller:'ecole|enregistrement|niveau|saison|typeMembre', action:'*') {
			before = {
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
				if(!session?.user){
					flash.message = "Accès non autorisé"
					redirect(controller:"user", action:"login")
					return false
				}
			}
		}
    }
}
