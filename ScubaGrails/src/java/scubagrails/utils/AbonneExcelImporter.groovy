package scubagrails.utils

import org.grails.plugins.excelimport.AbstractExcelImporter
import org.grails.plugins.excelimport.*

class AbonneExcelImporter extends AbstractExcelImporter {
	
	static Map CONFIG_ABONNE_COLUMN_MAP = [
          //sheet:'ListingSecrétariat', 
		  //sheet:'Feuil1',
		  startRow: 1,
          columnMap:  [
			      'A':'numeroLicence',
                  'B':'nom',
                  'C':'prenom',
				  'D':'niveau',
				  'E':'ecole',
				  'G':'niveauApnee',
				  'H':'ecoleApnee',
				  'J':'typeMembre',
				  'O':'nomRue',
				  'N':'comiteDirecteur',
				  'P':'codePostal',
				  'Q':'ville',
				  'R':'telephoneFixe',
				  'S':'telephonePortable',
				  'T':'mail',
				  'U':'allergique',
				  'V':'dateCertificat',
				  'X':'dateNaissance',
				  'AA':'autorisationParentale',
				  'AB':'sexe',
				  'AF':'prixAbonnement',
				  'AI':'prixAssurance'				                    
          ]
  ]
	
	// Validation du contenu du fichier excel
	static Map propertyConfigurationMap = [	
				  numeroLicence:([expectedType: ExpectedPropertyType.StringType, defaultValue:null]),
				  nom:([expectedType: ExpectedPropertyType.StringType, defaultValue:null]),
				  prenom:([expectedType: ExpectedPropertyType.StringType, defaultValue:null]),
				  niveau:([expectedType: ExpectedPropertyType.StringType, defaultValue:null]),
				  ecole:([expectedType: ExpectedPropertyType.StringType, defaultValue:null]),
				  niveauApnee:([expectedType: ExpectedPropertyType.StringType, defaultValue:null]),
				  ecoleApnee:([expectedType: ExpectedPropertyType.StringType, defaultValue:null]),
				  typeMembre:([expectedType: ExpectedPropertyType.StringType, defaultValue:null]),
				  nomRue:([expectedType: ExpectedPropertyType.StringType, defaultValue:null]),
				  comiteDirecteur:([expectedType: ExpectedPropertyType.StringType, defaultValue:null]),
				  codePostal:([expectedType: ExpectedPropertyType.StringType, defaultValue:'00000']),
				  ville:([expectedType: ExpectedPropertyType.StringType, defaultValue:null]),
				  telephoneFixe:([expectedType: ExpectedPropertyType.StringType, defaultValue:null]),
				  telephonePortable:([expectedType: ExpectedPropertyType.StringType, defaultValue:null]),
				  mail:([expectedType: ExpectedPropertyType.StringType, defaultValue:null]),
				  allergique:([expectedType: ExpectedPropertyType.StringType, defaultValue:null]),
				  dateCertificat:([expectedType: ExpectedPropertyType.DateType, defaultValue:null]),
				  dateNaissance:([expectedType: ExpectedPropertyType.DateType, defaultValue:null]),
				  autorisationParentale:([expectedType: ExpectedPropertyType.StringType, defaultValue:null]),
				  sexe:([expectedType: ExpectedPropertyType.StringType, defaultValue:null]),
				  prixAbonnement:([expectedType: ExpectedPropertyType.DoubleType, defaultValue:0]),
				  prixAssurance:([expectedType: ExpectedPropertyType.DoubleType, defaultValue:0])
	]
	
	private String nomFeuille
	
	//can also configure injection in resources.groovy
	def getExcelImportService() {
		ExcelImportService.getService()
	}
	
	public AbonneExcelImporter(fileName, nomFeuille) {
		super(fileName)
		this.nomFeuille = nomFeuille		
	  }
	
	List<Map> getAbonnes() {
		List abonneList = excelImportService.columns(workbook, CONFIG_ABONNE_COLUMN_MAP + [sheet:nomFeuille], null, propertyConfigurationMap)
		
		
		//List abonneList = excelImportService.columns(workbook, CONFIG_ABONNE_COLUMN_MAP)
	  }

}
