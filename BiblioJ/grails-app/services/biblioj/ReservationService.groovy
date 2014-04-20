package biblioj

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
		def resa = new Reservation(code: code, reservation: dateResa, membre: membre).save(flush : true)
		varSession["panier"].each { livre ->
			Livre.findById(livre.id).nombreExemplairesDisponibles--
			new ReservationLivre(reservation: resa, livre: livre).save(flush: true)
		}
		varSession["panier"] = []
	}
	
	def supprimerReservation(def code) {
		def resa = Reservation.findByCode(code);
		resa.reservationLivres.each { resaLivre ->
			resaLivre.delete()
		}
		resa.delete()
	}
	
	def rendreLivre(def codeResa, def titreLivre) {
		def resa = Reservation.findByCode(codeResa);
		resa.reservationLivres { resaLivre ->
			if (resaLivre.livre.titre.equals(titreLivre)) {
				resaLivre.delete()
			}
		}
		if (resa.reservationLivres.list().size == 0)
			resa.delete()
	}
}
