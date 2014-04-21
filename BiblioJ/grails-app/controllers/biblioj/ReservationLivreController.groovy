package biblioj

import org.springframework.dao.DataIntegrityViolationException
import groovy.time.TimeCategory


class ReservationLivreController {

	ReservationService reservationService
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
		redirect(action: "list", params: params)
	}

	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		[reservationLivreInstanceList: ReservationLivre.list(params), reservationLivreInstanceTotal: ReservationLivre.count()]
	}

	def create() {
		[reservationLivreInstance: new ReservationLivre(params)]
	}

	def save() {
		def reservationLivreInstance = new ReservationLivre(params)
		if (!reservationLivreInstance.save(flush: true)) {
			render(view: "create", model: [reservationLivreInstance: reservationLivreInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'reservationLivre.label', default: 'ReservationLivre'), reservationLivreInstance.id])
		redirect(action: "show", id: reservationLivreInstance.id)
	}

	def validerPanier(){
	}


	def validerReservation(){
		def list = reservationService.verrifierDiponnibilite(session)
		if(list.size() == session["panier"].size()){
			flash.message = "La commande ne peut pas être validé car tous les livres sont emprunter"
			redirect(uri:'/')
		}else if(session["panier"].size() == 0){
			flash.message = "La commande ne peut pas être validé car votre pannier est vide"
			redirect(uri:'/')
		}else if(list && (!(params.test.equals("second")))){
			def strFlashMsg = "Les livres suivant ne sont pas disponible: "
			list.each {curLivre ->
				strFlashMsg += curLivre
			}
			flash.message = strFlashMsg
			redirect(controller:"Reservation",action:"validerPanier", params:[test:"second"])

		}else{
			def reserv = reservationService.validerReservation(session)
			Date temp = reserv.reservation
			use (TimeCategory) {
				temp = temp + 24.hour
			}

			flash.message = "Reservation validé, Code Reservation "+reserv.code+", date limite :"+temp
			redirect(uri:'/')
		}
	}

	def show(Long id) {
		def reservationLivreInstance = ReservationLivre.get(id)
		if (!reservationLivreInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservationLivre.label', default: 'ReservationLivre'), id])
			redirect(action: "list")
			return
		}

		[reservationLivreInstance: reservationLivreInstance]
	}

	def edit(Long id) {
		def reservationLivreInstance = ReservationLivre.get(id)
		if (!reservationLivreInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservationLivre.label', default: 'ReservationLivre'), id])
			redirect(action: "list")
			return
		}

		[reservationLivreInstance: reservationLivreInstance]
	}

	def update(Long id, Long version) {
		def reservationLivreInstance = ReservationLivre.get(id)
		if (!reservationLivreInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservationLivre.label', default: 'ReservationLivre'), id])
			redirect(action: "list")
			return
		}

		if (version != null) {
			if (reservationLivreInstance.version > version) {
				reservationLivreInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[message(code: 'reservationLivre.label', default: 'ReservationLivre')] as Object[],
						"Another user has updated this ReservationLivre while you were editing")
				render(view: "edit", model: [reservationLivreInstance: reservationLivreInstance])
				return
			}
		}

		reservationLivreInstance.properties = params

		if (!reservationLivreInstance.save(flush: true)) {
			render(view: "edit", model: [reservationLivreInstance: reservationLivreInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'reservationLivre.label', default: 'ReservationLivre'), reservationLivreInstance.id])
		redirect(action: "show", id: reservationLivreInstance.id)
	}

	def delete(Long id) {
		def reservationLivreInstance = ReservationLivre.get(id)
		if (!reservationLivreInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservationLivre.label', default: 'ReservationLivre'), id])
			redirect(action: "list")
			return
		}

		try {
			reservationLivreInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'reservationLivre.label', default: 'ReservationLivre'), id])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'reservationLivre.label', default: 'ReservationLivre'), id])
			redirect(action: "show", id: id)
		}
	}
}
