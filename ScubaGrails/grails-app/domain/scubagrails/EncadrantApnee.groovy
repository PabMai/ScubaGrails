package scubagrails

class EncadrantApnee {

    String nom
	
	// Un type EncadrantApnée possède 1 ou plusieurs abonnés
	static hasMany = [abonnes:Abonne]

    static constraints = {
		nom(blank:false, maxSize:30, unique:true)
    }
	
	@Override
	public String toString() {
		nom
	}
}
