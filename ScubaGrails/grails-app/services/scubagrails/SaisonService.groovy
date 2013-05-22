package scubagrails

import org.apache.commons.lang.mutable.MutableInt
import scubagrails.type.Sexe;

class SaisonService {

	/**
	 * Récupération de la saison en cours
	 * @return {@link Saison}
	 */
    Saison getSaisonEnCours() {
		Saison.findByEnCours(true)		
    }
	
	/**
	 * Récupération des données statistiques pour la saison en cours
	 * @param saisonEnCours l'objet {@link Saison} représentant la saison en cours
	 * @param nbH le nombre d'Hommes
	 * @param nbF le nombre de Femmes
	 * @param statEcole les stats pour les écoles
	 */
	void getDonneesSaisonEnCours(Saison saisonEnCours, MutableInt nbH, 
		MutableInt nbF, Map<String, Integer> statEcole, Map<String, Integer> statTypeMembre) {
		// Récupération du nombre d'enregistrement pour cette saison
		Set<Enregistrement> enregSaisonEnCours = saisonEnCours.enregistrements		
		
		// Pour chaque enregistrement :
		// Récupération de l'abonné et de ses statistiques
		enregSaisonEnCours.each { enreg ->
			Abonne abonne = enreg.abonne
			
			// Compteur sexe
			if (abonne.sexe == Sexe.MASCULIN) {
				nbH.add(1);
			} else {
				nbF.add(1);
			}
			
			// Récupération de la liste de toutes les écoles
			List<EcoleScaphandre> listeEcole = EcoleScaphandre.findAll()
			
			// Pour chaque école, on comptabilise son nombre d'abonné
			// (pour la saison actuelle)
			listeEcole.each {
				ecole ->
				if (abonne.ecole?.nom == ecole.nom) {
					if (statEcole.containsKey(ecole.nom)) {
						Integer nb = (Integer) statEcole.get(ecole.nom)
						nb++
						statEcole.put(ecole.nom, nb)
					} else {
						statEcole.put(ecole.nom, 1)
					}
				}
			}	
			
			// Compteur type membre
			List<TypeMembre> listeTypeM = TypeMembre.findAll()
			
			// Pour chaque type de membre, on comptabilise son nombre d'abonné
			listeTypeM.each {
				typeM ->
				if (abonne.typeMembre.nom == typeM.nom){
					if (statTypeMembre.containsKey(typeM.nom)) {
						Integer nb = (Integer) statTypeMembre.get(typeM.nom)
						nb++
						statTypeMembre.put(typeM.nom, nb)
					} else {
						statTypeMembre.put(typeM.nom, 1)
					}
				} 
			}
			
								
		}
	}
}
