package scubagrails

class Niveau {
	
	String niveau
	
	// Un niveau est acquis par un ou plusieurs abonnés
	static hasMany = [abonnes:Abonne]

    static constraints = {
		niveau(blank:false, maxSize:50)
    }
	
	@Override
	public String toString() {
		niveau
	}
}
