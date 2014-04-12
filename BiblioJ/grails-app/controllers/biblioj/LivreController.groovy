package biblioj

import java.util.regex.Matcher
import org.springframework.dao.DataIntegrityViolationException

class LivreController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
		redirect(action: "list", params: params)
	}

	def indexRecherche(){
		redirect(controller:"Livre" ,action: "recherche")
	}

	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		[livreInstanceList: Livre.list(params), livreInstanceTotal: Livre.count()]
	}

	def create() {
		[livreInstance: new Livre(params)]
	}

	def recherche(){
		//[recherche:Rechercher, typeDocument.id:null, typeDocument:[id:null],
		//auteur:cxw, titre:dqs, action:recherche, controller:livre]
		def map = []
		def livreInstance = Livre.list()
		def findLivre = true
		def findType = true
		def findAuteur = true
		def typeLivre = null
		def size = 0

		try{
			if(params.typeDocument != null)
				typeLivre = TypeDocument.get(params.typeDocument.get('id'))
		}catch(e) {
			typeLivre = null
		}
		
		livreInstance.each { curLivre ->
			if(!params.titre.equals("")){
				if(!(curLivre.titre ==~ ".*"+params.titre+".*"))
					findLivre = false
			}
			if(typeLivre != null){
				if(!curLivre.typeDocument.equals(typeLivre))
					findType = false
			}
			if(!params.auteur.equals("") && findLivre == false){

			}
			if(findLivre == true && findType == true && findAuteur == true){
				map.add('livre':curLivre)
				println curLivre
				println curLivre.auteurLivres
				size++
			}
			findLivre = true
			findType = true
			findAuteur = true
		}

		//redirect(controller:"Livre" ,action: "recherche")
		//[livreInstanceList: map,  stationTransportInstanceTotal: size]

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
