package scubagrails

class EcoleApnee {

   // Pour le plugin searchable
	static searchable = true
	
	String nom
	
	// Une école possède 1 ou plusieurs abonnés
	static hasMany = [abonnes:Abonne]

    static constraints = {
		nom(blank:false, maxSize:30, unique:true)
    }
	
	@Override
	public String toString() {
		nom
	}
}
