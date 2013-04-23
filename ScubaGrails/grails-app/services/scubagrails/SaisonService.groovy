package scubagrails

class SaisonService {

	/**
	 * Récupération de la saison en cours
	 * @return {@link Saison}
	 */
    Saison getSaisonEnCours() {
		Saison.findByEnCours(true)		
    }
}
