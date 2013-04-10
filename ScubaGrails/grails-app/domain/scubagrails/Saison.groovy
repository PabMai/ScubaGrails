package scubagrails

class Saison {
	
	String libelle
	Date dateDebut
	Date dateFin
	
	// Une saison peut être enregistrée pour un ou plusieurs abonnés
	static hasMany = [enregistrements:Enregistrement]

    static constraints = {
		libelle(blank:false, maxSize:60)
		dateDebut(blank:false)
		dateFin(blank:false, validator: {val, obj ->
			// val --> champ courant
			// obj --> variable représentant une instance de Saison
			return val.after(obj.dateDebut)			
		})
    }
	
	@Override
	public String toString() {
		libelle
	}
	
}
