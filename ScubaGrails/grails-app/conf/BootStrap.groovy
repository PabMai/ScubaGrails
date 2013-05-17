import grails.util.Environment;
import scubagrails.Abonne
import scubagrails.Ecole
import scubagrails.Enregistrement
import scubagrails.Niveau
import scubagrails.Saison
import scubagrails.TypeMembre
import scubagrails.User
import scubagrails.type.Sexe;
import scubagrails.utils.AbonneExcelImporter;

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
				role: "autre...")
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

				def typeMembrePalme = new TypeMembre(nom: "Palmes loisir")
				typeMembrePalme.save()
				if(typeMembrePalme.hasErrors()){
					println typeMembrePalme.errors
				} else {
					println "TypeMembre 'Palmes loisir' cree : OK !"
				}

				def typeMembreNormal = new TypeMembre(nom: "Scaphandre")
				typeMembreNormal.save()
				if(typeMembreNormal.hasErrors()){
					println typeMembreNormal.errors
				} else {
					println "TypeMembre 'Scaphandre' cree : OK !"
				}

				def typeMembreApnee = new TypeMembre(nom: "Apnée")
				typeMembreApnee.save()
				if(typeMembreApnee.hasErrors()){
					println typeMembreApnee.errors
				} else {
					println "TypeMembre 'Apnee' cree : OK !"
				}
				
				def typeMembrePiscine = new TypeMembre(nom: "Piscine")
				typeMembreApnee.save()
				if(typeMembreApnee.hasErrors()){
					println typeMembreApnee.errors
				} else {
					println "TypeMembre 'Piscine' cree : OK !"
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
				dateFin: Date.parse("dd/MM/yyyy", "31/08/2013"), 
				enCours: true)
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
				nomRue: "10, Avenue du Général de Gaulle",
				codePostal: "75100",
				ville : "Paris",				
				password: "test" )

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
				nomRue: "75 Rue de la Paix",
				codePostal: "44000",
				ville : "Nantes",				
				password: "test"
				)
				abonne2.setEcole(ecoleN1)
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
				sexe: Sexe.FEMININ,
				dateCertificat: Date.parse("dd/MM/yyyy", "12/08/2012"),
				telephoneFixe: "0155887744",
				telephonePortable: "0652321425",
				mail:"christine.dupont@gmail.com",
				numeroLicence: "55488",
				//belongsTo: adresse3,coordonnees3,ecoleMF1,typeMembreLicence, niveauBees1,
				prixAbonnement: 772.52,
				prixAssurance: 101,				
				nomRue: "12Bis Rue des Pèrdrie",
				codePostal: "49000",
				ville : "Angers",				
				password: "test" )

				abonne3.setEcole(ecoleInit)
				abonne3.setTypeMembre(typeMembrePalme)
				abonne3.setNiveau(niveauBees1)
				abonne3.save()
				if(abonne3.hasErrors()){
					println abonne3.errors
				} else {
					println "Abonné 'Christine Dupont' crée : OK !"
				}

				def abonne4 = new Abonne(nom:"Dujardin",
				prenom: "Jean",
				dateNaissance: Date.parse("dd/MM/yyyy", "14/01/1972"),
				departementNaissance: "44",
				lieuNaissance: "Nantes",
				sexe: Sexe.MASCULIN,
				dateCertificat: Date.parse("dd/MM/yyyy", "13/09/2012"),
				telephoneFixe: "0152658744",
				telephonePortable: "0652415263",
				mail:"j.dujardin@gmail.com",
				numeroLicence: "5548855",
				//belongsTo: adresse3,coordonnees3,ecoleMF1,typeMembreLicence, niveauBees1,
				prixAbonnement: 100.25,
				prixAssurance: 1,				
				nomRue: "11 Rue des Tomates",
				codePostal: "37000",
				ville : "Tours",				
				password: "test" )

				abonne4.setEcole(ecoleN1)
				abonne4.setTypeMembre(typeMembrePalme)
				abonne4.setNiveau(niveauBees1)
				abonne4.save()
				if(abonne4.hasErrors()){
					println abonne4.errors
				} else {
					println "Abonné 'Jean Dujardin' crée : OK !"
				}

				def abonne5 = new Abonne(nom:"Denis",
				prenom: "Corentin",
				dateNaissance: Date.parse("dd/MM/yyyy", "31/07/1986"),
				departementNaissance: "69",
				lieuNaissance: "Lyon",
				sexe: Sexe.MASCULIN,
				dateCertificat: Date.parse("dd/MM/yyyy", "14/01/2013"),
				telephoneFixe: "0240156354",
				telephonePortable: "0679854612",
				mail:"cocal@gmail.com",
				numeroLicence: "4448812",
				//belongsTo: adresse3,coordonnees3,ecoleMF1,typeMembreLicence, niveauBees1,
				prixAbonnement: 885,
				prixAssurance: 126.21,				
				nomRue: "7 Rue Etienne Mangenote",
				codePostal: "92000",
				ville : "Boulogne Billancourt",				
				password: "test" )

				abonne5.setEcole(ecoleN1)
				abonne5.setTypeMembre(typeMembreApnee)
				abonne5.setNiveau(niveauN1)
				abonne5.save()
				if(abonne5.hasErrors()){
					println abonne5.errors
				} else {
					println "Abonné 'Corentin Denis' crée : OK !"
				}

				def abonne6 = new Abonne(nom:"Partour",
				prenom: "Aurélie",
				dateNaissance: Date.parse("dd/MM/yyyy", "04/06/1987"),
				departementNaissance: "53",
				lieuNaissance: "Laval",
				sexe: Sexe.FEMININ,
				dateCertificat: Date.parse("dd/MM/yyyy", "17/02/2013"),
				telephoneFixe: "0240153698",
				telephonePortable: "0606858585",
				mail:"aure.partour@gmail.com",
				numeroLicence: "4589877",
				//belongsTo: adresse3,coordonnees3,ecoleMF1,typeMembreLicence, niveauBees1,
				prixAbonnement: 17.25,
				prixAssurance: 156,				
				nomRue: "25 Rue Haute Goulaine",
				codePostal: "44000",
				ville : "Nantes",				
				password: "test" )

				abonne6.setEcole(ecoleN1)
				abonne6.setTypeMembre(typeMembrePalme)
				abonne6.setNiveau(niveauBees1)
				abonne6.save()
				if(abonne6.hasErrors()){
					println abonne6.errors
				} else {
					println "Abonné 'Aurélie Partour' crée : OK !"
				}
				
				def abonne7 = new Abonne(nom:"Dus",
					prenom: "Jean-Clause",
					dateNaissance: Date.parse("dd/MM/yyyy", "01/05/1972"),
					departementNaissance: "49",
					lieuNaissance: "Saint Barthélémy d'Anjou",
					sexe: Sexe.MASCULIN,
					dateCertificat: Date.parse("dd/MM/yyyy", "02/05/2013"),
					telephoneFixe: "0240115236",
					telephonePortable: "0655887744",
					mail:"jc.dus@yahoo.fr",
					numeroLicence: "1213E111",
					//belongsTo: adresse3,coordonnees3,ecoleMF1,typeMembreLicence, niveauBees1,
					prixAbonnement: 175,
					prixAssurance: 10,					
					nomRue: "25 Rue des Champom",
					codePostal: "75000",
					ville : "Paris",
					password: "test" )
	
					abonne7.setEcole(ecoleN1)
					abonne7.setTypeMembre(typeMembrePalme)
					abonne7.setNiveau(niveauBees1)
					abonne7.save()
					if(abonne7.hasErrors()){
						println abonne7.errors
					} else {
						println "Abonné 'Jean-Clause Dus' crée : OK !"
					}
					
					def abonne8 = new Abonne(nom:"Zidane",
						prenom: "Zinedine",
						dateNaissance: Date.parse("dd/MM/yyyy", "01/01/1965"),
						departementNaissance: "13",
						lieuNaissance: "Marseille",
						sexe: Sexe.MASCULIN,
						dateCertificat: Date.parse("dd/MM/yyyy", "01/04/2013"),
						telephoneFixe: "",
						telephonePortable: "0632887744",
						mail:"zizou@realmadrid.fr",
						numeroLicence: "451478",						
						prixAbonnement: 155,
						prixAssurance: 55,						
						nomRue: "17 Rue du trois zéro",
						codePostal: "49124",
						ville : "Saint Barthélémy d'Anjou",
						password: "test" )
		
						abonne8.setEcole(ecoleN2)
						abonne8.setTypeMembre(typeMembrePalme)
						abonne8.setNiveau(niveauN1)
						abonne8.save()
						if(abonne8.hasErrors()){
							println abonne8.errors
						} else {
							println "Abonné 'Zinedine Zidane' crée : OK !"
						}
						
						def abonne9 = new Abonne(nom:"Chirac",
							prenom: "Jacques",
							dateNaissance: Date.parse("dd/MM/yyyy", "01/10/1932"),
							departementNaissance: "75",
							lieuNaissance: "Paris",
							sexe: Sexe.MASCULIN,
							dateCertificat: Date.parse("dd/MM/yyyy", "01/01/2013"),
							telephoneFixe: "",
							telephonePortable: "0632887744",
							mail:"jacqueChirac@france.fr",
							numeroLicence: "45147814",
							prixAbonnement: 100,
							prixAssurance: 10,							
							nomRue: "11Bis, Rue de la présidence",
							codePostal: "53000",
							ville : "Laval",
							password: "test" )
			
							abonne9.setEcole(ecoleMF1)
							abonne9.setTypeMembre(typeMembreNormal)
							abonne9.setNiveau(null)
							abonne9.save()
							if(abonne9.hasErrors()){
								println abonne9.errors
							} else {
								println "Abonné 'Jacques Chirac' crée : OK !"
							}
							
							def abonne10 = new Abonne(nom:"Obama",
								prenom: "Barack",
								dateNaissance: Date.parse("dd/MM/yyyy", "17/02/1961"),
								departementNaissance: "75",
								lieuNaissance: "Paris",
								sexe: Sexe.MASCULIN,
								dateCertificat: Date.parse("dd/MM/yyyy", "11/12/2012"),
								telephoneFixe: "0255996633",
								telephonePortable: "0622558844",
								mail:"baraque@usa.com",
								numeroLicence: "54874",
								prixAbonnement: 185,
								prixAssurance: 52.25,								
								nomRue: "85 ter Avenue des champs élysées",
								codePostal: "75100",
								ville : "Paris",
								password: "test" )
				
								abonne10.setEcole(ecoleN2)
								abonne10.setTypeMembre(typeMembrePalme)
								abonne10.setNiveau(niveauN1)
								abonne10.save()
								if(abonne10.hasErrors()){
									println abonne10.errors
								} else {
									println "Abonné 'Barack Obama' crée : OK !"
								}
								
								def abonne11 = new Abonne(nom:"De Funès",
									prenom: "Louis",
									dateNaissance: Date.parse("dd/MM/yyyy", "12/12/1950"),
									departementNaissance: "44",
									lieuNaissance: "Nantes",
									sexe: Sexe.MASCULIN,
									dateCertificat: Date.parse("dd/MM/yyyy", "11/11/2012"),
									telephoneFixe: "0155886699",
									telephonePortable: "0744115588",
									mail:"louisDeFufu@free.fr",
									numeroLicence: "2121548",
									prixAbonnement: 785.65,
									prixAssurance: 17.34,									
									nomRue: "65 Boulevard des miserables",
									codePostal: "85000",
									ville : "La Roche sur Yon",
									password: "test" )
					
									abonne11.setEcole(ecoleN2)
									abonne11.setTypeMembre(typeMembrePalme)
									abonne11.setNiveau(niveauN1)
									abonne11.save()
									if(abonne11.hasErrors()){
										println abonne11.errors
									} else {
										println "Abonné 'Louis De Funès' crée : OK !"
									}

			/**
			 * ===============================================
			 * 				CREATION ENREGISTREMENTS
			 * ===============================================
			 */
				
				def enreg1 = new Enregistrement(abonne: abonne1, saison: saison20102011)
				enreg1.save()
				if(enreg1.hasErrors()){
					println enreg1.errors
				} else {
					println "Enregistrement de Guillaume Problo pour la saison 2010-2011"
				}
				
				def enreg2 = new Enregistrement(abonne: abonne1, saison: saison20112012)
				enreg2.save()
				if(enreg2.hasErrors()){
					println enreg2.errors
				} else {
					println "Enregistrement de Guillaume Problo pour la saison 2011-2012"
				}
				
				def enreg3 = new Enregistrement(abonne: abonne2, saison: saison20112012)
				enreg3.save()
				if(enreg3.hasErrors()){
					println enreg3.errors
				} else {
					println "Enregistrement de Marc Duroux pour la saison 2011-2012"
				}
				
				def enreg4 = new Enregistrement(abonne: abonne2, saison: saison20122013)
				enreg4.save()
				if(enreg4.hasErrors()){
					println enreg4.errors
				} else {
					println "Enregistrement de Marc Duroux pour la saison 2012-2013"
				}
				
				def enreg5 = new Enregistrement(abonne: abonne6, saison: saison20102011)
				enreg5.save()
				if(enreg5.hasErrors()){
					println enreg5.errors
				} else {
					println "Enregistrement de Aurélie Partour pour la saison 2010-2011"
				}
				
				def enreg6 = new Enregistrement(abonne: abonne7, saison: saison20122013)
				enreg6.save()
				if(enreg6.hasErrors()){
					println enreg6.errors
				} else {
					println "Enregistrement de Jean-Clause Dus pour la saison 2012-2013"
				}
				
				def enreg7 = new Enregistrement(abonne: abonne8, saison: saison20122013)
				enreg7.save()
				if(enreg7.hasErrors()){
					println enreg7.errors
				} else {
					println "Enregistrement de Zinédine Zidane pour la saison 2012-2013"
				}
				
				def enreg8 = new Enregistrement(abonne: abonne9, saison: saison20122013)
				enreg8.save()
				if(enreg8.hasErrors()){
					println enreg8.errors
				} else {
					println "Enregistrement de Jacques Chirac pour la saison 2012-2013"
				}
				
				def enreg9 = new Enregistrement(abonne: abonne3, saison: saison20122013)
				enreg9.save()
				if(enreg9.hasErrors()){
					println enreg9.errors
				} else {
					println "Enregistrement de Christine Dupont pour la saison 2012-2013"
				}

				println """
				=====================================
					  DUMP développement - stop
				====================================== """
				
				println """
				=====================================
					  IMPORT EXCEL - start
				===================================== """
				

				
				println """
				=====================================
					  IMPORT EXCEL - stop
				===================================== """
				
				

				break
			/* Environnement de production */
			case Environment.PRODUCTION :
				break
		}


	}
	def destroy = {
	}
}
