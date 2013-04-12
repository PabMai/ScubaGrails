package scubagrails

class Enregistrement {

	// Pour connaitre de facon automatique 
	// la dernière mise à jour de l'enregistrement
	Date lastUpdated	

	// Un enregistrement appartient à une unique saison pour un unique abonné
	Abonne abonne
	Saison saison

    static constraints = {
		abonne(unique: ['saison'])
    }
}
