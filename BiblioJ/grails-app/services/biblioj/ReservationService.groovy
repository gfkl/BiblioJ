package biblioj

import groovy.time.TimeCategory

class ReservationService {

    def verrifierDiponnibilite(def varSession) {
		def listLivreNonDisponible = []
		varSession["panier"].each { livre ->
			if (Livre.findById(livre.id).nombreExemplairesDisponibles == 0) {
				listLivreNonDisponible.add(livre.getTitre())
			}
		}
		return listLivreNonDisponible
	}
	
	def validerReservation(def varSession) {
		/* suppression des livres non dispo du panier */
		def listLivreNonDisponible = verrifierDiponnibilite(varSession)
		listLivreNonDisponible.each { titreLivre ->
			println "-> " + titreLivre
			varSession["panier"].remove(Livre.findByTitre(titreLivre))
		}
		/* validation du panier */
		def dateResa = new Date()
		def membre = Membre.findByLogin(varSession["user"])
		def code = dateResa.format("yyyy-MM-dd HH:mm:ss") + membre.login
		def resa = new Reservation(code: code, reservation: dateResa, membre: membre, receptionnee:false).save(flush : true)
		varSession["panier"].each { livre ->
			Livre.findById(livre.id).nombreExemplairesDisponibles--
			new ReservationLivre(reservation: resa, livre: livre).save(flush: true)
		}
		varSession["panier"] = []
		resa
	}
	
	def supprimerReservation(def code) {
		def resa = Reservation.findByCode(code)
		def reservationLivres = ReservationLivre.findAllByReservation(resa)
		reservationLivres.each { resaLivre ->
			rendreLivre(code, resaLivre.livre.id)
		}
	}
	
	def rendreLivre(def codeResa, def idLivre) {
		def resa = Reservation.findByCode(codeResa)
		def livre = Livre.findById(idLivre)
		def resaLivre = ReservationLivre.findByReservationAndLivre(resa, livre)
		resa.reservationLivres.remove(resaLivre)
		livre.reservationLivres.remove(resaLivre)
		livre.nombreExemplairesDisponibles++
		resaLivre.delete()
		if (resa.reservationLivres.size() == 0)
			resa.delete()
	}
	
	def expirationReservation() {
		def current = new Date()
		Reservation.list().each { resa ->
			if (resa.receptionnee == false) {
				use (TimeCategory) {
					Date temp = resa.reservation
					temp = temp + 24.hour
					if (temp < current) {
						supprimerReservation(resa.code)
					}
				}
			}
		}
	}
	
	def reserverCommande(def code){
		def reserv = Reservation.findByCode(params.code)
		reserv.receptionnee = true
		reserv.save()
	}
}
