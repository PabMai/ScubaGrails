package scubagrails

class Ecole {
	
	String nom
	
	// Une école possède 1 ou plusieurs abonnés
	static hasMany = [abonnes:Abonne]

    static constraints = {
		nom(blank:false, maxSize:30)
    }
	
	@Override
	public String toString() {
		nom
	}
}
