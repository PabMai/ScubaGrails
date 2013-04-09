class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		/**
		 * TODO modifier l'accès automatique à la page d'admin ?
		 */
		//"/"(controller:"admin", action:"index")
		"500"(view:'/error')
	}
}
