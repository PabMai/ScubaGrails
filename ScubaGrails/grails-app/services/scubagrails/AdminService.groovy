package scubagrails

import java.text.SimpleDateFormat

import scubagrails.type.Sexe;

class AdminService {

    List<String> traiterImportAbonne(List abonnesMapList ) {
		List<String> listeLogs = new ArrayList<String>()
		abonnesMapList.each { Map abonneParams ->

			listeLogs.add("=====================")
			listeLogs.add("Traitement de l'abonné ${abonneParams.prenom} ${abonneParams.nom} en cours")
			
			// Téléphone fixe
			if (abonneParams.telephoneFixe != null) {
				if (abonneParams.telephoneFixe.substring(0, 1) != "0") {
					abonneParams.telephoneFixe = "0" + abonneParams.telephoneFixe.replaceAll("\\s","")
				} else {
					abonneParams.telephoneFixe = abonneParams.telephoneFixe.replaceAll("\\s","")
				}
			}

			// Téléphone portable
			if (abonneParams.telephonePortable != null)
				if (abonneParams.telephonePortable.substring(0, 1) != "0") {
					abonneParams.telephonePortable = "0" + abonneParams.telephonePortable.replaceAll("\\s","")
				} else {
					abonneParams.telephonePortable = abonneParams.telephonePortable.replaceAll("\\s","")
				}

			// Date de certificat médical
			if (abonneParams.dateCertificat != null) {
				if (abonneParams.dateCertificat instanceof String) {
					abonneParams.dateCertificat = new Date(abonneParams.dateCertificat)
				} else {
					String tempDate = abonneParams.dateCertificat.toString()
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					abonneParams.dateCertificat = sdf.parse(tempDate)
				}
			} else {
				listeLogs.add("ERROR : date de certificat vide !")
			}

			// Date naissance
			if (abonneParams.dateNaissance != null) {
				if (abonneParams.dateNaissance instanceof String) {
					abonneParams.dateNaissance = new Date(abonneParams.dateNaissance)
				} else {
					String tempDate = abonneParams.dateNaissance.toString()
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					abonneParams.dateNaissance = sdf.parse(tempDate)
				}
			} else {
				listeLogs.add("ERROR : date de naissance vide !")
			}

			// Allergie aspirine
			if (abonneParams.allergique?.toLowerCase() == "oui") {
				abonneParams.allergique = true
			} else {
				abonneParams.allergique = false
			}

			// Autorisation parentale
			if (abonneParams.autorisationParentale?.toLowerCase() == "ok") {
				abonneParams.autorisationParentale = true
			} else {
				abonneParams.autorisationParentale = false
			}

			// Comite directeur
			if (abonneParams.comiteDirecteur?.toLowerCase() == "cd" ||
			abonneParams.comiteDirecteur?.toLowerCase() == "bureau") {
				abonneParams.comiteDirecteur = true
			} else {
				abonneParams.comiteDirecteur = false
			}


			// Département naissance
			// TODO que faire si département vide ???
			if (abonneParams.departementNaissance == null) {
				listeLogs.add("ERROR : Département de naissance vide")
				//abonneParams.departementNaissance == "00"
			}	
			
			// Lieu de naissance
			if (abonneParams.lieuNaissance == null) {
				listeLogs.add("WARNING : Lieu de naissance vide")
			}

			// Sexe
			if (abonneParams.sexe == null || abonneParams.sexe == "M") {
				abonneParams.sexe = Sexe.MASCULIN
			} else {
				abonneParams.sexe = Sexe.FEMININ
			}

			// Type de membre
			if (abonneParams.typeMembre != null) {
				String typeMembre = abonneParams.typeMembre
				abonneParams.typeMembre = TypeMembre.findByNomIlike(typeMembre)
				if (!abonneParams.typeMembre) {
					listeLogs.add("Type de membre [" + typeMembre + "] non trouvé")
				}
				
			}

			// Niveau Scaphandre			
			if (abonneParams.niveau != null) {
				String niveau = abonneParams.niveau
				abonneParams.niveau = NiveauScaphandre.findByNiveauIlike(abonneParams.niveau)
				if (!abonneParams.niveau) {
					listeLogs.add("Niveau Scaphandre [" + niveau + "] non trouvé")					
				}
			}
			
			// Niveau Apnée
			if (abonneParams.niveauApnee != null) {
				String niveau = abonneParams.niveauApnee
				abonneParams.niveauApnee = NiveauApnee.findByNiveauIlike(abonneParams.niveauApnee)
				if (!abonneParams.niveauApnee) {
					listeLogs.add("Niveau Apnée [" + niveau + "] non trouvé")
				}
			}

			//TODO faire une meilleure gestion des erreurs
			def newAbonne = new Abonne(abonneParams)
			if (!newAbonne.save()) {
				listeLogs.add("Abonné non inséré (KO): ${newAbonne.errors}")	
				listeLogs.add("=====================")
			} else {
				listeLogs.add("Abonné inséré (OK)")
				listeLogs.add("=====================")
			}
			
			
		}		
		return listeLogs
		
    }
}
