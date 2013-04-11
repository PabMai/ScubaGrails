package scubagrails

class AdminFilters {

    def filters = {
        // filtre pour les admins
        adminOnly(controller:'admin|ecole|enregistrement|niveau|saison|typemembre', action:'*') {
			before = {
				// pas admin ?
				if(!session?.user?.admin){
					flash.message = "Accès non autorisé"
					redirect(controller:"user", action:"login")
					return false
				}
			}
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}
