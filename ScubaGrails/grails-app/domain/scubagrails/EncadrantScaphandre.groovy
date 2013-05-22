package scubagrails

class EncadrantScaphandre {
	
	String nom
	
	// Un type EncadrantScaphandre possède 1 ou plusieurs abonnés
	static hasMany = [abonnes:Abonne]

    static constraints = {
		nom(blank:false, maxSize:30, unique:true)
    }
	
	@Override
	public String toString() {
		nom
	}
}
