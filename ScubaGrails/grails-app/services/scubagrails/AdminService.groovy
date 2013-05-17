package scubagrails

import java.text.SimpleDateFormat

import scubagrails.type.Sexe;

class AdminService {

    def traiterImportAbonne(List abonnesMapList ) {
		
		abonnesMapList.each { Map abonneParams ->

			if (abonneParams.telephoneFixe != null) {
				if (abonneParams.telephoneFixe.substring(0, 1) != "0") {
					abonneParams.telephoneFixe = "0" + abonneParams.telephoneFixe.replaceAll("\\s","")
				} else {
					abonneParams.telephoneFixe = abonneParams.telephoneFixe.replaceAll("\\s","")
				}
			}

			if (abonneParams.telephonePortable != null)
				if (abonneParams.telephonePortable.substring(0, 1) != "0") {
					abonneParams.telephonePortable = "0" + abonneParams.telephonePortable.replaceAll("\\s","")
				} else {
					abonneParams.telephonePortable = abonneParams.telephonePortable.replaceAll("\\s","")
				}

			// date de certificat médical
			if (abonneParams.dateCertificat instanceof String) {
				abonneParams.dateCertificat = new Date(abonneParams.dateCertificat)
			} else {
				String tempDate = abonneParams.dateCertificat.toString()
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				abonneParams.dateCertificat = sdf.parse(tempDate)
			}

			// date naissance
			if (abonneParams.dateNaissance instanceof String) {
				abonneParams.dateNaissance = new Date(abonneParams.dateNaissance)
			} else {
				String tempDate = abonneParams.dateNaissance.toString()
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				abonneParams.dateNaissance = sdf.parse(tempDate)
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
			//		if (abonneParams.departementNaissance == null) {
			//			abonneParams.departementNaissance == "00"
			//		}			

			// Sexe
			if (abonneParams.sexe == null || abonneParams.sexe == "M") {
				abonneParams.sexe = Sexe.MASCULIN
			} else {
				abonneParams.sexe = Sexe.FEMININ
			}

			// Type de membre
			if (abonneParams.typeMembre != null) {
				switch (abonneParams.typeMembre) {
					case "Scaphandre":
					abonneParams.typeMembre = TypeMembre.findByNomIlike("Scaphandre")
					break;
					case "Apnée" :
					abonneParams.typeMembre = TypeMembre.findByNomIlike("Apnée")
					break;
					case "Palmes loisir" :
					abonneParams.typeMembre = TypeMembre.findByNomIlike("Palmes loisir")
					break;
					case "Piscine" :
					abonneParams.typeMembre = TypeMembre.findByNomIlike("Piscine")
					break;
					default:
					abonneParams.typeMembre = null
					break;
				}
			}

			// Niveau
			// TODO a faire


			def newAbonne = new Abonne(abonneParams)
			if (!newAbonne.save()) {
				println "Abonne not saved, errors = ${newAbonne.errors}"
			} else {
				println "OK"
			}
		}
		
    }
}
