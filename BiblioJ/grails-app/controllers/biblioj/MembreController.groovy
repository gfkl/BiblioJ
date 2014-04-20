package biblioj

import org.springframework.dao.DataIntegrityViolationException

class MembreController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
		redirect(action: "list", params: params)
	}

	def deconnexion(){
		session.invalidate()
		redirect(uri:'/')
	}
	
	def indexInscription(){
		redirect(controller:"Membre" ,action: "inscription")
	}

	def inscription(){

	}

	def inscriptionForm(){
		if(Membre.findByLogin(params.login)){
			flash.message = "Le login "+params.login+" est déjà utilisé"
			redirect(controller:"Membre" ,action: "inscription")
		}else{
			(new Membre(login:params.login,mdp:params.mdp,status:"client")).save()
			flash.message = "Compte créer : login : "+params.login+" - mdp : "+params.mdp
			redirect(uri:'/')
		}
	}

	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		[membreInstanceList: Membre.list(params), membreInstanceTotal: Membre.count()]
	}

	def connection(){
		if(Membre.findByLoginAndMdp(params.login, params.mdp)){
			session["user"] = params.login
			session["status"] = params.status
			redirect(uri:'/')
		}else{
			flash.message = "Erreur lors de la connection"
			redirect(uri:'/')
		}
	}

	def create() {
		[membreInstance: new Membre(params)]
	}

	def save() {
		def membreInstance = new Membre(params)
		if (!membreInstance.save(flush: true)) {
			render(view: "create", model: [membreInstance: membreInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'membre.label', default: 'Membre'), membreInstance.id])
		redirect(action: "show", id: membreInstance.id)
	}

	def show(Long id) {
		def membreInstance = Membre.get(id)
		if (!membreInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'membre.label', default: 'Membre'), id])
			redirect(action: "list")
			return
		}

		[membreInstance: membreInstance]
	}

	def edit(Long id) {
		def membreInstance = Membre.get(id)
		if (!membreInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'membre.label', default: 'Membre'), id])
			redirect(action: "list")
			return
		}

		[membreInstance: membreInstance]
	}

	def update(Long id, Long version) {
		def membreInstance = Membre.get(id)
		if (!membreInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'membre.label', default: 'Membre'), id])
			redirect(action: "list")
			return
		}

		if (version != null) {
			if (membreInstance.version > version) {
				membreInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[message(code: 'membre.label', default: 'Membre')] as Object[],
						"Another user has updated this Membre while you were editing")
				render(view: "edit", model: [membreInstance: membreInstance])
				return
			}
		}

		membreInstance.properties = params

		if (!membreInstance.save(flush: true)) {
			render(view: "edit", model: [membreInstance: membreInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'membre.label', default: 'Membre'), membreInstance.id])
		redirect(action: "show", id: membreInstance.id)
	}

	def delete(Long id) {
		def membreInstance = Membre.get(id)
		if (!membreInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'membre.label', default: 'Membre'), id])
			redirect(action: "list")
			return
		}

		try {
			membreInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'membre.label', default: 'Membre'), id])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'membre.label', default: 'Membre'), id])
			redirect(action: "show", id: id)
		}
	}
}
