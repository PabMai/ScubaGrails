package scubagrails

import java.sql.Blob;

import org.grails.datastore.gorm.finders.MethodExpression.InList;

import scubagrails.type.Sexe;

/**
 * Domain class représentant un abonné
 * @author Guillaume MONJAL
 *
 */
class Abonne {
	
	String nom
	String prenom
	Date dateNaissance
	String departementNaissance
	String lieuNaissance
	Sexe sexe
	String telephoneFixe
	String telephonePortable
	String mail
	
	String nomRue
	String numeroRue
	String codePostal
	String ville
	
	Ecole ecole	
	TypeMembre typeMembre
	Niveau niveau
	
	// Un abonné peut être enregistré dans une ou plusieurs saisons
	static hasMany = [enregistrements:Enregistrement]

	Date dateCertificat
	String numeroLicence
	
	boolean encadrant
	boolean technicien
	boolean secouriste
	boolean allergique
	boolean comiteDirecteur
	boolean aideFamille
	boolean autorisationParentale
	
	Double prixAbonnement
	Double prixAssurance
	
	//TODO Blob photo
	
	// filename ?
	// mimeType ?
	// last update date (photo) ?
	

    static constraints = {
		numeroLicence(blank:false, maxSize: 20, unique:true)
		nom(blank:false, maxSize:50)
		prenom(blank:false, maxSize:50)
		// validator --> custom validator :
		// ici : empeche la saisie d'une date dans le futur
		// "it" représente la startDate saisie par l'utilisateur
		// validator est évalué à chaque fois qu'il est invoqué !
		dateNaissance(validator: {return (it < new Date())})
		
		departementNaissance(blank:false, maxSize:30)
		lieuNaissance(blank:false, maxSize:50)
		sexe(nullable : false)
		
		numeroRue(blank:false, maxSize:8)
		nomRue(blank:false,maxSize:100)
		codePostal(blank:false, size:5..5)
		ville(blank:false,maxSize:50)
		
		telephoneFixe(blank:true, maxSize: 10, matches: "0[1-7]{1}(([0-9]{2}){4})|((\\s[0-9]{2}){4})|((-[0-9]{2}){4})"
			, validator : {val, obj -> return (!val.isEmpty() || !obj.telephonePortable.isEmpty())})
		telephonePortable(blank:true, maxSize: 10,  matches: "0[1-7]{1}(([0-9]{2}){4})|((\\s[0-9]{2}){4})|((-[0-9]{2}){4})"
			, validator : {val, obj -> return (!val.isEmpty() || !obj.telephoneFixe.isEmpty())})
		mail(blank:false, email:true)
		
		
		// la date du certificat inséré ne doit pas être inférieur de 1 an
		// a partir de la date d'insertion
		dateCertificat(validator: {return (it > (new Date() - 365))})
		ecole(blank:true, nullable:true)
		typeMembre(blank:true,nullable:true) 
		niveau(black:true, nullable:true)
		encadrant(blank:true)
		technicien(blank:true)
		secouriste(blank:true)
		allergique(blank:true)
		comiteDirecteur(blank:true)
		aideFamille(blank:true)
		autorisationParentale(blank:true)
		prixAbonnement(min: Double.valueOf("0"), max: Double.valueOf("1000"), blank:false)
		prixAssurance(min: Double.valueOf("0"), max: Double.valueOf("1000"), blank:false)
    }
	
	@Override
	public String toString() {
		prenom + " " + nom + " (" + dateNaissance.format("dd/MM/yyyy") + ")"
	}
}
