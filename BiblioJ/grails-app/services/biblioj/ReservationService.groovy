package biblioj

class ReservationService {

    def verrifierDiponnibilite() {
		def listLivreNonDisponible = []
		session.panier.each { livre ->
			if (livre.getNombreExemplairesDisponibles() == 0) {
				listLivreNonDisponible.add(livre.getTitre())
			}
		}
		return listLivreNonDisponible
	}
	
	def validerReservation() {
		/* suppression des livres non dispo du panier */
		listLivreNonDisponible = verrifierDiponnibilite()
		listLivreNonDisponible.each { titreLivre ->
			session.panier.remove(titreLivre)
		}
		/* validation du panier */
		def dateResa = new Date("yyyy-MM-dd hh:mm:ss")
		def membre = Membre().findByLogin(session.user)
		def code = dateResa.toString() + membre.login
		def resa =  new Reservation(code: code, reservation: dateResa, membre: membre).save()
		session.panier.each { titreLivre ->
			def livre = Livre().findByTitre(titreLivre)
			livre.nombreExemplairesDisponibles--
			new ReservationLivre(reservation: resa, livre: livre).save()
		}
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
		resa.reservationLivres { resaLivre
			if (resaLivre.livre.titre.equals(titreLivre)) {
				resaLivre.delete()
			}
		}
		if (resa.reservationLivres.list().size == 0)
			resa.delete()
	}
}
