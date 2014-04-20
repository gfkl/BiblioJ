package biblioj

import org.springframework.dao.DataIntegrityViolationException

class ReservationController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
		redirect(action: "list", params: params)
	}

	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		[reservationInstanceList: Reservation.list(params), reservationInstanceTotal: Reservation.count()]
	}

	def create() {
		[reservationInstance: new Reservation(params)]
	}

	def save() {
		def reservationInstance = new Reservation(params)
		if (!reservationInstance.save(flush: true)) {
			render(view: "create", model: [reservationInstance: reservationInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'reservation.label', default: 'Reservation'), reservationInstance.id])
		redirect(action: "show", id: reservationInstance.id)
	}

	def show(Long id) {
		def reservationInstance = Reservation.get(id)
		if (!reservationInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
			redirect(action: "list")
			return
		}

		[reservationInstance: reservationInstance]
	}

	def edit(Long id) {
		def reservationInstance = Reservation.get(id)
		if (!reservationInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
			redirect(action: "list")
			return
		}

		[reservationInstance: reservationInstance]
	}

	def update(Long id, Long version) {
		def reservationInstance = Reservation.get(id)
		if (!reservationInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
			redirect(action: "list")
			return
		}

		if (version != null) {
			if (reservationInstance.version > version) {
				reservationInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[message(code: 'reservation.label', default: 'Reservation')] as Object[],
						"Another user has updated this Reservation while you were editing")
				render(view: "edit", model: [reservationInstance: reservationInstance])
				return
			}
		}

		reservationInstance.properties = params

		if (!reservationInstance.save(flush: true)) {
			render(view: "edit", model: [reservationInstance: reservationInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'reservation.label', default: 'Reservation'), reservationInstance.id])
		redirect(action: "show", id: reservationInstance.id)
	}

	def delete(Long id) {
		def reservationInstance = Reservation.get(id)
		if (!reservationInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
			redirect(action: "list")
			return
		}

		try {
			reservationInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
			redirect(action: "show", id: id)
		}
	}

	def validerPanier(Integer max) {
		def myOffset = 9
		params.max = Math.min(max ?: 10,10)
		def size = session["panier"].size()
		def listRet = []
		def sessionPanier = session["panier"]

		try{
			if(params?.offset){
				def place = params.offset.toInteger()
				if(place+myOffset > size-1)
					for(i in place..size-1)
						listRet.add(sessionPanier.get(i))
				else
					for(i in place..place+myOffset)
						listRet.add(sessionPanier.get(i))
			}else{
				if(myOffset > size-1)
					for(i in 0..size-1)
						listRet.add(sessionPanier.get(i))
				else
					for(i in 0..myOffset)
						listRet.add(sessionPanier.get(i))
			}

			[livreInstanceList: listRet, livreInstanceTotal: size]
		}catch(e){
			params.offset = 0
			[livreInstanceList: sessionPanier, livreInstanceTotal: size]
		}
	}
}
