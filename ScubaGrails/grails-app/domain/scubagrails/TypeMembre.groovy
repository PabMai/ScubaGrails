package scubagrails

/**
 * Type de membre de l'association
 * (Licence seul, Normal, Apnée ...)
 * @author MONJAL
 * 
 */
class TypeMembre {
	
	String nom
	
	// un type de membre peut avoir 1 ou plusieurs abonnés
	static hasMany = [abonnes:Abonne]

    static constraints = {
		nom(blank:false, maxSize:50)
    }
	
	@Override
	public String toString() {
		nom
	}
}
