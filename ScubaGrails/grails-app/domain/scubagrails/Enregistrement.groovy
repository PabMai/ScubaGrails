package scubagrails

class Enregistrement {

	// Pour connaitre de facon automatique 
	// la dernière mise à jour de l'enregistrement
	Date lastUpdated	

	// Un enregistrement appartient à une unique saison pour un unique abonné
	static belongsTo = [abonne:Abonne, saison:Saison]

    static constraints = {
		abonne(unique: ['saison'])
    }
}
