package scubagrails

class NiveauApnee {

    // Pour le plugin searchable
	static searchable = true
	
	String niveau
	
	// Un niveau Apnée est acquis par un ou plusieurs abonnés
	static hasMany = [abonnes:Abonne]

    static constraints = {
		niveau(blank:false, maxSize:50, unique:true)
    }
	
	@Override
	public String toString() {
		niveau
	}
}
