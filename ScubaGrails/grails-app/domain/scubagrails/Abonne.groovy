package scubagrails

import java.sql.Blob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.grails.datastore.gorm.finders.MethodExpression.InList;

import scubagrails.type.Sexe;

/**
 * Domain class représentant un abonné
 * @author Guillaume MONJAL
 *
 */
class Abonne {
	
	// Pour le plugin searchable
	static searchable = true
	
	String nom
	String prenom
	String login
	String password
	Date dateNaissance
	Sexe sexe
	String telephoneFixe
	String telephonePortable
	String mail
	
	String nomRue
	String codePostal
	String ville
	
	EcoleScaphandre ecole	
	EcoleApnee ecoleApnee
	TypeMembre typeMembre
	NiveauScaphandre niveau
	NiveauApnee niveauApnee
	EncadrantApnee encadrantApnee
	EncadrantScaphandre encadrantScaphandre
	
	// Un abonné peut être enregistré dans une ou plusieurs saisons
	static hasMany = [enregistrements:Enregistrement]
	
	// Afin de supprimer les enregistrements liés à un abonné
	// si celui-ci est supprimé
	static mapping = {
		enregistrements cascade: 'all-delete-orphan'
	}

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
	
	byte[] avatar
	String mimeType
	
	int nbJourPerimeCM	

    static constraints = {		
		login(blank:true, nullable:true)		
		password(blank:true,nullable:true, password:true, minSize:4)
		numeroLicence(blank:false, maxSize: 20, unique:true)
		nom(blank:false, maxSize:50, unique: ['prenom', 'dateNaissance'])
		prenom(blank:false, maxSize:50)
		// validator --> custom validator :
		// ici : empeche la saisie d'une date dans le futur
		// "it" représente la startDate saisie par l'utilisateur
		// validator est évalué à chaque fois qu'il est invoqué !
		dateNaissance(validator: {return (it < new Date())})	
		sexe(nullable : false)		

		nomRue(blank:false,maxSize:100)
		codePostal(blank:false, size:5..5, matches: "[0-9]+")
		ville(blank:false,maxSize:50)
		
		telephoneFixe(blank:true,nullable:true,  maxSize: 10, matches: "0[1-9]{1}(([0-9]{2}){4})|((\\s[0-9]{2}){4})|((-[0-9]{2}){4})"
			, validator : {val, obj -> return (!val?.isEmpty() || !obj.telephonePortable?.isEmpty())})
		telephonePortable(blank:true,nullable:true, maxSize: 10,  matches: "0[6-7]{1}(([0-9]{2}){4})|((\\s[0-9]{2}){4})|((-[0-9]{2}){4})"
			, validator : {val, obj -> return (!val?.isEmpty() || !obj.telephoneFixe?.isEmpty())})
		mail(blank:false, email:true, nullable:true)
		
		//TODO a remettre
		// la date du certificat inséré ne doit pas être inférieur de 1 an
		// a partir de la date d'insertion
//		dateCertificat(validator: {
//			Date now  = new Date()
//			Date anneePrecedente = new Date() - 365
//			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy")			
//			if ((it < anneePrecedente) || (it > now)) {				
//				return ['abonne.dateCertificat.validator.invalid', format.format(anneePrecedente), format.format(now)]
//			} else {
//				return true
//			}	
//			//return ((it > (new Date() - 365)) && (it < new Date()))
//		})
		ecole(blank:true, nullable:true)
		ecoleApnee(blank:true, nullable:true)
		typeMembre(blank:true,nullable:true) 
		niveau(blank:true, nullable:true)
		niveauApnee(blank:true, nullable:true)
		encadrantScaphandre(blank:true, nullable:true)
		encadrantApnee(blank:true, nullable:true)
		encadrant(blank:true)
		technicien(blank:true)
		secouriste(blank:true)
		allergique(blank:true)
		comiteDirecteur(blank:true)
		aideFamille(blank:true)
		autorisationParentale(blank:true)
		prixAbonnement(min: Double.valueOf("0"), max: Double.valueOf("1000"), blank:false, scale: 2)
		prixAssurance(min: Double.valueOf("0"), max: Double.valueOf("1000"), blank:false, scale: 2)
		avatar(nullable:true, maxSize: 524288) /* 512 Ko */
		mimeType(nullable:true)
    }
	
	def beforeInsert = {
		password = password.encodeAsMD5()
		login = prenom.toLowerCase().substring(0, 1) + "." + nom.toLowerCase()
	}
	
//	def beforeUpdate = {		
//		password = password.encodeAsMD5()
//	}
	
	@Override
	public String toString() {
		prenom + " " + nom + " (" + dateNaissance.format("dd/MM/yyyy") + ")"
	}
	
	public String toMail() {
		mail
	}
	
	// pour éviter de persister age (getAge)
	static transients = ['age','nbJourPerimeCM']
	
	public getAge(){
		def now = new GregorianCalendar()
		
		Integer birthMonth = dateNaissance.getAt(Calendar.MONTH) + 1
		Integer birthYear = dateNaissance.getAt(Calendar.YEAR)
		Integer birthDate = dateNaissance.getAt(Calendar.DATE)
		Integer yearNow = now.get(Calendar.YEAR)
		
		def offset = new GregorianCalendar(
				   yearNow,
				   birthMonth-1,
				   birthDate)
		return (yearNow - birthYear - (offset > now ? 1 : 0))
	}
}
