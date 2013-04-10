import grails.util.Environment;
import scubagrails.Abonne
import scubagrails.Ecole
import scubagrails.Niveau
import scubagrails.Saison
import scubagrails.TypeMembre
import scubagrails.User
import scubagrails.type.Sexe;

class BootStrap {

	def init = { servletContext ->

		switch(Environment.getCurrent()){
			/* Environnement de développement */
			case Environment.DEVELOPMENT:
			
			println """ 
				=====================================
					  DUMP développement - start
				====================================== """
			
			/**
			 * ===============================================
			 * 				CREATION UTILISATEURS
			 * ===============================================
			 */

			// Création d'un Admin
				def agnes = new User(login:"agnes",
				password:"agnes",
				email:"agnes@test.com",
				nom: "tri",
				prenom: "agnes",
				role: "administrateur")
				agnes.save()
				if(agnes.hasErrors()){
					println agnes.errors
				} else {
					println "Utilisateur 'Agnes' cree : OK !"
				}

			// Création d'un User
				def moi = new User(login:"moimoi",
				password:"moimoi",
				email:"moi@test.com",
				nom: "moiNom",
				prenom: "moiPrenom",
				role: "utilisateur")
				moi.save()
				if(moi.hasErrors()){
					println moi.errors
				} else {
					println "Utilisateur 'Moi' cree : OK !"
				}

			/**
			 * ===============================================
			 * 				CREATION ECOLES
			 * ===============================================
			 */

				def ecoleInit = new Ecole(nom: "Initiation")
				ecoleInit.save()
				if(ecoleInit.hasErrors()){
					println ecoleInit.errors
				} else {
					println "Ecole 'Initiation' cree : OK !"
				}

				def ecoleN1 = new Ecole(nom: "N1")
				ecoleN1.save()
				if(ecoleN1.hasErrors()){
					println ecoleN1.errors
				} else {
					println "Ecole 'N1' cree : OK !"
				}

				def ecoleN2 = new Ecole(nom: "N2")
				ecoleN2.save()
				if(ecoleN2.hasErrors()){
					println ecoleN2.errors
				} else {
					println "Ecole 'N2' cree : OK !"
				}

				def ecoleMF1 = new Ecole(nom: "MF1")
				ecoleMF1.save()
				if(ecoleMF1.hasErrors()){
					println ecoleMF1.errors
				} else {
					println "Ecole 'MF1' cree : OK !"
				}

			/**
			 * ===============================================
			 * 				CREATION TYPE MEMBRE
			 * ===============================================
			 */

				def typeMembreLicence = new TypeMembre(nom: "Licence seule")
				typeMembreLicence.save()
				if(typeMembreLicence.hasErrors()){
					println typeMembreLicence.errors
				} else {
					println "TypeMembre 'Licence seule' cree : OK !"
				}

				def typeMembreNormal = new TypeMembre(nom: "Normal")
				typeMembreNormal.save()
				if(typeMembreNormal.hasErrors()){
					println typeMembreNormal.errors
				} else {
					println "TypeMembre 'Normal' cree : OK !"
				}

				def typeMembreApnee = new TypeMembre(nom: "Apnée")
				typeMembreApnee.save()
				if(typeMembreApnee.hasErrors()){
					println typeMembreApnee.errors
				} else {
					println "TypeMembre 'Apnee' cree : OK !"
				}
			/**
			 * ===============================================
			 * 				CREATION SAISONS
			 * ===============================================
			 */

				def saison20102011 = new Saison(libelle: "2010-2011",
				dateDebut: Date.parse("dd/MM/yyyy", "01/09/2010"),
				dateFin: Date.parse("dd/MM/yyyy", "31/08/2011"))
				saison20102011.save()
				if(saison20102011.hasErrors()){
					println saison20102011.errors
				} else {
					println "Saison 2010-2011 cree : OK !"
				}

				def saison20112012 = new Saison(libelle: "2011-2012",
				dateDebut: Date.parse("dd/MM/yyyy", "01/09/2011"),
				dateFin: Date.parse("dd/MM/yyyy", "31/08/2012"))
				saison20112012.save()
				if(saison20112012.hasErrors()){
					println saison20112012.errors
				} else {
					println "Saison 2011-2012 cree : OK !"
				}

				def saison20122013 = new Saison(libelle: "2012-2013",
				dateDebut: Date.parse("dd/MM/yyyy", "01/09/2012"),
				dateFin: Date.parse("dd/MM/yyyy", "31/08/2013"))
				saison20122013.save()
				if(saison20122013.hasErrors()){
					println saison20122013.errors
				} else {
					println "Saison 2012-2013 cree : OK !"
				}

			/**
			 * ===============================================
			 * 				CREATION NIVEAU
			 * ===============================================
			 */
				def niveauN1 = new Niveau(niveau: "N1")
				niveauN1.save()
				if(niveauN1.hasErrors()){
					println niveauN1.errors
				} else {
					println "Niveau 'N1' cree : OK !"
				}

				def niveauN2 = new Niveau(niveau: "N2")
				niveauN2.save()
				if(niveauN2.hasErrors()){
					println niveauN2.errors
				} else {
					println "Niveau 'N2' cree : OK !"
				}

				def niveauN3 = new Niveau(niveau: "N3")
				niveauN3.save()
				if(niveauN3.hasErrors()){
					println niveauN3.errors
				} else {
					println "Niveau 'N3' cree : OK !"
				}

				def niveauBees1 = new Niveau(niveau: "BEES1")
				niveauBees1.save()
				if(niveauBees1.hasErrors()){
					println niveauBees1.errors
				} else {
					println "Niveau 'BEES1' cree : OK !"
				}


			/**
			 * ===============================================
			 * 				CREATION ABONNES
			 * ===============================================
			 */

				def abonne1 = new Abonne(nom:"Problo",
				prenom: "Guillaume",
				dateNaissance: Date.parse("dd/MM/yyyy", "14/12/1988"),
				departementNaissance: "49",
				lieuNaissance: "Angers",
				sexe: Sexe.MASCULIN,
				dateCertificat: Date.parse("dd/MM/yyyy", "15/07/2012"),
				telephoneFixe: "0241552233",
				telephonePortable: "0674885566",  
				mail:"guillaume.problo@gmail.com",
				numeroLicence: "3556484",				
				prixAbonnement: 450,
				prixAssurance: 54.24,
				secouriste: true,
				numeroRue: "10",
				nomRue: "Alcide de Gaspéri",
				codePostal: "49240",
				ville : "Avrillé"  )
							
				abonne1.setEcole(ecoleN1)
				abonne1.setTypeMembre(typeMembreApnee)
				abonne1.setNiveau(niveauN1)
				abonne1.save()
				if(abonne1.hasErrors()){
					println abonne1.errors
				} else {
					println "Abonne 'Guillaume Problo' cree : OK !"
				}

				def abonne2 = new Abonne(nom:"Duroux",
				prenom: "Marc",
				dateNaissance: Date.parse("dd/MM/yyyy", "17/01/1964"),
				departementNaissance: "53",
				lieuNaissance: "Laval",
				sexe: Sexe.MASCULIN,
				dateCertificat: Date.parse("dd/MM/yyyy", "16/12/2012"),
				numeroLicence: "111284",
				telephoneFixe: "",
				telephonePortable: "0755889966",
				mail:"marc.duroux@gmail.com",
				prixAbonnement: 122,
				prixAssurance: 17.66,				
				secouriste: true,
				encadrant: true,
				allergique: true,
				numeroRue: "75",
				nomRue: "Rue de la Paix",
				codePostal: "44000",
				ville : "Nantes"
				)							
				abonne2.setEcole(ecoleN2)
				abonne2.setTypeMembre(typeMembreNormal)
				abonne2.setNiveau(niveauN2)
				abonne2.save()
				if(abonne2.hasErrors()){
					println abonne2.errors
				} else {
					println "Abonne 'Marc Duroux' cree : OK !"
				}

				def abonne3 = new Abonne(nom:"Dupont",
				prenom: "Christine",
				dateNaissance: Date.parse("dd/MM/yyyy", "19/05/1975"),
				departementNaissance: "44",
				lieuNaissance: "Nantes",
				sexe: Sexe.FEMINIM,
				dateCertificat: Date.parse("dd/MM/yyyy", "12/08/2012"),
				telephoneFixe: "0155887744",
				telephonePortable: "0652321425",
				mail:"christine.dupont@gmail.com",
				numeroLicence: "55488",				
				//belongsTo: adresse3,coordonnees3,ecoleMF1,typeMembreLicence, niveauBees1,
				prixAbonnement: 772.52,
				prixAssurance: 101, 
				numeroRue: "12Bis",
				nomRue: "Rue des Pèrdrie",
				codePostal: "49000",
				ville : "Angers" )
				
				abonne3.setEcole(ecoleMF1)
				abonne3.setTypeMembre(typeMembreLicence)
				abonne3.setNiveau(niveauBees1)
				abonne3.save()
				if(abonne3.hasErrors()){
					println abonne3.errors
				} else {
					println "Abonné 'Christine Dupont' crée : OK !"
				}
				
				println """
				=====================================
					  DUMP développement - stop
				====================================== """

				break
			/* Environnement de production */
			case Environment.PRODUCTION :
				break
		}


	}
	def destroy = {
	}
}
