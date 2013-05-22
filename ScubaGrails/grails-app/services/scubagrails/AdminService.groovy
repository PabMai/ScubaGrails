package scubagrails

import java.text.SimpleDateFormat

import scubagrails.type.Sexe;

class AdminService {

    List<String> traiterImportAbonne(List abonnesMapList ) {
		List<String> listeLogs = new ArrayList<String>()
		abonnesMapList.each { Map abonneParams ->

			listeLogs.add("=====================")
			listeLogs.add("Traitement de l'abonné ${abonneParams?.prenom} ${abonneParams?.nom} en cours")
			
			// Formatage du nom
			if (abonneParams.nom != null) {
				String nomMinuscule = abonneParams.nom.toLowerCase()
				//Majuscule sur la première lettre
				String nomFormat = nomMinuscule.substring(0, 1).toUpperCase() + nomMinuscule.substring(1, nomMinuscule.length())
				abonneParams.nom = nomFormat
			}				
			
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
				listeLogs.add("WARNING : date de certificat vide ! On force le 09/09/1999")
				String tempDate = "1999-09-09"
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				abonneParams.dateCertificat = sdf.parse(tempDate)
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
				listeLogs.add("WARNING : date de naissance vide ! On force 01/01/1800")
				String tempDate = "1800-01-01"
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
			
			// Ecole Scaphandre
			if (abonneParams.ecole != null) {
				String ecole = abonneParams.ecole
				abonneParams.ecole = EcoleScaphandre.findByNomIlike(abonneParams.ecole)
				if (!abonneParams.ecole) {
					listeLogs.add("Ecole Scaphandre [" + ecole + "] non trouvé")
				}
			}
			
			// Ecole Apnée
			if (abonneParams.ecoleApnee != null) {
				String ecole = abonneParams.ecoleApnee
				abonneParams.ecoleApnee = EcoleApnee.findByNomIlike(abonneParams.ecoleApnee)
				if (!abonneParams.ecoleApnee) {
					listeLogs.add("Ecole Apnée [" + ecole + "] non trouvé")
				}
			}	
			
			// TODO GMO : gérer les encadrants
			
			// Création du mot de passe (dateDeNaissance ddMMyyyy)
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy")
			abonneParams.password = sdf.format(abonneParams.dateNaissance)			
			
			// Création de l'abonné
			def newAbonne = new Abonne(abonneParams)
			if (!newAbonne.save(flush: true)) {
				listeLogs.add("Abonné non inséré (KO): ${newAbonne.errors}")	
				listeLogs.add("=====================")
			} else {
				listeLogs.add("Abonné inséré (OK)")				
				// La création de l'abonné est effective, on lui ajoute un 
				// enregistrement pour la saison 2012/2013
				// Prix abonnement
				// Si un abonnement a été payé, alors cela veut dire
				// que l'abonné est valide pour la saison 2012/2013
				if (newAbonne.prixAbonnement != null &&
					newAbonne.prixAbonnement.compareTo(Double.valueOf(0)) > 0) {
					Saison saison20122013 = Saison.findByLibelleLike("2012-2013");
					Enregistrement enreg = new Enregistrement(abonne: newAbonne,saison: saison20122013)
					if (!enreg.save(flush: true)) {
						listeLogs.add("Enregistrement saison 2012/2013 KO : ${enreg.errors}")						
						listeLogs.add("=====================")
					} else {
						listeLogs.add("Enregistrement saison 2012/2013 OK")
						listeLogs.add("=====================")
					}
				}
			}			
			
		}		
		return listeLogs
		
    }
}
