package biblioj

class ReservationService {

    def verrifierDiponnibilit�() {
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
		listLivreNonDisponible = verrifierDiponnibilit�()
		listLivreNonDisponible.each { titreLivre ->
			session.panier.remove(titreLivre)
		}
		/* validation du panier */
		def dateResa = new Date("yyyy-MM-dd hh:mm:ss")
		def membre = Membre().findByLogin(session.user)
		def code = dateResa.toString() + membre.login
		def resa =  new Reservation(code: code, reservation: dateResa, membre: membre).save()
		session.panier.each { titreLivre ->
			new ReservationLivre(reservation: resa, livre: Livre().findByTitre(livre)).save()
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
