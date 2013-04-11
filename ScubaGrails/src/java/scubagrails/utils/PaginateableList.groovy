package scubagrails.utils

@Category(List)
class PaginateableList {

	/**
	 * Utilisé pour paginer une liste
	 * @param max : nombre d'élément de la liste a afficher
	 * @param offset : indice de début de la sous-liste
	 * @return sous-liste paginée
	 */
	List paginate(max, offset = 0) {
		this.subList( Math.min(offset as Integer, this.size()), 
			Math.min((offset as Integer) + (max as Integer), this.size())
		)
	}
}
