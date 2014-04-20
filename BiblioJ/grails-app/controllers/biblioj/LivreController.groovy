package biblioj

import java.util.regex.Matcher
import org.springframework.dao.DataIntegrityViolationException

class LivreController {

	GestionPanierService gestionPanierService
	LivreRechercheService livreRechercheService


	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
		redirect(action: "list", params: params)
	}

	def indexRecherche(){
		redirect(controller:"Livre" ,action: "recherche")
	}
	
	def recherche(){
	}

	def list(Integer max) {
		println params
		params.max = Math.min(max ?: 10, 100)
		[livreInstanceList: Livre.list(params), livreInstanceTotal: Livre.count()]
	}

	def create() {
		[livreInstance: new Livre(params)]
	}

	def emprunter(){
		gestionPanierService.emprunter(params, session)
		//redirect(controller: params.controller, action: params.currentAction)

		if(params.currentAction.equals("index"))
			redirect(uri:'/')
		else if(params.currentAction.equals("list")){
			redirect(action:"list", params:[offset:params.offset])
		}else if(params.currentAction.equals("listRecherche")){
			def titreVar = params.titreLivre
			if(titreVar == null)
				titreVar =""
			def auteurVar = params.auteurLivre
			if(auteurVar == null)
				auteurVar = ""
			def typeVar = params.typeDocumentId
			if(typeVar == null)
				typeVar = ""
			println typeVar

			redirect(action:"listRecherche", params:[offset:params.offset, titre:titreVar, auteur:auteurVar, typeDocumentId:typeVar])
		}else
			redirect(controller: params.controller, action: params.currentAction, id:params.id)


	}


	def removePanier(){
		gestionPanierService.removePanier(params, session)

		if(params.currentAction.equals("index"))
			redirect(uri:'/')
		else if(params.currentAction.equals("list")){
			redirect(action:"list", params:[offset:params.offset])
		}else if(params.currentAction.equals("listRecherche")){
			def titreVar = params.titreLivre
			if(titreVar == null)
				titreVar =""
			def auteurVar = params.auteurLivre
			if(auteurVar == null)
				auteurVar = ""
			def typeVar = params.typeDocumentId
			if(typeVar == null)
				typeVar = ""
			println typeVar

			redirect(action:"listRecherche", params:[offset:params.offset, titre:titreVar, auteur:auteurVar, typeDocumentId:typeVar])
		}else if(params.currentAction.equals("validerPanier"))
			redirect(controller: params.currentController, action: params.currentAction,  params:[offset:params.offset])
		else
			redirect(controller: params.controller, action: params.currentAction, id:params.id)
	}

	def listRecherche(Integer max){

		int myOffset = 4
		def map = []
		def mapOffset = []
		def livreInstance = Livre.list()

		map = livreRechercheService.rechercheLivre(params, livreInstance)
		try{
			mapOffset = livreRechercheService.mapByOffset(map, params, myOffset)
			params.max = Math.min(max ?: 5, 5)
			[livreInstanceList: mapOffset,  livreInstanceTotal: map.size()]
		}catch(e){
			flash.message = "Aucun résultat trouvé"
			[livreInstanceList: map,  livreInstanceTotal: 0]
		}

	}

	def save() {
		def livreInstance = new Livre(params)
		if (!livreInstance.save(flush: true)) {
			render(view: "create", model: [livreInstance: livreInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'livre.label', default: 'Livre'), livreInstance.id])
		redirect(action: "show", id: livreInstance.id)
	}

	def show(Long id) {
		def livreInstance = Livre.get(id)
		if (!livreInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'livre.label', default: 'Livre'), id])
			redirect(action: "list")
			return
		}

		[livreInstance: livreInstance]
	}

	def edit(Long id) {
		def livreInstance = Livre.get(id)
		if (!livreInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'livre.label', default: 'Livre'), id])
			redirect(action: "list")
			return
		}

		[livreInstance: livreInstance]
	}

	def update(Long id, Long version) {
		def livreInstance = Livre.get(id)
		if (!livreInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'livre.label', default: 'Livre'), id])
			redirect(action: "list")
			return
		}

		if (version != null) {
			if (livreInstance.version > version) {
				livreInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[message(code: 'livre.label', default: 'Livre')] as Object[],
						"Another user has updated this Livre while you were editing")
				render(view: "edit", model: [livreInstance: livreInstance])
				return
			}
		}

		livreInstance.properties = params

		if (!livreInstance.save(flush: true)) {
			render(view: "edit", model: [livreInstance: livreInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'livre.label', default: 'Livre'), livreInstance.id])
		redirect(action: "show", id: livreInstance.id)
	}

	def delete(Long id) {
		def livreInstance = Livre.get(id)
		if (!livreInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'livre.label', default: 'Livre'), id])
			redirect(action: "list")
			return
		}

		try {
			livreInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'livre.label', default: 'Livre'), id])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'livre.label', default: 'Livre'), id])
			redirect(action: "show", id: id)
		}
	}
}
