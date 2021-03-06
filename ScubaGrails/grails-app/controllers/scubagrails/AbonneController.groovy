package scubagrails

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javassist.bytecode.stackmap.BasicBlock.Catch;

import org.springframework.dao.DataIntegrityViolationException

import com.sun.org.apache.bcel.internal.generic.RETURN;

import scubagrails.utils.PaginateableList;
//import uk.co.desirableobjects.sendgrid.SendGridService


class AbonneController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	AbonneService abonneService
	def exportService
	def grailsApplication
	def filterPaneService
	
	def index() {
		redirect(action: "list", params: params)
	}

	def list(Integer max) {
		
		// Par défaut, on tri par Nom
		if (!params?.sort) {
			params.sort="nom"
		}
		generationExport(params, Abonne.list(params))
		
		params.max = Math.min(max ?: 10, 100)
		[abonneInstanceList: Abonne.list(params), 
			abonneInstanceTotal: Abonne.count(),
			filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params)]
	}

	def create() {
		List<Saison> listeDesSaisons = Saison.list()
		[abonneInstance: new Abonne(params), listeDesSaisons:listeDesSaisons]
	}

	def save() {		
		def abonneInstance = new Abonne(params)	
		if (!abonneInstance.save(flush: true)) {			
			List<Saison> listeDesSaisons = Saison.list()
			render(view: "create", model: [abonneInstance: abonneInstance, listeDesSaisons:listeDesSaisons])
			return
		}
		
		// Si on doit gérer la liste des enregistrements (admin only)
		if (params.containsKey("lesSaisonsSelection")) {
			//ici on est dans le cas où il ne peut y avoir que de l'ajout
			// car c'est une création d'abonné
			
			// Gestion des enregistrements dans le select multiple
			List<Saison> selectedSaisons = new ArrayList<Saison>();
			params["lesSaisonsSelection"].each { idAsString ->
				selectedSaisons.add(Saison.get(Integer.parseInt(idAsString)))}

			// On ajoute les saisons à ajouter
			selectedSaisons.each { saison ->
				Enregistrement e = new Enregistrement(abonne: abonneInstance,
				saison : saison)
				e.save(flush: true)
				abonneInstance.addToEnregistrements(e)
			}
		}

		flash.message = message(code: 'abonne.created.message', args: [
				abonneInstance.prenom + " " + abonneInstance.nom
			])
		redirect(action: "show", id: abonneInstance.id)
	}

	def show(Long id) {
		def abonneInstance = Abonne.get(id)
		if (!abonneInstance) {
			flash.message = message(code: 'abonne.not.found.message')
			redirect(action: "list")
			return
		}
		
		//Tri de la liste des saisons par dates
		List<Enregistrement> listeEnreg = abonneInstance.enregistrements?.toList()
		listeEnreg?.sort{
			it.saison.dateDebut
		}
		
		List<Enregistrement> listeEnregTroisDernier = []
		if (listeEnreg?.size() > 3) {
			listeEnregTroisDernier = listeEnreg.subList(listeEnreg.size() - 3, listeEnreg.size())
		} else {
			listeEnregTroisDernier = listeEnreg
		}

		[abonneInstance: abonneInstance, listeEnregistrement : listeEnregTroisDernier]
	}

	def edit(Long id) {
		def abonneInstance = Abonne.get(id)
		if (!abonneInstance) {
			flash.message = message(code: 'abonne.not.found.message')
			redirect(action: "list")
			return
		}
		
		List<Saison> listeDesSaisons = Saison.list()
		[abonneInstance: abonneInstance, listeDesSaisons:listeDesSaisons]
	}

	def update(Long id, Long version) {
		def abonneInstance = Abonne.get(id)
		if (!abonneInstance) {
			flash.message = message(code: 'abonne.not.found.message')
			redirect(action: "list")
			return
		}

		if (version != null) {
			if (abonneInstance.version > version) {
				abonneInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[
							message(code: 'abonne.label', default: 'Abonne')] as Object[],
						"Another user has updated this Abonne while you were editing")
				List<Saison> listeDesSaisons = Saison.list()
				render(view: "edit", model: [abonneInstance: abonneInstance, listeDesSaisons:listeDesSaisons])
				return
			}
		}
		
		// Si on doit gérer la liste des enregistrements (admin only)
		if (params.containsKey("lesSaisonsSelection")) {
			// Gestion des enregistrements dans le select multiple
			List<Saison> selectedSaisons = new ArrayList<Saison>();
			params["lesSaisonsSelection"].each { idAsString ->
				selectedSaisons.add(Saison.get(Integer.parseInt(idAsString)))}

			// création d'une liste temporaire
			List<Enregistrement> tmpEnregAbonne = []
			// agrégation de cette liste avec les enregistrements de l'abonné
			abonneInstance.enregistrements.each { tmpEnregAbonne << it }

			// Pour chaque enregistrement de l'abonné
			tmpEnregAbonne.each{ enreg ->
				if (!selectedSaisons.contains(enreg.saison)) {
					// La saison n'a pas été sélectionné pas l'utilisateur
					// on la supprime l'enregistrement correspondant
					abonneInstance.removeFromEnregistrements(enreg)
				} else {
					// sinon on supprime cette saison de la liste des saisons
					// sélectionnées car celle-ci est déjà présente
					// dans un enregistrement de l'abonné
					selectedSaisons.remove(enreg.saison)
				}
			}

			// On ajoute les saisons à ajouter
			selectedSaisons.each { saison ->
				Enregistrement e = new Enregistrement(abonne: abonneInstance,
				saison : saison)
				e.save(flush: true)
				abonneInstance.addToEnregistrements(e)
			}
		}
		
		abonneInstance.properties = params

		if (!abonneInstance.save(flush: true)) {
			List<Saison> listeDesSaisons = Saison.list()
			render(view: "edit", model: [abonneInstance: abonneInstance, listeDesSaisons:listeDesSaisons])
			return
		}

		flash.message = message(code: 'abonne.updated.message')
		redirect(action: "show", id: abonneInstance.id)
	}

	def delete(Long id) {
		def abonneInstance = Abonne.get(id)
		if (!abonneInstance) {
			flash.message = message(code: 'abonne.not.found.message')
			redirect(action: "list")
			return
		}

		try {
			abonneInstance.delete(flush: true)
			flash.message = message(code: 'abonne.deleted.message', args: [
				abonneInstance.prenom + " " + abonneInstance.nom
			])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'abonne.not.deleted.message', args: [
				abonneInstance.prenom + " " + abonneInstance.nom
			])
			redirect(action: "show", id: id)
		}
	}

	def ajouterAvatarAbonne = {
		def abonneInstance = Abonne.get(params.idAbonne)
		if (!abonneInstance) {
			flash.message = message(code: 'abonne.not.found.message')
			redirect(action: "list")
			return
		}

		/** File Upload **/
		def file = request.getFile('avatar')
		if (file.size != 0) {
			if (file.size <= 524288) {

				def okcontents = [
					'image/png',
					'image/jpeg',
					'image/gif'
				]
				if (! okcontents.contains(file.getContentType())) {
					flash.message = "La photo doit être de type : PNG, JPEG ou GIF"
					render(view:'show', model:[abonneInstance: abonneInstance])
					return;
				}
				abonneInstance.avatar = file.getBytes()
				abonneInstance.mimeType = file.getContentType()				
			} else {
				// on ne fait rien, trop gros
				flash.message = "Image trop volumineuse (maxi : 512Ko) !"
				render(view:'show', model:[abonneInstance: abonneInstance])
				return
			}
		} else {
			// on ne fait rien
			flash.message = "Vous n'avez pas choisi de photo !"
			render(view:'show', model:[abonneInstance: abonneInstance])
			return
		}
		/** File upload **/

		if (!abonneInstance.save(flush: true)) {
			render(view: "show", model: [abonneInstance: abonneInstance])
			return
		}

		flash.message = message(code: 'abonne.updated.message')
		redirect(action: "show", id: abonneInstance.id)
	}

	def getAvatarAbonne = {
		def abonneInstance = Abonne.get(params.id)
		if (!abonneInstance || !abonneInstance.avatar || !abonneInstance.mimeType) {
			response.sendError(404)
			return;
		}
		response.setContentType(abonneInstance.mimeType)
		response.setContentLength(abonneInstance.avatar.size())
		OutputStream out = response.getOutputStream();
		out.write(abonneInstance.avatar);
		out.close();
	}

	def showAbonneCMPerime() {
		params.max = Math.min(params.max ? params.int('max') : 5,100)
		// récupération de la liste en session
		List<Abonne> listeAbonneCMPerime = []
		listeAbonneCMPerime = (List<Abonne>) session?.listeAbonneCMPerime
		List<Abonne> listePaginee = []
		if (!listeAbonneCMPerime?.isEmpty())	{
			if (params.sort) {
				if (params?.order == "asc") {
					listeAbonneCMPerime.sort{it.(params.sort)}
				} else if (params?.order == "desc") {
					listeAbonneCMPerime.sort{a,b -> b.(params.sort) <=> a.(params.sort)}
				}
			} else {
				listeAbonneCMPerime.sort{it.nom}
			}			
			use(PaginateableList) {
				listePaginee = listeAbonneCMPerime.paginate(5,(params.offset ?: 0))
			}
		} 
		
		listePaginee.each {
			Abonne abonne -> abonne.nbJourPerimeCM = abonneService.getNbJourPeremptionCM(abonne.getDateCertificat())
		}

		[listePagineAbonneCMPerime : listePaginee, abonneInstanceTotal:listeAbonneCMPerime.size(), listeComplete : listeAbonneCMPerime]
	}
	
	def showAbonneCMPerimeMois() {
		params.max = Math.min(params.max ? params.int('max') : 5,100)
		// récupération de la liste en session
		List<Abonne> listeAbonneCMPerime = []
		listeAbonneCMPerime = (List<Abonne>) session?.listeAbonneCMPerimeDansLeMois
		List<Abonne> listePaginee = []
		if (!listeAbonneCMPerime.isEmpty())	{
			if (params.sort) {
				if (params?.order == "asc") {
					listeAbonneCMPerime.sort{it.(params.sort)}
				} else if (params?.order == "desc") {
					listeAbonneCMPerime.sort{a,b -> b.(params.sort) <=> a.(params.sort)}
				}
			} else {
				listeAbonneCMPerime.sort{it.nom}
			}
			use(PaginateableList) {
				listePaginee = listeAbonneCMPerime.paginate(5,(params.offset ?: 0))
			}
		}
		
		listePaginee.each {
			Abonne abonne -> abonne.nbJourPerimeCM = abonneService.getNbJourAvantPeremptionCM(abonne.getDateCertificat())
		}

		[listePagineAbonneCMPerime : listePaginee, abonneInstanceTotal:listeAbonneCMPerime.size(), listeComplete : listeAbonneCMPerime]
	}
	
	def editMotDePasse(Long id) {
		def abonneInstance = Abonne.get(id)
		if (!abonneInstance) {
			flash.message = message(code: 'abonne.not.found.message')
			redirect(action: "list")
			return
		}

		[abonneInstance: abonneInstance]
	}
	
	def updatePassword(Long id, Long version) {
		def abonneInstance = Abonne.get(id)
		if (!abonneInstance) {
			flash.message = message(code: 'abonne.not.found.message')
			redirect(action: "list")
			return
		}

		if (version != null) {
			if (abonneInstance.version > version) {
				abonneInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[
							message(code: 'abonne.label', default: 'Abonne')] as Object[],
						"Another user has updated this Abonne while you were editing")
				render(view: "edit", model: [abonneInstance: abonneInstance])
				return
			}
		}
		
		
		if (params.password != params.passwordConfirmation) {
			flash.message = "Les mots de passe ne correspondent pas!"
			render(view: 'editMotDePasse', model: [abonneInstance: abonneInstance])
			return
		}
		
		// encodage en MD5
		params.password = params.password.encodeAsMD5()
		
		abonneInstance.properties = params

		if (!abonneInstance.save(flush: true)) {
			render(view: "editMotDePasse", model: [abonneInstance: abonneInstance])
			return
		}

		flash.message = message(code: 'abonne.updated.password.message')
		redirect(action: "show", id: abonneInstance.id)
	}
	
	def search = {
		if (!params.q.isEmpty()){

			// Par défaut, on tri par numéro de licence
//			if (!params?.sort) {
//				params.sort="nom"
//			}
			
			request.messageRequete = "Résulats pour : ${params.q}"
			def resultsMap = [:]
			params.escape="true"
			try {
				resultsMap = Abonne.search(params.q, params)
				generationExport(params, resultsMap.results)

				render(view:'list',
				model:[
					abonneInstanceList:resultsMap.results,
					abonneInstanceTotal:Abonne.countHits(params.q),
					valeurRecherche:params.q,
					filterParams : [:]
				])
			} catch (SearchEngineQueryParseException) {
				flash.message = "Veuillez saisir un critère de recherche adéquat"
				redirect(controller:"admin", action:"index")
				return [parseException: true]
			}
		} else {
			flash.message = "Veuillez saisir un critère de recherche"
			// redirection
			redirect(controller:"admin", action:"index")
		}
	}
	
	def calendrier() {	
	}
	
	/**
	 * TODO supprimer
	 * @deprecated
	 * @return
	 */
	def ajouterUnMois() {
		def abonneInstance = Abonne.get(params.id)
		if (!abonneInstance) {
			flash.message = message(code: 'abonne.not.found.message')
			redirect(action: "list")
			return
		}
		abonneService.ajouterUnMois(abonneInstance)
		
		if (!abonneInstance.save(validate : false)) {			
			redirect(action: "show", id: abonneInstance.id)
			flash.message = "erreur dans augmentation mois"
			return
		}

		flash.message = message(code: 'abonne.updated.message')
		redirect(action: "show", id: abonneInstance.id)
		
	}
	
	/**
	 * TODO supprimer
	 * @deprecated
	 * @return
	 */
	def enleverUnMois() {
		def abonneInstance = Abonne.get(params.id)
		if (!abonneInstance) {
			flash.message = message(code: 'abonne.not.found.message')
			redirect(action: "list")
			return
		}
		abonneService.enleverUnMois(abonneInstance)
		
		if (!abonneInstance.save(validate : false)) {
			redirect(action: "show", id: abonneInstance.id)
			flash.message = "erreur dans augmentation mois"
			return
		}

		flash.message = message(code: 'abonne.updated.message')
		redirect(action: "show", id: abonneInstance.id)
		
	}
	
	
	private void generationExport(params, List<Abonne> liste ) {
		if(params?.format && params.format != "html"){
			response.contentType = grailsApplication.config.grails.mime.types[params.format]
			response.setHeader("Content-disposition", "attachment; filename=abonnes.${params.extension}")

			List fields = ["numeroLicence", "nom", "prenom", "dateNaissance", "sexe"]
			Map labels = ["numeroLicence": "N° Licence", "nom": "Nom", "prenom": "Prénom",
				"dateNaissance": "Date de naissance", "sexe": "Sexe"]
			
			// Formatter closure
			def upperCase = { domain, value ->
				return value.toUpperCase()
			}
			
			def formatDate = { domain, value ->
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
				return sdf.format(value)				
			}

			Map formatters = [nom: upperCase, dateNaissance: formatDate]
			Map parameters = [title: "Liste des abonnés", "column.widths": [0.225, 0.225, 0.225, 0.225, 0.10]]

			exportService.export(params.format, response.outputStream, liste, fields, labels, formatters, parameters)
		}
	}
	
	def searchAjaxAutoComplete = {
		def abonne = Abonne.findAllByNomIlikeOrPrenomIlike("%${params.query}%", "%${params.query}%")
		//Create XML response
		render(contentType: "text/xml") {
			results() {
				abonne.each { abonneTrouve ->
					result(){						
						name(abonneTrouve.prenom + " " + abonneTrouve.nom)

					}
				}
			}
		}
	}
	
	def filter = {
		// récupération de la liste filtré complète
		def listeFiltreeSansMax = filterPaneService.filter( params, Abonne )
		if(!params.max) params.max = 10
		// récupération de la liste filtré mais avec un max de 10 abonnés
		def listeFiltree = filterPaneService.filter( params, Abonne )
		generationExport(params, listeFiltreeSansMax)
		render( view:'list',
			model:[ abonneInstanceList: listeFiltree,
			abonneInstanceTotal: filterPaneService.count( params, Abonne ),
			filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
			params:params ] )
	}	
	
}
